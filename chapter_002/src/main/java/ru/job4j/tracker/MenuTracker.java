package ru.job4j.tracker;

import java.util.List;

public class MenuTracker  {
    private Input input;
    private int position;
    private Tracker tracker;
    private UserAction[] actions = new UserAction[7];
    private boolean isRunning = true;

    public boolean isRunning() {
        return isRunning;
    }

    public int availableKeys() {
        return actions.length;
    }

    public MenuTracker(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
       fillActions();
    }

    /**
     * fills array with actions.
     */

    public void fillActions() {
        addAction(new AddItem(1, "Add new item"));
        addAction(new MenuTracker.ShowItems(2, "Show all items"));
        addAction(new EditItem(3, "Edit item by ID"));
        addAction(new DeleteItem(4, "Delete item by ID"));
        addAction(new FindItemById(5, "Find item by ID"));
        addAction(new FindItemByName(6, "Find item by name"));
        addAction(new ExitProgram(7, "Exit program"));
    }

    /**
     *
     * @param userAction - action class instance to add.
     */
    public void addAction(UserAction userAction) {
        this.actions[position++] = userAction;
    }


    /**
     * Shows main menu of the program.
     */
    public void showMenu() {
        for (UserAction eachAction : this.actions) {
            System.out.println(eachAction.info());
        }
    }

    /**
     * Selects the menu item corresponding to the passed key and executes it.
     * @param key - passed key of the menu item.
     */
    public void selectKey(int key) {
        if (key >= 0 && key < actions.length) {
            this.actions[key].execute(input, tracker);
        }
    }

    /**
     * Adds new item to tracker.
     */

    private class AddItem extends BaseAction {

        public AddItem(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            String itemName = input.ask("Please, enter the name of new item: ");
            String itemDescr = input.ask("Please, enter the description of new item: ");
            Item newItem = new Item(itemName, itemDescr);
            tracker.add(newItem);
            System.out.printf("----- Новая заявка ID:%s добавлена -----\n", newItem.getId());
        }
    }

    /**
     * Shows all available items.
     */
    private static class ShowItems extends BaseAction {
        public ShowItems(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            List<Item> allItems = tracker.getAll();
            System.out.println("----- Список всех заявок: -----");
            for (Item eachItem : allItems) {
                System.out.println(eachItem);
            }
            System.out.println("----- Конец списка -----");
        }
    }

    /**
     * Assigns new item to selected id.
     */
    class EditItem extends BaseAction {
        public EditItem(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            String idToReplace = input.ask("Please, enter the id of the item You want to edit: ");
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

    }

    /**
     * Deletes item by id.
     */
    class DeleteItem extends BaseAction {
        public DeleteItem(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("----- Удаление заявки -----");
            String idToDelete = input.ask("Please, enter the id of the item You want to delete: ");
            if (tracker.delete(idToDelete)) {
                System.out.printf("----- Заявка ID:%s удалена -----\n", idToDelete);
            } else {
                System.out.printf("----- Не удалось удалить заявку ID:%s -----\n", idToDelete);
            }
        }

    }

    /**
     * Finds item by id.
     */
    class FindItemById extends BaseAction {
        public FindItemById(int key, String name) {
            super(key, name);
        }
        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("----- Поиск заявки по ID: -----");
            String idToFind = input.ask("Please, enter the id of the item You want to find: ");
            Item foundItem = tracker.findById(idToFind);
            System.out.println(foundItem);
            System.out.println("----- Поиск завершен -----");
        }
    }

    /**
     * Finds ru.job4j.list of items by name
     */
    class FindItemByName extends BaseAction {
        public FindItemByName(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("----- Поиск заявок по названию: -----");
            String nameToFind = input.ask("Please, enter the name of the item You want to find: ");
            List<Item> allItems = tracker.findByName(nameToFind);
            for (Item eachItem : allItems) {
                System.out.println(eachItem);
            }
            System.out.println("----- Поиск завершен -----");
        }

    }

    /**
     * Exits program.
     */
    class ExitProgram extends BaseAction {
        public ExitProgram(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            isRunning = false;
            System.out.println("Program complete.");
        }

    }

}
