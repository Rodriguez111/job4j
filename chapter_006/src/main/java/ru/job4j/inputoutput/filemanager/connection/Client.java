package ru.job4j.inputoutput.filemanager.connection;

import ru.job4j.inputoutput.filemanager.commands.ClientCommandHandler;
import ru.job4j.inputoutput.filemanager.Messages;
import ru.job4j.inputoutput.filemanager.exceptions.ExceptionsHandler;
import ru.job4j.inputoutput.filemanager.utils.ConsoleManager;
import ru.job4j.inputoutput.filemanager.utils.ManageProperties;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.net.Socket;


public class Client {
    private Socket socket;
    private int port;
    private String ipAddress;
    private Connection connection;
    private ExceptionsHandler exceptionHandler = new ExceptionsHandler();
    private ConsoleManager consoleManager = new ConsoleManager();
    ClientCommandHandler clientCommandHandler;


    public Client() {
    }

    public void startClient() {
        initProperties();
        exceptionHandler.handleException(this::startSocket);
        exceptionHandler.handleException(this::startConnection);
        startClientCommands();
        exceptionHandler.handleException(this::negotiationWithServer);
    }

    private void initProperties() {
        ManageProperties manageProperties = new ManageProperties();
        port = manageProperties.getPort();
        ipAddress = manageProperties.getServerIpAddress();
    }


    private void startConnection() {
        connection = new Connection(socket);
    }

    private void startClientCommands() {
        clientCommandHandler = new ClientCommandHandler(connection);
    }

    private void startSocket() throws IOException {
        socket = new Socket(ipAddress, port);
    }

    private void negotiationWithServer() throws InvocationTargetException, IllegalAccessException {
        int command;
        while (clientCommandHandler.isWorking()) {
            clientCommandHandler.listOfFilesAndMenuRequest();
            command = consoleManager.consoleNumberReader();
            if (!clientCommandHandler.commandExists(command)) {
                consoleManager.print(Messages.WRONG_COMMAND.getMessage());
            } else {
                clientCommandHandler.executeCommand(command);
            }
        }
    }

    public static void main(String[] args) {
        Client client = new Client();
        client.startClient();
    }
}
