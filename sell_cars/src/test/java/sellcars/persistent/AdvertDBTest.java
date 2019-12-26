package sellcars.persistent;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import sellcars.models.Advert;
import sellcars.models.Car;
import sellcars.models.User;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class AdvertDBTest {
    private static final CarDBTest CAR_DB_TEST = new CarDBTest();
    private static final UserDBTest USER_DB_TEST = new UserDBTest();

    @AfterEach
    public void clear() {
        SessionManager.handleQuery(session -> {
            session.createSQLQuery("delete from photos cascade").executeUpdate();
            session.createSQLQuery("delete from adverts cascade").executeUpdate();
            session.createSQLQuery("delete from cars cascade").executeUpdate();
            session.createSQLQuery("delete from users cascade").executeUpdate();

            session.createSQLQuery("ALTER TABLE photos ALTER COLUMN id RESTART WITH 1").executeUpdate();
            session.createSQLQuery("ALTER TABLE adverts ALTER COLUMN id RESTART WITH 1").executeUpdate();
            session.createSQLQuery("ALTER TABLE cars ALTER COLUMN id RESTART WITH 1").executeUpdate();
            session.createSQLQuery("ALTER TABLE users ALTER COLUMN id RESTART WITH 1").executeUpdate();

        });
    }



    @Test
    void whenAddAdvertThenWeCanGetItFromDB() {
        AdvertStorage advertStorage = AdvertDB.getINSTANCE();
        Advert advert = generateAdvert1();
        advertStorage.add(advert);
        Advert foundAdvert = advertStorage.findById(1);
        String actual = foundAdvert.getCar().getCarModel();
        String expected = "TestModel";
        assertThat(actual, is(expected));
    }



    @Test
    void whenUpdateAdvertThenWeCanGetUpdatedAdvertFromDB() {
        AdvertStorage advertStorage = AdvertDB.getINSTANCE();
        Advert advert = generateAdvert1();
        advertStorage.add(advert);
        Advert foundAdvert = advertStorage.findById(1);
        boolean actual = foundAdvert.isSold();
        boolean expected = false;
        assertThat(actual, is(expected));
        foundAdvert.setSold(true);
        advertStorage.update(foundAdvert);
        foundAdvert = advertStorage.findById(1);
        actual = foundAdvert.isSold();
        expected = true;
        assertThat(actual, is(expected));
    }


    @Test
    void whenAdd2AdvertsThenGetAll2() {
        AdvertStorage advertStorage = AdvertDB.getINSTANCE();
        Advert advert1 = generateAdvert1();
        Advert advert2 = generateAdvert2();
        advertStorage.add(advert1);
        advertStorage.add(advert2);
        List<Advert> result = advertStorage.getAll();
        int actual = result.size();
        int expected = 2;
        assertThat(actual, is(expected));
    }

    @Test
    void whenIdExistsThenGetCar() {
        AdvertStorage advertStorage = AdvertDB.getINSTANCE();
        Advert advert1 = generateAdvert1();
        advertStorage.add(advert1);
        Advert foundAdvert = advertStorage.findById(1);
        String actual = foundAdvert.getCar().getCarModel();
        String expected = "TestModel";
        assertThat(actual, is(expected));
    }

    @Test
    void whenFindByModelThenReturn1AdvertOf2() {
        AdvertStorage advertStorage = AdvertDB.getINSTANCE();
        Advert advert1 = generateAdvert1();
        Advert advert2 = generateAdvert2();
        advertStorage.add(advert1);
        advertStorage.add(advert2);

        Map<String, List<String>> params = new HashMap<>();
        params.put("carModel", List.of("TestModel"));
        List<Advert> result = advertStorage.findByFilter(params);
        int actual = result.size();
        int expected = 1;
        assertThat(actual, is(expected));
    }

    @Test
    void whenFindByModelThenReturn2AdvertOf2() {
        AdvertStorage advertStorage = AdvertDB.getINSTANCE();
        Advert advert1 = generateAdvert1();
        Advert advert2 = generateAdvert2();
        advertStorage.add(advert1);
        advertStorage.add(advert2);

        Map<String, List<String>> params = new HashMap<>();
        params.put("carModel", List.of("TestModel", "TestModel2"));
        List<Advert> result = advertStorage.findByFilter(params);
        int actual = result.size();
        int expected = 2;
        assertThat(actual, is(expected));
    }

    @Test
    void whenFindByPriceThenReturn1AdvertOf2() {
        AdvertStorage advertStorage = AdvertDB.getINSTANCE();
        Advert advert1 = generateAdvert1();
        Advert advert2 = generateAdvert2();
        advertStorage.add(advert1);
        advertStorage.add(advert2);

        Map<String, List<String>> params = new HashMap<>();
        params.put("price", List.of("9000", "11000"));
        List<Advert> result = advertStorage.findByFilter(params);
        int actual = result.size();
        int expected = 1;
        assertThat(actual, is(expected));
    }

    @Test
    void whenFindByPriceThenReturn2AdvertOf2() {
        AdvertStorage advertStorage = AdvertDB.getINSTANCE();
        Advert advert1 = generateAdvert1();
        Advert advert2 = generateAdvert2();
        advertStorage.add(advert1);
        advertStorage.add(advert2);

        Map<String, List<String>> params = new HashMap<>();
        params.put("price", List.of("9000", "21000"));
        List<Advert> result = advertStorage.findByFilter(params);
        int actual = result.size();
        int expected = 2;
        assertThat(actual, is(expected));
    }


    public Advert generateAdvert1() {
        Car car = addCar1ToDBAndGetIt();
        User user = addUser1ToDBAndGetIt();
        String date = getFormattedDateStamp();
        double price = 10000;
        Advert advert = new Advert();
        advert.setCar(car);
        advert.setUser(user);
        advert.setDate(date);
        advert.setPrice(price);
        return advert;
    }

    public Advert generateAdvert2() {
        Car car = addCar2ToDBAndGetIt();
        User user = addUser2ToDBAndGetIt();
        String date = getFormattedDateStamp();
        double price = 20000;
        Advert advert = new Advert();
        advert.setCar(car);
        advert.setUser(user);
        advert.setDate(date);
        advert.setPrice(price);
        return advert;
    }

    public String getFormattedDateStamp() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(timestamp);
    }

    public Car addCar1ToDBAndGetIt() {
        CarStorage carStorage = CarDB.getINSTANCE();
        Car car = CAR_DB_TEST.generateCar1();
        carStorage.add(car);
        return car;
    }

    public Car addCar2ToDBAndGetIt() {
        CarStorage carStorage = CarDB.getINSTANCE();
        Car car = CAR_DB_TEST.generateCar2();
        carStorage.add(car);
        return car;
    }

    public User addUser1ToDBAndGetIt() {
        UserStorage userStorage = UserDB.getINSTANCE();
        User user = USER_DB_TEST.generateTestUser1();
        userStorage.add(user);
        return user;
    }

    public User addUser2ToDBAndGetIt() {
        UserStorage userStorage = UserDB.getINSTANCE();
        User user = USER_DB_TEST.generateTestUser2();
        userStorage.add(user);
        return user;
    }

}