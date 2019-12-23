package sellcars.service;


import org.json.JSONObject;

public interface UserValidator {

    String addUser(JSONObject jsonFromClient);

    String updateUser(String jsonFromClient);

    String deleteUser(int id);

    JSONObject authorizeUser(String login, String password);


}
