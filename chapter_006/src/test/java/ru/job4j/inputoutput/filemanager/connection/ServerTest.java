package ru.job4j.inputoutput.filemanager.connection;

import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.net.Socket;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ServerTest {
    private final String path = System.getProperty("java.io.tmpdir");
    private final String serverFileSystem = path + "/00TestServer";
    private final String clientFileSystem = path + "/00TestClient";

    private ByteArrayOutputStream baos;
    private ByteArrayInputStream bais;
    private PrintStream printStream;
    private String ls = System.lineSeparator();
    @Before
    public void init() throws IOException {
        createTestFilesStructure();
        baos = new ByteArrayOutputStream();
        printStream = new PrintStream(baos);
        System.setOut(printStream);

    }

    public void createTestFilesStructure() throws IOException {
        new File(serverFileSystem).mkdirs();
        new File(serverFileSystem + "/text.pdf").createNewFile();
        new File(serverFileSystem + "/text.txt").createNewFile();
        new File(serverFileSystem + "/file.java").createNewFile();
        new File(serverFileSystem + "/file.xml").createNewFile();
        new File(serverFileSystem + "/newfolder").mkdirs();
        new File(serverFileSystem + "/newfolder/text.pdf").createNewFile();
        new File(serverFileSystem + "/newfolder/text.txt").createNewFile();
        new File(serverFileSystem + "/newfolder/file.xml").createNewFile();
        new File(serverFileSystem + "/newfolder/file.java").createNewFile();
        new File(serverFileSystem + "/newfolder/newfolder2").mkdirs();
        new File(serverFileSystem + "/newfolder/newfolder2/text.pdf").createNewFile();
        new File(serverFileSystem + "/newfolder/newfolder2/text.txt").createNewFile();
        new File(serverFileSystem + "/newfolder/newfolder2/file.xml").createNewFile();
        new File(serverFileSystem + "/newfolder/newfolder2/file.java").createNewFile();

        new File(clientFileSystem).mkdirs();
        new File(clientFileSystem + "/textFileFromClient.txt").createNewFile();
    }

    @Test
    public void whenExitCommandThenServerShutDown() throws IOException {
        bais = new ByteArrayInputStream("EXIT_COMMAND_SERVER*".getBytes());
        Socket socket = mock(Socket.class);
        when(socket.getInputStream()).thenReturn(bais);
        when(socket.getOutputStream()).thenReturn(baos);

        String root = serverFileSystem;
        Server server = new Server(root);
        server.clientSocket = socket;
        server.startServer();

        String expected = "INFO! Exit command received from client, server is shutting down." + ls;
        String actual = baos.toString();

        assertThat(actual, is(expected));
    }


}