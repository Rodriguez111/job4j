package ru.job4j.inputoutput.chat;

import java.io.*;
import java.nio.charset.StandardCharsets;


public class Chat {
  InputStreamReader isr;
  OutputStream os;
  Input input;

    public Chat(Input input) {
        this.input = input;
    }
   private String logFile = getClass().getClassLoader().getResource("ru/job4j/inputoutput/chat/textlog.txt").getFile();
   private String phrases = getClass().getClassLoader().getResource("ru/job4j/inputoutput/chat/phrases.txt").getFile();

    private String pause = "пауза";
    private String resume = "продолжить";
    private String end = "стоп";
    private String lineSeparator = System.lineSeparator();

    boolean isPaused = false;
    String message;



    public void chat() {

      try  {
          os = new FileOutputStream(logFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
            message = input.typeMassage();
            while (!message.equals(end)) {
                writeLog(message + lineSeparator, os);
                if (!isPaused) {
                    String answer = getRandomLine();
                    System.out.println(answer);
                    writeLog(answer, os);
                }
                message = input.typeMassage();
                if (message.equals(pause)) {
                    isPaused = true;
                } else if (message.equals(resume)) {
                    isPaused = false;
                }
            }
    }

    private void writeLog(String line, OutputStream os) {

        byte[] buffer = line.getBytes();
        try {
            os.write(buffer, 0, buffer.length);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getRandomLine() {
        int numberOfLines = calculateLinesAmount();
        int randomLineNumber = (int) (Math.random() * numberOfLines);
        String result = "";
        try {
            isr = new InputStreamReader(new FileInputStream(phrases), StandardCharsets.UTF_8);
            if (randomLineNumber > 0) {
                int count = 0;
                int read = isr.read();
                while (count < randomLineNumber) {
                    char character = (char) read;
                    if (character == '\n') {
                        count++;
                        if (count == randomLineNumber) {
                            break;
                        }
                    }
                    read = isr.read();
                }
            }
            char character = (char) isr.read();
            while (character != '\n') {
                result = result + String.valueOf(character);
                character = (char) isr.read();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    return result;
    }


    private int calculateLinesAmount() {
        int count = 0;
        try {
            isr = new InputStreamReader(new FileInputStream(phrases), StandardCharsets.UTF_8);

            int read = isr.read();
            while (read > 0) {
                char character = (char) read;
                if (character == '\n') {
                    count++;
                }
                read = isr.read();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    return count;
    }


    public static void main(String[] args) {



        Chat chat = new Chat(new ConsoleInput());
        chat.chat();
    }

}
