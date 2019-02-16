package ru.job4j.inputoutput.filemanager;

import java.io.File;
import java.util.List;

public class FileManager  {
    FileBrowser fileBrowser = new FileBrowser();
    FileLoader fileLoader = new FileLoader();


    public List<File> enterDirectory(File directory) {
        System.out.println("Entering dir");
       return fileBrowser.goDown(directory);
    }

    public List<File> exitDirectory(File directory) {
        System.out.println("Exiting dir");
        return fileBrowser.goUp(directory);
    }




}
