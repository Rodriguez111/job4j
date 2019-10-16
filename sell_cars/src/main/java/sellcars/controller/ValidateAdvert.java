package sellcars.controller;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sellcars.models.*;
import sellcars.persistent.*;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class ValidateAdvert implements AdvertValidator {
    private final static Logger LOG = LoggerFactory.getLogger(ValidateAdvert.class);
    private final static AdvertValidator INSTANCE = new ValidateAdvert();
    private final CarStorage carStorage = CarDB.getINSTANCE();
    private final UserStorage userStorage = UserDB.getINSTANCE();
    private final AdvertStorage advertStorage = AdvertDB.getINSTANCE();
    private final PhotoStorage photoStorage = PhotoDB.getINSTANCE();

    private ValidateAdvert() {
    }

    public static AdvertValidator getINSTANCE() {
        return INSTANCE;
    }

    @Override
    public String addAdvert(int carId, double price, List<String> listOfPhotos, String userLogin) {
        LOG.info("Enter method");
        String result;
        Advert advert = new Advert();
        Car car = carStorage.findCarById(carId);
        User user = userStorage.findUserByLogin(userLogin);
        if (car.getBodyType() == null) {
            result = "Произошла непредвиденная ошибка, при создании объявления не найден объект Car";
            LOG.error(result);
        } else {
            advert.setCar(car);
            advert.setUser(user);
            advert.setDate(getFormattedDateStamp());
            advert.setPrice(price);
            result = advertStorage.add(advert);
        }
        LOG.info("Exit method");

        int advertId = 0;

        try {
            advertId = Integer.parseInt(result);
            setPhotos(advertId, listOfPhotos);
            result = "OK";
        } catch (NumberFormatException e) {
            /*NOP*/
        }

        return result;
    }

    @Override
    public String setSoldStatus(int advertId) {
        LOG.info("Enter method");
        Advert advert = advertStorage.findById(advertId);
        advert.setSold(true);
        advertStorage.update(advert);
        LOG.info("Exit method");
        return "OK";
    }

    @Override
    public String deleteAdvert(int id) {
        return null;
    }

    @Override
    public JSONObject getAllAdverts() {
        JSONObject result = new JSONObject();
        List<Advert> allAdverts = advertStorage.getAll();
        allAdverts.sort(new Comparator<Advert>() {
            @Override
            public int compare(Advert o1, Advert o2) {
                return o2.getDate().compareTo(o1.getDate());
            }
        });
        result.put("allAdverts", allAdverts);
        return result;
    }

    @Override
    public JSONObject getAdvertById(int id) {
        Advert advert = advertStorage.findById(id);
        return new JSONObject(advert);
    }

    private String getFormattedDateStamp() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(timestamp);
    }

    private void setPhotos(int advertId, List<String> listOfPhotos) {
        Advert advert = advertStorage.findById(advertId);
        List<Photo> list = new ArrayList<>();
        for (String eachFileName : listOfPhotos) {
            Photo photo = new Photo(eachFileName);
            photo.setAdvertId(advertId);
            photoStorage.add(photo);
        }
        advert.setPhotos(list);
    }

}
