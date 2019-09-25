package ru.job4j.carstoragexml.persistent;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.junit.jupiter.api.*;
import ru.job4j.carstoragexml.models.CarBody;

import javax.persistence.NoResultException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CarBodyDBTest {
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
    void whenAddCarBodyThenCarBodyAdded() {
        CarBodyDB storageDB = new CarBodyDB();
        CarBody carBody = new CarBody("Test");
        storageDB.add(carBody);

        Query query = session.createQuery("from CarBody where bodyType = :bodyType");
        query.setParameter("bodyType", "Test");
        CarBody actual = (CarBody) query.getSingleResult();
        storageDB.delete(actual);
        assertThat(actual, notNullValue());
    }

    @Test
    void whenUpdateThenReturnUpdatedValue() {
        CarBodyDB storageDB = new CarBodyDB();
        CarBody carBody = new CarBody("Test");
        storageDB.add(carBody);

        storageDB.update("Test", "newTest");
        Query query = session.createQuery("from CarBody where bodyType = :bodyType");
        query.setParameter("bodyType", "newTest");
        CarBody result = (CarBody) query.getSingleResult();
        String actual = result.getBodyType();
        String expected = "newTest";
        storageDB.delete(result);
        assertThat(actual, is(expected));
    }

    @Test
    void whenDeleteThenThrowNoResultException() {
        CarBodyDB storageDB = new CarBodyDB();
        CarBody carBody = new CarBody("Test");
        storageDB.add(carBody);

        Query query = session.createQuery("from CarBody where bodyType = :bodyType");
        query.setParameter("bodyType", "Test");

        storageDB.delete(carBody);

        assertThrows(NoResultException.class, () -> {
            CarBody actual = (CarBody) query.getSingleResult();
        });

    }
}