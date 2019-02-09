package ru.job4j.inputoutput;

import java.io.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class DropAbuses {

    void dropAbuses(InputStream in, OutputStream out, String[] abuse) {
        Set<String> abuseSet = new HashSet<>(Arrays.asList(abuse));
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out))) {
            while (reader.ready()) {
                String[] line = reader.readLine().split(" ");
                for (int i = 0; i <  line.length; i++) {
                    String strippedInput = line[i].replaceAll("[^А-яA-z0-9]", "");
                    if (abuseSet.contains(strippedInput)) {
                        line[i] = line[i].replaceAll("[А-яA-z]", "");
                    }
                    if (!reader.ready() && i == line.length - 1) {
                        writer.write(line[i]);
                    } else {
                        writer.write(line[i] + " ");
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
