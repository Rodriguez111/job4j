package ru.job4j.inputoutput.filemanager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class FileBrowserTest {
    private final String path = System.getProperty("java.io.tmpdir");
    private final String serverFileSystem = path + "/00TestServer";

    @Before
    public void init() throws IOException {
        createTestFilesStructure();
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
    public void shouldReturnTrueWhenEnterDirectory() {
        FileBrowser fileBrowser = new FileBrowser();
       boolean actual = fileBrowser.goDown(new File(serverFileSystem),  new File(serverFileSystem + "/newfolder"));
       boolean expected = true;

       assertThat(actual, is(expected));
    }

    @Test
    public void shouldReturnTrueWhenExitDirectory() {
        FileBrowser fileBrowser = new FileBrowser();
        fileBrowser.goDown(new File(serverFileSystem),  new File(serverFileSystem + "/newfolder"));
        boolean actual = fileBrowser.goUp(new File(serverFileSystem),  new File(serverFileSystem + "/newfolder"));
        boolean expected = true;

        assertThat(actual, is(expected));
    }

    @Test
    public void shouldReturnTrueWhenDirectoryContainsFile() {
        FileBrowser fileBrowser = new FileBrowser();
        boolean actual =  fileBrowser.directoryContainsFile(new File(serverFileSystem), new File(serverFileSystem + "/text.pdf"));
        boolean expected = true;
        assertThat(actual, is(expected));
    }

}