package ru.job4j.inputoutput.filemanager.commands;

import ru.job4j.inputoutput.filemanager.FileManager;
import ru.job4j.inputoutput.filemanager.Messages;
import ru.job4j.inputoutput.filemanager.connection.Connection;

import java.io.File;
import java.util.function.Consumer;

public class ServerCommandHandler extends CommandHandler {
    private final String ls = System.lineSeparator();
    private FileManager fileManager;

    public ServerCommandHandler(Connection connection, String rootDir) {
        super(connection);
        this.fileManager = new FileManager(new File(rootDir), connection);
        initMenu();
    }

    private void initMenu() {
        menu.get(0).setCommand(enterDirectory);
        menu.get(1).setCommand(exitDirectory);
        menu.get(2).setCommand(downloadFile);
        menu.get(3).setCommand(uploadFile);
        menu.get(4).setCommand(exitProgram);
    }

    Consumer<Integer> enterDirectory = (commandIndex)-> {
        String directoryToEnter = connection.read();
        if (fileManager.enterDirectory(directoryToEnter)) {
            sendOkMessageToClient("");
        } else {
            sendErrorMessageToClient(Messages.WRONG_DIR.getMessage());
        }
    };

    Consumer<Integer> exitDirectory = (commandIndex)-> {
        if (fileManager.exitDirectory()) {
        sendOkMessageToClient("");
    } else {
        sendErrorMessageToClient(Messages.CAN_NOT_GO_UP.getMessage());
    }
    };

    Consumer<Integer> uploadFile = (commandIndex) -> {
        String messageFromClient = connection.read();
        if (!messageFromClient.equals(Messages.CLIENT_REJECTED.toString())) {
            long fileSize = Long.parseLong(messageFromClient); //1. receive file length
            String filename = connection.read(); //2. receive filename
            if (!fileManager.isFits(fileSize)) {
                sendErrorMessageToClient(Messages.TOO_LARGE_FILE.getMessage());
            } else if (fileManager.fileExistsInDirectory(filename)) {
                overWriteLocalFile(filename, fileSize);
            } else {
                transferFileToServer(filename, fileSize);
            }
        }
    };

    Consumer<Integer> downloadFile = (commandIndex) -> {
        String filename = connection.read();
        if (fileManager.fileExistsInDirectory(filename)) {
            long fileSize = fileManager.getFileSize(filename);
            connection.write(fileSize + Messages.END_OF_FRAME.getMessage());
            String clientMessage = connection.read(); //If wrong directory from client side
            if (!clientMessage.equals(Messages.CLIENT_REJECTED.toString())) {
                clientMessage = connection.read(); //If remote file exists.
                if (clientMessage.equals(Messages.FILE_ALREADY_EXISTS.toString())) {
                    overWriteRemoteFile(filename);
                } else {
                    transferFileToClient(filename);
                }
            }
        } else {
            connection.write(Messages.WRONG_FILE.toString() + Messages.END_OF_FRAME.getMessage());
        }
    };

    Consumer<Integer> exitProgram = (commandIndex) -> {
        consoleManager.print(Messages.EXIT_COMMAND_SERVER.getMessage());
    };

    private void overWriteLocalFile(String filename, long fileSize) {
        connection.write(Messages.FILE_ALREADY_EXISTS.toString() + Messages.END_OF_FRAME.getMessage());
        String messageFromClient = connection.read();
        if (messageFromClient.equals("y")) {
            transferFileToServer(filename, fileSize);
        }
    }

    private void overWriteRemoteFile(String fileName) {
        String clientMessage = connection.read();
        if (clientMessage.equals("y")) {
            transferFileToClient(fileName);
        }
    }

    private void transferFileToServer(String fileName, long fileSize) {
        sendOkMessageToClient(Messages.FILE_TRANSFER_START.getMessage());
        fileManager.uploadFile(connection, fileName, fileSize);
        sendOkMessageToClient(Messages.FILE_UPLOAD_SUCCESS.getMessage());
    }

    private void transferFileToClient(String fileName) {
        sendOkMessageToClient(Messages.FILE_TRANSFER_START.getMessage());
        fileManager.downloadFile(fileName);
        sendOkMessageToClient(Messages.FILE_DOWNLOAD_SUCCESS.getMessage());
    }

    public String printMenu() {
        StringBuilder sb = new StringBuilder();
        for (MenuCommand eachMenuCommand : menu) {
            sb.append(eachMenuCommand);
        }
        sb.append(ls + "Enter your choice: ");
        return sb.toString();
    }

    private void sendErrorMessageToClient(String message) {
        connection.write(message + Messages.END_OF_FRAME.getMessage());
    }

    private void sendOkMessageToClient(String message) {
        connection.write(message + Messages.END_OF_FRAME.getMessage());
    }

    public void sendListOfFilesAndMenu() {
        connection.write(fileManager.getListOfFiles() + ls + printMenu() + Messages.END_OF_FRAME.getMessage());
    }

}