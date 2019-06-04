package ru.job4j.cash.ver2;


import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class GetTextFromCashTest {
    private static final String TEMP_DIR = System.getProperty("java.io.tmpdir");
    private static final String TEST_DIR = TEMP_DIR + "/Test";
    private static final String TEST_FILE_NAME = "TestFile.txt";
    private static final String TEST_FILE_PATH = TEST_DIR + "/" + TEST_FILE_NAME;
    private static final String FILE_BODY = "This is the body of the test file";

    @Before
    public void initTestFile() {
        try (FileWriter fileWriter = new FileWriter(createFile())) {
            fileWriter.write(FILE_BODY);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public File createFile() throws IOException {
        File testDir = new File(TEST_DIR);
        testDir.mkdirs();
        File file = new File(TEST_FILE_PATH);
        file.createNewFile();
        return file;
    }

    @Test
    public void whenFileExistsThenReturnTextFromTheFileBody() {
        GetDataFromCash getDataFromCash = new GetTextFromCash(TEST_DIR);
        String actual =  getDataFromCash.get(TEST_FILE_NAME);

        String expected = FILE_BODY;

        assertThat(actual, is(expected));
    }

    @Test
    public void whenFileDoesNotExistsThenFileNotExistsException() {
        GetDataFromCash getDataFromCash = new GetTextFromCash(TEST_DIR);

        Throwable exception = assertThrows(FileNotExistsException.class, () -> {
            getDataFromCash.get("Not existing file");

        });
    }


}