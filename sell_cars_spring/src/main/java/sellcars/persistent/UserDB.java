package sellcars.persistent;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import sellcars.models.User;

import java.util.List;
import java.util.function.Consumer;
@Component
public class UserDB implements UserStorage {
    private final static Logger LOG = LoggerFactory.getLogger(UserDB.class);


    private UserDB() {
    }



    @Override
    public String add(User user) {

        return SessionManager.handleQuery(session -> {
            String res = "OK";
            String login = user.getLogin();
            User existingUser = findUserByLogin(login);
            if (existingUser.getLogin() == null) {
                session.save(user);
            } else {
                res = "Пользователь с таким логином уже существует";
            }
            return res;
        }).orElse("Возникла неизвестная ошибка при добавлении пользователя");
    }

    @Override
    public void update(User user) {
        SessionManager.handleQuery(session -> {
            return session.merge(user);
        });
    }

    @Override
    public void delete(User user) {
        SessionManager.handleQuery((Consumer<Session>) session -> session.delete(user));
    }

    @Override
    public User findUserByLogin(String login) {
        LOG.info("Enter method");
        return SessionManager.handleQuery(session -> {
            Query query = session.createQuery("from User where login = :login");
            query.setParameter("login", login);
            LOG.info("Exit method");
            List<User> list = query.getResultList();
            User user = new User();
            if (list.size() != 0) {
                user = list.get(0);
            }
            return user;
        }).orElse(new User());

    }


    @Override
    public User findById(int id) {
        return SessionManager.handleQuery(session -> {
            Query query = session.createQuery("from User where id = :id");
            query.setParameter("id", id);
            return (User) query.getSingleResult();
        }).orElse(new User());
    }
}
