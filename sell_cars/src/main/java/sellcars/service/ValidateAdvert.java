package sellcars.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sellcars.models.*;
import sellcars.persistent.*;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

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
    public String getAllAdverts() {
        List<Advert> allAdverts = advertStorage.getAll();
        allAdverts.sort(new Comparator<Advert>() {
            @Override
            public int compare(Advert o1, Advert o2) {
                return o2.getDate().compareTo(o1.getDate());
            }
        });
        String result = "";
        try {
            result = new ObjectMapper().writeValueAsString(allAdverts);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public String getAdvertById(int id) {
        Advert advert = advertStorage.findById(id);
        String result = "";
        try {
            result = new ObjectMapper().writeValueAsString(advert);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return result;
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

    @Override
    public String getAdvertByFilters(String jsonFromClient) {
                JSONObject json = new JSONObject(jsonFromClient);
        Map<String, List<String>> params = json.getJSONObject("filterSelect").toMap().entrySet()
                .stream().collect(Collectors.toMap(Map.Entry::getKey, e -> List.of((String) e.getValue())));

        if (params.containsKey("lastDay")) {
            params.put("date", List.of(getSomeDaysAgoFormattedTime(1), getCurrentFormattedTime()));
            params.remove("lastDay");
        }
        if (params.containsKey("priceFrom") || params.containsKey("priceTo")) {
            handlePriceParameters(params);
        }

        List<Advert> listOfAdverts = advertStorage.findByFilter(params);
        listOfAdverts.sort((o1, o2) -> o2.getDate().compareTo(o1.getDate()));
        String result = "";
        try {
            result = new ObjectMapper().writeValueAsString(listOfAdverts);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return result;
    }

    private String getCurrentFormattedTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return formatter.format(now);
    }

    private String getSomeDaysAgoFormattedTime(int daysAgo) {
        LocalDateTime ago = LocalDateTime.now().minusDays(daysAgo);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return formatter.format(ago);
    }

    private void handlePriceParameters(Map<String, List<String>> params) {
        List<String> list = new ArrayList<>();
        list.add("0");
        list.add(String.valueOf(Double.MAX_VALUE));
        params.put("price", list);
        if (params.containsKey("priceFrom")) {
            params.computeIfPresent("price", (k, v) -> {
                        v.remove(0);
                        v.add(0, params.get("priceFrom").get(0));
                        return v;
                    });
            params.remove("priceFrom");
        }
        if (params.containsKey("priceTo")) {
            params.computeIfPresent("price", (k, v) -> {
                v.remove(1);
                v.add(params.get("priceTo").get(0));
                return v;
            });
            params.remove("priceTo");
        }
    }


}
