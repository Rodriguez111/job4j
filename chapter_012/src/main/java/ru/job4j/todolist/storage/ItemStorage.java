package ru.job4j.todolist.storage;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.todolist.models.Item;
import java.util.List;
import java.util.function.Function;

public class ItemStorage implements Storage {
    private static final Logger LOG = LoggerFactory.getLogger(ItemStorage.class);

    private static final Storage INSTANCE = new ItemStorage();

    private ItemStorage() {
        try {
            createTable();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Storage getINSTANCE() {
        return INSTANCE;
    }

    private Session getSession() {
        LOG.info("Enter method");
        SessionFactory factory = new Configuration()
                .configure() // configures settings from hibernate.cfg.xml
                .buildSessionFactory();
        LOG.info("Exit method");
        return factory.openSession();
    }

    @Override
    public List<Item> getAllItems(boolean onlyUndone) {
        LOG.info("Enter method");
        LOG.info("Exit method");
        return onlyUndone ? transaction(session -> session.createQuery("from Item WHERE done=false").list())
                : transaction(session -> session.createQuery("from Item").list());
    }

    @Override
    public void addItem(Item item) {
        LOG.info("Enter method");
        transaction(session -> session.save(item));
        LOG.info("Exit method");
    }

    private void createTable() {
        String createTable = "CREATE table items"
                + "(id serial primary key,"
                + "description character varying(500) NOT NULL,"
                + "create_date character varying(19) NOT NULL,"
                + "done boolean  NOT NULL)";
        transaction(session -> session.createSQLQuery(createTable).executeUpdate());
    }

    private <T> T transaction(Function<Session, T> command) {
        final Session session = getSession();
        final Transaction transaction = session.beginTransaction();
        T result = null;
        try {
            result = command.apply(session);
            transaction.commit();

        } catch (Exception e) {
            transaction.rollback();
            LOG.error(e.getMessage());
            e.printStackTrace();
        } finally {
            session.close();
        }
        return result;
    }
}
