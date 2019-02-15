package ru.job4j.inputoutput.filemanager;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileBrowser implements FileBrowse {

    @Override
    public List<File> goDown(File currentDirectory) {
        List<File> listOfFiles = new ArrayList<>();
        if (currentDirectory.isDirectory()) {
            listOfFiles = Arrays.asList(currentDirectory.listFiles());
        }
        return listOfFiles;
    }

    @Override
    public List<File> goUp(File currentDirectory) {
        List<File> listOfFiles = new ArrayList<>();
        File parent;
        if (currentDirectory.getParent() != null) {
            parent = new File(currentDirectory.getParent());
            listOfFiles = Arrays.asList(parent.listFiles());
        }
            return listOfFiles;
    }
}
