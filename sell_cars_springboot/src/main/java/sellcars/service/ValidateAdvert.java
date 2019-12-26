package sellcars.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.criterion.Restrictions;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sellcars.models.*;
import sellcars.repository.AdvertRepository;
import sellcars.repository.CarRepository;
import sellcars.repository.PhotoRepository;
import sellcars.repository.UserRepository;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class ValidateAdvert implements AdvertValidator {
    private final static Logger LOG = LoggerFactory.getLogger(ValidateAdvert.class);

    private CarRepository carRepository;

    private UserRepository userRepository;
    private AdvertRepository advertRepository;
    private PhotoRepository photoRepository;

    private CarValidator carValidator;


    @Override
    public String addAdvert(int carId, double price, List<String> listOfPhotos, String userLogin) {
        LOG.info("Enter method");
        String result = "";
        Advert advert = new Advert();
        Optional<Car> optionalCar = carRepository.findById(carId);
        User user = userRepository.findByLogin(userLogin);
        if (optionalCar.isEmpty()) {
            result = "Произошла непредвиденная ошибка, при создании объявления не найден объект Car";
            LOG.error(result);
        } else {
            Car car = optionalCar.get();
            advert.setCar(car);
            advert.setUser(user);
            advert.setDate(getFormattedDateStamp());
            advert.setPrice(price);
            Advert savedAdvert = advertRepository.save(advert);
            if (savedAdvert != null) {
                result = String.valueOf(savedAdvert.getId());
            } else {
                result = "При добавлении объявления произошла непредвиденная ошибка.";
            }
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
        Optional<Advert> optionalAdvert = advertRepository.findById(advertId);
        if (optionalAdvert.isPresent()) {
            Advert advert = optionalAdvert.get();
            advert.setSold(true);
            advertRepository.save(advert);
        }
        LOG.info("Exit method");
        return "OK";
    }

    @Override
    public String getAllAdverts() {
        List<Advert> allAdverts = (List<Advert>) advertRepository.findAll();
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
        String result = "";
        Optional<Advert> optionalAdvert = advertRepository.findById(id);
        if (optionalAdvert.isPresent()) {
            Advert advert = optionalAdvert.get();
            try {
                result = new ObjectMapper().writeValueAsString(advert);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    private String getFormattedDateStamp() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(timestamp);
    }

    private void setPhotos(int advertId, List<String> listOfPhotos) {
        List<Photo> list = new ArrayList<>();
        Optional<Advert> optionalAdvert = advertRepository.findById(advertId);
        if (optionalAdvert.isPresent()) {
            Advert advert = optionalAdvert.get();
            for (String eachFileName : listOfPhotos) {
                Photo photo = new Photo(eachFileName);
                photo.setAdvertId(advertId);
                photoRepository.save(photo);
            }
            advert.setPhotos(list);

        }


    }

    @Override
    public String getAdvertByFilters(String jsonFromClient) {
        JSONObject json = new JSONObject(jsonFromClient);
        Map<String, List<String>> params = json.getJSONObject("filterSelect").toMap().entrySet()
                .stream().collect(Collectors.toMap(Map.Entry::getKey, e -> List.of((String) e.getValue())));

        List<Car> listOfCars = carValidator.getCarsByFilter(params);

        Specification<Advert> specification = AdvertSpecifications.matchAdvertsByCarList(listOfCars);
        List<Advert> resultList = new ArrayList<>();
        if (listOfCars.size() > 0) {
            if (params.containsKey("lastDay")) {
                params.put("date", List.of(getSomeDaysAgoFormattedTime(1), getCurrentFormattedTime()));
                params.remove("lastDay");
                String min = params.get("date").get(0);
                String max = params.get("date").get(1);
                specification = specification.and(AdvertSpecifications.selectByDateBetween(min, max));
            }
            if (params.containsKey("priceFrom") || params.containsKey("priceTo")) {
                handlePriceParameters(params);
                double min = Double.parseDouble(params.get("price").get(0));
                double max = Double.parseDouble(params.get("price").get(1));
                specification = specification.and(AdvertSpecifications.selectByPriceBetween(min, max));
            }
            if (params.containsKey("sold")) {
                boolean isSold = params.get("sold").get(0).equals("true");
                specification = specification.and(AdvertSpecifications.selectBySoldStatus(isSold));
            }
            if (params.containsKey("photos")) {
                if (params.get("photos").get(0).equals("true")) {
                    specification = specification.and(AdvertSpecifications.selectOnlyWithPhoto());
                }
            }
            resultList = advertRepository.findAll(specification);
        }


        String result = "";
        try {
            result = new ObjectMapper().writeValueAsString(resultList);
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


    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setPhotoRepository(PhotoRepository photoRepository) {
        this.photoRepository = photoRepository;
    }

    @Autowired
    public void setCarRepository(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Autowired
    public void setAdvertRepository(AdvertRepository advertRepository) {
        this.advertRepository = advertRepository;
    }

    @Autowired
    public void setCarValidator(CarValidator carValidator) {
        this.carValidator = carValidator;
    }
}
