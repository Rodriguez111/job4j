package ru.job4j.inputoutput.filemanager.connection;

import ru.job4j.inputoutput.filemanager.exceptions.ExceptionHandler;
import ru.job4j.inputoutput.filemanager.utils.ManageProperties;

import java.io.*;

import java.net.Socket;
import java.net.URL;


public class Client {

    private Socket socket;
    private int port;
    private String ipAddress;
    private Connection connection;
    private InputStream inputStream;
    private OutputStream outputStream;



    public void startClient() {
        initProperties();
        handleException(this::startSocket);

    }



    private void initProperties() {
        ManageProperties manageProperties = new ManageProperties();
        port = manageProperties.getPort();
        ipAddress = manageProperties.getServerIpAddress();
    }







    private void startSocket() throws IOException {
        socket = new Socket(ipAddress, port);
        System.out.println(socket.getLocalPort());
    }

    private void startStreams() throws IOException {
        inputStream = socket.getInputStream();
        outputStream = socket.getOutputStream();


    }


    private void handleException(ExceptionHandler exceptionHandler) {
        try {
            exceptionHandler.handleException();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }





}
