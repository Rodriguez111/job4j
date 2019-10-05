package ru.job4j.todolist.storage;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.todolist.models.Item;

import java.util.List;


public class ItemStorage implements Storage {
    private static final Logger LOG = LoggerFactory.getLogger(ItemStorage.class);

    private static final Storage INSTANCE = new ItemStorage();

    private static final Session SESSION = new Configuration()
            .configure() // configures settings from hibernate.cfg.xml
            .buildSessionFactory().openSession();

    public static Storage getINSTANCE() {
        return INSTANCE;
    }


    @Override
    public List<Item> getAllItems(boolean onlyUndone) {
        LOG.info("Enter method");
        List<Item> list =
                onlyUndone ? SESSION.createQuery("from Item WHERE done=false").list() : SESSION.createQuery("from Item").list();
        LOG.info("Exit method");
        return list;
    }

    @Override
    public void addItem(Item item) {
        LOG.info("Enter method");
        SESSION.beginTransaction();
        SESSION.save(item);
        SESSION.getTransaction().commit();
        LOG.info("Exit method");
    }
}
