package ru.job4j.todolist.storage;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.todolist.models.Item;

import java.util.List;


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
        Session session = getSession();
        List<Item> list =
                onlyUndone ? session.createQuery("from Item WHERE done=false").list() : session.createQuery("from Item").list();
        session.close();
        LOG.info("Exit method");
        return list;
    }

    @Override
    public void addItem(Item item) {
        LOG.info("Enter method");
        Session session = getSession();
        session.beginTransaction();
        session.save(item);
        session.getTransaction().commit();
        session.close();
        LOG.info("Exit method");
    }

    private void createTable() {
        String createTable = "CREATE table items" +
                "(id serial primary key," +
                "description character varying(500) NOT NULL," +
                "create_date character varying(19) NOT NULL," +
                "done boolean  NOT NULL)";
        Session session = getSession();
        session.beginTransaction();
        session.createSQLQuery(createTable).executeUpdate();
        session.getTransaction().commit();
        session.close();
    }
}
