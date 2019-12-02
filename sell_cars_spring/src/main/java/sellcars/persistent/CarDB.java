package sellcars.persistent;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import sellcars.models.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Component
public class CarDB implements CarStorage {
    private final static Logger LOG = LoggerFactory.getLogger(CarDB.class);

    private CarDB() {
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
         SessionManager.handleQuery(session -> {
             session.merge(car);
        });
    }

    @Override
    public void delete(Car car) {
        SessionManager.handleQuery(session -> {
            session.remove(car);
        });
    }

    @Override
    public Car findCarById(int id) {
        return SessionManager.handleQuery(session -> {
            return session.get(Car.class, id);
        }).orElse(new Car());
    }

    /**
     *
     * @param params - query parameters. Key - field name of class Car,
     *               value - list of values for this field which are selected.
     * @return - list of selected cars
     */

    @Override
    public List<Car> findCarsByFilter(Map<String, List<String>> params) {
        List<Car> result = new ArrayList<>();
        SessionManager.handleQuery(session -> {
            Criteria carsCriteria = session.createCriteria(Car.class);

            for (String key : params.keySet()) {
                if (key.equals("carBrand")) {
                    Criteria criteria = session.createCriteria(CarBrand.class);
                    criteria.add(Restrictions.in("carBrand", params.get(key)));
                    List<CarBrand>  listOfCarBrands = criteria.list();
                    carsCriteria.add(Restrictions.in("carBrand", listOfCarBrands));
                }
                if (key.equals("carModel")) {
                    List<String>  listOfModels = params.get(key);
                    carsCriteria.add(Restrictions.in("carModel", listOfModels));
                }
                if (key.equals("bodyType")) {
                    Criteria criteria = session.createCriteria(CarBody.class);
                    criteria.add(Restrictions.in("bodyType", params.get(key)));
                    List<CarBody>  listOfCarBodies = criteria.list();
                    carsCriteria.add(Restrictions.in("bodyType", listOfCarBodies));
                }
                if (key.equals("engine")) {
                    Criteria criteria = session.createCriteria(Engine.class);
                    criteria.add(Restrictions.in("engine", params.get(key)));
                    List<CarBody>  listOfEngines = criteria.list();
                    carsCriteria.add(Restrictions.in("engine", listOfEngines));
                }
                if (key.equals("engineVolume")) {
                    List<Double>  listOfEngineVolumes = params.get(key).stream()
                            .mapToDouble(Double::parseDouble).boxed().collect(Collectors.toList());
                    double min = listOfEngineVolumes.get(0);
                    double max = listOfEngineVolumes.get(1);
                    Criteria criteria = session.createCriteria(Engine.class);
                    carsCriteria.add(Restrictions.between("engineVolume", min, max));
                }
                if (key.equals("transmission")) {
                    Criteria criteria = session.createCriteria(Transmission.class);
                    criteria.add(Restrictions.in("transmission", params.get(key)));
                    List<Transmission>  listOfTransmissions = criteria.list();
                    carsCriteria.add(Restrictions.in("transmission", listOfTransmissions));
                }
                if (key.equals("mileage")) {
                    List<Integer> listOfMileages = params.get(key).stream()
                            .mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());
                    Criteria criteria = session.createCriteria(Transmission.class);
                    int min = listOfMileages.get(0);
                    int max = listOfMileages.get(1);
                    carsCriteria.add(Restrictions.between("mileage", min, max));
                }
                if (key.equals("years")) {
                    List<Integer> listOfYears = params.get(key).stream()
                            .mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());
                    Criteria criteria = session.createCriteria(Transmission.class);
                    int min = listOfYears.get(0);
                    int max = listOfYears.get(1);
                    carsCriteria.add(Restrictions.between("years", min, max));
                }
                if (key.equals("vin")) {
                    carsCriteria.add(Restrictions.in("vin", params.get(key)));
                }
                if (key.equals("color")) {
                    List<String>  listOfColors = params.get(key);
                    carsCriteria.add(Restrictions.in("color", listOfColors));
                }
            }
            result.addAll(carsCriteria.list());
        });
        return result;
    }


}
