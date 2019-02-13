package ru.job4j.inputoutput.socket.bot.client;

import org.junit.Test;

import java.io.*;
import java.net.Socket;


import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ClientTest {

    private final String ls = System.lineSeparator();

    private void testServer(String consInput, String inputFromServer) throws IOException {
        Socket socket = mock(Socket.class);
        ByteArrayInputStream inputStream = new ByteArrayInputStream(inputFromServer.getBytes());
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        ByteArrayInputStream consoleInput = new ByteArrayInputStream(consInput.getBytes());
        System.setIn(consoleInput);
        ByteArrayOutputStream consoleOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(consoleOutput));

        when(socket.getInputStream()).thenReturn(inputStream);
        when(socket.getOutputStream()).thenReturn(outputStream);
        Client client = new Client(socket);
        client.startClient();
        assertThat(consoleOutput.toString(), is(inputFromServer + ls));
    }

    @Test
    public void whenTypeExitThenExit() throws IOException {
        testServer("exit", "Chat is over.");
    }


}