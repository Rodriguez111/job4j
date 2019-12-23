package sellcars.service;


import java.util.List;

public interface AdvertValidator {

    String addAdvert(int carId, double price, List<String> listOfPhotos, String userLogin);

    String setSoldStatus(int advertId);

    String getAllAdverts();

    String getAdvertById(int id);

    String getAdvertByFilters(String jsonFromClient);

}
