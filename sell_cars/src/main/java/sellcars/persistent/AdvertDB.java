package sellcars.persistent;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sellcars.models.Advert;
import sellcars.models.User;

import java.util.List;
import java.util.function.Consumer;

public class AdvertDB implements AdvertStorage {
    private final static Logger LOG = LoggerFactory.getLogger(AdvertDB.class);
    private final static AdvertStorage INSTANCE = new AdvertDB();
    private final UserStorage userStorage = UserDB.getINSTANCE();

    private AdvertDB() {
    }

    public static AdvertStorage getINSTANCE() {
        return INSTANCE;
    }

    @Override
    public String add(Advert advert) {
        LOG.info("Enter method");
        return SessionManager.handleQuery(session -> {
            String result;
            int id = (int) session.save(advert);
            result = String.valueOf(id);
            LOG.info("Exit method");
            return result;
        }).orElse("При добавлении объявления произошла непредвиденная ошибка.");
    }

    @Override
    public void update(Advert advert) {
        SessionManager.handleQuery(session -> {
            return session.merge(advert);
        });
    }

    @Override
    public void delete(Advert advert) {
        SessionManager.handleQuery((Consumer<Session>) session -> session.delete(advert));
    }

    @Override
    public List<Advert> getAll() {
        LOG.info("Enter method");
        return SessionManager.handleQuery(session -> {
            Query query = session.createQuery("from Advert");
            LOG.info("Exit method");
            List<Advert> list = (List<Advert>) query.getResultList();
            list.stream().forEach(adv -> {
                int userId = adv.getUser().getId();
                User user = userStorage.findById(userId);
                user.setAdverts(null);
                adv.setUser(user);
            });
            return list;
        }).get();
    }

    @Override
    public Advert findById(int id) {
        return SessionManager.handleQuery(session -> {
            Query query = session.createQuery("from Advert where id = :id");
            query.setParameter("id", id);
            Advert advert = (Advert) query.getSingleResult();
            int userId = advert.getUser().getId();
            User user = userStorage.findById(userId);
            user.setAdverts(null);
            advert.setUser(user);
            return advert;
        }).orElse(new Advert());
    }

}
