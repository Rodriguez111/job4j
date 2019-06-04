package ru.job4j.cash.ver2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.ref.SoftReference;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

public class GetTextFromCash implements GetDataFromCash {
    private final Map<String, SoftReference<String>> cashedData = new HashMap<>();
    private final String rootDir;

    public GetTextFromCash(String rootDir) {
        this.rootDir = rootDir;
        cash();
    }

    @Override
    public String get(String key) {
        if (!cashedData.containsKey(key)) {
            throw new FileNotExistsException("File with such name does not exist.");
        }
        if (cashedData.get(key).get() == null) {
            cash();
        }

        return cashedData.get(key).get();
    }

    private void cash() {
        File directory = new File(rootDir);
        for (File eachFile : directory.listFiles()) {
            if (Files.isRegularFile(eachFile.toPath())) {
                cashedData.put(eachFile.getName(), new SoftReference<>(readFile(eachFile)));
            }
        }
    }

    private String readFile(File file) {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String readString = bufferedReader.readLine();
            while (readString != null) {
                sb.append(readString);
                readString = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
