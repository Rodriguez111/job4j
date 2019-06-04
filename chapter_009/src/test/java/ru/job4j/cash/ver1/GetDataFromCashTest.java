package ru.job4j.cash.ver1;

import ru.job4j.cash.ver1.cashfinders.FileFinder;
import ru.job4j.cash.ver1.cashmakers.CashMaker;
import ru.job4j.cash.ver1.cashmakers.FileCashMaker;
import ru.job4j.cash.ver1.exceptions.FileNotExistsException;
import ru.job4j.cash.ver1.resulthandlers.SearchResultHandler;
import ru.job4j.cash.ver1.resulthandlers.TextReader;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.ref.SoftReference;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiFunction;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class GetDataFromCashTest {
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
        CashMaker<File> cashMaker = new FileCashMaker(TEST_DIR);
        BiFunction<String, Map<String, SoftReference<File>>, Optional<File>> cashSearcher = new FileFinder();
        SearchResultHandler<File, String> resultHandler = new TextReader();
        GetDataFromCash<String, File, String> getDataFromCash = new GetDataFromCash<>(cashMaker, cashSearcher, resultHandler);

        String actual = getDataFromCash.getData(TEST_FILE_NAME);
        String expected = FILE_BODY;

        assertThat(actual, is(expected));
    }

    @Test
    public void whenFileDoesNotExistsThenFileNotExistsException() {
        CashMaker<File> cashMaker = new FileCashMaker(TEST_DIR);
        BiFunction<String, Map<String, SoftReference<File>>, Optional<File>> cashSearcher = new FileFinder();
        SearchResultHandler<File, String> resultHandler = new TextReader();
        GetDataFromCash<String, File, String> getDataFromCash = new GetDataFromCash<>(cashMaker, cashSearcher, resultHandler);

        Throwable exception = assertThrows(FileNotExistsException.class, () -> {
            getDataFromCash.getData("Not existing file");
        });
    }
}