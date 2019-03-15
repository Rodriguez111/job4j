package ru.job4j.filesearch;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;

public class FileSearch {
private final Args args;
private Output output;

    public FileSearch(Args args) {
        this.args = args;
        initOutputType();
    }

    private void initOutputType() {
        output = args.getOutputFilePath() == "" ? new ConsoleOutput() : new FileOutput(new File(args.getOutputFilePath()));
        try {
            if (args.getOutputFilePath() == "") {
                new File(args.getOutputFilePath()).createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<File> searchFiles() {
        List<File> listOfFiles = new ArrayList<>();
        Queue<File> queue = new LinkedList<>();
        queue.offer(new File(args.getRoodDir()));
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
                DirectoryStream<Path> ds = Files.newDirectoryStream(currentDir, args.getSearchTypes().get(args.getSearchPatternType()));
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
}
