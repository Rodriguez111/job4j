package ru.job4j.carstorageannot.persistent;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import ru.job4j.carstorageannot.models.CarBody;

import java.util.function.Consumer;
import java.util.function.Function;

public class CarBodyDB implements StorageCarBody {
    @Override
    public void add(CarBody carBody) {
        transaction(session -> session.save(carBody));
    }

    @Override
    public void update(String oldBodyType, String newBodyType) {
        transaction(session -> {
            Query query = session.createQuery("from CarBody where bodyType = :bodyType");
            query.setParameter("bodyType", oldBodyType);
            CarBody carBody = (CarBody) query.getSingleResult();
            carBody.setBodyType(newBodyType);
            return session.save(carBody);
        });
    }

    @Override
    public void delete(CarBody carBody) {
        transactionVoid(session -> session.delete(carBody));
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
