package ru.job4j.filesearch.argparser;

import ru.job4j.filesearch.argparser.exceptions.ArgumentException;

import java.util.*;

public class RCommand {
    /**
    Set of arguments which were parsed from command line.
     */
   private final Set<RArgument> arguments = new LinkedHashSet<>();

    /**
     * Map of possible valid arguments.
     * key - index number of argument of the option.
     * value - list of valid argument items corresponded to defined argument index.
     */
   private final Map<Integer, List<RArgument>> possibleValues = new HashMap<>();

   private String name;
   private String description;
   private boolean hasArgs;
   private boolean isRequired;
   private int numberOfRequiredArguments;

    public RCommand(String name, String description, boolean hasArgs, boolean isRequired, int numberOfRequiredArguments) {
        this.name = name;
        this.description = description;
        this.hasArgs = hasArgs;
        this.isRequired = isRequired;
        this.numberOfRequiredArguments = numberOfRequiredArguments;
    }

    public RCommand(String name, String description) {
        this.name = name;
        this.description = description;
    }


    public void addArgument(RArgument argument) {
        this.arguments.add(argument);
    }

    public String getName() {
        return name;
    }

    /**
     * This method add valid argument values for a certain ordinal value of the argument of the specified option.
     * This values  ​​allow you: 1) to validate input arguments. 2) To print info about possible args.
     * @param argumentIndex - index of argument for option.
     *        for example: option -d --c:/temp --m --z
     *                   --c:/temp - index = 1;
     *                   --m - index = 2;
     *                   --z - index = 3;
     * @param argumentPossibleValue = possible argument value
     *                  Example:
     *      addPossibleArgs(2, new RArgument("--f", "search by full file name;"));
     *      addPossibleArgs(2, new RArgument("--m", "search by mask;"));
     *      addPossibleArgs(2, new RArgument("--r", "search by regular expression;"));
     */
    public void addPossibleArgs(int argumentIndex, RArgument argumentPossibleValue) {
        possibleValues.computeIfPresent(argumentIndex, (key, list) -> {
            list.add(argumentPossibleValue);
            return list;
        });
        possibleValues.computeIfAbsent(argumentIndex, (key) -> {
            List<RArgument> lst = new ArrayList<>();
            lst.add(argumentPossibleValue);
            return lst;
        });
    }

    public boolean hasArguments() {
        return arguments.size() > 0;
    }

    public boolean isRequired() {
        return isRequired;
    }

    public void printDescription() {
        System.out.println(this.name + ": " + this.description + (isRequired ? " (required)" : " (optional)"));
        for (Map.Entry<Integer, List<RArgument>> eachEntry : possibleValues.entrySet()) {
            eachEntry.getValue().forEach(arg -> System.out.println(arg.getArgumentGuide()));
        }
    }

    public void validateOption() {
        if (numberOfRequiredArguments != arguments.size()) {
            throw new ArgumentException("Command " + name + " must have " + numberOfRequiredArguments + " argument(s)");
        }
        int count = 1;
        for (RArgument eachArg : arguments) {
            if (possibleValues.containsKey(count)) {
                if (!possibleValues.get(count).contains(eachArg)) {
                    throw new ArgumentException("Unknown argument: " + eachArg.getArgumentGuide());
                }
            }
            count++;
        }
    }

    public Set<RArgument> getArguments() {
        return arguments;
    }
}
