package ru.job4j.filesearch;

import ru.job4j.filesearch.argparser.RArgument;
import ru.job4j.filesearch.argparser.RCommand;
import ru.job4j.filesearch.argparser.RCommandManager;

public class ArgsManager {
    private RCommandManager rCommandManager;

    public ArgsManager(String[] arguments) {
        this.rCommandManager = new RCommandManager(arguments);
        initOptions();
    }

    private final String sourceDirectory = "-d";
    private final String sourceDirectoryDescription = "input source directory";
    private final String pattern = "-n";
    private final String patternDescription = "input search pattern (string)";

    private final String outputToFile = "-o";
    private final String outputToDescription = "input file name for output";


    public void initOptions() {
        RCommand sourceDirectoryOption = new RCommand(sourceDirectory, sourceDirectoryDescription, true, true, 1);
        RCommand patternOption = new RCommand(pattern, patternDescription, true, true, 2);
        patternOption.addPossibleArgs(2, new RArgument("--f", "search by full file name;"));
        patternOption.addPossibleArgs(2, new RArgument("--m", "search by mask;"));
        patternOption.addPossibleArgs(2, new RArgument("--r", "search by regular expression;"));
        RCommand outputToFileOption = new RCommand(outputToFile, outputToDescription, true, false, 1);
        rCommandManager.addOption(sourceDirectoryOption);
        rCommandManager.addOption(patternOption);
        rCommandManager.addOption(outputToFileOption);
        rCommandManager.parseArguments();
    }


    public String getRootDir() {
        String result = rCommandManager.getArgument("-d", 1);
       return result.substring(2);
    }

    public String getSearchPattern() {
        String result = rCommandManager.getArgument("-n", 1);
        return result.substring(2);
    }

    public String getSearchPatternType() {
        String result = rCommandManager.getArgument("-n", 2);
        return result.substring(2);
    }

    public String getOutputFilePath() {
        String result = rCommandManager.getArgument("-o", 1);
        if (result.length() > 1) {
          result = result.substring(2);
        }
        return result;
    }

}
