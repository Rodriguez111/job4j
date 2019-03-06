package ru.job4j.filesearch;


import ru.job4j.filesearch.exceptions.ExceptionsHandler;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;

public class FileSearch {
    private static String roodDir;
    private static String searchPattern;
    private static String searchPatternType;
    private static String outputFilePath;

    private final ExceptionsHandler eh = new ExceptionsHandler();
    private final Map<String, DirectoryStream.Filter<Path>> searchTypes = new HashMap<>();
    private Output output;


    private void initParameters() {
        initSearchType();
        initOutputType();
    }


    private void initSearchType() {
        SearchType searchType = new SearchType(searchPattern);
        searchTypes.put("f", searchType.getFullName());
        searchTypes.put("m", searchType.getMask());
        searchTypes.put("r", searchType.getRegular());    }

    private void initOutputType() {
        output = outputFilePath == "" ? new ConsoleOutput() : new FileOutput(new File(outputFilePath));
    }

    private List<File> searchFiles() {
        List<File> listOfFiles = new ArrayList<>();
        Queue<File> queue = new LinkedList<>();
        queue.offer(new File(roodDir));
        while (!queue.isEmpty()) {
            File file = queue.poll();
            Path currentDir = Paths.get(file.getPath());
                try {
                DirectoryStream<Path> dsSearchDir =  Files.newDirectoryStream(currentDir);
                    dsSearchDir.forEach(each -> {
                        if (Files.isDirectory(each)) {
                            queue.offer(each.toFile());
                        }
                    });
                    DirectoryStream<Path> ds = Files.newDirectoryStream(currentDir, searchTypes.get(searchPatternType));
                    ds.forEach(each -> listOfFiles.add(each.toFile().getAbsoluteFile()));
                } catch (AccessDeniedException e) {
                    System.out.println("Access to this element denied: " + currentDir);
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
        return listOfFiles;
    }

public void search() {
       output.output(searchFiles());
}

    public static void main(String[] args) {
        ArgsManager argsManager = new ArgsManager(args);
        roodDir = argsManager.getRootDir();
        searchPattern = argsManager.getSearchPattern();
        searchPatternType = argsManager.getSearchPatternType();
        outputFilePath = argsManager.getOutputFilePath();

       FileSearch fileSearch = new FileSearch();
       fileSearch.initParameters();
       fileSearch.search();
    }
}
