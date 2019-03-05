package ru.job4j.inputoutput.filemanager.commands;

import ru.job4j.inputoutput.filemanager.Messages;
import ru.job4j.inputoutput.filemanager.connection.Connection;

import java.io.File;
import java.nio.file.Files;
import java.util.function.Consumer;

public class ClientCommandHandler extends CommandHandler {
    private boolean isWorking = true;

    public ClientCommandHandler(Connection connection) {
        super(connection);
        initMenu();
    }

    private void initMenu()  {
        menu.get(0).setCommand(enterDirectory);
        menu.get(1).setCommand(exitDirectory);
        menu.get(2).setCommand(downloadFile);
        menu.get(3).setCommand(uploadFile);
        menu.get(4).setCommand(exitProgram);
    }

    Consumer<Integer> enterDirectory = (commandIndex)-> {
        connection.write(commandIndex + Messages.END_OF_FRAME.getMessage());
        String argument = consoleManager.consoleStringReader("Enter directory:");
        connection.write(argument + Messages.END_OF_FRAME.getMessage());
        consoleManager.print(connection.read());
        };

    Consumer<Integer> exitDirectory = (commandIndex)-> {
        connection.write(commandIndex + Messages.END_OF_FRAME.getMessage());
        consoleManager.print(connection.read());
        };

    Consumer<Integer> uploadFile = (commandIndex) -> {
        connection.write(commandIndex + Messages.END_OF_FRAME.getMessage());
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
    };

    Consumer<Integer> downloadFile = (commandIndex) -> {
        connection.write(commandIndex + Messages.END_OF_FRAME.getMessage());
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
                    overwriteLocalFile(file, fileSize);
                } else {
                    connection.write(Messages.FILE_TRANSFER_START.toString() + Messages.END_OF_FRAME.getMessage());
                    transferFileToClient(file, fileSize);
                }
            }
        }
    };

    Consumer<Integer> exitProgram = (commandIndex) -> {
        connection.write(Messages.EXIT_COMMAND_SERVER.toString() + Messages.END_OF_FRAME.getMessage());
        consoleManager.print("Program complete.");
        isWorking = false;
    };

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

    private void overwriteLocalFile(File file, long fileSize) {
        connection.write(Messages.FILE_ALREADY_EXISTS.toString() + Messages.END_OF_FRAME.getMessage());
        consoleManager.print(Messages.FILE_ALREADY_EXISTS.getMessage());
        String answer = consoleManager.consoleStringReader("?");
        connection.write(answer + Messages.END_OF_FRAME.getMessage());
        if (answer.equals("y")) {
            if (file.renameTo(file)) {
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

    private boolean fileExists(File file) {
        boolean result = false;
        if (file.exists() && Files.isRegularFile(file.toPath())) {
            result = true;
        }
        return result;
    }

    public boolean commandExists(int commandItem) {
        return commandItem > 0 && commandItem <= menu.size();
    }

    public boolean isWorking() {
        return isWorking;
    }
}
