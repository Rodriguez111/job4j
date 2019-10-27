package sellcars.persistent;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sellcars.models.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class AdvertDB implements AdvertStorage {
    private final static Logger LOG = LoggerFactory.getLogger(AdvertDB.class);
    private final static AdvertStorage INSTANCE = new AdvertDB();
    private final UserStorage userStorage = UserDB.getINSTANCE();
    private final CarStorage carStorage = CarDB.getINSTANCE();

    private AdvertDB() {
    }

    public static AdvertStorage getINSTANCE() {
        return INSTANCE;
    }

    @Override
    public String add(Advert advert) {
        LOG.info("Enter method");
        return SessionManager.handleQuery(session -> {
            String result;
            int id = (int) session.save(advert);
            result = String.valueOf(id);
            LOG.info("Exit method");
            return result;
        }).orElse("При добавлении объявления произошла непредвиденная ошибка.");
    }

    @Override
    public void update(Advert advert) {
        SessionManager.handleQuery(session -> {
            return session.merge(advert);
        });
    }

    @Override
    public List<Advert> getAll() {
        LOG.info("Enter method");
        return SessionManager.handleQuery(session -> {
            Query query = session.createQuery("from Advert");
            LOG.info("Exit method");
            List<Advert> list = (List<Advert>) query.getResultList();
//            list.stream().forEach(adv -> {
//                int userId = adv.getUser().getId();
//                User user = userStorage.findById(userId);
//                user.setAdverts(null);
//                adv.setUser(user);
//            });
            return list;
        }).get();
    }

    @Override
    public Advert findById(int id) {
        return SessionManager.handleQuery(session -> {
            Query query = session.createQuery("from Advert where id = :id");
            query.setParameter("id", id);
            Advert advert = (Advert) query.getSingleResult();
//            int userId = advert.getUser().getId();
//            User user = userStorage.findById(userId);
//            user.setAdverts(null);
//            advert.setUser(user);
            return advert;
        }).orElse(new Advert());
    }

    /**
     *
     * @param params - query parameters. Key - field name of class Advert,
     *               value - list of values for this field which are selected.
     * @return - list of selected Adverts
     */
    @Override
    public List<Advert> findByFilter(Map<String, List<String>> params) {
        Map<String, List<String>> paramsForCar = selectParametersForCar(params);
        params.entrySet().removeAll(paramsForCar.entrySet());
        List<Advert> result = new ArrayList<>();
        SessionManager.handleQuery(session -> {
            Criteria advertsCriteria = session.createCriteria(Advert.class);
            List<Car> listOfCars = carStorage.findCarsByFilter(paramsForCar);
            if (listOfCars.size() > 0) {
                advertsCriteria.add(Restrictions.in("car", listOfCars));
            }
            for (String key : params.keySet()) {
                if (key.equals("date")) {
                    List<String> listOfDates = params.get(key);
                    String minDate = listOfDates.get(0);
                    String maxDate = listOfDates.get(1);
                    advertsCriteria.add(Restrictions.between("date", minDate, maxDate));
                }
                if (key.equals("price")) {
                    List<Double> listOfPrices = params.get(key).stream()
                            .mapToDouble(Double::parseDouble).boxed().collect(Collectors.toList());
                    double minPrice = listOfPrices.get(0);
                    double maxPrice = listOfPrices.get(1);
                    advertsCriteria.add(Restrictions.between("price", minPrice, maxPrice));
                }
                if (key.equals("sold")) {
                    boolean sold = params.get(key).get(0).equals("true");
                    advertsCriteria.add(Restrictions.eq("sold", sold));
                }
                if (key.equals("photos")) {
                    if (params.get(key).get(0).equals("true")) {
                        advertsCriteria.add(Restrictions.isNotEmpty("photos"));
                    } else if (params.get(key).get(0).equals("false")) {
                        advertsCriteria.add(Restrictions.isEmpty("photos"));
                    }
                }
            }
            result.addAll(advertsCriteria.list());
        });

        return result;
    }


    /**Accepts parameters for the entire advert and returns parameters relating to a strictly nested Car entity
     *
     * @param params - parameters for entire Advert entity ;
     * @return - parameters only for nested Car entity;
     */
    private Map<String, List<String>> selectParametersForCar(Map<String, List<String>> params) {
        return params.entrySet().stream().filter(eachEntry -> eachEntry.getKey().equals("carBrand")
                || eachEntry.getKey().equals("carModel")
                || eachEntry.getKey().equals("bodyType")
                || eachEntry.getKey().equals("engine")
                || eachEntry.getKey().equals("engineVolume")
                || eachEntry.getKey().equals("transmission")
                || eachEntry.getKey().equals("mileage")
                || eachEntry.getKey().equals("years")
                || eachEntry.getKey().equals("vin")
                || eachEntry.getKey().equals("color"))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

}
