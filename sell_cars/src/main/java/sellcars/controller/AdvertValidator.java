package sellcars.controller;

import org.json.JSONObject;

import java.util.List;
import java.util.Map;

public interface AdvertValidator {

    String addAdvert(int carId, double price, List<String> listOfPhotos, String userLogin);

    String setSoldStatus(int advertId);

    String deleteAdvert(int id);

    JSONObject getAllAdverts();

    JSONObject getAdvertById(int id);

}
