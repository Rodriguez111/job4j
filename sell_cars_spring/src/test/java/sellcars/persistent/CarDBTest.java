//package sellcars.persistent;
//
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.annotation.AnnotationConfigApplicationContext;
//import sellcars.config.CoreConfig;
//import sellcars.models.*;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import static org.hamcrest.MatcherAssert.assertThat;
//import static org.hamcrest.Matchers.is;
//import static org.junit.jupiter.api.Assertions.*;
//
//class CarDBTest {
//
//   private ApplicationContext context = new AnnotationConfigApplicationContext(CoreConfig.class);
//
//
//    @AfterEach
//    public void clear() {
//        SessionManager.handleQuery(session -> {
//            session.createSQLQuery("delete from photos cascade").executeUpdate();
//            session.createSQLQuery("delete from adverts cascade").executeUpdate();
//            session.createSQLQuery("delete from cars cascade").executeUpdate();
//            session.createSQLQuery("delete from users cascade").executeUpdate();
//
//            session.createSQLQuery("ALTER TABLE photos ALTER COLUMN id RESTART WITH 1").executeUpdate();
//            session.createSQLQuery("ALTER TABLE adverts ALTER COLUMN id RESTART WITH 1").executeUpdate();
//            session.createSQLQuery("ALTER TABLE cars ALTER COLUMN id RESTART WITH 1").executeUpdate();
//            session.createSQLQuery("ALTER TABLE users ALTER COLUMN id RESTART WITH 1").executeUpdate();
//
//        });
//    }
//
//
//
//    @Test
//    void whenAddCarThenReturnThisCar() {
//        Car car = generateCar1();
//        CarStorage carStorage = context.getBean(CarStorage.class);
//        carStorage.add(car);
//        Car foundCar = carStorage.findCarById(1);
//        String actual = foundCar.getCarModel();
//        String expected = car.getCarModel();
//        assertThat(actual, is(expected));
//    }
//
//
//    @Test
//    void whenAddCarThenReturnUpdatedCar() {
//        Car car = generateCar1();
//        CarStorage carStorage = context.getBean(CarStorage.class);
//        carStorage.add(car);
//        Car foundCar = carStorage.findCarById(1);
//        String updatedCarModel = "updatedModel";
//        foundCar.setCarModel(updatedCarModel);
//        carStorage.update(foundCar);
//        Car resultCar = carStorage.findCarById(1);
//        String actual = resultCar.getCarModel();
//        String expected = updatedCarModel;
//        assertThat(actual, is(expected));
//    }
//
//    @Test
//    void whenDeleteCarThenWeCanNotGetItFromDBAndExceptionIsThrow() {
//        Car car = generateCar1();
//        CarStorage carStorage = context.getBean(CarStorage.class);
//        carStorage.add(car);
//        Car foundCar = carStorage.findCarById(1);
//        carStorage.delete(foundCar);
//        assertThrows(IllegalStateException.class, () -> {
//            carStorage.findCarById(1);
//        });
//    }
//
//    @Test
//    void whenIdExistsThenGetCar() {
//        Car car = generateCar1();
//        CarStorage carStorage = context.getBean(CarStorage.class);
//        carStorage.add(car);
//        Car foundCar = carStorage.findCarById(1);
//        String actual = foundCar.getCarModel();
//        String expected = car.getCarModel();
//        assertThat(actual, is(expected));
//    }
//
//    @Test
//    void whenFindByModelThenReturn1CarOf2() {
//        Car car1 = generateCar1();
//        Car car2 = generateCar2();
//        CarStorage carStorage = context.getBean(CarStorage.class);
//        carStorage.add(car1);
//        carStorage.add(car2);
//        Map<String, List<String>> params = new HashMap<>();
//        params.put("carModel", List.of("TestModel"));
//        List<Car> result = carStorage.findCarsByFilter(params);
//        int actual = result.size();
//        int expected = 1;
//        assertThat(actual, is(expected));
//    }
//
//    @Test
//    void whenFindByModelThenReturn2CarsOf2() {
//        Car car1 = generateCar1();
//        Car car2 = generateCar2();
//        CarStorage carStorage = context.getBean(CarStorage.class);
//        carStorage.add(car1);
//        carStorage.add(car2);
//        Map<String, List<String>> params = new HashMap<>();
//        params.put("carModel", List.of("TestModel", "TestModel2"));
//        List<Car> result = carStorage.findCarsByFilter(params);
//        int actual = result.size();
//        int expected = 2;
//        assertThat(actual, is(expected));
//    }
//
//    @Test
//    void whenFindByModelThenReturn0CarsOf2() {
//        Car car1 = generateCar1();
//        Car car2 = generateCar2();
//        CarStorage carStorage = context.getBean(CarStorage.class);
//        carStorage.add(car1);
//        carStorage.add(car2);
//        Map<String, List<String>> params = new HashMap<>();
//        params.put("carModel", List.of("ModelNotExists"));
//        List<Car> result = carStorage.findCarsByFilter(params);
//        int actual = result.size();
//        int expected = 0;
//        assertThat(actual, is(expected));
//    }
//
//    @Test
//    void whenFindByMileageThenReturn1CarsOf2() {
//        Car car1 = generateCar1();
//        Car car2 = generateCar2();
//        CarStorage carStorage = context.getBean(CarStorage.class);
//        carStorage.add(car1);
//        carStorage.add(car2);
//        Map<String, List<String>> params = new HashMap<>();
//        params.put("mileage", List.of("900", "1100"));
//        List<Car> result = carStorage.findCarsByFilter(params);
//        int actual = result.size();
//        int expected = 1;
//        assertThat(actual, is(expected));
//    }
//
//    @Test
//    void whenFindByMileageThenReturn2CarsOf2() {
//        Car car1 = generateCar1();
//        Car car2 = generateCar2();
//        CarStorage carStorage = context.getBean(CarStorage.class);
//        carStorage.add(car1);
//        carStorage.add(car2);
//        Map<String, List<String>> params = new HashMap<>();
//        params.put("mileage", List.of("900", "2100"));
//        List<Car> result = carStorage.findCarsByFilter(params);
//        int actual = result.size();
//        int expected = 2;
//        assertThat(actual, is(expected));
//    }
//
//    @Test
//    void whenFindByEngineVolumeThenReturn1CarsOf2() {
//        Car car1 = generateCar1();
//        Car car2 = generateCar2();
//        CarStorage carStorage = context.getBean(CarStorage.class);
//        carStorage.add(car1);
//        carStorage.add(car2);
//        Map<String, List<String>> params = new HashMap<>();
//        params.put("engineVolume", List.of("2.5", "2.5"));
//        List<Car> result = carStorage.findCarsByFilter(params);
//        int actual = result.size();
//        int expected = 1;
//        assertThat(actual, is(expected));
//    }
//
//    @Test
//    void whenFindByEngineVolumeThenReturn2CarsOf2() {
//        Car car1 = generateCar1();
//        Car car2 = generateCar2();
//        CarStorage carStorage = context.getBean(CarStorage.class);
//        carStorage.add(car1);
//        carStorage.add(car2);
//        Map<String, List<String>> params = new HashMap<>();
//        params.put("engineVolume", List.of("2.5", "3.0"));
//        List<Car> result = carStorage.findCarsByFilter(params);
//        int actual = result.size();
//        int expected = 2;
//        assertThat(actual, is(expected));
//    }
//
//
//
//
//
//    public Car generateCar1() {
//        String model = "TestModel";
//        int mileage = 1000;
//        int year = 2001;
//        String vin = "TestVin";
//        String color = "red";
//        double engineVolume = 2.5;
//       Car car = new Car();
//        car.setCarModel(model);
//        car.setCarBrand(generateCarBrand());
//        car.setBodyType(generateCarBody());
//        car.setEngine(generateEngine());
//        car.setTransmission(generateTransmission());
//        car.setEngineVolume(engineVolume);
//        car.setMileage(mileage);
//        car.setYear(year);
//        car.setVin(vin);
//        car.setColor(color);
//        return car;
//    }
//
//    public Car generateCar2() {
//        String model = "TestModel2";
//        int mileage = 2000;
//        int year = 2005;
//        String vin = "TestVin2";
//        String color = "black";
//        double engineVolume = 3.0;
//        Car car = new Car();
//        car.setCarModel(model);
//        car.setCarBrand(generateCarBrand());
//        car.setBodyType(generateCarBody());
//        car.setEngine(generateEngine());
//        car.setTransmission(generateTransmission());
//        car.setEngineVolume(engineVolume);
//        car.setMileage(mileage);
//        car.setYear(year);
//        car.setVin(vin);
//        car.setColor(color);
//        return car;
//    }
//
//
//
//    public CarBody generateCarBody() {
//        ModelGetter<CarBody> modelGetter = new GetModel<>();
//        return modelGetter.getByName("CarBody", "body_type", "Hatchback");
//    }
//
//
//    public CarBrand generateCarBrand() {
//        ModelGetter<CarBrand> modelGetter = new GetModel<>();
//        return modelGetter.getByName("CarBrand", "car_brand", "Audi");
//    }
//
//    public Engine generateEngine() {
//        ModelGetter<Engine> modelGetter = new GetModel<>();
//        return modelGetter.getByName("Engine", "engine_type", "VEE");
//    }
//
//    public Transmission generateTransmission() {
//        ModelGetter<Transmission> modelGetter = new GetModel<>();
//        return modelGetter.getByName("Transmission", "transmission_type", "Automatic");
//    }
//
//}