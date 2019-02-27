package ru.job4j.inputoutput.filemanager.commands;

import java.util.function.Consumer;

public class MenuCommand {
    private int commandIndex;
    private String commandDescription;
    private Consumer<Integer> command;

    public MenuCommand(int commandIndex, String commandDescription) {
        this.commandIndex = commandIndex;
        this.commandDescription = commandDescription;
    }

   void executeCommand() {
       command.accept(this.commandIndex);
   }

    public void setCommand(Consumer<Integer> command) {
        this.command = command;
    }

    @Override
    public String toString() {
        return this.commandIndex + " - " + this.commandDescription + "; ";
    }
}
