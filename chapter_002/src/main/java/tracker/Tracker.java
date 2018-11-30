package tracker;

import java.util.Random;

public class Tracker {
    private int position = 0;
    private static final Random RANDOM = new Random();
    private Item[] items = new Item[100];

    public Item add(Item item) {
        item.setId(generateID());
        items[position++] = item;
        return item;
    }

    public void replace(String id, Item item) {

    }

    public void delete(String id) {

    }

    public Item[] getAll() {
    Item[] allItems = new Item[this.position];
    for (int i = 0; i <= this.position; i++){
        allItems[i] =  items[i];
    }
    return allItems;
    }

    public Item[] findByName(String key) {

    }

    public Item findById(String id) {

    }

    private String generateID() {
        return String.valueOf(System.currentTimeMillis() + RANDOM.nextInt(100));
    }


}
