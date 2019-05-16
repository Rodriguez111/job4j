package ru.job4j.menu;




public class MenuInitiator {
    private final Menu rootMenu;
    private boolean isRunning = true;



    public MenuInitiator(Menu rootMenu) {
        this.rootMenu = rootMenu;
        initMenu();
    }

    private void initRootItems() {

        this.rootMenu.addItem("0.", new MenuItem("First"));
        this.rootMenu.addItem("0.", new MenuItem("Second"));
        this.rootMenu.addItem("0.", new MenuItem("Third"));
        this.rootMenu.addItem("0.", new MenuItem("Exit"));
    }

    private void initSubItems() {
        this.rootMenu.addItem("1.", new MenuItem("SubItem"));
        this.rootMenu.addItem("1.", new MenuItem("Another subItem"));
        this.rootMenu.addItem("1.", new MenuItem("One more subItem"));
        this.rootMenu.addItem("1.", new MenuItem("Just one more else subItem"));

        this.rootMenu.addItem("1.3.", new MenuItem("Sub subItem"));
        this.rootMenu.addItem("1.3.", new MenuItem("Another sub subItem"));
        this.rootMenu.addItem("1.4.", new MenuItem("This is sub subItem too"));
    }

    private void initActions() {
        this.rootMenu.addAction("1.", () -> System.out.println("Menu item is running..."));
        this.rootMenu.addAction("2.", () -> System.out.println("Another rootMenu item is running..."));

        this.rootMenu.addAction("4.", () -> {
            this.isRunning = false;
            System.out.println("Bye-bye");
        });

        this.rootMenu.addAction("1.1.", () -> System.out.println("SubItem is running..."));
        this.rootMenu.addAction("1.2.", () -> System.out.println("Another subItem is running..."));
        this.rootMenu.addAction("1.3.", () -> System.out.println("One more subItem is running..."));
        this.rootMenu.addAction("1.4.", () -> System.out.println("This is sub subItem too is running..."));

        this.rootMenu.addAction("1.3.1.", () -> System.out.println("Sub subItem is running..."));
        this.rootMenu.addAction("1.3.2.", () -> System.out.println("Another sub subItem is running..."));
        this.rootMenu.addAction("1.4.1.", () -> System.out.println("This is sub subItem too is running..."));

    }

    private void initMenu() {
        initRootItems();

        initSubItems();
        initActions();
    }

    public boolean isRunning() {
        return isRunning;
    }
}
