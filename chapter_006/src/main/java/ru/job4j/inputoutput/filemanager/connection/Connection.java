package ru.job4j.inputoutput.filemanager.connection;

import ru.job4j.inputoutput.filemanager.exceptions.ExceptionHandler;
import ru.job4j.inputoutput.filemanager.exceptions.SupplierTypeExceptionHandler;

import java.io.*;
import java.net.Socket;

public class Connection {

    private final Socket socket;
    private final InputStreamReader inputStreamReader;
    private final OutputStreamWriter outputStreamWriter;



    public Connection(Socket socket) throws IOException {
        this.socket = socket;
        this.inputStreamReader = new InputStreamReader(this.socket.getInputStream());
        this.outputStreamWriter = new OutputStreamWriter(this.socket.getOutputStream());
    }


    private String unhandledRead() throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append((char) inputStreamReader.read());
        while (inputStreamReader.ready()) {
            sb.append((char) inputStreamReader.read());
        }
    return sb.toString();
    }

    private void unhandledWrite(String line) throws IOException {
        outputStreamWriter.write(line);
        outputStreamWriter.flush();
    }

    public String read() {
      return handleException(this::unhandledRead);
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

    private String handleException(SupplierTypeExceptionHandler<String> exceptionHandler) {
        String result = "";
        try {
            result = exceptionHandler.handleException();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }




}
