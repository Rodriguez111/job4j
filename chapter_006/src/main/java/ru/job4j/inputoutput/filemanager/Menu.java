package ru.job4j.inputoutput.filemanager;

import ru.job4j.inputoutput.filemanager.exceptions.ExceptionHandler;

import java.io.File;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class Menu {
    private final Map<Integer, String> menu = new  HashMap();
    private final Map<Integer, Method> menuOfCommands = new  HashMap();
    private final String ls = System.lineSeparator();


    public Menu() {
        handleException(this::initMenu);
    }

    private void initMenu() throws NoSuchMethodException {
        menu.put(1, "Enter directory (input folder)");
        menu.put(2, "Exit up from current directory");
        menu.put(3, "Upload file here");
        menu.put(4, "Download file (input file name to download)");
        menu.put(5, "Exit program");

        menuOfCommands.put(1, FileManager.class.getMethod("enterDirectory", File.class));
        menuOfCommands.put(2, FileManager.class.getMethod("exitDirectory", File.class));
    }

    public String printMenu () {
        StringBuilder sb = new StringBuilder();
        for(Map.Entry<Integer, String> eachEntry : menu.entrySet()) {
            sb.append(eachEntry.getKey() + " - " + eachEntry.getValue() + ls);
        }
        return sb.toString();
    }

    public Method getMethod(Integer menuItem) {
        return menuOfCommands.get(menuItem);
    }

    private void handleException(ExceptionHandler exceptionHandler) {
        try{
            exceptionHandler.handleException();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
