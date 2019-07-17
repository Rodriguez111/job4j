package ru.job4j.crudservlet.logic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.crudservlet.User;
import ru.job4j.crudservlet.store.Store;
import ru.job4j.crudservlet.store.UserStore;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class ValidateService implements Validator {
    private final static Store USER_STORE = UserStore.getInstance();
    private final static ValidateService INSTANCE = new ValidateService();
    private static final Logger LOG = LoggerFactory.getLogger(ValidateService.class);

    private ValidateService() {
    }

    public static ValidateService getInstance() {
        return INSTANCE;
    }

    @Override
    public synchronized boolean add(HttpServletRequest request) {
        boolean result = false;
        Optional<User> optionalUser = createUser(request);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (!userExist(user)) {
                USER_STORE.add(user);
                result = true;
                LOG.info("New user added successfully");
            }
        }

        return result;
    }

    @Override
    public synchronized boolean update(HttpServletRequest request) {
        boolean result = true;
        int id = 0;
        try {
            id = Integer.valueOf(request.getParameter("id"));
            User user = findById(id);
            if (user != null) {
                updateUser(request, user);
                LOG.info("User with ID = " + id + " updated successfully");
            } else {
                result = false;
            }
        } catch (NumberFormatException e) {
            result = false;
        }
        return result;
    }

    @Override
    public synchronized boolean delete(HttpServletRequest request) {
        boolean result = true;
        int id = 0;
        try {
            id = Integer.valueOf(request.getParameter("id"));
            User user = findById(id);
            if (user != null) {
                USER_STORE.delete(user);
                LOG.info("User with ID = " + id + " deleted successfully");
            } else {
                result = false;
            }
        } catch (NumberFormatException e) {
            result = false;
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

    private boolean userExist(User user) {
        return findAll().contains(user);
    }

    private Optional<User> createUser(HttpServletRequest request) {
        Optional<User> optionalUser = Optional.empty();
        String userName = request.getParameter("name");
        String userLogin = request.getParameter("login");
        String userEmail = request.getParameter("email");
        String userCreateDate = formatDate();
        if (nonNullCheck(userName) && nonNullCheck(userLogin) && nonNullCheck(userEmail)) {
            optionalUser = Optional.of(new User(userName, userLogin, userEmail, userCreateDate));
        }
        return optionalUser;
    }

    private void updateUser(HttpServletRequest request, User user) {
        if (nonNullCheck(request.getParameter("name"))) {
            user.setName(request.getParameter("name"));
        }
        if (nonNullCheck(request.getParameter("login"))) {
            user.setLogin(request.getParameter("login"));
        }
        if (nonNullCheck(request.getParameter("email"))) {
            user.setEmail(request.getParameter("email"));
        }
    }

    private String formatDate() {
        Date rawDate = new Date(System.currentTimeMillis());
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.YYYY HH:mm:ss");
        return dateFormat.format(rawDate);
    }

    private boolean nonNullCheck(String field) {
        return field != null && !field.isEmpty();
    }
}