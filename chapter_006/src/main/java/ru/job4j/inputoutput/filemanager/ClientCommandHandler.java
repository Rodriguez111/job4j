package ru.job4j.inputoutput.filemanager;

import ru.job4j.inputoutput.filemanager.connection.Connection;
import ru.job4j.inputoutput.filemanager.exceptions.ExceptionsHandler;
import ru.job4j.inputoutput.filemanager.utils.ConsoleManager;

import java.io.File;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

public class ClientCommandHandler {
    private final Connection connection;
    private final ConsoleManager consoleManager;

    private ExceptionsHandler exceptionHandler = new ExceptionsHandler();
    private final Map<Integer, Method> clientCommands = new HashMap();
    private boolean isWorking = true;

    public ClientCommandHandler(Connection connection,ConsoleManager consoleManager) {
        this.connection = connection;
        this.consoleManager = consoleManager;
        exceptionHandler.handleException(this::initCommands);
    }

    private void initCommands() throws NoSuchMethodException {
        clientCommands.put(1, this.getClass().getMethod("enterDirectoryCommand", Integer.class));
        clientCommands.put(2, this.getClass().getMethod("exitDirectoryCommand", Integer.class));
        clientCommands.put(3, this.getClass().getMethod("uploadFileCommand", Integer.class));
        clientCommands.put(4, this.getClass().getMethod("downloadFileCommand", Integer.class));
        clientCommands.put(5, this.getClass().getMethod("exitCommand", Integer.class));
    }


    public void enterDirectoryCommand(Integer commandItem) {
        connection.write(commandItem + Messages.END_OF_FRAME.getMessage());
        String argument = consoleManager.consoleStringReader("Enter directory:");
        connection.write(argument + Messages.END_OF_FRAME.getMessage());
        consoleManager.print(connection.read());
    }

    public void exitDirectoryCommand(Integer commandItem) {
        connection.write(commandItem + Messages.END_OF_FRAME.getMessage());
        consoleManager.print(connection.read());
    }

    public void uploadFileCommand(Integer commandItem) {
        connection.write(commandItem + Messages.END_OF_FRAME.getMessage());
        String argument = consoleManager.consoleStringReader("Enter local filepath to upload to the destination directory:");
        File fileToUpload = new File(argument);
        if (fileExists(fileToUpload)) {
            String serverAnswer = checkDestinationFreeSpace(fileToUpload);
            if (serverAnswer.equals(Messages.FILE_TRANSFER_START.getMessage())) {
                consoleManager.print(serverAnswer);
                transferFileToServer(fileToUpload);
            } else if (serverAnswer.equals(Messages.FILE_ALREADY_EXISTS.toString())) {
                overwriteFile(fileToUpload);
            } else {
                consoleManager.print(serverAnswer);
            }
        } else {
            consoleManager.print(Messages.FILE_DOES_NOT_EXIST.getMessage());
            connection.write(Messages.CLIENT_REJECTED.toString() + Messages.END_OF_FRAME.getMessage());
        }
    }

    private void overwriteFile(File file) {
        consoleManager.print(Messages.FILE_ALREADY_EXISTS.getMessage());
        String answer = consoleManager.consoleStringReader("?");
        connection.write(answer + Messages.END_OF_FRAME.getMessage());
        if (answer.equals("y")) {
            consoleManager.print(connection.read());
            transferFileToServer(file);
        }
    }

    private String checkDestinationFreeSpace(File file) {
        String argument = String.valueOf(file.length());
        connection.write(argument + Messages.END_OF_FRAME.getMessage()); // 1. Send file length.
        connection.write(file.getName() + Messages.END_OF_FRAME.getMessage()); // 2. Send file name.
        return connection.read();
    }

    public void downloadFileCommand(Integer commandItem) {
        connection.write(commandItem + Messages.END_OF_FRAME.getMessage());
        String fileName = consoleManager.consoleStringReader("Enter file name:");
        connection.write(fileName + Messages.END_OF_FRAME.getMessage());
        String serverAnswer = connection.read();
        if (serverAnswer.equals(Messages.WRONG_FILE.toString())) {
            consoleManager.print(Messages.WRONG_FILE.getMessage());
        } else {
            long fileSize = Long.parseLong(serverAnswer);
            String localPath = consoleManager.consoleStringReader("Enter local download directory path:");
            String filePath = localPath + "/" + fileName;
            File file = new File(filePath);
            if (directoryValidate(localPath, fileSize)) {
                if (fileExists(file)) {
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
            consoleManager.print(Messages.WRONG_DIR.getMessage());
        } else if (file.getFreeSpace() < fileSize) {
            result = false;
            consoleManager.print(Messages.TOO_LARGE_FILE.getMessage());
        }
        if (result) {
            connection.write(Messages.FILE_TRANSFER_START.toString() + Messages.END_OF_FRAME.getMessage());
        } else {
            connection.write(Messages.CLIENT_REJECTED.toString() + Messages.END_OF_FRAME.getMessage());
        }
        return result;
    }

    private void overWriteLocalFile(File file, long fileSize) {
        connection.write(Messages.FILE_ALREADY_EXISTS.toString() + Messages.END_OF_FRAME.getMessage());
        consoleManager.print(Messages.FILE_ALREADY_EXISTS.getMessage());
        String answer = consoleManager.consoleStringReader("?");
        connection.write(answer + Messages.END_OF_FRAME.getMessage());
        if (answer.equals("y")) {
            if (file.delete()) {
                transferFileToClient(file, fileSize);
            }
        }
    }

    private void transferFileToClient(File file, long fileSize) {
        consoleManager.print(connection.read());
        connection.streamToFile(file, fileSize);
        consoleManager.print(connection.read());
    }

    private void transferFileToServer(File file) {
        connection.fileToStream(file, file.length());
        consoleManager.print(connection.read());
    }

    public void listOfFilesAndMenuRequest() {
        connection.write(Messages.MENU_REQUEST.toString() + Messages.END_OF_FRAME.getMessage());
        consoleManager.print(connection.read());
    }

    public void exitCommand(Integer commandItem) {
        connection.write(Messages.EXIT_COMMAND_SERVER.toString() + Messages.END_OF_FRAME.getMessage());
        consoleManager.print("Program complete.");
        isWorking = false;
    }


    private boolean fileExists(File file) {
        boolean result = false;
        if (file.exists() && Files.isRegularFile(file.toPath())) {
            result = true;
        }
        return result;
    }

    public Method getMethod(Integer commandItem) {
        return clientCommands.get(commandItem);
    }

    public boolean commandExists(int commandItem) {
        return this.clientCommands.containsKey(commandItem);
    }

    public boolean isWorking() {
        return isWorking;
    }
}
