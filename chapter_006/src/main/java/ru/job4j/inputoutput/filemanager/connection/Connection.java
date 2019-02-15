package ru.job4j.inputoutput.filemanager.connection;

import ru.job4j.inputoutput.filemanager.exceptions.ExceptionHandler;

import java.io.*;
import java.net.Socket;

public class Connection {

    private final Socket socket;
    private final BufferedInputStream inputStreamReader;
    private final OutputStreamWriter outputStreamWriter;

    public Connection(Socket socket) throws IOException {
        this.socket = socket;
        this.inputStreamReader = new BufferedInputStream(socket.getInputStream());
        this.outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());
    }


    public String read() throws IOException {
        StringBuilder sb = new StringBuilder();
        while (inputStreamReader.available() > 0) {
            sb.append(inputStreamReader.read());
        }
    return sb.toString();
    }

    public void unhandledWrite(String line) throws IOException {
        outputStreamWriter.write(line);
    }


    public void write(String line) {
        handleException(() -> unhandledWrite(line)) ;
    }

    private void handleException(ExceptionHandler exceptionHandler) {
        try {
            exceptionHandler.handleException();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




}
