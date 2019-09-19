package cinema.persistence.storage;

import cinema.persistence.models.Place;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

public interface HallStorage {
    void engagePlace(int row, int place, Connection connection) throws Exception;

    void releasePlace(int row, int place, Connection connection) throws Exception;

    List<Place> getHallInfo();

    String clearHall();
}
