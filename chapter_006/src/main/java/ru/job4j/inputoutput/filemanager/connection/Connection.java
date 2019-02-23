package ru.job4j.inputoutput.filemanager.connection;

import ru.job4j.inputoutput.filemanager.exceptions.ExceptionsHandler;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Connection {
    private final ExceptionsHandler exceptionsHandler = new ExceptionsHandler();
    private Socket socket;


    public Connection(Socket socket) {
        this.socket = socket;
    }

    private String unhandledRead() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(this.socket.getInputStream(), StandardCharsets.UTF_8));
        StringBuilder sb = new StringBuilder();
        String currentChar;
        currentChar = String.valueOf((char) bufferedReader.read());
        while (bufferedReader.ready() && !currentChar.equals("*")) {
            sb.append(currentChar);
            currentChar = String.valueOf((char) bufferedReader.read());
        }
        return sb.toString();
    }

    private void unhandledWrite(String line) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(this.socket.getOutputStream(), StandardCharsets.UTF_8));
        bufferedWriter.write(line);
        bufferedWriter.flush();
    }

    private void unhandledFromFileToStream(File file, long fileSize) throws IOException {
        InputStream is = new FileInputStream(file);
        OutputStream outputStream = socket.getOutputStream();
        transferData(is, outputStream, fileSize);
        is.close();
    }

    private void unhandledFromStreamToFile(File file, long fileSize) throws IOException {
        InputStream inputStream = socket.getInputStream();
        OutputStream os = new FileOutputStream(file);
        transferData(inputStream, os, fileSize);
        os.close();
    }

    private void transferData(InputStream dataInputStream, OutputStream dataOutputStream, long fileSize) throws IOException {
        byte[] buffer = new byte[8192];
        int count;
        while (fileSize > 0 && (count = dataInputStream.read(buffer, 0, (int) Math.min(buffer.length, fileSize))) != -1) {
            dataOutputStream.write(buffer, 0, count);
            fileSize -= count;
        }
        dataOutputStream.flush();
    }

    public String read() {
        return (String) exceptionsHandler.handleExceptionSupplier(this::unhandledRead);
    }

    public void write(String line) {
        exceptionsHandler.handleException(() -> unhandledWrite(line));
    }


    public void fileToStream(File file, long fileSize) {
        exceptionsHandler.handleException(() -> unhandledFromFileToStream(file, fileSize));
    }

    public void streamToFile(File file, long fileSize) {
        exceptionsHandler.handleException(() -> unhandledFromStreamToFile(file, fileSize));
    }
}
