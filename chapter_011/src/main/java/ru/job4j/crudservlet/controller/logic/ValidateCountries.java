package ru.job4j.crudservlet.controller.logic;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.crudservlet.model.DBStore;
import ru.job4j.crudservlet.model.Store;

import java.util.Comparator;
import java.util.List;

public class ValidateCountries implements CountriesValidator {

    private static final CountriesValidator INSTANCE = new ValidateCountries();
    private final static Store USER_STORE = DBStore.getInstance();
    private static final Logger LOG = LoggerFactory.getLogger(ValidateService.class);

    private ValidateCountries() {
    }

    public static CountriesValidator getInstance() {
        return INSTANCE;
    }

    @Override
    public JSONObject getAllCitiesByCountry(String country) {
        int countryId = USER_STORE.findIdByField(country, "country", "countries");
        List<String> listOfCities = USER_STORE.getListOfValuesByField("countryId", countryId, "cities", "city");
        listOfCities.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("listOfCities", listOfCities);
        return jsonObject;
    }

    @Override
    public JSONObject getAllCountries() {
        List<String> listOfCountries = USER_STORE.getAllCountries();
        listOfCountries.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("listOfCountries", listOfCountries);
        return jsonObject;
    }
}
