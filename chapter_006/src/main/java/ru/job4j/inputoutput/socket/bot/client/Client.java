package ru.job4j.inputoutput.socket.bot.client;

import ru.job4j.inputoutput.socket.bot.exceptions.ExceptionHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private final int port = 5001;
    private final String ip = "127.0.0.1";

    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;

    private void negotiate() throws IOException {
        socket = new Socket(InetAddress.getByName(ip), port);
        out = new PrintWriter(socket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        Scanner console = new Scanner(System.in);
        String str;
        do {
            str = console.nextLine();
            out.println(str);

            while (!(str = in.readLine()).isEmpty()) {
                System.out.println(str);
            }
        } while (str.equals("exit"));



    }

    public void handleNegotiation() {
        handleException(this::negotiate);
    }


    private void handleException(ExceptionHandler handler ) {
        try {
            handler.handleException();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Client client = new Client();
        client.handleNegotiation();
    }


}
