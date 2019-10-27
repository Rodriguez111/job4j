package sellcars.persistent;

import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sellcars.models.CarBrand;

import java.util.List;

public class GetModel<T> implements ModelGetter<T> {

    private final static Logger LOG = LoggerFactory.getLogger(GetModel.class);


    @Override
    public List<T> getAll(String entityName) {
        LOG.info("Enter method");
        return SessionManager.handleQuery(session -> {
            Query query = session.createQuery("from " + entityName + "");
            List<T> list = query.getResultList();
            LOG.info("Exit method");
            return list;
        }).get();
    }

    @Override
    public T getByName(String entityName, String fieldName, String fieldValue) {
        LOG.info("Enter method");
        return SessionManager.handleQuery(session -> {
            Query query = session.createQuery("from " + entityName + " WHERE " + fieldName + " = :" + fieldName);
            query.setParameter(fieldName, fieldValue);
            LOG.info("Exit method");
            return (T) query.getSingleResult();
        }).get();
    }

}
