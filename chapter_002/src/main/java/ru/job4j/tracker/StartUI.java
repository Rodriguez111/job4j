package ru.job4j.tracker;

public class StartUI {
    private final Input input;
    private final Tracker tracker;
    private static final String[] MENU =
    {"0 - Add new Item",
     "1 - Show all items",
     "2 - Edit item",
     "3 - Delete item",
     "4 - Find item by Id",
     "5 - Find items by name",
     "6 - Exit Program",
    };

    private static final String ADD = "0";
    private static final String SHOW = "1";
    private static final String EDIT = "2";
    private static final String DELETE = "3";
    private static final String FIND_BY_ID = "4";
    private static final String FIND_BY_NAME = "5";
    private static final String EXIT = "6";



    public StartUI() {
        this.input = new ConsoleInput();
        this.tracker = new Tracker();
    }

    public StartUI(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
        init();
    }

    private void loop() {
        boolean exit = false;
        while (!exit) {

            for (String eachItemOfMenu : MENU) {
                System.out.println(eachItemOfMenu);
            }
            String choice = input.ask("Please, enter Your choice: ");
            switch (choice) {
                case ADD:
                    add();
                    break;

                case SHOW:
                    show();
                    break;

                case EDIT:
                    edit();
                    break;

                case DELETE:
                    delete();
                    break;

                case FIND_BY_ID:
                    findById();
                    break;

                case FIND_BY_NAME:
                    findByName();
                    break;

                case EXIT:
                    exit = true;
                    System.out.println("Программа завершена.");
                    break;

                default:
                    System.out.println("Выберите пункт из списка.");

            }
        }
    }

    /**
     * initiating main logic.
     */
    public void init() {
        loop();
    }

    public static void main(String[] args) {
        Input input = new ConsoleInput();
        new StartUI().init();
    }

    /**
     * adding new item.
     */
    private void add() {
        System.out.println("----- Добавление новой заявки -----");
        String itemName = input.ask("Please, enter the name of new item: ");
        String itemDescr = input.ask("Please, enter the description of new item: ");
        Item newItem = new Item(itemName, itemDescr);
        tracker.add(newItem);
        System.out.printf("----- Новая заявка ID:%s создана -----\n", newItem.getId());
    }

    /**
     * show all available items.
     */
    private void show() {
        System.out.println("----- Список всех заявок: -----");
       Item[] allItems = tracker.getAll();
       for (Item eachItem : allItems) {
           printItem(eachItem.getId(), eachItem.getName(), eachItem.getDescription());
       }
        System.out.println("----- Конец списка -----");
    }

    /**
     * edit item by id.
     */
    private void edit() {
        System.out.println("----- Редактирование заявки -----");
        String idToReplace = input.ask("Please, enter the id of the item You want to replace: ");
        String itemName = input.ask("Please, enter the name of new item: ");
        String itemDescr = input.ask("Please, enter the description of new item: ");
        Item newItem = new Item(itemName, itemDescr);
        newItem.setId(idToReplace);
        if (tracker.replace(idToReplace, newItem)) {
            System.out.printf("----- Заявка ID:%s отредактирована -----\n", idToReplace);
        } else {
            System.out.printf("----- Не удалось отредактировать заявку ID:%s -----\n", idToReplace);
        }
    }

    /**
     * delete item by id.
     */
    private void delete() {
        System.out.println("----- Удаление заявки -----");
        String idToDelete = input.ask("Please, enter the id of the item You want to delete: ");
        if (tracker.delete(idToDelete)) {
            System.out.printf("----- Заявка ID:%s удалена -----\n", idToDelete);
        } else {
            System.out.printf("----- Не удалось удалить заявку ID:%s -----\n", idToDelete);
        }
    }

    /**
     * find item by id.
     */
    private void findById() {
        System.out.println("----- Поиск заявки по ID: -----");
        String idToFind = input.ask("Please, enter the id of the item You want to find: ");
        Item foundItem = tracker.findById(idToFind);
        printItem(foundItem.getId(), foundItem.getName(), foundItem.getDescription());
        System.out.println("----- Поиск завершен -----");
    }

    /**
     * find item by name.
     */
    private void findByName() {
        System.out.println("----- Поиск заявок по названию: -----");
        String nameToFind = input.ask("Please, enter the name of the item You want to find: ");
        Item[] allItems = tracker.findByName(nameToFind);
        for (Item eachItem : allItems) {
            System.out.println(eachItem.getId() + " " + eachItem.getName() + " " + eachItem.getDescription());
        }
        System.out.println("----- Поиск завершен -----");
    }

    /**
     * Print item info.
     * @param id - id of the item.
     * @param name - name of the item.
     * @param description - description of the item.
     */
    private void printItem(String id, String name, String description) {
        System.out.println(id + " " + name + " " + description);
    }

}
