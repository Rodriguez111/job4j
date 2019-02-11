package ru.job4j.inputoutput.chat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class ChatTest {
    private ByteArrayOutputStream baos = new ByteArrayOutputStream();
    private PrintStream original = System.out;
    private PrintStream newStream = new PrintStream(baos);
    private Chat chat;

    private String logFile = "C:\\projects\\job4j\\chapter_006\\target\\classes\\ru\\job4j\\inputoutput\\chat/textlog.txt";
    private String lineSeparator = System.lineSeparator();

    @Before
    public void init() {
     System.setOut(newStream);
    }

    @After
    public void end() {
        System.setOut(original);
    }

    @Test
    public void shouldChat() {
        List<String> listOfMessages = new ArrayList<>();
        listOfMessages.add("Сообщение1");
        listOfMessages.add("Сообщение2");
        listOfMessages.add("пауза");
        listOfMessages.add("Сообщение3");
        listOfMessages.add("Сообщение4");
        listOfMessages.add("продолжить");
        listOfMessages.add("Сообщение5");
        listOfMessages.add("Сообщение6");
        listOfMessages.add("стоп");
        chat = new Chat(new InputForTest(listOfMessages));

        chat.chat();
        ByteArrayOutputStream baosFromLogFile = new ByteArrayOutputStream();
        try {
            FileInputStream fis = new FileInputStream(logFile);
            byte[] buffer = new byte[1024];
            int byteRead = fis.read(buffer);
            while (byteRead != -1) {
                baosFromLogFile.write(buffer, 0, byteRead);
                byteRead = fis.read(buffer);
            }

            String actual = baosFromLogFile.toString();
            String botRandomAnswers = baos.toString();
            List<String> botRandomAnswersList = Arrays.asList(botRandomAnswers.split(lineSeparator));
            List<String> expectedChatMessages = new ArrayList<>();
            expectedChatMessages.add("Сообщение1" + lineSeparator);
            expectedChatMessages.add(botRandomAnswersList.get(0));
            expectedChatMessages.add("Сообщение2" + lineSeparator);
            expectedChatMessages.add(botRandomAnswersList.get(1));
            expectedChatMessages.add("пауза" + lineSeparator);
            expectedChatMessages.add("Сообщение3" + lineSeparator);
            expectedChatMessages.add("Сообщение4" + lineSeparator);
            expectedChatMessages.add("продолжить" + lineSeparator);
            expectedChatMessages.add(botRandomAnswersList.get(2));
            expectedChatMessages.add("Сообщение5" + lineSeparator);
            expectedChatMessages.add(botRandomAnswersList.get(3));
            expectedChatMessages.add("Сообщение6" + lineSeparator);
            expectedChatMessages.add(botRandomAnswersList.get(4));

            StringBuilder sb = new StringBuilder();
            for (String eachMessage : expectedChatMessages) {
                sb.append(eachMessage);
            }
            String expected = sb.toString();
            assertThat(actual, is(expected));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}