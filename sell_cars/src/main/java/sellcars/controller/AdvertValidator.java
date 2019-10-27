package sellcars.controller;


import sellcars.models.Advert;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface AdvertValidator {

    String addAdvert(int carId, double price, List<String> listOfPhotos, String userLogin);

    String setSoldStatus(int advertId);

    String deleteAdvert(int id);

    String getAllAdverts();

    String getAdvertById(int id);

    String getAdvertByFilters(String jsonFromClient);

}
