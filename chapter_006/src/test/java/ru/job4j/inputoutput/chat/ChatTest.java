package ru.job4j.inputoutput.chat;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.inputoutput.chat.input.InputForTest;
import ru.job4j.inputoutput.chat.output.OutputForTest;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class ChatTest {
    private final ByteArrayOutputStream baos = new ByteArrayOutputStream();
    private final List<String> listOfPresetMessages = new ArrayList<>();
    private Chat chat;
    private final String lineSeparator = System.lineSeparator();

    @Before
    public void doBefore() {
        System.setOut(new PrintStream(new ByteArrayOutputStream()));
    }


    @Test
    public void shouldChat() {
        listOfPresetMessages.add("Сообщение1");
        listOfPresetMessages.add("Сообщение2");
        listOfPresetMessages.add("пауза");
        listOfPresetMessages.add("Сообщение3");
        listOfPresetMessages.add("Сообщение4");
        listOfPresetMessages.add("продолжить");
        listOfPresetMessages.add("Сообщение5");
        listOfPresetMessages.add("Сообщение6");
        listOfPresetMessages.add("стоп");
        chat = new Chat(new InputForTest(listOfPresetMessages), new OutputForTest(baos));

        chat.chat();
        String botRandomAnswers = baos.toString();
        List<String> botRandomAnswersList = Arrays.asList(botRandomAnswers.split(lineSeparator));
        List<String> expectedChatMessages = new ArrayList<>();
        expectedChatMessages.add("Сообщение1");
        expectedChatMessages.add(botRandomAnswersList.get(1));
        expectedChatMessages.add("Сообщение2");
        expectedChatMessages.add(botRandomAnswersList.get(3));
        expectedChatMessages.add("пауза");
        expectedChatMessages.add("Сообщение3");
        expectedChatMessages.add("Сообщение4");
        expectedChatMessages.add("продолжить");
        expectedChatMessages.add(botRandomAnswersList.get(8));
        expectedChatMessages.add("Сообщение5");
        expectedChatMessages.add(botRandomAnswersList.get(10));
        expectedChatMessages.add("Сообщение6");
        expectedChatMessages.add(botRandomAnswersList.get(12));

        StringBuilder sb = new StringBuilder();
        for (String eachMessage : expectedChatMessages) {
            sb.append(eachMessage + lineSeparator);
        }
        String actual = baos.toString();
        String expected = sb.toString();
        assertThat(actual, is(expected));
    }

}