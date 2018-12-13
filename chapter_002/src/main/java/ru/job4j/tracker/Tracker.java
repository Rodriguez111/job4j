package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Accounting claims.
 */

public class Tracker {
    //private int position = 0;
    private String id = "0000000";
    private static final Random RANDOM = new Random();
    private List<Item> items = new ArrayList<>();

    /**
     *
     * @param item - item to add.
     * @return - added item.
     */
    public Item add(Item item) {
        item.setId(generateID());
        items.add(item);
        return item;
    }

    /**
     *
     * @param id - id of the item we want to replace.
     * @param item - new item.
     */
    public boolean replace(String id, Item item) {
        boolean success = false;
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getId().equals(id)) {
                items.remove(i);
                items.add(i, item);
                success = true;
                break;
            }
        }
        return success;
    }

    /**
     *
     * @param id - id of the item we want to delete.
     */
    public boolean delete(String id) {
        boolean success = false;
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getId().equals(id)) {
             items.remove(i);
             success = true;
             break;
            }
        }
        return success;
    }

    /**
     *
     * @return - all not-null items from the array.
     */
    public List<Item> getAll() {
    return this.items;
    }

    /**
     *
     * @param key - name we are looking for.
     * @return - array weth found items.
     */
    public List<Item> findByName(String key) {
        List<Item> foundNames = new ArrayList<>();
        int count = 0;
        for (int i = 0; i < this.items.size(); i++) {
            if (items.get(i).getName().equals(key)) {
                foundNames.add(items.get(i));
            }
        }
        return foundNames;
    }

    /**
     *
     * @param id - id of the item we want to find.
     * @return - found item.
     */
    public Item findById(String id) {
        for (int i = 0; i < this.items.size(); i++) {
            if (items.get(i).getId().equals(id)) {
             return  items.get(i);
            }
        }
        return null;
    }

    /**
     *
     * @return - generated id.
     */
    private String generateID() {
        //return String.valueOf(System.currentTimeMillis() + RANDOM.nextInt(100));
        int intId = Integer.parseInt(this.id);
        intId++;
        String newId = String.valueOf(intId);
        int length = newId.length();
        for (int i = 0; i < 7 - length; i++) {
            newId = "0" + newId;
        }
        this.id = newId;
        return newId;
    }


}
