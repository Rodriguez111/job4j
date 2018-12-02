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
    public boolean replace(String id, Item item) {
        boolean success = false;
        for (int i = 0; i < position; i++) {
            if (items[i].getId().equals(id)) {
                items[i] = item;
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
        for (int i = 0; i < position; i++) {
            if (items[i].getId().equals(id)) {
             System.arraycopy(items, i + 1, items, i, items.length - (i + 1));
             items[items.length - 1] = null;
             position--;
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
    public Item[] getAll() {
    return Arrays.copyOf(items, position);
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
        for (int i = 0; i < position; i++) {
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
