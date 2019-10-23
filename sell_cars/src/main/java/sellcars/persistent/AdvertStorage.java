package sellcars.persistent;

import sellcars.models.Advert;
import sellcars.models.CarBody;

import java.util.List;
import java.util.Map;

public interface AdvertStorage {

    String add(Advert advert);

    void update(Advert advert);

    void delete(Advert advert);

    Advert findById(int id);

    List<Advert> getAll();

    List<Advert> findByFilter(Map<String, List<String>> params);

}
