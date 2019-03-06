package ru.job4j.filesearch.argparser;

import ru.job4j.filesearch.argparser.exceptions.ArgumentException;
import ru.job4j.filesearch.argparser.exceptions.CommandIsNotProvidedException;

import java.util.*;

public class RCommandManager {
   private final List<RCommand> listOfCommands = new ArrayList<>();
   private final String[] arguments;

    public RCommandManager(String[] arguments) {
        this.arguments = arguments;
    }

    public void addOption(RCommand option) {
        this.listOfCommands.add(option);
    }

    private int optionExists(String option) {
        int result = -1;
        for (int i = 0; i < listOfCommands.size(); i++) {
            if (listOfCommands.get(i).getName().equals(option)) {
                result = i;
                break;
            }
        }
        return result;
    }

    private boolean optionArgumentIsRequired(String option) {
        boolean result = false;
        for (RCommand eachElement : listOfCommands) {
            if (eachElement.getName().equals(option) && eachElement.isRequired()) {
                result = true;
                break;
            }
        }
        return result;
    }

    public void parseArguments() {
        List<String> lst = convertArgumentsToList();
        for (int i = 0; i < lst.size(); i++) {
            if (lst.get(i).startsWith("-")) {
                int optionIndex = optionExists(lst.get(i));
                if (optionIndex == -1) {
                    throw new CommandIsNotProvidedException("No such command was provided: " + lst.get(i));
                }
                int currentIndex = i;
                i++;
                while (i < lst.size() && lst.get(i).startsWith("--")) {
                    listOfCommands.get(optionIndex).addArgument(new RArgument(lst.get(i)));
                    i++;
                }
                i--;
                if (optionArgumentIsRequired(lst.get(currentIndex)) && !listOfCommands.get(optionIndex).hasArguments()) {
                    throw new ArgumentException("This command " + lst.get(currentIndex) + " requires arguments ");
                }
                listOfCommands.get(optionIndex).validateOption();
            }
        }
    }

    private List<String> convertArgumentsToList() {
        List<String> lst = new ArrayList<>();
        for (int i = 0; i < arguments.length; i++) {
            if ((arguments[i].charAt(0) == '-' && arguments[i].charAt(1) != '-')
            || (arguments[i].charAt(0) == '-' && arguments[i].charAt(1) == '-')) {
                lst.add(arguments[i]);
                continue;
            }
            String lastElement =  lst.get(lst.size() - 1);
            lst.remove(lst.size() - 1);
            lastElement = lastElement + " " + arguments[i];
            lst.add(lastElement);
        }
      return lst;
    }

    public void printOptionsGuide() {
        System.out.println("Commands and arguments help:");
        listOfCommands.forEach(RCommand::printDescription);
    }

    public String getArgument(String command, int argumentIndex) {
        String result = "";
        for (RCommand e : listOfCommands) {
            if (e.getName().equals(command)) {
                int count = 0;
                for (RArgument eachElement : e.getArguments()) {
                    if (count == argumentIndex - 1) {
                        result = eachElement.getValue();
                        break;
                    }
                    count++;
                }
            }
        }
        return result;
    }
}