package ru.job4j.inputoutput.filemanager;

import java.io.File;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class FileBrowser implements FileBrowse {




    @Override
    public boolean goDown(File currentDir, File directory) {
        boolean result = false;
        if (this.directoryContainsDirectory(currentDir, directory)) {
            result = true;
        }
        return result;
    }

    @Override
    public boolean goUp(File root, File currentDirectory) {
        boolean result = false;
        if (!root.equals(currentDirectory)) {
            result = true;
        }
            return result;
    }


    public boolean directoryContainsFile(File currentDir, File file){
        boolean result = false;
        List<File> current = Arrays.asList(currentDir.listFiles());
        Optional<File> optionalFile =  current.stream().filter(eachFile -> eachFile.equals(file)).findFirst();
        if(optionalFile.isPresent() && Files.isRegularFile(optionalFile.get().toPath())) {
            result = true;
        }
        return result;
    }

    private boolean directoryContainsDirectory(File currentDir, File directory){
        boolean result = false;
        List<File> current = Arrays.asList(currentDir.listFiles());
        Optional<File> optionalFile =  current.stream().filter(eachFile -> eachFile.equals(directory)).findFirst();
        if(optionalFile.isPresent() && optionalFile.get().isDirectory()) {
            result = true;
        }
        return result;
    }



}
