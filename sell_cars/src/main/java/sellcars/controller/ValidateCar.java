package sellcars.controller;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sellcars.models.*;
import sellcars.persistent.*;

import java.util.HashMap;
import java.util.Map;

public class ValidateCar implements CarValidator {
    private final static Logger LOG = LoggerFactory.getLogger(ValidateCar.class);
    private final static CarValidator INSTANCE = new ValidateCar();
    private final CarStorage carStorage = CarDB.getINSTANCE();


    private ValidateCar() {
    }

    public static CarValidator getINSTANCE() {
        return INSTANCE;
    }

    @Override
    public String addCar(Map<String, String> parameters) {
        LOG.info("Enter method");
        String carModel = parameters.get("car_model");
        int mileage = Integer.parseInt(parameters.get("mileage"));
        int year = Integer.parseInt(parameters.get("year"));
        String color = parameters.get("color");
        String vin = parameters.get("vin");
        double engineVolume = Double.parseDouble(parameters.get("engine_volume"));
        CarBrand carBrand = new GetModel<CarBrand>().getByName("CarBrand", "carBrand", parameters.get("car_brand"));
        CarBody carBody = new GetModel<CarBody>().getByName("CarBody", "bodyType", parameters.get("body_type"));
        Engine engine = new GetModel<Engine>().getByName("Engine", "engineType", parameters.get("engine_type"));
        Transmission transmission = new GetModel<Transmission>().getByName("Transmission", "transmissionType", parameters.get("transmission"));

        Car car = new Car();
        car.setCarBrand(carBrand);
        car.setCarModel(carModel);
        car.setBodyType(carBody);
        car.setEngine(engine);
        car.setEngineVolume(engineVolume);
        car.setTransmission(transmission);
        car.setVin(vin);
        car.setMileage(mileage);
        car.setYear(year);
        car.setColor(color);
        LOG.info("Exit method");
        return carStorage.add(car);
    }

    @Override
    public String updateCar(JSONObject jsonFromClient) {
        return null;
    }

    @Override
    public Car getCarById(int id) {
        return null;
    }


}
