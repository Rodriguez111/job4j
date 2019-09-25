package ru.job4j.carstorageannot.persistent;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.job4j.carstorageannot.models.CarBody;
import ru.job4j.carstorageannot.models.Engine;

import javax.persistence.NoResultException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.jupiter.api.Assertions.*;

class EngineDBTest {
    private Session session;

    @BeforeEach
    void initSession() {
        session = SessionGenerator.getSession();
    }

    @AfterEach
    void closeSession() {
        session.close();
    }

    @Test
    void whenAddEngineThenEngineAdded() {
        EngineDB engineDB = new EngineDB();
        Engine engine = new Engine("Test");
        engineDB.add(engine);

        Query query = session.createQuery("from Engine where engineType = :engineType");
        query.setParameter("engineType", "Test");
        Engine actual = (Engine) query.getSingleResult();
        engineDB.delete(actual);
        assertThat(actual, notNullValue());
    }

    @Test
    void whenUpdateThenReturnUpdatedValue() {
        EngineDB engineDB = new EngineDB();
        Engine engine = new Engine("Test");
        engineDB.add(engine);

        engineDB.update("Test", "newTest");
        Query query = session.createQuery("from Engine where engineType = :engineType");
        query.setParameter("engineType", "newTest");
        Engine result = (Engine) query.getSingleResult();
        String actual = result.getEngineType();
        String expected = "newTest";
        engineDB.delete(result);
        assertThat(actual, is(expected));
    }

    @Test
    void whenDeleteThenThrowNoResultException() {
        EngineDB engineDB = new EngineDB();
        Engine engine = new Engine("Test");
        engineDB.add(engine);


        Query query = session.createQuery("from Engine where engineType = :engineType");
        query.setParameter("engineType", "Test");

        engineDB.delete(engine);

        assertThrows(NoResultException.class, () -> {
            CarBody actual = (CarBody) query.getSingleResult();
        });

    }

}