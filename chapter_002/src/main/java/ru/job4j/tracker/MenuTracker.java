package ru.job4j.tracker;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public class MenuTracker {
    private Input input;
    private int position;
    private ITracker iTracker;
    private UserAction[] actions = new UserAction[7];
    private boolean isRunning = true;
    private final Consumer<String> output;

    public boolean isRunning() {
        return isRunning;
    }

    public int availableKeys() {
        return actions.length;
    }

    public MenuTracker(Input input, ITracker iTracker, Consumer<String> output) {
        this.input = input;
        this.iTracker = iTracker;
        this.output = output;
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
     * @param userAction - action class instance to add.
     */
    public void addAction(UserAction userAction) {
        this.actions[position++] = userAction;
    }


    /**
     * Shows main menu of the program.
     */
    public void showMenu(Consumer<String> consumer) {
        for (UserAction eachAction : this.actions) {
            consumer.accept(eachAction.info());
            //System.out.println(eachAction.info());
        }
    }

    /**
     * Selects the menu item corresponding to the passed key and executes it.
     *
     * @param key - passed key of the menu item.
     */
    public void selectKey(int key) {
        if (key >= 0 && key < actions.length) {
            this.actions[key].execute(input, iTracker, output);
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
        public void execute(Input input, ITracker iTracker, Consumer<String> output) {
            String itemName = input.ask("Please, enter the name of new item: ");
            String itemDescr = input.ask("Please, enter the description of new item: ");
            Item newItem = new Item(itemName, itemDescr);
            iTracker.add(newItem);
            output.accept(String.format("----- Новая заявка ID:%s добавлена -----", newItem.getId()));

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
        public void execute(Input input, ITracker iTracker, Consumer<String> output) {
            List<Item> allItems = iTracker.findAll();
            output.accept(String.format("----- Список всех заявок: -----"));
            for (Item eachItem : allItems) {
                output.accept(String.format(eachItem.toString()));

            }
            output.accept(String.format("----- Конец списка -----"));
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
        public void execute(Input input, ITracker iTracker, Consumer<String> output) {
            String idToReplace = input.ask("Please, enter the id of the item You want to edit: ");

            Item item = iTracker.findById(idToReplace);
            if (item != null) {
                String itemName = input.ask("Please, enter the name of new item: ");
                String itemDescr = input.ask("Please, enter the description of new item: ");
                Item newItem = new Item(itemName, itemDescr);
                newItem.setId(idToReplace);
                if (iTracker.replace(idToReplace, newItem)) {
                    output.accept(String.format("----- Заявка ID:%s отредактирована -----", idToReplace));
                } else {
                    output.accept(String.format("----- Не удалось отредактировать заявку ID:%s -----", idToReplace));
                }
            } else {
                output.accept(String.format("----- Несуществующий ID:%s -----", idToReplace));
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
        public void execute(Input input, ITracker iTracker, Consumer<String> output) {
            output.accept(String.format("----- Удаление заявки -----"));
            String idToDelete = input.ask("Please, enter the id of the item You want to delete: ");
            Item item = iTracker.findById(idToDelete);
            if (item != null) {
                if (iTracker.delete(idToDelete)) {
                    output.accept(String.format("----- Заявка ID:%s удалена -----", idToDelete));
                } else {
                    output.accept(String.format("----- Не удалось удалить заявку ID:%s -----", idToDelete));
                }
            } else {
                output.accept(String.format("----- Несуществующий ID:%s -----", idToDelete));
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
        public void execute(Input input, ITracker iTracker, Consumer<String> output) {
            output.accept(String.format("----- Поиск заявки по ID: -----"));
            String idToFind = input.ask("Please, enter the id of the item You want to find: ");
            Optional<Item> itemOptional = Optional.ofNullable(iTracker.findById(idToFind));
            if (itemOptional.isPresent()) {
                output.accept(String.format(itemOptional.get().toString()));
            } else {
                output.accept("Item is not found");
            }
            //Item foundItem = iTracker.findById(idToFind);
            //output.accept(String.format(foundItem.toString()));
            output.accept(String.format("----- Поиск завершен -----"));
        }
    }

    /**
     * Finds ru.job4j.ru.job4j.list of items by name
     */
    class FindItemByName extends BaseAction {
        public FindItemByName(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, ITracker iTracker, Consumer<String> output) {
            output.accept(String.format("----- Поиск заявок по названию: -----"));
            String nameToFind = input.ask("Please, enter the name of the item You want to find: ");
            List<Item> allItems = iTracker.findByName(nameToFind);
            for (Item eachItem : allItems) {
                output.accept(String.format(eachItem.toString()));
            }
            output.accept(String.format("----- Поиск завершен -----"));
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
        public void execute(Input input, ITracker iTracker, Consumer<String> output) {
            isRunning = false;
            output.accept(String.format("Program complete."));
        }

    }

}
