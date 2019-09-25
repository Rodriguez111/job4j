package ru.job4j.carstoragexml.persistent;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.job4j.carstoragexml.models.Car;
import ru.job4j.carstoragexml.models.CarBody;
import ru.job4j.carstoragexml.models.Engine;
import ru.job4j.carstoragexml.models.Transmission;

import javax.persistence.NoResultException;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.jupiter.api.Assertions.*;

class CarDBTest {
    private Session session;

    @BeforeEach
    void prepareTest() {
        session = SessionGenerator.getSession();
        addTestEngine();
        addTestBody();
        addTestTransmission();
    }

    @AfterEach
    void completeTest() {
        deleteTestEngine();
        deleteTestBody();
        deleteTestTransmission();
        session.close();
    }

    @Test
    void whenAddCarThenCarAdded() {
        CarDB carDB = new CarDB();
        CarBodyDB storageDB = new CarBodyDB();
        Query query = session.createQuery("from CarBody where bodyType = :bodyType");
        query.setParameter("bodyType", "TestBody");
        CarBody carBody = (CarBody) query.getSingleResult();

        query = session.createQuery("from Engine where engineType = :engineType");
        query.setParameter("engineType", "TestEngine");
        Engine engine = (Engine) query.getSingleResult();

        query = session.createQuery("from Transmission where transmissionType = :transmissionType");
        query.setParameter("transmissionType", "TestTransmission");
        Transmission transmission = (Transmission) query.getSingleResult();

        Car car = new Car("TestManufacturer", "TestModel");
        car.setCarBody(carBody);
        car.setEngine(engine);
        car.setTransmission(transmission);
        carDB.add(car);

        query = session.createQuery("from Car where manufacturer = :manufacturer");
        query.setParameter("manufacturer", "TestManufacturer");
        Car actual = (Car) query.getSingleResult();
        carDB.delete(actual);
        assertThat(actual, notNullValue());
    }

    @Test
    void whenUpdateThenReturnUpdatedCar() {
        CarDB carDB = new CarDB();
        CarBodyDB storageDB = new CarBodyDB();

        Query query = session.createQuery("from CarBody where bodyType = :bodyType");
        query.setParameter("bodyType", "TestBody");
        CarBody carBody = (CarBody) query.getSingleResult();

        query = session.createQuery("from Engine where engineType = :engineType");
        query.setParameter("engineType", "TestEngine");
        Engine engine = (Engine) query.getSingleResult();

        query = session.createQuery("from Transmission where transmissionType = :transmissionType");
        query.setParameter("transmissionType", "TestTransmission");
        Transmission transmission = (Transmission) query.getSingleResult();

        query = session.createQuery("from CarBody where bodyType = :bodyType");
        query.setParameter("bodyType", "TestBody2");
        CarBody carBody2 = (CarBody) query.getSingleResult();



        Car car = new Car("TestManufacturer", "TestModel");
        car.setCarBody(carBody);
        car.setEngine(engine);
        car.setTransmission(transmission);
        carDB.add(car);

        car.setCarBody(carBody2);

        carDB.update(car);
        query = session.createQuery("from Car where manufacturer = :manufacturer");
        query.setParameter("manufacturer", "TestManufacturer");
        Car result = (Car) query.getSingleResult();
        String actual = result.getCarBody().getBodyType();
        String expected = "TestBody2";
        carDB.delete(result);
        assertThat(actual, is(expected));
    }

    @Test
    void whenDeleteThenThrowNoResultException() {
        CarDB carDB = new CarDB();
        CarBodyDB storageDB = new CarBodyDB();
        Query query = session.createQuery("from CarBody where bodyType = :bodyType");
        query.setParameter("bodyType", "TestBody");
        CarBody carBody = (CarBody) query.getSingleResult();

        query = session.createQuery("from Engine where engineType = :engineType");
        query.setParameter("engineType", "TestEngine");
        Engine engine = (Engine) query.getSingleResult();

        query = session.createQuery("from Transmission where transmissionType = :transmissionType");
        query.setParameter("transmissionType", "TestTransmission");
        Transmission transmission = (Transmission) query.getSingleResult();

        Car car = new Car("TestManufacturer", "TestModel");
        car.setCarBody(carBody);
        car.setEngine(engine);
        car.setTransmission(transmission);
        carDB.add(car);

        carDB.delete(car);

        assertThrows(NoResultException.class, () -> {
            Query query2 = session.createQuery("from Car where manufacturer = :manufacturer");
            query2.setParameter("manufacturer", "TestManufacturer");
            Car actual = (Car) query2.getSingleResult();
        });

    }

    private void addTestEngine() {
        EngineDB engineDB = new EngineDB();
        Engine engine = new Engine("TestEngine");
        Engine engine2 = new Engine("TestEngine2");
        engineDB.add(engine);
        engineDB.add(engine2);
    }

    private void addTestBody() {
        CarBodyDB storageDB = new CarBodyDB();
        CarBody carBody = new CarBody("TestBody");
        CarBody carBody2 = new CarBody("TestBody2");
        storageDB.add(carBody);
        storageDB.add(carBody2);
    }

    private void addTestTransmission() {
        TransmissionDB transmissionDB = new TransmissionDB();
        Transmission transmission = new Transmission("TestTransmission");
        Transmission transmission2 = new Transmission("TestTransmission2");
        transmissionDB.add(transmission);
        transmissionDB.add(transmission2);
    }

    private void deleteTestEngine() {
        EngineDB engineDB = new EngineDB();
        Query query = session.createQuery("from Engine");
        List<Engine>  engineList =  query.getResultList();
        engineDB.delete(engineList.get(0));
        engineDB.delete(engineList.get(1));

    }

    private void deleteTestBody() {
        CarBodyDB storageDB = new CarBodyDB();
        Query query = session.createQuery("from CarBody");
        List<CarBody>  carBodyList =  query.getResultList();
        storageDB.delete(carBodyList.get(0));
        storageDB.delete(carBodyList.get(1));

    }

    private void deleteTestTransmission() {
        TransmissionDB transmissionDB = new TransmissionDB();
        Query query = session.createQuery("from Transmission");
        List<Transmission>  transmissionList =  query.getResultList();
        transmissionDB.delete(transmissionList.get(0));
        transmissionDB.delete(transmissionList.get(1));
    }

}