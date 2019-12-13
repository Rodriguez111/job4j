package sellcars.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sellcars.models.*;
import sellcars.repository.*;

import java.util.ArrayList;

import java.util.List;
import java.util.Map;

@Service
public class ValidateCar implements CarValidator {
    private final static Logger LOG = LoggerFactory.getLogger(ValidateCar.class);

    private CarRepository carRepository;

    private CarBrandRepository carBrandRepository;

    private CarBodyRepository carBodyRepository;

    private EngineRepository engineRepository;

    private TransmissionRepository transmissionRepository;

    private ValidateCar() {
    }


    @Transactional
    @Override
    public String addCar(Map<String, String> parameters) {
        LOG.info("Enter method");
        String carModel = parameters.get("car_model");
        int mileage = Integer.parseInt(parameters.get("mileage"));
        int year = Integer.parseInt(parameters.get("year"));
        String color = parameters.get("color");
        String vin = parameters.get("vin");
        double engineVolume = Double.parseDouble(parameters.get("engine_volume"));
        CarBrand carBrand = carBrandRepository.findByCarBrand(parameters.get("car_brand"));
        CarBody carBody = carBodyRepository.findByBodyType(parameters.get("body_type"));
        Engine engine = engineRepository.findByEngineType(parameters.get("engine_type"));
        Transmission transmission = transmissionRepository.findByTransmissionType(parameters.get("transmission"));

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

        String result = "";
        Car savedCar = carRepository.save(car);
        if (savedCar != null) {
            result = String.valueOf(savedCar.getId());
        } else {
            result = "При добавлении объекта Car произошла непредвиденная ошибка.";
        }
        LOG.info("Exit method");
        return result;
    }

    @Override
    public String updateCar(String jsonFromClient) {
        return null;
    }

    @Override
    public Car getCarById(int id) {
        return null;
    }

    @Override
    public List<Car> getCarsByFilter(Map<String, List<String>> parameters) {
        Specification<Car> specification = CarSpecifications.orderById();
        for (String key : parameters.keySet()) {
            if (key.equals("carBrand")) {
                for (String eachParam : parameters.get(key)) {
                    specification = specification.and(CarSpecifications.selectByCarBrand(eachParam));
                }
            }
            if (key.equals("carModel")) {
                for (String eachParam : parameters.get(key)) {
                    specification = specification.and(CarSpecifications.selectByCarModel(eachParam));
                }
            }
            if (key.equals("bodyType")) {
                for (String eachParam : parameters.get(key)) {
                    specification = specification.and(CarSpecifications.selectByBodyType(eachParam));
                }
            }
            if (key.equals("engine")) {
                for (String eachParam : parameters.get(key)) {
                    specification = specification.and(CarSpecifications.selectByEngine(eachParam));
                }
            }
            if (key.equals("engineVolume")) {
                double min = Double.parseDouble(parameters.get(key).get(0));
                double max = Double.parseDouble(parameters.get(key).get(1));
                specification = specification.and(CarSpecifications.selectByEngineVolumeBetween(min, max));
            }
            if (key.equals("transmission")) {
                for (String eachParam : parameters.get(key)) {
                    specification = specification.and(CarSpecifications.selectByTransmission(eachParam));
                }
            }
            if (key.equals("mileage")) {
                handleMileageParameters(parameters);
                int min = Integer.parseInt(parameters.get(key).get(0));
                int max = Integer.parseInt(parameters.get(key).get(1));
                specification = specification.and(CarSpecifications.selectByMileageBetween(min, max));
            }
            if (key.equals("year")) {
                handleYearParameters(parameters);
                int min = Integer.parseInt(parameters.get(key).get(0));
                int max = Integer.parseInt(parameters.get(key).get(1));
                specification = specification.and(CarSpecifications.selectByYearBetween(min, max));
            }
            if (key.equals("vin")) {
                for (String eachParam : parameters.get(key)) {
                    specification = specification.and(CarSpecifications.selectByVin(eachParam));
                }
            }
            if (key.equals("color")) {
                for (String eachParam : parameters.get(key)) {
                    specification = specification.and(CarSpecifications.selectByColor(eachParam));
                }
            }

        }

        return carRepository.findAll(specification);
    }


    private void handleMileageParameters(Map<String, List<String>> params) {
        List<String> list = new ArrayList<>();
        list.add("0");
        list.add(String.valueOf(Double.MAX_VALUE));
        params.put("mileage", list);
        if (params.containsKey("mileageFrom")) {
            params.computeIfPresent("mileage", (k, v) -> {
                v.remove(0);
                v.add(0, params.get("mileageFrom").get(0));
                return v;
            });
            params.remove("mileageFrom");
        }
        if (params.containsKey("mileageTo")) {
            params.computeIfPresent("mileage", (k, v) -> {
                v.remove(1);
                v.add(params.get("mileageTo").get(0));
                return v;
            });
            params.remove("mileageTo");
        }
    }

    private void handleYearParameters(Map<String, List<String>> params) {
        List<String> list = new ArrayList<>();
        list.add("0");
        list.add(String.valueOf(Double.MAX_VALUE));
        params.put("year", list);
        if (params.containsKey("yearFrom")) {
            params.computeIfPresent("year", (k, v) -> {
                v.remove(0);
                v.add(0, params.get("yearFrom").get(0));
                return v;
            });
            params.remove("yearFrom");
        }
        if (params.containsKey("yearTo")) {
            params.computeIfPresent("year", (k, v) -> {
                v.remove(1);
                v.add(params.get("yearTo").get(0));
                return v;
            });
            params.remove("yearTo");
        }
    }

    @Autowired
    public void setCarRepository(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Autowired
    public void setCarBrandRepository(CarBrandRepository carBrandRepository) {
        this.carBrandRepository = carBrandRepository;
    }

    @Autowired
    public void setCarBodyRepository(CarBodyRepository carBodyRepository) {
        this.carBodyRepository = carBodyRepository;
    }

    @Autowired
    public void setEngineRepository(EngineRepository engineRepository) {
        this.engineRepository = engineRepository;
    }

    @Autowired
    public void setTransmissionRepository(TransmissionRepository transmissionRepository) {
        this.transmissionRepository = transmissionRepository;
    }

}
