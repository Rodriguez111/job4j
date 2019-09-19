package cinema.controller;

import org.json.JSONObject;

public interface HallValidator {
    JSONObject getHallInfo();

    JSONObject clearHall();
}
