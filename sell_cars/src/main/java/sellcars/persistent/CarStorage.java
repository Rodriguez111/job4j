package sellcars.persistent;


import sellcars.models.Car;

import java.util.List;
import java.util.Map;

public interface CarStorage {

    String add(Car car);

    void update(Car car);

    void delete(Car car);

    Car findCarById(int id);

    List<Car> findCarsByFilter(Map<String, List<String>> params);

}
