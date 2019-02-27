package ru.job4j.inputoutput.filemanager;

import ru.job4j.inputoutput.filemanager.connection.Connection;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FileManager {
    private final File rootDirectory;
    private File currentDirectory;
    private final FileBrowser fileBrowser;
    private final FileLoader fileLoader;
    private final static String LINE_SEPARATOR = System.lineSeparator();
    private final static String DOUBLE_SEPARATOR = "==============================";
    private final static String SINGLE_SEPARATOR = "------------------------------";
    private final static String SLASH = "/";
    private final static String ROOT = "root/";

    public FileManager(File currentDirectory, Connection connection) {
        this.rootDirectory = currentDirectory;
        this.currentDirectory = currentDirectory;
        this.getAndSortDirectoryContent();
        fileBrowser = new FileBrowser();
        fileLoader = new FileLoader(connection);
    }


    public boolean enterDirectory(String directory) {
        boolean result = false;
        File dir = new File(currentDirectory.getAbsolutePath() + SLASH + directory);
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
        List<String> currentDirectoryContent =  getAndSortDirectoryContent();
        String relative = rootDirectory.toURI().relativize(currentDirectory.toURI()).getPath();
        StringBuilder sb = new StringBuilder();
        sb.append(DOUBLE_SEPARATOR + LINE_SEPARATOR);
        sb.append(ROOT + relative + LINE_SEPARATOR);
        sb.append(SINGLE_SEPARATOR + LINE_SEPARATOR);
        if (currentDirectoryContent.size() == 0) {
            sb.append("..." + LINE_SEPARATOR);
        } else {
            for (String eachFile : currentDirectoryContent) {
                sb.append(eachFile + LINE_SEPARATOR);
            }
        }
        sb.append(DOUBLE_SEPARATOR);
        return sb.toString();
    }

    private List<String> getAndSortDirectoryContent() {
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
        return listOfFiles.stream().map(File::getName).collect(Collectors.toList());
    }

    public boolean isFits(long fileSize) {
        return currentDirectory.getFreeSpace() > fileSize;
    }

    public boolean fileExistsInDirectory(String fileName) {
        String filePath = currentDirectory + SLASH + fileName;
        return fileBrowser.directoryContainsFile(currentDirectory, new File(filePath));
    }

    public void uploadFile(Connection connection, String filename, long fileSize) {
        String filePath = currentDirectory + SLASH + filename;
        File fileToReceive = new File(filePath);
        connection.streamToFile(fileToReceive, fileSize);
    }

    public void downloadFile(String filename) {
        String filePath = currentDirectory + SLASH + filename;
        File fileToSend = new File(filePath);
        fileLoader.sendFile(fileToSend);
    }

    public long getFileSize(String filename) {
        String filePath = currentDirectory + SLASH + filename;
        File file = new File(filePath);
        return file.length();
    }
}