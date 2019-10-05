package ru.job4j.carstorageannot.persistent;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import ru.job4j.carstorageannot.models.Transmission;

import java.util.function.Consumer;
import java.util.function.Function;

public class TransmissionDB implements StorageTransmission {

    @Override
    public void add(Transmission transmission) {
        transaction(session -> session.save(transmission));
    }

    @Override
    public void delete(Transmission transmission) {
        transactionVoid(session -> session.delete(transmission));
    }

    @Override
    public void update(String oldTransmissionType, String newTransmissionType) {
        transaction(session -> {
            Query query = session.createQuery("from Transmission where transmissionType = :transmissionType");
            query.setParameter("transmissionType", oldTransmissionType);
            Transmission transmission = (Transmission)  query.getSingleResult();
            transmission.setTransmissionType(newTransmissionType);
            return session.save(transmission);
        });
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
