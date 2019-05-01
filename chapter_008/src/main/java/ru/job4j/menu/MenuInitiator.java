package ru.job4j.menu;

public class MenuInitiator {
    private final Menu rootMenu;
    private boolean isRunning = true;

    private MenuItem menu1;
    private MenuItem menu2;
    private MenuItem menu3;
    private MenuItem menu4;

    public MenuInitiator(Menu rootMenu) {
        this.rootMenu = rootMenu;
        initMenu();
    }

    private void initRootItems() {
        this.menu1 = new MenuItem("Menu item");
        this.menu2 = new MenuItem("Another rootMenu item");
        this.menu3 = new MenuItem("One more rootMenu item");
        this.menu4 = new MenuItem("Exit");

        this.rootMenu.setItem(menu1);
        this.rootMenu.setItem(menu2);
        this.rootMenu.setItem(menu3);
        this.rootMenu.setItem(menu4);
    }

    private void init1LevelSubItems() {
        MenuItem level1Menu1 = new MenuItem("SubItem");
        MenuItem level1Menu2 = new MenuItem("Another subItem");
        MenuItem level1Menu3 = new MenuItem("One more subItem");
        MenuItem level1Menu4 = new MenuItem("Just one more else subItem");

        this.menu1.setSubMenu(level1Menu1);
        this.menu1.setSubMenu(level1Menu2);
        this.menu1.setSubMenu(level1Menu3);
        this.menu1.setSubMenu(level1Menu4);
    }

    private void init2LevelSubItems() {
        MenuItem level2Menu1 = new MenuItem("Sub subItem");
        MenuItem level2Menu2 = new MenuItem("Another sub subItem");
        MenuItem level2Menu3 = new MenuItem("This is sub subItem too");

        this.menu1.getSubMenu().get(2).setSubMenu(level2Menu1);
        this.menu1.getSubMenu().get(2).setSubMenu(level2Menu2);
        this.menu1.getSubMenu().get(3).setSubMenu(level2Menu3);
    }

    private void setRootActions() {
        this.menu1.setAction(menu1Action);
        this.menu2.setAction(menu2Action);
        this.menu3.setAction(menu3Action);
        this.menu4.setAction(exitAction);
    }

    private void set1LevelActions() {
        this.menu1.getSubMenu().get(0).setAction(level1Action1);
        this.menu1.getSubMenu().get(1).setAction(level1Action2);
        this.menu1.getSubMenu().get(2).setAction(level1Action3);
        this.menu1.getSubMenu().get(3).setAction(level1Action4);
    }

    private void set2LevelActions() {
        this.menu1.getSubMenu().get(2).getSubMenu().get(0).setAction(level2Action1);
        this.menu1.getSubMenu().get(2).getSubMenu().get(1).setAction(level2Action2);
        this.menu1.getSubMenu().get(3).getSubMenu().get(0).setAction(level2Action3);
    }

    private void initMenu() {
        initRootItems();
        init1LevelSubItems();
        init2LevelSubItems();

        setRootActions();
        set1LevelActions();
        set2LevelActions();
    }

    Action menu1Action = () -> {
        System.out.println("Menu item is running...");

    };

    Action menu2Action = () -> {
        System.out.println("Another rootMenu item is running...");
    };

    Action menu3Action = () -> {
        System.out.println("One more rootMenu item is running...");
    };

    Action exitAction = () -> {
        this.isRunning = false;
        System.out.println("Bye-bye");
    };

    Action level1Action1 = () -> {
        System.out.println("SubItem is running...");
    };

    Action level1Action2 = () -> {
        System.out.println("Another subItem is running...");
    };

    Action level1Action3 = () -> {
        System.out.println("One more subItem is running...");
    };

    Action level1Action4 = () -> {
        System.out.println("This is sub subItem too is running...");
    };

    Action level2Action1 = () -> {
        System.out.println("Sub subItem is running...");
    };

    Action level2Action2 = () -> {
        System.out.println("Another sub subItem is running...");
    };

    Action level2Action3 = () -> {
        System.out.println("This is sub subItem too is running...");
    };

    public boolean isRunning() {
        return isRunning;
    }
}
