package ru.job4j.filesearch.argparser;

public class CommandLineDemo {
    private final String sourceDirectory = "-d";
    private final String sourceDirectoryDescription = "input source directory";
    private final String pattern = "-n";
    private final String patternDescription = "input search pattern (string)";


    private final String outputToFile = "-o";
    private final String outputToDescription = "input file name for output";
   static RCommandManager rCommandManager;



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
    }

    public static void main(String[] args) {
        String args1 = "-d --c:/ -n --мои документы.txt --m -o --d:\\fileSearchlog.txt";




        rCommandManager = new RCommandManager(args);
        CommandLineDemo commandLineDemo = new CommandLineDemo();
        commandLineDemo.initOptions();
        commandLineDemo.rCommandManager.parseArguments();
        commandLineDemo.rCommandManager.printOptionsGuide();

    }

}
