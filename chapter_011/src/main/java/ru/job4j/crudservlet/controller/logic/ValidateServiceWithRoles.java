package ru.job4j.crudservlet.controller.logic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.crudservlet.AdvancedUser;
import ru.job4j.crudservlet.User;
import ru.job4j.crudservlet.model.DBStoreWithRoles;
import ru.job4j.crudservlet.model.Store;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

public class ValidateServiceWithRoles implements Validator {

    private static final Validator VALIDATOR = ValidateService.getInstance();
    private final static Store USER_STORE = DBStoreWithRoles.getInstance();
    private static final Logger LOG = LoggerFactory.getLogger(ValidateServiceWithRoles.class);

    @Override
    public boolean add(HttpServletRequest request) {
        boolean result = false;
        Optional<AdvancedUser> optionalUser = createUser(request);
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
            AdvancedUser advancedUser = (AdvancedUser)findById(id);
            if (advancedUser != null) {
                if (updateUser(request, advancedUser)) {
                    USER_STORE.update(id, advancedUser);
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
        return false;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public User findById(int id) {
        return null;
    }

    @Override
    public boolean isCredential(String login, String password) {
        return false;
    }

    private Optional<AdvancedUser> createUser(HttpServletRequest request) {
        Optional<AdvancedUser> optionalUser = Optional.empty();
        String userName = request.getParameter("name");
        String userLogin = request.getParameter("login");
        String role = request.getParameter("role");
        String userPass = request.getParameter("password");
        String userEmail = request.getParameter("email");
        String userCreateDate = formatDate();
        if (nonNullCheck(userName) && nonNullCheck(userLogin) && nonNullCheck(role) && nonNullCheck(userPass) && nonNullCheck(userEmail)) {
            optionalUser = Optional.of(new AdvancedUser(userName, userLogin, role, userPass, userEmail, userCreateDate));
        }
        return optionalUser;
    }

    private boolean updateUser(HttpServletRequest request, AdvancedUser user) {
        boolean result = true;
        if (nonNullCheck(request.getParameter("name"))) {
            user.setName(request.getParameter("name"));
        }
        if (nonNullCheck(request.getParameter("login"))) {
            user.setLogin(request.getParameter("login"));
        }
        if (nonNullCheck(request.getParameter("role"))) {
            user.setRole(request.getParameter("role"));
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
    public boolean nonNullCheck(String field) {
        return VALIDATOR.nonNullCheck(field);
    }

    @Override
    public String formatDate() {
        return VALIDATOR.formatDate();
    }


}
