package ru.job4j.inputoutput.socket.bot.server;

import ru.job4j.inputoutput.filemanager.FileBrowser;
import ru.job4j.inputoutput.socket.bot.exceptions.*;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Server {
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private final String defaultAnswer = "Sorry, I don't understand U.";
    private Map<String, String> mapOfAnswers = new HashMap<>();


    public Server(Socket socket) {
        this.socket = socket;
    }

    public void start() {
        initMapOfAnswers();
        handleNegotiation();
    }

    private void initMapOfAnswers() {
        mapOfAnswers.put("hello", "Hello, dear friend, I'm a oracle.");
        mapOfAnswers.put("bye", "GoodBye, see U later.");
        mapOfAnswers.put("how are u", "Thank You, I'm fine.");
        mapOfAnswers.put("do u like cola?", "No, I like Pepsi.");
        mapOfAnswers.put("exit", "Chat is over.");
    }

    private String chooseAnswer(String ask) {
        return mapOfAnswers.containsKey(ask) ? mapOfAnswers.get(ask) : defaultAnswer;
    }

    private void negotiate() throws IOException {
        out = new PrintWriter(socket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String ask = "";
        while (!"exit".equals(ask)) {
            System.out.println("wait command ...");
            ask = in.readLine();
            out.println(chooseAnswer(ask));
        }
    }

    public void handleNegotiation() {
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
        File file = new File("D:\\Flash");
        System.out.println(file.getParent() != null);

        FileBrowser fileBrowser = new FileBrowser();
        List<File> lf = fileBrowser.goDown(file);
        //lf = fileBrowser.goUp(file);
        for (File e:
                lf) {
            System.out.println(e);
        }

        try (Socket socket = new ServerSocket(5001).accept()) {





            Server server = new Server(socket);
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
