package sellcars.controller;

import org.json.JSONObject;
import sellcars.models.Car;
import sellcars.models.User;

public interface UserValidator {

    String addUser(JSONObject jsonFromClient);

    String updateUser(JSONObject jsonFromClient);

    String deleteUser(int id);

    JSONObject authorizeUser(String login, String password);


}
