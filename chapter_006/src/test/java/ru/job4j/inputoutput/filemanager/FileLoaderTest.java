package ru.job4j.inputoutput.filemanager;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.inputoutput.filemanager.connection.Connection;

import java.io.*;
import java.net.Socket;
import java.nio.file.Files;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FileLoaderTest {
    private final String path = System.getProperty("java.io.tmpdir");
    private final String serverFileSystem = path + "00TestServer/";

    @Before
    public void init() throws IOException {
        createTestFilesStructure();
    }

    @After
    public void deleteTestFile() {
        File testFile = new File(serverFileSystem + "/receivedFile.file");
        if (testFile.exists()) {
            testFile.delete();
        }
    }

    public void createTestFilesStructure() throws IOException {
        new File(serverFileSystem).mkdirs();
        new File(serverFileSystem + "/text.pdf").createNewFile();
        new File(serverFileSystem + "/text.txt").createNewFile();
        new File(serverFileSystem + "/file.java").createNewFile();
        new File(serverFileSystem + "/file.xml").createNewFile();
        new File(serverFileSystem + "/fileToDownload.file").createNewFile();
        new File(serverFileSystem + "/newfolder").mkdirs();
        new File(serverFileSystem + "/newfolder/fileToUpload.file").createNewFile();
        new File(serverFileSystem + "/newfolder/text.pdf").createNewFile();
        new File(serverFileSystem + "/newfolder/text.txt").createNewFile();
        new File(serverFileSystem + "/newfolder/file.xml").createNewFile();
        new File(serverFileSystem + "/newfolder/file.java").createNewFile();
        new File(serverFileSystem + "/newfolder/newfolder2").mkdirs();
        new File(serverFileSystem + "/newfolder/newfolder2/text.pdf").createNewFile();
        new File(serverFileSystem + "/newfolder/newfolder2/text.txt").createNewFile();
        new File(serverFileSystem + "/newfolder/newfolder2/file.xml").createNewFile();
        new File(serverFileSystem + "/newfolder/newfolder2/file.java").createNewFile();
    }

    @Test
    public void whenReceiveFileThenHaveFileFromInputStream() throws IOException {
        File fileToReceive = createAndFillTestFile(serverFileSystem + "/newfolder/fileToUpload.file");
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(Files.readAllBytes(fileToReceive.toPath()));
        Socket socket = mock(Socket.class);
        when(socket.getInputStream()).thenReturn(byteArrayInputStream);
        Connection fakeConnection = new Connection(socket);
        File testFileWhichShouldBeDownloaded = new File(serverFileSystem + "/fileToUpload.file");

        FileLoader fileLoader = new FileLoader(fakeConnection);
        System.out.println(testFileWhichShouldBeDownloaded.exists());
        fileLoader.receiveFile(testFileWhichShouldBeDownloaded);
        System.out.println(testFileWhichShouldBeDownloaded.exists());

        boolean actual = testFileWhichShouldBeDownloaded.exists();
        boolean expected = true;

        assertThat(actual, is(expected));
    }

    @Test
    public void whenSendFileThenHaveOutputStreamWithFileData() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Socket socket = mock(Socket.class);
        when(socket.getOutputStream()).thenReturn(baos);
        Connection fakeConnection = new Connection(socket);
        File testFile = createAndFillTestFile(serverFileSystem + "/text.pdf");
        FileLoader fileLoader = new FileLoader(fakeConnection);
        fileLoader.sendFile(testFile);

        ByteArrayOutputStream expectedBaos = new ByteArrayOutputStream();
        expectedBaos.write(Files.readAllBytes(testFile.toPath()));
        String expected = expectedBaos.toString();
        String actual = baos.toString();

        assertThat(actual, is(expected));
    }



    public File createAndFillTestFile(String filePath) throws IOException {
        File sourceFile = new File(filePath);
        FileOutputStream fileOutputStream = new FileOutputStream(sourceFile);
        fileOutputStream.write("12345".getBytes());
        fileOutputStream.close();
        return sourceFile;
    }

    public Connection createFakeConnectionWithSourceFileInTheInputStream(File file) throws IOException {
        ByteArrayInputStream bais = new ByteArrayInputStream(FileUtils.readFileToByteArray(file));
        Socket socket = mock(Socket.class);
        when(socket.getInputStream()).thenReturn(bais);
        Connection connection = new Connection(socket);
        return connection;
    }

}