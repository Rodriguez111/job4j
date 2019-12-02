package sellcars.service;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import sellcars.models.User;
import sellcars.persistent.UserDB;
import sellcars.persistent.UserStorage;

@Component
public class ValidateUser implements UserValidator {


    private UserStorage userStorage;

    private PasswordEncoder passwordEncoder;

    private ValidateUser() {
    }


    @Transactional
    @Override
    public String addUser(JSONObject jsonFromClient) {
        String login = jsonFromClient.getString("login");
        String password = jsonFromClient.getString("password");
        String name = jsonFromClient.getString("name");
        String surname = jsonFromClient.getString("surname");
        String phone = jsonFromClient.getString("phone");
        String email = jsonFromClient.getString("email");

        password = passwordEncoder.encode(password);
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

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void setUserStorage(UserStorage userStorage) {
        this.userStorage = userStorage;
    }
}
