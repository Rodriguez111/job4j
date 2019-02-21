package ru.job4j.inputoutput.filemanager.connection;

import ru.job4j.inputoutput.filemanager.exceptions.ExceptionsHandler;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class CommandsConnection {
    private final ExceptionsHandler exceptionsHandler = new ExceptionsHandler();
    private final Socket socket;

    public CommandsConnection(Socket socket) {
        this.socket = socket;

    }

    private String unhandledRead() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(this.socket.getInputStream(), StandardCharsets.UTF_8));
        StringBuilder sb = new StringBuilder();
        sb.append((char) bufferedReader.read());
        while (bufferedReader.ready()) {
            sb.append((char) bufferedReader.read());
        }
    return sb.toString();
    }

    private void unhandledWrite(String line) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(this.socket.getOutputStream(), StandardCharsets.UTF_8));
        bufferedWriter.write(line);
        bufferedWriter.flush();
    }



    public String read() {
      return exceptionsHandler.handleException(this::unhandledRead);
    }

    public void write(String line) {
        exceptionsHandler.handleException(() -> unhandledWrite(line)) ;
    }



}
