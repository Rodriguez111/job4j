package ru.job4j.inputoutput.socket.bot.server;

import com.google.common.base.Joiner;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.Socket;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ServerTest {
    private final String ls = System.lineSeparator();

    private void testServer(String input, String expected) throws IOException {
        Socket socket = mock(Socket.class);
        ByteArrayInputStream bais = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        when(socket.getInputStream()).thenReturn(bais);
        when(socket.getOutputStream()).thenReturn(baos);
        Server server = new Server(socket);
        server.start();
        assertThat(baos.toString(), is(expected + ls));
    }

    @Test
    public void whenTypeExitThenExit() throws IOException {
        testServer("exit", "Chat is over.");
    }


    @Test
    public void whenByeThenCorrespondingAnswer() throws IOException {
        testServer(Joiner.on(ls).join("bye", "exit"),
                Joiner.on(ls).join("GoodBye, see U later.", "Chat is over."));
    }

    @Test
    public void whenUnintendedThenSorryIDontUnderstand() throws IOException {
        testServer(Joiner.on(ls).join("12345", "exit"),
                Joiner.on(ls).join("Sorry, I don't understand U.", "Chat is over."));

    }

}