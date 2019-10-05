package ru.job4j.carstoragexml.persistent;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import ru.job4j.carstoragexml.models.Engine;

import java.util.function.Consumer;
import java.util.function.Function;

public class EngineDB implements StorageEngine {

    @Override
    public void add(Engine engine) {
        transaction(session -> session.save(engine));
    }

    @Override
    public void delete(Engine engine) {
        transactionVoid(session -> session.delete(engine));
    }

    @Override
    public void update(String oldEngineTypeType, String newengineTypeType) {
        transaction(session -> {
            Query query = session.createQuery("from Engine where engineType = :engineType");
            query.setParameter("engineType", oldEngineTypeType);
            Engine engine = (Engine)  query.getSingleResult();
            engine.setEngineType(newengineTypeType);
            return session.save(engine);
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
