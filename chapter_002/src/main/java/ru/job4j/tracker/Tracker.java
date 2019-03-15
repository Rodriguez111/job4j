package ru.job4j.tracker;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Accounting claims.
 */

public class Tracker implements ITracker {
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
                items.set(i, item);
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
    public List<Item> findAll() {
    return this.items;
    }

    /**
     *
     * @param key - name we are looking for.
     * @return - array weth found items.
     */
    public List<Item> findByName(String key) {
       return items.stream().filter(item -> item.getName().equals(key)).collect(Collectors.toList());
    }

    /**
     *
     * @param id - id of the item we want to find.
     * @return - found item.
     */
    public Item findById(String id) {
        Item resultItem = null;
        Optional<Item> optionalItem = items.stream().filter(item -> item.getId().equals(id)).findFirst();
        if (optionalItem.isPresent()) {
            resultItem =   optionalItem.get();
        }
      return resultItem;
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
