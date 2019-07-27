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
    // private final static Store USER_STORE = UserStore.getInstance();
    private final static Store USER_STORE = DBStore.getInstance();
    private final static ValidateService INSTANCE = new ValidateService();
    private static final Logger LOG = LoggerFactory.getLogger(ValidateService.class);

    private ValidateService() {
    }

    public static ValidateService getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean add(HttpServletRequest request) {
        boolean result = false;
        Optional<User> optionalUser = createUser(request);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (USER_STORE.add(user)) {
                result = true;
                LOG.info("New user added successfully");
            }
        }
        return result;
    }


    @Override
    public boolean update(HttpServletRequest request) {
        boolean result = true;
        int id = 0;
        try {
            id = Integer.valueOf(request.getParameter("id"));
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
        return result;
    }

    @Override
    public boolean delete(HttpServletRequest request) {
        boolean result;
        int id = 0;
        try {
            id = Integer.valueOf(request.getParameter("id"));
            User user = findById(id);
            if (user != null) {
                result = USER_STORE.delete(user);
            } else {
                result = false;
            }
        } catch (NumberFormatException e) {
            result = false;
        }
        if (result) {
            LOG.info("TestUser with ID = " + id + " deleted successfully");
        }
        return result;
    }

    @Override
    public List<User> findAll() {
        return USER_STORE.findAll();
    }

    @Override
    public User findById(int id) {
        return USER_STORE.findById(id);
    }


    private Optional<User> createUser(HttpServletRequest request) {
        Optional<User> optionalUser = Optional.empty();
        String userName = request.getParameter("name");
        String userLogin = request.getParameter("login");
        String userPass = request.getParameter("password");
        String userEmail = request.getParameter("email");
        String userCreateDate = formatDate();
        if (nonNullCheck(userName) && nonNullCheck(userLogin) && nonNullCheck(userPass) && nonNullCheck(userEmail)) {
            optionalUser = Optional.of(new User(userName, userLogin, userPass, userEmail, userCreateDate));
        }
        return optionalUser;
    }

    private boolean updateUser(HttpServletRequest request, User user) {
        boolean result = true;
        if (nonNullCheck(request.getParameter("name"))) {
            user.setName(request.getParameter("name"));
        }
        if (nonNullCheck(request.getParameter("login"))) {
            user.setLogin(request.getParameter("login"));
        }
        if (nonNullCheck(request.getParameter("password"))) {
            user.setLogin(request.getParameter("password"));
        }
        if (nonNullCheck(request.getParameter("email"))) {
            user.setEmail(request.getParameter("email"));
        }
        List<User> listOfUsers = USER_STORE.findAll();
        if (listOfUsers.contains(user)) {
            result = false;
        }
        return result;
    }

    @Override
    public boolean isCredential(String login, String password) {
        boolean result = false;
        for (User eachUser : USER_STORE.findAll()) {
            if(eachUser.getLogin().equals(login) && eachUser.getPassword().equals(password)) {
                result = true;
                break;
            }
        }
        return result;
    }

    public String formatDate() {
        Date rawDate = new Date(System.currentTimeMillis());
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.YYYY HH:mm:ss");
        return dateFormat.format(rawDate);
    }

    public boolean nonNullCheck(String field) {
        return field != null && !field.isEmpty();
    }
}