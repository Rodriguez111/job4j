package ru.job4j.inputoutput.ziparcivator;

import java.util.Arrays;
import java.util.List;

public class Args {
    private final String sourceDirectory = "-d";
    private final String output = "-o";
    private final String extensions = "-e";


    public String directory(String[] args) {
        return parseArguments(args, sourceDirectory);
   }
    public List<String> extensions(String[] args) {
        String result = parseArguments(args, extensions);
        return Arrays.asList(result.split(" "));
    }

    public String output(String[] args) {
        return parseArguments(args, output);
    }

    private String parseArguments(String[] args, String parameter) {
        String result = "";
        for (int i = 0; i <  args.length; i++) {
            if (args[i].equals(parameter)) {
                while (i < args.length - 1) {
                    result = result + args[++i] + " ";
                    if (i + 1 == args.length ||  args[i + 1].contains("-")) {
                        break;
                    }
                }
                break;
            }
        }
        return result.trim();
    }







}
