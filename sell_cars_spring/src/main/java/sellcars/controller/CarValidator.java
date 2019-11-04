package sellcars.controller;

import sellcars.models.Car;

import java.util.List;
import java.util.Map;

public interface CarValidator {

    String addCar(Map<String, String> parameters);

    String updateCar(String jsonFromClient);

    Car getCarById(int id);

    List<Car> getCarsByFilter(Map<String, List<String>> parameters);
}
