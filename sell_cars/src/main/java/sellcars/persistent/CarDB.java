package sellcars.persistent;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sellcars.controller.CarValidator;
import sellcars.models.*;

import java.util.List;
import java.util.function.Consumer;

public class CarDB implements CarStorage {
   private final static Logger LOG = LoggerFactory.getLogger(CarDB.class);
    private final static CarStorage INSTANCE = new CarDB();

    private CarDB() {
    }

    public static CarStorage getINSTANCE() {
        return INSTANCE;
    }

    @Override
    public String add(Car car) {
        LOG.info("Enter method");
          return SessionManager.handleQuery(session -> {
              String result;
              int id = (int) session.save(car);
              result = String.valueOf(id);
             LOG.info("Exit method");
            return result;
        }).orElse("При добавлении объекта Car произошла непредвиденная ошибка.");
    }

    @Override
    public void update(Car car) {

    }

    @Override
    public void delete(Car car) {

    }

    @Override
    public Car findCarById(int id) {
        return SessionManager.handleQuery(session -> {
            return session.get(Car.class, id);
        }).orElse(new Car());
    }

}
