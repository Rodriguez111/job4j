package ru.job4j.crudservlet.controller.logic;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.crudservlet.model.DBStore;
import ru.job4j.crudservlet.model.Store;

import java.util.List;

public class ValidateRoles implements RolesValidator {
    private static final RolesValidator INSTANCE = new ValidateRoles();
    private final static Store USER_STORE = DBStore.getInstance();
    private static final Logger LOG = LoggerFactory.getLogger(ValidateService.class);

    private ValidateRoles() {
    }

    public static RolesValidator getInstance() {
        return INSTANCE;
    }

    @Override
    public JSONObject getAllRoles() {
        List<String> listOfRoles = USER_STORE.getAllRoles();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("listOfRoles", listOfRoles);
        return jsonObject;
    }
}