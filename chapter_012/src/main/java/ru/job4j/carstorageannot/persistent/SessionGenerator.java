package ru.job4j.carstorageannot.persistent;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionGenerator {

    public static org.hibernate.Session getSession() {
        SessionFactory factory = new Configuration()
                .configure() // configures settings from hibernate.cfg.xml
                .buildSessionFactory();
        return factory.openSession();
    }
}
