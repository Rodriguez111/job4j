package sellcars.controller;

import org.json.JSONObject;
import sellcars.models.Car;

import java.util.Map;

public interface CarValidator {

    String addCar(Map<String, String> parameters);

    String updateCar(JSONObject jsonFromClient);

    Car getCarById(int id);
}
