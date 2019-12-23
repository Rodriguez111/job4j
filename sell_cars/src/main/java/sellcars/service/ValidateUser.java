package sellcars.service;

import org.json.JSONObject;
import sellcars.models.User;
import sellcars.persistent.UserDB;
import sellcars.persistent.UserStorage;

public class ValidateUser implements UserValidator {

    private final static UserValidator INSTANCE = new ValidateUser();
    private final UserStorage userStorage = UserDB.getINSTANCE();

    private ValidateUser() {
    }

    public static UserValidator getINSTANCE() {
        return INSTANCE;
    }

    @Override
    public String addUser(JSONObject jsonFromClient) {
        String login = jsonFromClient.getString("login");
        String password = jsonFromClient.getString("password");
        String name = jsonFromClient.getString("name");
        String surname = jsonFromClient.getString("surname");
        String phone = jsonFromClient.getString("phone");
        String email = jsonFromClient.getString("email");
        User user = new User(login, password, name, surname, phone, email);
        return userStorage.add(user);
    }

    @Override
    public String updateUser(String jsonFromClient) {
        return null;
    }

    @Override
    public String deleteUser(int id) {
        return null;
    }

    @Override
    public JSONObject authorizeUser(String login, String password) {
        JSONObject jsonObject = new JSONObject();
        User user = userStorage.findUserByLogin(login);
        if (user.getLogin() != null) {
            if (!password.equals(user.getPassword())) {
                jsonObject.put("errorPassword", "Неверный пароль");
            } else {
                jsonObject.put("userName", user.getName());
                jsonObject.put("userSurname", user.getSurname());
            }
        } else {
            jsonObject.put("errorLogin", "Пользователя с таким логином не существует");
        }
        return jsonObject;
    }
}
