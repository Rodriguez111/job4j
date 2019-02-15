package ru.job4j.inputoutput.filemanager.connection;

import ru.job4j.inputoutput.filemanager.exceptions.ExceptionHandler;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {
    private Socket clientSocket;
    private Connection connection;

    private int port;
    private File rootDirectory;


    public Server(String rootDirectory) {
        this.rootDirectory = new File(rootDirectory);
    }

    public void startServer() {
        handleException(this::startSocket);
        handleException(this::startConnection);

    }





    private void startSocket() throws IOException {
        clientSocket =  new ServerSocket(port).accept();
    }

    private void startConnection() throws IOException {
        connection = new Connection(clientSocket);
    }



    private void handleException(ExceptionHandler exceptionHandler) {
        try {
            exceptionHandler.handleException();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        Server server = new Server("d:/flash");
        server.startServer();
    }


}
