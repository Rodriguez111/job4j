package ru.job4j.bootstrap;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class UserValidator implements Validator {

    private final static Storage STORAGE = UserStorage.getINSTANCE();

    private final static Validator INSTANCE = new UserValidator();

    private UserValidator() {
    }

    public static Validator getINSTANCE() {
        return INSTANCE;
    }

    @Override
    public void addUser(String user) {
        ObjectMapper mapper = new ObjectMapper();
        User newUser = null;
        try {
            newUser = mapper.readValue(user, User.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        STORAGE.addUser(newUser);
    }

    @Override
    public String getAllUsers() {
        ObjectMapper mapper = new ObjectMapper();
        String result = "";
        try {
            result = mapper.writeValueAsString(STORAGE.getAllUsers());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void removeUser(String user) {

    }


}
