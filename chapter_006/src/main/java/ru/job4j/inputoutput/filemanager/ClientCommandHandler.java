package ru.job4j.inputoutput.filemanager;

import ru.job4j.inputoutput.filemanager.connection.Connection;
import ru.job4j.inputoutput.filemanager.exceptions.ExceptionsHandler;
import ru.job4j.inputoutput.filemanager.utils.ConsoleManager;

import java.io.File;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

public class ClientCommands {
    private final Connection connection;


    private ExceptionsHandler exceptionHandler = new ExceptionsHandler();
    private final Map<Integer, Method> clientCommands = new HashMap();
    private final String ok = "OK";
    private final String ls = System.lineSeparator();

    public ClientCommands(Connection connection) {
        this.connection = connection;

        exceptionHandler.handleException(this::initCommands);
    }



    private void initCommands() throws NoSuchMethodException {
        clientCommands.put(1, this.getClass().getMethod("enterDirectoryCommand", Integer.class));
        clientCommands.put(2, this.getClass().getMethod("exitDirectoryCommand", Integer.class));
        clientCommands.put(3, this.getClass().getMethod("uploadFileCommand", Integer.class));
        clientCommands.put(4, this.getClass().getMethod("downloadFileCommand", Integer.class));
        clientCommands.put(5, this.getClass().getMethod("exitCommand", Integer.class));
        clientCommands.put(101, this.getClass().getMethod("askListOfFilesAndMenu", Integer.class));
    }



    public void enterDirectoryCommand(Integer commandItem) {
       connection.write(commandItem + Messages.END_OF_FRAME.getMessage());
       String argument = ConsoleManager.consoleStringReader("Enter directory:");
       connection.write(argument + Messages.END_OF_FRAME.getMessage());
        ConsoleManager.print(connection.read());
    }

    public void exitDirectoryCommand(Integer commandItem) {
        connection.write(commandItem + Messages.END_OF_FRAME.getMessage());
        ConsoleManager.print(connection.read());
    }

    public void uploadFileCommand(Integer commandItem) {
        connection.write(commandItem + Messages.END_OF_FRAME.getMessage());
        String argument = ConsoleManager.consoleStringReader("Enter local filepath to upload to the destination directory:");
        File fileToUpload = new File(argument);
        if (fileExists(fileToUpload)) {
          String serverAnswer = checkDestinationFreeSpace(fileToUpload);
          if(serverAnswer.equals(Messages.FILE_TRANSFER_START.getMessage())) {
              ConsoleManager.print(serverAnswer);
              connection.fileSend(fileToUpload, fileToUpload.length());
              ConsoleManager.print(connection.read());
          }  else if (serverAnswer.equals(Messages.FILE_ALREADY_EXISTS.toString())) {
              overwriteFile(fileToUpload);
          }
          else {
              ConsoleManager.print(serverAnswer);
          }
        } else {
            ConsoleManager.print(Messages.FILE_DOES_NOT_EXIST.getMessage());
            connection.write(Messages.CLIENT_REJECTED.toString() + Messages.END_OF_FRAME.getMessage());
        }
    }

    private void overwriteFile(File file) {
        ConsoleManager.print(Messages.FILE_ALREADY_EXISTS.getMessage());
        String answer = ConsoleManager.consoleStringReader("?");
        connection.write(answer + Messages.END_OF_FRAME.getMessage());
        if (answer.equals("y")) {
            ConsoleManager.print(connection.read());
            connection.fileSend(file, file.length());
            ConsoleManager.print(connection.read());
        }
    }

    private String checkDestinationFreeSpace(File file) {
       String argument =  String.valueOf(file.length());
        connection.write(argument + Messages.END_OF_FRAME.getMessage()); // 1. Send file length.
        connection.write(file.getName() + Messages.END_OF_FRAME.getMessage()); // 2. Send file name.
        return connection.read();
    }

    public void downloadFileCommand(Integer commandItem) {
        connection.write(commandItem + Messages.END_OF_FRAME.getMessage());
        String fileName = ConsoleManager.consoleStringReader("Enter file name:");
        connection.write(fileName + Messages.END_OF_FRAME.getMessage());
        String serverAnswer = connection.read();
        if(serverAnswer.equals(Messages.WRONG_FILE.toString())) {
            ConsoleManager.print(Messages.WRONG_FILE.getMessage());
        } else {
            long fileSize = Long.parseLong(serverAnswer);
            String localPath = ConsoleManager.consoleStringReader("Enter local download directory path:");
            String filePath = localPath + "/" + fileName;
            File file = new File(filePath);
            if(directoryValidate(localPath, fileSize)) {
                if(fileExists(file)) {
                    overWriteLocalFile(file, fileSize);
                } else {
                    connection.write(Messages.FILE_TRANSFER_START.toString() + Messages.END_OF_FRAME.getMessage());
                    transferFileToClient(file, fileSize);
                }
            }
        }
    }

    private boolean directoryValidate(String dirPath, long fileSize) {
        File file = new File(dirPath);
        boolean result = true;
        if (!file.exists() || !file.isDirectory()) {
            result = false;
            ConsoleManager.print(Messages.WRONG_DIR.getMessage());
        } else if (file.getFreeSpace() < fileSize) {
            result = false;
            ConsoleManager.print(Messages.TOO_LARGE_FILE.getMessage());
        }
        if(result) {
            connection.write(Messages.FILE_TRANSFER_START.toString() + Messages.END_OF_FRAME.getMessage());
        } else {
            connection.write(Messages.CLIENT_REJECTED.toString() + Messages.END_OF_FRAME.getMessage());
        }
    return result;
    }

    private void overWriteLocalFile(File file, long fileSize) {
        connection.write(Messages.FILE_ALREADY_EXISTS.toString() + Messages.END_OF_FRAME.getMessage());
        ConsoleManager.print(Messages.FILE_ALREADY_EXISTS.getMessage());
        String answer = ConsoleManager.consoleStringReader("?");

        connection.write(answer + Messages.END_OF_FRAME.getMessage());
        if (answer.equals("y")) {
            if(file.delete()) {
                transferFileToClient(file, fileSize);
            }
        }
    }

    private void transferFileToClient(File file, long fileSize) {
        ConsoleManager.print(connection.read());
        connection.fileReceive(file, fileSize);
        ConsoleManager.print(connection.read());
    }


    public void askListOfFilesAndMenu(Integer commandItem) {
        connection.write(commandItem + Messages.END_OF_FRAME.getMessage());
        ConsoleManager.print(connection.read());
    }

    public void exitCommand(Integer commandItem) {
        connection.write(commandItem + Messages.END_OF_FRAME.getMessage());
    }


    private boolean fileExists(File file) {
        boolean result = false;
        if (file.exists() && Files.isRegularFile(file.toPath())){
            result = true;
        }
        return result;
    }




    public Method getMethod(Integer commandItem) {
        return clientCommands.get(commandItem);
    }

}
