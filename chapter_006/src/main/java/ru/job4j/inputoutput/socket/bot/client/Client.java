package ru.job4j.inputoutput.socket.bot.client;

import ru.job4j.inputoutput.socket.bot.exceptions.ExceptionHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {


    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private final String ls = System.lineSeparator();


    public Client(Socket socket) {
        this.socket = socket;
    }

    private void negotiate() throws IOException {
        out = new PrintWriter(socket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        Scanner console = new Scanner(System.in);
        String str = "";

        while (!str.equals("exit")) {
            str = console.nextLine();
            out.println(str);
            String answer = "";
            while (answer.isEmpty()) {
                while (in.ready()) {
                    answer = in.readLine();
                    System.out.println(answer);
                }
            }
        }
    }

    public void startClient() {
        handleException(this::negotiate);
    }

    private void handleException(ExceptionHandler handler) {
        try {
            handler.handleException();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
         final int port = 5001;
         final String ip = "127.0.0.1";
        try (Socket socket = new Socket(ip, port)) {
            Client client = new Client(socket);
            client.startClient();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
