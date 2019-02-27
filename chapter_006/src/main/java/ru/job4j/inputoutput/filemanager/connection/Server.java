package ru.job4j.inputoutput.filemanager.connection;

import ru.job4j.inputoutput.filemanager.Messages;
import ru.job4j.inputoutput.filemanager.commands.ServerCommandHandler;
import ru.job4j.inputoutput.filemanager.exceptions.ExceptionHandler;
import ru.job4j.inputoutput.filemanager.utils.ConsoleManager;
import ru.job4j.inputoutput.filemanager.utils.ManageProperties;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public Socket clientSocket;
    private Connection connection;
    private int port;
    private String rootDir;
    private ServerCommandHandler serverCommandHandler;
    private boolean isWorking = true;
    private final ConsoleManager consoleManager = new ConsoleManager();

    public Server(String root) {
        this.rootDir = root;
    }


    public void startServer() {
        initProperties();
        handleException(this::startSocket);
        handleException(this::startConnection);
        startMenu();
        handleException(this::handleClient);
    }

    private void initProperties() {
        ManageProperties manageProperties = new ManageProperties();
        port = manageProperties.getPort();
    }

    private void startSocket() throws IOException {
        if (clientSocket == null) {
            clientSocket = new ServerSocket(port).accept();
            System.out.println("Connected to client");
        }

    }

    private void startConnection() {
        connection = new Connection(clientSocket);
    }

    private void startMenu() {
        serverCommandHandler = new ServerCommandHandler(connection, rootDir);
    }

    private void handleClient() throws InvocationTargetException, IllegalAccessException {
        String commandFromClient;
        while (isWorking) {
            commandFromClient = connection.read();

            if (commandFromClient.equals(Messages.EXIT_COMMAND_SERVER.toString())) {
                consoleManager.print(Messages.EXIT_COMMAND_SERVER.getMessage());
                isWorking = false;
            } else if (commandFromClient.equals(Messages.MENU_REQUEST.toString())) {
                serverCommandHandler.sendListOfFilesAndMenu();
            } else {
                serverCommandHandler.executeCommand(Integer.parseInt(commandFromClient));
            }
        }
    }

    private void handleException(ExceptionHandler exceptionHandler) {
        try {
            exceptionHandler.handleException();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String root = System.getProperty("java.io.tmpdir") + "/00Test";
        Server server = new Server(root);
        server.startServer();
    }
}