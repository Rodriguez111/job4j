package ru.job4j.inputoutput.filemanager;

import ru.job4j.inputoutput.filemanager.connection.Connection;
import ru.job4j.inputoutput.filemanager.exceptions.ExceptionsHandler;
import ru.job4j.inputoutput.filemanager.utils.ConsoleManager;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class ServerCommands {
    private ExceptionsHandler exceptionHandler = new ExceptionsHandler();
    private final Map<Integer, String> menu = new  HashMap();
    private final Map<Integer, Method> menuOfCommands = new  HashMap();
    private final String ls = System.lineSeparator();
    private final String warning = "ERROR! ";
    private final String ok = "OK ";


    private final Connection connection;
    private final FileManager fileManager;


    public ServerCommands(Connection connection, FileManager fileManager) {
        exceptionHandler.handleException(this::initMenu);
        this.connection = connection;
        this.fileManager = fileManager;
    }

    private void initMenu() throws NoSuchMethodException {
        menu.put(1, "Enter directory (input folder)");
        menu.put(2, "Exit up from current directory");
        menu.put(3, "Upload file here");
        menu.put(4, "Download file (input file name to download)");
        menu.put(5, "Exit program");

        menuOfCommands.put(1, ServerCommands.class.getMethod("enterDirectoryCommand"));
        menuOfCommands.put(2, ServerCommands.class.getMethod("exitDirectoryCommand"));
        menuOfCommands.put(3, ServerCommands.class.getMethod("uploadFileCommand"));
        menuOfCommands.put(4, ServerCommands.class.getMethod("downloadFileCommand"));
        menuOfCommands.put(5, ServerCommands.class.getMethod("exitCommand"));
        menuOfCommands.put(101, ServerCommands.class.getMethod("sendListOfFilesAndMenu"));
    }



    public void enterDirectoryCommand() {
       String directoryToEnter = connection.read();
       if (fileManager.enterDirectory(directoryToEnter)) {
           sendOkMessageToClient("");
       } else {
           sendErrorMessageToClient(Messages.WRONG_DIR.getMessage());
       }
    }

    public void exitDirectoryCommand() {
        if (fileManager.exitDirectory()) {
            sendOkMessageToClient("");
        } else {
            sendErrorMessageToClient(Messages.CAN_NOT_GO_UP.getMessage());
        }
    }

    public void uploadFileCommand() {
        String messageFromClient = connection.read();
        if(!messageFromClient.equals(Messages.CLIENT_REJECTED.toString())) {
            long fileSize = Long.parseLong(messageFromClient); //1. receive file length
            String filename = connection.read(); //2. receive filename
            if (!fileManager.isFits(fileSize)) {
                sendErrorMessageToClient(Messages.TOO_LARGE_FILE.getMessage());
            } else if (fileManager.fileExistsInDirectory(filename)) {
                overWriteFile(filename, fileSize);
            } else {
                transferFileToServer(filename, fileSize);
            }
        }
    }

    public void downloadFileCommand() {
        String filename = connection.read();
        if (fileManager.fileExistsInDirectory(filename)) {
            long fileSize = fileManager.getFileSize(filename);
            connection.write(fileSize + Messages.END_OF_FRAME.getMessage());
            String clientMessage = connection.read(); //If wrong directory from client side
            if(!clientMessage.equals(Messages.CLIENT_REJECTED.toString())) {
                clientMessage = connection.read(); //If remote file exists.
                if(clientMessage.equals(Messages.FILE_ALREADY_EXISTS.toString())) {
                    overWriteRemoteFile(filename);
                } else {
                    transferFileToClient(filename);
                }
            }
        } else {
            connection.write(Messages.WRONG_FILE.toString() + Messages.END_OF_FRAME.getMessage());
        }
    }


    private void overWriteFile(String filename, long fileSize) {
        connection.write(Messages.FILE_ALREADY_EXISTS.toString() + Messages.END_OF_FRAME.getMessage());
       String messageFromClient = connection.read();
        if (messageFromClient.equals("y")) {
            transferFileToServer(filename, fileSize);
        }
    }

    public void exitCommand () {
        ConsoleManager.print(Messages.EXIT_COMMAND_SERVER.getMessage());
    }


    private void overWriteRemoteFile(String fileName) {
      String clientMessage = connection.read();
      if(clientMessage.equals("y")) {
          transferFileToClient(fileName);
      }
    }

    private void transferFileToClient(String fileName) {
        sendOkMessageToClient(Messages.FILE_TRANSFER_START.getMessage());
        fileManager.downloadFile(connection, fileName);
        sendOkMessageToClient(Messages.FILE_DOWNLOAD_SUCCESS.getMessage());
    }

    private void transferFileToServer(String fileName, long fileSize) {
        sendOkMessageToClient(Messages.FILE_TRANSFER_START.getMessage());
        fileManager.uploadFile(connection, fileName, fileSize);
        sendOkMessageToClient(Messages.FILE_UPLOAD_SUCCESS.getMessage());
    }




    public String printMenu() {
        StringBuilder sb = new StringBuilder();
        for(Map.Entry<Integer, String> eachEntry : menu.entrySet()) {
            sb.append(eachEntry.getKey() + " - " + eachEntry.getValue() +"; ");
        }
        sb.append(ls + "Enter your choice: ");
        return sb.toString();
    }

    private void sendErrorMessageToClient(String message) {
        connection.write(warning + message + Messages.END_OF_FRAME.getMessage());
    }

    private void sendOkMessageToClient(String message) {
        connection.write(message + Messages.END_OF_FRAME.getMessage());
    }

    public void sendListOfFilesAndMenu() {
        connection.write(fileManager.getListOfFiles() + ls + printMenu() + Messages.END_OF_FRAME.getMessage());
    }


    public Method getMethod(Integer menuItem) {
        return menuOfCommands.get(menuItem);
    }

}
