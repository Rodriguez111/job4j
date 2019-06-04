package ru.job4j.cash.ver1.cashmakers;

import java.io.File;
import java.lang.ref.SoftReference;
import java.nio.file.Files;
import java.util.*;

/**
 * Makes ru.job4j.cash of files that are in a given directory.
 */

public class FileCashMaker implements CashMaker<File> {
    private final String rootDir;

    public FileCashMaker(String rootDir) {
        this.rootDir = rootDir;
    }

    /**
     * Generates map with filenames as a key and soft reference to the file as a value.
     * @return - map with cashed files.
     */
    @Override
    public Map<String, SoftReference<File>> cash() {
        Map<String, SoftReference<File>> cashedData = new HashMap<>();
        File directory = new File(rootDir);
        for (File eachFile : directory.listFiles()) {
            if (Files.isRegularFile(eachFile.toPath())) {
                cashedData.put(eachFile.getName(), new SoftReference<File>(eachFile));
            }
        }
        return cashedData;
    }
}
