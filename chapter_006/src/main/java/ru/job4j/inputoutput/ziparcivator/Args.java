package ru.job4j.inputoutput.ziparcivator;

import java.util.Arrays;
import java.util.List;

public class Args {
  private String[] args;

    public Args(String[] args) {
        this.args = args;
    }

    private final String sourceDirectory = "-d";
    private final String output = "-o";
    private final String extensions = "-e";


    public String directory() {
        return parseArguments(sourceDirectory);
   }
    public List<String> extensions() {
        String result = parseArguments(extensions);
        return Arrays.asList(result.split(" "));
    }

    public String output() {
        return parseArguments(output);
    }

    private String parseArguments(String parameter) {
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
