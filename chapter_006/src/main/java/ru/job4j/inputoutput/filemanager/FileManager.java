package ru.job4j.inputoutput.filemanager;

import ru.job4j.inputoutput.filemanager.connection.Connection;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FileManager {
    private final File rootDirectory;
    private List<String> currentDirectoryContent;
    private File currentDirectory;
    private final String ls = System.lineSeparator();
    private FileBrowser fileBrowser;
    private FileLoader fileLoader;
    private final String doubleSeparator = "==============================";
    private final String singleSeparator = "------------------------------";
    private final String slash = "/";

    public FileManager(File currentDirectory, Connection connection) {
        this.rootDirectory = currentDirectory;
        this.currentDirectory = currentDirectory;
        this.getAndSortDirectoryContent();
        fileBrowser = new FileBrowser();
        fileLoader = new FileLoader(connection);
    }


    public boolean enterDirectory(String directory) {
        boolean result = false;
        File dir = new File(currentDirectory.getAbsolutePath() + "/" + directory);
        if (fileBrowser.goDown(currentDirectory, dir)) {
            currentDirectory = dir;
            getAndSortDirectoryContent();
            result = true;
        }
        return result;
    }

    public boolean exitDirectory() {
        boolean result = false;
        if (fileBrowser.goUp(rootDirectory, currentDirectory)) {
            currentDirectory = currentDirectory.getParentFile();
            getAndSortDirectoryContent();
            result = true;
        }
        return result;
    }

    public String getListOfFiles() {
        getAndSortDirectoryContent();
        String relative = rootDirectory.toURI().relativize(currentDirectory.toURI()).getPath();
        StringBuilder sb = new StringBuilder();
        sb.append(doubleSeparator + ls);
        sb.append("root/" + relative + ls);
        sb.append(singleSeparator + ls);
        if (currentDirectoryContent.size() == 0) {
            sb.append("..." + ls);
        } else {
            for (String eachFile : currentDirectoryContent) {
                sb.append(eachFile + ls);
            }
        }
        sb.append(doubleSeparator);
        return sb.toString();
    }


    private void getAndSortDirectoryContent() {
        List<File> listOfFiles = Arrays.asList(currentDirectory.listFiles());
        listOfFiles.sort((File f1, File f2) -> {
            int result;
            if (f1.isDirectory() && !f2.isDirectory()) {
                result = -1;
            } else if (!f1.isDirectory() && f2.isDirectory()) {
                result = 1;
            } else {
                result = f1.compareTo(f2);
            }
            return result;
        });
        currentDirectoryContent = listOfFiles.stream().map(File::getName).collect(Collectors.toList());
    }


    public boolean isFits(long fileSize) {
        return currentDirectory.getFreeSpace() > fileSize;
    }

    public boolean fileExistsInDirectory(String fileName) {
        String filePath = currentDirectory + slash + fileName;
        return fileBrowser.directoryContainsFile(currentDirectory, new File(filePath));
    }


    public void uploadFile(Connection connection, String filename, long fileSize) {
        String filePath = currentDirectory + slash + filename;
        File fileToReceive = new File(filePath);
        fileLoader.receiveFile(fileToReceive);
    }

    public void downloadFile(String filename) {
        String filePath = currentDirectory + slash + filename;
        File fileToSend = new File(filePath);
        fileLoader.sendFile(fileToSend);
    }

    public long getFileSize(String filename) {
        String filePath = currentDirectory + slash + filename;
        File file = new File(filePath);
        return file.length();
    }
}