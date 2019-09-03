package ru.job4j.crudservlet.controller.logic;

import netscape.javascript.JSObject;
import org.json.JSONObject;

import java.util.List;

public interface CountriesValidator {

    JSONObject getAllCitiesByCountry(String country);

    JSONObject getAllCountries();

}
