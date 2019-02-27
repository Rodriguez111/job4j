package ru.job4j.inputoutput.filemanager.commands;

import ru.job4j.inputoutput.filemanager.connection.Connection;
import ru.job4j.inputoutput.filemanager.utils.ConsoleManager;

import java.util.ArrayList;
import java.util.List;

public class CommandHandler {
    final ConsoleManager consoleManager = new ConsoleManager();
    final List<MenuCommand> menu = new ArrayList<>();
    final Connection connection;

    public CommandHandler(Connection connection) {
        this.connection = connection;
        initMenu();
    }

    private void initMenu() {
        MenuCommand menuCommand1 = new MenuCommand(1, "Enter directory (input folder)");
        MenuCommand menuCommand2 = new MenuCommand(2, "Exit up from current directory");
        MenuCommand menuCommand3 = new MenuCommand(3, "Download file (input file name to download)");
        MenuCommand menuCommand4 = new MenuCommand(4, "Upload file here");
        MenuCommand menuCommand5 = new MenuCommand(5, "Exit program");

        menu.add(menuCommand1);
        menu.add(menuCommand2);
        menu.add(menuCommand3);
        menu.add(menuCommand4);
        menu.add(menuCommand5);
    }

    public void executeCommand(int commandIndex) {
        menu.get(commandIndex - 1).executeCommand();
    }


}
