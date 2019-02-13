package ru.job4j.inputoutput.socket.bot.server;

import ru.job4j.inputoutput.socket.bot.exceptions.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Server {
   private final int port = 5001;
   private Socket socket;
   private PrintWriter out;
   private BufferedReader in;
   private final String ls = System.lineSeparator();
   private final String defaultAnswer = "Sorry, I don't understand U" + ls;
   private Map<String, String> mapOfAnswers = new HashMap<>();

   private void initMapOfAnswers() {
       mapOfAnswers.put("hello", "Hello, dear friend, I'm a oracle." + ls);
       mapOfAnswers.put("bye", "GoodBye, see U later." + ls);
       mapOfAnswers.put("how are u", "Thank You, I'm fine" + ls);
       mapOfAnswers.put("do u like cola?", "No, I like Pepsi" + ls);
   }

   private String chooseAnswer(String ask) {
       return mapOfAnswers.containsKey(ask) ? mapOfAnswers.get(ask) : defaultAnswer;
   }



    private void negotiate() throws IOException {
        socket =  new ServerSocket(port).accept();
        out = new PrintWriter(socket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String ask;
        do {
            System.out.println("wait command ...");
            ask = in.readLine();
            System.out.println(chooseAnswer(ask));

        } while ("exit".equals(ask));

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
        Server server = new Server();
        server.handleNegotiation();
    }


}
