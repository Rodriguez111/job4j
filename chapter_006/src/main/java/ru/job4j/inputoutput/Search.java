package ru.job4j.inputoutput;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

public class Search {
    List<File> files(String parent, List<String> extensions) {
        Set<String> extensionsSet = extensions.stream().collect(Collectors.toSet());
        List<File> listOfFiles = new ArrayList<>();
        Queue<File> queue = new LinkedList<>();
        queue.offer(new File(parent));
        while (!queue.isEmpty()) {
            File file = queue.poll();
            for (File eachFile : file.listFiles()) {
                if (eachFile.isDirectory()) {
                    queue.offer(eachFile);
                } else {
                    if (extensionsSet.contains(eachFile.getName().substring(eachFile.getName().lastIndexOf(".") + 1))) {
                        listOfFiles.add(eachFile);
                    }
                }
            }
        }
        return listOfFiles;
    }

}
