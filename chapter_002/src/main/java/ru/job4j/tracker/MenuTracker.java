package ru.job4j.tracker;

public class MenuTracker  {
    private Input input;
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

    public void fillActions() {
        this.actions[0] = new AddItem();
        this.actions[1] = new MenuTracker.ShowItems();
        this.actions[2] = new EditItem();
        this.actions[3] = new DeleteItem();
        this.actions[4] = new FindItemById();
        this.actions[5] = new FindItemByName();
        this.actions[6] = new ExitProgram();

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

    private class AddItem implements UserAction {
        @Override
        public int key() {
            return 1;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            String itemName = input.ask("Please, enter the name of new item: ");
            String itemDescr = input.ask("Please, enter the description of new item: ");
            Item newItem = new Item(itemName, itemDescr);
            tracker.add(newItem);
            System.out.printf("----- Новая заявка ID:%s добавлена -----\n", newItem.getId());

        }

        @Override
        public String info() {
            return String.format("%s %s", this.key(), " - Add new item");
        }
    }

    /**
     * Shows all available items.
     */
    private static class ShowItems implements UserAction {
        @Override
        public int key() {
            return 2;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            Item[] allItems = tracker.getAll();
            System.out.println("----- Список всех заявок: -----");
            for (Item eachItem : allItems) {
                System.out.println(eachItem);
            }
            System.out.println("----- Конец списка -----");
        }

        @Override
        public String info() {
            return String.format("%s %s", this.key(), " - Show all items");
        }
    }

    /**
     * Assigns new item to selected id.
     */
    class EditItem implements UserAction {
        @Override
        public int key() {
            return 3;
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

        @Override
        public String info() {
            return String.format("%s %s", this.key(), " - Edit item by ID");
        }

    }

    /**
     * Deletes item by id.
     */
    class DeleteItem implements UserAction {
        @Override
        public int key() {
            return 4;
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

        @Override
        public String info() {
            return String.format("%s %s", this.key(), " - Delete item by ID");
        }
    }

    /**
     * Finds item by id.
     */
    class FindItemById implements UserAction {
        @Override
        public int key() {
            return 5;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("----- Поиск заявки по ID: -----");
            String idToFind = input.ask("Please, enter the id of the item You want to find: ");
            Item foundItem = tracker.findById(idToFind);
            System.out.println(foundItem);
            System.out.println("----- Поиск завершен -----");
        }

        @Override
        public String info() {
            return String.format("%s %s", this.key(), " - Find item by ID");
        }
    }

    /**
     * Finds list of items by name
     */
    class FindItemByName implements UserAction {
        @Override
        public int key() {
            return 6;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("----- Поиск заявок по названию: -----");
            String nameToFind = input.ask("Please, enter the name of the item You want to find: ");
            Item[] allItems = tracker.findByName(nameToFind);
            for (Item eachItem : allItems) {
                System.out.println(eachItem);
            }
            System.out.println("----- Поиск завершен -----");
        }

        @Override
        public String info() {
            return String.format("%s %s", this.key(), " - Find item by name");
        }
    }

    /**
     * Exits program.
     */
    class ExitProgram implements UserAction {
        @Override
        public int key() {
            return 7;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            isRunning = false;
            System.out.println("Program complete.");
        }

        @Override
        public String info() {
            return String.format("%s %s", this.key(), " - Exit programm");
        }
    }

}
