package ru.job4j.inputoutput.filemanager;

import org.apache.commons.io.FileUtils;
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

public class FileManagerTest {
    private final String path = System.getProperty("java.io.tmpdir");
    private final String serverFileSystem = path + "/00TestServer";


    private final static String LINE_SEPARATOR = System.lineSeparator();
    private final static String DOUBLE_SEPARATOR = "==============================";
    private final static String SINGLE_SEPARATOR = "------------------------------";
    private final static String ROOT = "root/";

    Socket socket = new Socket();
    Connection connection = new Connection(socket);

    @Before
    public void init() throws IOException {
        createTestFilesStructure();
        File testFileThatShouldBeUploaded =  new File(serverFileSystem + "/fileToUpload.file");
        deleteTestFileIfExists(testFileThatShouldBeUploaded);
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
  public void whenGetListOfFilesThenReturnDirectoryContent() {
        FileManager fileManager = new FileManager(new File(serverFileSystem), connection);
        String actual = fileManager.getListOfFiles();

        StringBuilder sb = new StringBuilder();
        sb.append(DOUBLE_SEPARATOR)
                .append(LINE_SEPARATOR)
                .append(ROOT)
                .append(LINE_SEPARATOR)
                .append(SINGLE_SEPARATOR)
                .append(LINE_SEPARATOR)
                .append("newfolder")
                .append(LINE_SEPARATOR)
                .append("file.java")
                .append(LINE_SEPARATOR)
                .append("file.xml")
                .append(LINE_SEPARATOR)
                .append("fileToDownload.file")
                .append(LINE_SEPARATOR)
                .append("text.pdf")
                .append(LINE_SEPARATOR)
                .append("text.txt")
                .append(LINE_SEPARATOR)
                .append(DOUBLE_SEPARATOR);
        String expected = sb.toString();

        assertThat(actual, is(expected));
    }

    @Test
    public void whenEnterDirectoryThenReturnTrue() {
        FileManager fileManager = new FileManager(new File(serverFileSystem), connection);
        boolean actual = fileManager.enterDirectory("newfolder");

        boolean expected = true;
        assertThat(actual, is(expected));
    }

    @Test
    public void whenEnterDirectoryWhichNotExistsThenReturnFalse() {
        FileManager fileManager = new FileManager(new File(serverFileSystem), connection);
        boolean actual = fileManager.enterDirectory("foldernotexists");

        boolean expected = false;
        assertThat(actual, is(expected));
    }

    @Test
    public void whenExitDirectoryDirectoryThenReturnTrue() {
        FileManager fileManager = new FileManager(new File(serverFileSystem), connection);
        fileManager.enterDirectory("newfolder");
        boolean actual = fileManager.exitDirectory();

        boolean expected = true;
        assertThat(actual, is(expected));
    }

    @Test
    public void whenExitDirectoryRootDirectoryThenReturnFalse() {
        FileManager fileManager = new FileManager(new File(serverFileSystem), connection);
        boolean actual = fileManager.exitDirectory();

        boolean expected = false;
        assertThat(actual, is(expected));
    }

    @Test
    public void whenFileExistsInDirectoryThenReturnTrue() {
        FileManager fileManager = new FileManager(new File(serverFileSystem), connection);
        boolean actual = fileManager.fileExistsInDirectory("file.java");

        boolean expected = true;
        assertThat(actual, is(expected));
    }

    @Test
    public void whenFileNotExistsInDirectoryThenReturnFalse() {
        FileManager fileManager = new FileManager(new File(serverFileSystem), connection);
        boolean actual = fileManager.fileExistsInDirectory("fileNotExists.java");

        boolean expected = false;
        assertThat(actual, is(expected));
    }

    @Test
    public void whenUploadFileThenFileExistsInCurrentDirectory() throws IOException {
      File sourceFile = createAndFillTestFile(serverFileSystem + "/newfolder/fileToUpload.file");
      long fileSize = sourceFile.length();
      File testFileThatShouldBeUploaded =  new File(serverFileSystem + "/fileToUpload.file");

      Connection fakeConnection = createFakeConnectionWithSourceFileInTheInputStream(sourceFile);

      FileManager fileManager = new FileManager(new File(serverFileSystem), fakeConnection);
      fileManager.uploadFile(fakeConnection, testFileThatShouldBeUploaded.getName(), fileSize);
      boolean actual = fileManager.fileExistsInDirectory(testFileThatShouldBeUploaded.getName());

      boolean expected = true;
      assertThat(actual, is(expected));
    }

    @Test
    public void whenDownloadFileThenFileExistsInDestinationDirectory() throws IOException {
        File fileToDownload = createAndFillTestFile(serverFileSystem + "/fileToDownload.file");


        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Socket socket = mock(Socket.class);
        when(socket.getOutputStream()).thenReturn(baos);
        Connection fakeConnection = new Connection(socket);

        FileManager fileManager = new FileManager(new File(serverFileSystem), fakeConnection);
        fileManager.downloadFile(fileToDownload.getName());

        String actual = baos.toString();
        ByteArrayOutputStream expectedBaos = new ByteArrayOutputStream();
        expectedBaos.write(Files.readAllBytes(fileToDownload.toPath()));
        String expected = expectedBaos.toString();
        assertThat(actual, is(expected));
    }



    public File createAndFillTestFile(String filePath) throws IOException {
        File sourceFile = new File(filePath);
        FileOutputStream fileOutputStream = new FileOutputStream(sourceFile);
        fileOutputStream.write("123".getBytes());
        fileOutputStream.close();
        return sourceFile;
    }

    public void deleteTestFileIfExists(File file) {
        if (file.exists()) {
            file.delete();
        }
    }

    public Connection createFakeConnectionWithSourceFileInTheInputStream(File file) throws IOException {
     ByteArrayInputStream bais = new ByteArrayInputStream(FileUtils.readFileToByteArray(file));
     Socket socket = mock(Socket.class);
     when(socket.getInputStream()).thenReturn(bais);
     Connection connection = new Connection(socket);
     return connection;
    }



}