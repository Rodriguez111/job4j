package ru.job4j.inputoutput.filemanager.connection;

import ru.job4j.inputoutput.filemanager.FileManager;
import ru.job4j.inputoutput.filemanager.Menu;
import ru.job4j.inputoutput.filemanager.exceptions.ExceptionHandler;
import ru.job4j.inputoutput.filemanager.utils.ManageProperties;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {
    private Socket clientSocket;
    private Connection connection;
    private Menu menu = new Menu();
    private final String exit = "exit";
    private int port;
    private FileManager fileManager = new FileManager();





    public void startServer() {
        initProperties();
        handleException(this::startSocket);
        handleException(this::startConnection);
        handleException(this::handleClient);


    }


    private void initProperties() {
        ManageProperties manageProperties = new ManageProperties();
        port = manageProperties.getPort();
    }




    private void startSocket() throws IOException {
        clientSocket =  new ServerSocket(port).accept();
        System.out.println("Connected to client");
    }

    private void startConnection() throws IOException {
        connection = new Connection(clientSocket);
    }

    private void handleClient() throws InvocationTargetException, IllegalAccessException {
        connection.write(menu.printMenu());
        String commandFromClient = connection.read();

        while (!commandFromClient.equals(exit)) {
            System.out.println("Комманда была " + commandFromClient);
            menu.getMethod(Integer.parseInt(commandFromClient)).invoke(fileManager, new File("newfolder"));


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
        Server server = new Server();
        server.startServer();
    }


}
