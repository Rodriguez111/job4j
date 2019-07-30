package ru.job4j.crudservlet.controller.logic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.crudservlet.AdvancedUser;
import ru.job4j.crudservlet.User;
import ru.job4j.crudservlet.model.DBStoreWithRoles;
import ru.job4j.crudservlet.model.RolesStore;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ValidateServiceWithRoles implements Validator, ValidatorWithRole {

    private static final Validator VALIDATOR = ValidateService.getInstance();
    private static final ValidateServiceWithRoles INSTANCE = new ValidateServiceWithRoles();
    private final static RolesStore USER_STORE = DBStoreWithRoles.getInstance();
    private static final Logger LOG = LoggerFactory.getLogger(ValidateServiceWithRoles.class);

    private ValidateServiceWithRoles() {
    }

    public static ValidateServiceWithRoles getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean add(HttpServletRequest request) {
        boolean result = false;
        if (!USER_STORE.userExists(request.getParameter("login"))) {
            Optional<AdvancedUser> optionalUser = createUser(request);
            if (optionalUser.isPresent()) {
                AdvancedUser user = optionalUser.get();
                if (USER_STORE.addAdvUser(user)) {
                    result = true;
                    LOG.info("New user added successfully");
                }
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
            AdvancedUser advancedUser = findAdvUserById(id);
            if (advancedUser != null) {
                if (updateUser(request, advancedUser)) {
                    USER_STORE.updateAdvUser(id, advancedUser);
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
            AdvancedUser advancedUser = USER_STORE.findAdvUserById(id);
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
        return result;
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

    @Override
    public String isCredentialWithRole(String login, String password) {
        String role = "";
        for (AdvancedUser eachUser : findAllAdvUsers()) {
            if (eachUser.getLogin().equals(login) && eachUser.getPassword().equals(password)) {
                role = eachUser.getRole();
                break;
            }
        }
        return role;
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

    @Override
    public List<AdvancedUser> findAllAdvUsers() {
        return USER_STORE.findAll().stream().map(user -> ((AdvancedUser) user)).collect(Collectors.toList());
    }

    @Override
    public AdvancedUser findAdvUserById(int id) {
        return USER_STORE.findAdvUserById(id);
    }
}
