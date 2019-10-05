package ru.job4j.todolist.controller;

import org.json.JSONObject;
import ru.job4j.todolist.models.Item;
import ru.job4j.todolist.storage.ItemStorage;
import ru.job4j.todolist.storage.Storage;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

public class ValidateItems implements Validator {
    private static final Validator INSTANCE = new ValidateItems();

    private final Storage storage = ItemStorage.getINSTANCE();

    private ValidateItems() {
    }

    public static Validator getINSTANCE() {
        return INSTANCE;
    }

    @Override
    public void add(String description) {
        Item item = new Item();
        item.setDescription(description);
        item.setCreateDate(formatTime(new Timestamp(System.currentTimeMillis())));
        storage.addItem(item);
    }

    private String formatTime(Timestamp timestamp) {
        SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-dd hh:mm:ss");
        return format.format(timestamp);
    }

    @Override
    public JSONObject getItems(boolean onlyUndone) {
        List<Item> resultList = storage.getAllItems(onlyUndone);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("listOfItems", resultList);
        return jsonObject;
    }
}
