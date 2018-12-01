package tracker;

import java.util.Arrays;
import java.util.Random;

/**
 * Accounting claims.
 */

public class Tracker {
    private int position = 0;
    private static final Random RANDOM = new Random();
    private Item[] items = new Item[100];

    /**
     *
     * @param item - item to add.
     * @return - added item.
     */
    public Item add(Item item) {
        item.setId(generateID());
        items[position++] = item;
        return item;
    }

    /**
     *
     * @param id - id of the item we want to replace.
     * @param item - new item.
     */
    public void replace(String id, Item item) {
        for (int i = 0; i <= items.length; i++) {
            if (items[i] == null) {
                break;
            }
            if (items[i].getId().equals(id)) {
                items[i] = item;
            }
        }
    }

    /**
     *
     * @param id - id of the item we want to delete.
     */
    public void delete(String id) {
        for (int i = 0; i <= items.length; i++) {
            if (items[i] == null) {
                break;
            }
            if (items[i].getId().equals(id)) {
             System.arraycopy(items, i + 1, items, i, items.length - (i + 1));
             items[items.length - 1] = null;
             position--;
            }
        }
    }

    /**
     *
     * @return - all not-null items from the array.
     */
    public Item[] getAll() {
    Item[] allItems = new Item[this.position];
    for (int i = 0; i < this.position; i++) {
        allItems[i] =  items[i];
    }
    return allItems;
    }

    /**
     *
     * @param key - name we are looking for.
     * @return - array weth found items.
     */
    public Item[] findByName(String key) {
        Item[] foundNames = new Item[this.position];
        int count = 0;
        for (int i = 0; i < this.position; i++) {
            if (items[i].getName().equals(key)) {
                foundNames[count]  = items[i];
                count++;
            }
        }
        return Arrays.copyOf(foundNames, count);
    }

    /**
     *
     * @param id - id of the item we want to find.
     * @return - found item.
     */
    public Item findById(String id) {
        for (int i = 0; i < items.length; i++) {
            if (items[i] == null) {
                break;
            }
            if (items[i].getId().equals(id)) {
             return  items[i];
            }
        }
        return null;
    }

    /**
     *
     * @return - generated id.
     */
    private String generateID() {
        return String.valueOf(System.currentTimeMillis() + RANDOM.nextInt(100));
    }


}
