package ru.job4j.crudservlet.controller.logic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.crudservlet.User;
import ru.job4j.crudservlet.model.DBStore;
import ru.job4j.crudservlet.model.Store;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class ValidateService implements Validator {

    private static final Validator INSTANCE = new ValidateService();
    private final static Store USER_STORE = DBStore.getInstance();
    private static final Logger LOG = LoggerFactory.getLogger(ValidateService.class);

    private ValidateService() {
    }

    public static Validator getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean add(HttpServletRequest request) {
        LOG.info("Enter method");
        boolean result = false;
        String login = request.getParameter("login");
        if (USER_STORE.findIdByField(login, "login", "users") == -1) {
            Optional<User> optionalUser = createUser(request);
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                if (USER_STORE.add(user)) {
                    result = true;
                    LOG.info("New user added successfully");
                }
            }
        }
        LOG.info("Exit method");
        return result;
    }

    @Override
    public boolean update(HttpServletRequest request) {
        LOG.info("Enter method");
        boolean result = true;
        int id = 0;
        try {
            id = Integer.parseInt(request.getParameter("id"));
            User user = findById(id);
            if (user != null) {
                if (updateUser(request, user)) {
                    USER_STORE.update(id, user);
                    LOG.info("TestUser with ID = " + id + " updated successfully");
                }
            } else {
                result = false;
            }
        } catch (NumberFormatException e) {
            result = false;
        }
        LOG.info("Exit method");
        return result;
    }

    @Override
    public boolean delete(HttpServletRequest request) {
        LOG.info("Enter method");
        boolean result;
        int id = 0;
        try {
            id = Integer.valueOf(request.getParameter("id"));
            User advancedUser = USER_STORE.findById(id);
            if (advancedUser != null) {
                result = USER_STORE.delete(advancedUser);
            } else {
                result = false;
            }
        } catch (NumberFormatException e) {
            result = false;
        }
        if (result) {
            LOG.info("TestUser with ID = " + id + " deleted successfully");
        }
        LOG.info("Exit method");
        return result;
    }


    private Optional<User> createUser(HttpServletRequest request) {
        LOG.info("Enter method");
        Optional<User> optionalUser = Optional.empty();
        String name = request.getParameter("name");
        String login = request.getParameter("login");
        String role = request.getParameter("role");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String country = request.getParameter("country");
        String city = request.getParameter("city");
        String createDate = formatDate();
        if (nonNullCheck(name) && nonNullCheck(login) && nonNullCheck(role) && nonNullCheck(password)
                && nonNullCheck(email) && nonNullCheck(country) && nonNullCheck(city)) {
            optionalUser = Optional.of(new User(name, login, password, email, role, country, city, createDate));
        }
        LOG.info("Exit method");
        return optionalUser;
    }

    private boolean updateUser(HttpServletRequest request, User user) {
        LOG.info("Enter method");
        boolean result = false;
        if (nonNullCheck(request.getParameter("name"))) {
            user.setName(request.getParameter("name"));
            result = true;
        }
        if (nonNullCheck(request.getParameter("login"))) {
            user.setLogin(request.getParameter("login"));
            result = true;
        }
        if (nonNullCheck(request.getParameter("password"))) {
            user.setLogin(request.getParameter("password"));
            result = true;
        }
        if (nonNullCheck(request.getParameter("email"))) {
            user.setEmail(request.getParameter("email"));
            result = true;
        }
        if (nonNullCheck(request.getParameter("role"))) {
            user.setRole(request.getParameter("role"));
            result = true;
        }
        if (nonNullCheck(request.getParameter("country"))) {
            user.setCountry(request.getParameter("country"));
            result = true;
        }
        if (nonNullCheck(request.getParameter("city"))) {
            user.setCity(request.getParameter("city"));
            result = true;
        }
        LOG.info("Exit method");
        return result;
    }

    public String formatDate() {
        LOG.info("Enter method");
        Date rawDate = new Date(System.currentTimeMillis());
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.YYYY HH:mm:ss");
        LOG.info("Exit method");
        return dateFormat.format(rawDate);
    }


    @Override
    public List<User> findAll() {
        return USER_STORE.getAllUsers();
    }

    @Override
    public User findById(int id) {
        return USER_STORE.findById(id);
    }


    @Override
    public String isCredential(String login, String password) {
        String role = "";
        for (User eachUser : USER_STORE.getAllUsers()) {
            if (eachUser.getLogin().equals(login) && eachUser.getPassword().equals(password)) {
                role = eachUser.getRole();
                break;
            }
        }
        return role;
    }

    public boolean nonNullCheck(String field) {
        return field != null && !field.isEmpty();
    }
}
