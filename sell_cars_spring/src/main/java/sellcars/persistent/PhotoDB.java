package sellcars.persistent;

import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import sellcars.models.*;

import java.util.List;

@Component
public class PhotoDB implements PhotoStorage {
    private final static Logger LOG = LoggerFactory.getLogger(PhotoDB.class);


    private PhotoDB() {
    }


    @Override
    public String add(Photo photo) {
        LOG.info("Enter method");
        return SessionManager.handleQuery(session -> {
            String result;
            int id = (int) session.save(photo);
            result = String.valueOf(id);
            LOG.info("Exit method");
            return result;
        }).orElse("При добавлении объекта Photo произошла непредвиденная ошибка.");
    }

    @Override
    public String delete(Photo photo) {
        LOG.info("Enter method");
        return SessionManager.handleQuery(session -> {
            String result;
            session.remove(photo);
            result = "OK";
            LOG.info("Exit method");
            return result;
        }).orElse("При удалении объекта Photo произошла непредвиденная ошибка.");
    }

    @Override
    public Photo findPhotoByAdvertId(int id) {
        LOG.info("Enter method");
        return SessionManager.handleQuery(session -> {
            Query query = session.createQuery("from Photo where advert_id = :advert");
            query.setParameter("advert", id);
            LOG.info("Exit method");
            return (Photo) query.getSingleResult();
        }).orElse(new Photo());
    }
}
