package ru.job4j.carstorageannot.persistent;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.job4j.carstorageannot.models.Car;

import java.util.function.Consumer;
import java.util.function.Function;

public class CarDB implements StorageCar {


    @Override
    public void add(Car car) {
        transaction(session -> session.save(car));
    }

    @Override
    public void delete(Car car) {
        transactionVoid(session -> session.delete(car));
    }


    @Override
    public void update(Car car) {
        transaction(session -> session.merge(car));
    }


    private <T> T transaction(Function<Session, T> command) {
        final Session session = SessionGenerator.getSession();
        final Transaction transaction = session.beginTransaction();
        T result = null;
        try {
            result = command.apply(session);
        } catch (Exception e) {
            transaction.rollback();
            session.close();
            e.printStackTrace();
        } finally {
            transaction.commit();
            session.close();
        }
        return result;
    }

    private void transactionVoid(Consumer<Session> command) {
        final Session session = SessionGenerator.getSession();
        final Transaction transaction = session.beginTransaction();
        try {
            command.accept(session);
        } catch (Exception e) {
            transaction.rollback();
            session.close();
            e.printStackTrace();
        } finally {
            transaction.commit();
            session.close();
        }
    }


}
