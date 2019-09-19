package cinema.controller;

import cinema.persistence.models.Place;
import cinema.persistence.storage.DBHall;
import cinema.persistence.storage.HallStorage;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ValidateHall implements HallValidator {
    private static final Logger LOG = LoggerFactory.getLogger(ValidateHall.class);
    private final HallStorage hallStorage = DBHall.getINSTANCE();
    private final static ValidateHall INSTANCE = new ValidateHall();

    private ValidateHall() {
    }

    public static ValidateHall getINSTANCE() {
        return INSTANCE;
    }

    @Override
    public JSONObject getHallInfo() {
        LOG.info("Enter method");
        List<Place> listOfPlaces = hallStorage.getHallInfo();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("listOfPlaces", listOfPlaces);
        LOG.info("Exit method");
        return jsonObject;
    }

    @Override
    public JSONObject clearHall() {
        LOG.info("Enter method");
        JSONObject jsonObject = new JSONObject();
        String result = hallStorage.clearHall();
        jsonObject.put("clearHallResult", result);
        LOG.info("Exit method");
        return jsonObject;

    }

}
