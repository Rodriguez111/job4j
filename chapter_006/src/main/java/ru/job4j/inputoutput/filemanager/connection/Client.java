package ru.job4j.inputoutput.filemanager.connection;

import ru.job4j.inputoutput.filemanager.exceptions.ExceptionHandler;
import ru.job4j.inputoutput.filemanager.utils.ConsoleManager;
import ru.job4j.inputoutput.filemanager.utils.ManageProperties;

import java.io.*;
import java.net.Socket;


public class Client {

    private Socket socket;
    private int port;
    private String ipAddress;
    private Connection connection;
    private ConsoleManager consoleManager = new ConsoleManager();



    public void startClient() {
        initProperties();
        handleException(this::startSocket);
        handleException(this::startConnection);
        negotiationWithServer();

    }



    private void initProperties() {
        ManageProperties manageProperties = new ManageProperties();
        port = manageProperties.getPort();
        ipAddress = manageProperties.getServerIpAddress();
    }


    private void startConnection() throws IOException {
        connection = new Connection(socket);
    }




    private void startSocket() throws IOException {
        socket = new Socket(ipAddress, port);
    }



    private void negotiationWithServer() {

        ConsoleManager.print(connection.read());

       int command =  consoleManager.consoleNumberReader();
       while (command != 5) {
           connection.write(String.valueOf(command));
           String serverMessage = connection.read();

       }




        String serverMessage = connection.read();
        System.out.println("Сообщение от сервера: " + serverMessage);

    }


    private void handleException(ExceptionHandler exceptionHandler) {
        try {
            exceptionHandler.handleException();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Client client = new Client();
        client.startClient();
    }





}
