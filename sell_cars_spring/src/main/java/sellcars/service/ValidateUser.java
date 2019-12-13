package sellcars.service;

import javassist.ClassPath;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sellcars.config.CoreConfig;
import sellcars.models.User;

import sellcars.repository.UserRepository;


@Service
public class ValidateUser implements UserValidator {

    private PasswordEncoder passwordEncoder;

    private UserRepository userRepository;

    private ValidateUser() {
    }

    @Transactional
    @Override
    public String addUser(JSONObject jsonFromClient) {
        String result = "Пользователь с таким логином уже существует";
        String login = jsonFromClient.getString("login");
        String password = jsonFromClient.getString("password");
        String name = jsonFromClient.getString("name");
        String surname = jsonFromClient.getString("surname");
        String phone = jsonFromClient.getString("phone");
        String email = jsonFromClient.getString("email");

        password = passwordEncoder.encode(password);

        User existingUser = userRepository.findByLogin(login);
        if (existingUser == null) {

            User user = new User(login, password, name, surname, phone, email);
            userRepository.save(user);
            result = "OK";
        }
        return result;
    }

    @Override
    public String updateUser(String jsonFromClient) {
        return null;
    }


    @Override
    public String deleteUser(int id) {
        return null;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

}
