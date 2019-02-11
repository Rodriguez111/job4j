package ru.job4j.inputoutput;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

public class Search {


    /**
     *
     * @param parent - source directory.
     * @param extensions - file extensions to include or exclude.
     * @param include - true: include, false: exclude;
     * @return List of found files
     */

  public List<File> files(String parent, List<String> extensions, boolean include) {
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
                    if (include) {
                        if (extensionPresent(extensionsSet, eachFile)) {
                            listOfFiles.add(eachFile);
                        }
                    } else {
                        if (!extensionPresent(extensionsSet, eachFile)) {
                            listOfFiles.add(eachFile);
                        }

                    }
                }
            }
        }
        return listOfFiles;
    }

private boolean extensionPresent(Set<String> extensionsSet, File file) {
   return extensionsSet.contains(file.getName().substring(file.getName().lastIndexOf(".") + 1));
}



}
