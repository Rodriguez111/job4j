package sellcars.persistent;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sellcars.models.CarBody;
import sellcars.models.CarBrand;
import sellcars.models.Engine;
import sellcars.models.Transmission;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;


public class SessionManager {
    private final static Logger LOG = LoggerFactory.getLogger(SessionManager.class);

    static {
        initData();
    }


    public static <T> Optional<T> handleQuery(Function<Session, T> func) {
        SessionFactory factory = new Configuration()
                .configure()
                .buildSessionFactory();
        LOG.info("Enter method");
        final Session session = factory.openSession();
        final Transaction transaction = session.beginTransaction();
        Optional<T> result = Optional.empty();
        try {
            result = Optional.of(func.apply(session));
        } catch (Exception e) {
            transaction.rollback();
            session.close();
            e.printStackTrace();
            LOG.error(e.getMessage());
        } finally {
            transaction.commit();
            session.close();
            factory.close();
            LOG.info("Exit method");
        }
        return result;
    }

    public static void handleQuery(Consumer<Session> cons) {
        LOG.info("Enter method");
        SessionFactory factory = new Configuration()
                .configure()
                .buildSessionFactory();
        LOG.info("Enter method");
        final Session session = factory.openSession();
        final Transaction transaction = session.beginTransaction();
        try {
            cons.accept(session);
        } catch (Exception e) {
            transaction.rollback();
            session.close();
            e.printStackTrace();
            LOG.error(e.getMessage());
        } finally {
            transaction.commit();
            session.close();
            factory.close();
            LOG.info("Exit method");
        }
    }

    private static void initData() {
        int index = SessionManager.handleQuery(session -> {
            Query query = session.createQuery("select min(id) from CarBody");
            Integer res = (Integer) query.getSingleResult();
            if (res == null) {
                res = -1;
            }
            return res;
        }).orElse(-1);
        if (index == -1) {
            initCarBodyData();
            initTransmissionData();
            initBrandData();
            initEngineData();
        }
    }

    private static void initCarBodyData() {
        LOG.info("Init carBody data");
        List<String> values = List.of("Hatchback", "Sedan", "Crossover", "Coupe", "MPV", "Fastback", "SUV");
        values.forEach(each -> SessionManager.handleQuery(session -> {
            LOG.info("Exit carBody data");
            return session.merge(new CarBody(each));
        }));
    }

    private static void initTransmissionData() {
        List<String> values = List.of("Automatic", "Automated-Manual", "Manual",
                "Dual-Clutch", "Direct Shift Gearbox", "Tiptronic");
        values.forEach(each -> SessionManager.handleQuery(session -> {
            return session.merge(new Transmission(each));
        }));
    }

    private static void initBrandData() {
        List<String> values = List.of("Audi", "BMW", "Jeep",
                "Porsche", "Ford", "Ferrari");
        values.forEach(each -> SessionManager.handleQuery(session -> {
            return session.merge(new CarBrand(each));
        }));
    }

    private static void initEngineData() {
        List<String> values = List.of("VEE", "INLINE", "Boxer");
        values.forEach(each -> SessionManager.handleQuery(session -> {
            return session.merge(new Engine(each));
        }));
    }

    /*
    drop table cars cascade;
    drop table car_body cascade;
    drop table car_brand cascade;
    drop table engine cascade;
    drop table photos cascade;
    drop table users cascade;
    drop table transmission cascade;
    drop table adverts cascade;
    */

}
