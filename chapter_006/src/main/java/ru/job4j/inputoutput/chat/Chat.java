package ru.job4j.inputoutput.chat;

import ru.job4j.inputoutput.chat.exceptions.SupplierException;
import ru.job4j.inputoutput.chat.input.ConsoleInput;
import ru.job4j.inputoutput.chat.input.Input;
import ru.job4j.inputoutput.chat.exceptions.UnaryException;
import ru.job4j.inputoutput.chat.output.FileOutput;
import ru.job4j.inputoutput.chat.output.Output;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;


public class Chat {
    private final Input input;
    private final Output output;
    private final List<String> listOfLines = new ArrayList<>();

    Chat(Input input, Output output) {
        this.input = input;
        this.output = output;
    }

    private final String phrases = getClass().getClassLoader().getResource("ru/job4j/inputoutput/chat/phrases.txt").getFile();

    private final String pause = "пауза";
    private final String resume = "продолжить";
    private final String end = "стоп";
    private boolean isPaused = false;


    public void chat() {
        handleException(this::takeLines);

        String message = inputMessage(input);
        while (!message.equals(end)) {
            writeOutput(message, output);
            if (!isPaused) {
                String answer = getRandomLine();
                System.out.println(answer);
                writeOutput(answer, output);
            }
            message = inputMessage(input);
            if (message.equals(pause)) {
                isPaused = true;
            } else if (message.equals(resume)) {
                isPaused = false;
            }
        }
    }


    private String getRandomLine() {
        int randomLineNumber = (int) (Math.random() * listOfLines.size());
        return listOfLines.get(randomLineNumber);
    }


    private void takeLines() throws IOException {
        InputStreamReader isr = new InputStreamReader(new FileInputStream(phrases), StandardCharsets.UTF_8);
        int read;
        StringBuilder sb = new StringBuilder();
        while ((read = isr.read()) > 0) {
            char character = (char) read;
            sb.append(character);
            if (character == '\n') {
                listOfLines.add(sb.toString().trim());
                sb = new StringBuilder();
            }
        }
    }


    /**
     * This method writes data and handles exception with handleException();
     *
     * @param line
     * @param output
     */
    private void writeOutput(String line, Output output) {
        handleException(() -> output.writeLog(line));
    }

    private String inputMessage(Input input) {
        return handleExceptionWithReturn(input::typeMassage);
    }


    /**
     * This method handles some abstract method which throws any exception.
     *
     * @param unaryException
     */
    private void handleException(UnaryException unaryException) {
        try {
            unaryException.action();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String handleExceptionWithReturn(SupplierException<String> supplierException) {
        String lineFromConsole = "";
        try {
            lineFromConsole = supplierException.action();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lineFromConsole;
    }

    public static void main(String[] args) {
        Chat chat = new Chat(new ConsoleInput(), new FileOutput());
        chat.chat();
    }

}
