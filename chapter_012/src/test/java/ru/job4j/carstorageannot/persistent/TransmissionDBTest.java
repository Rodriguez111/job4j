package ru.job4j.carstorageannot.persistent;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.job4j.carstorageannot.models.CarBody;
import ru.job4j.carstorageannot.models.Transmission;

import javax.persistence.NoResultException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.jupiter.api.Assertions.*;

class TransmissionDBTest {

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
    void whenAddTransmissionThenTransmissionAdded() {
        TransmissionDB transmissionDB = new TransmissionDB();
        Transmission transmission = new Transmission("Test");
        transmissionDB.add(transmission);

        Query query = session.createQuery("from Transmission where transmissionType = :transmissionType");
        query.setParameter("transmissionType", "Test");
        Transmission actual = (Transmission) query.getSingleResult();
        transmissionDB.delete(actual);
        assertThat(actual, notNullValue());
    }

    @Test
    void whenUpdateThenReturnUpdatedValue() {
        TransmissionDB transmissionDB = new TransmissionDB();
        Transmission transmission = new Transmission("Test");
        transmissionDB.add(transmission);

        transmissionDB.update("Test", "newTest");
        Query query = session.createQuery("from Transmission where transmissionType = :transmissionType");
        query.setParameter("transmissionType", "newTest");
        Transmission result = (Transmission) query.getSingleResult();
        String actual = result.getTransmissionType();
        String expected = "newTest";
        transmissionDB.delete(result);
        assertThat(actual, is(expected));
    }

    @Test
    void whenDeleteThenThrowNoResultException() {
        TransmissionDB transmissionDB = new TransmissionDB();
        Transmission transmission = new Transmission("Test");
        transmissionDB.add(transmission);

        Query query = session.createQuery("from Transmission where transmissionType = :transmissionType");
        query.setParameter("transmissionType", "Test");

        transmissionDB.delete(transmission);

        assertThrows(NoResultException.class, () -> {
            CarBody actual = (CarBody) query.getSingleResult();
        });

    }
}