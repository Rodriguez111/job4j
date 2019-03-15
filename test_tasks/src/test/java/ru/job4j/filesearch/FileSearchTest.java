package ru.job4j.filesearch;

import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;


public class FileSearchTest {
    private final String path = System.getProperty("java.io.tmpdir");
    private final String source = path + "/00Test";
    private final String outputDir = path + "/00Output";
    private final String outputFile = outputDir + "/log.txt";
    private final String ls = System.lineSeparator();

    public void createTestFilesStructure() throws IOException {
        new File(source).mkdirs();
        new File(outputDir).mkdirs();
        new File(outputFile).createNewFile();
        new File(source + "/text.pdf").createNewFile();
        new File(source + "/text.txt").createNewFile();
        new File(source + "/file.java").createNewFile();
        new File(source + "/file.xml").createNewFile();
        new File(source + "/newfolder").mkdirs();
        new File(source + "/newfolder/text.pdf").createNewFile();
        new File(source + "/newfolder/text.txt").createNewFile();
        new File(source + "/newfolder/file.xml").createNewFile();
        new File(source + "/newfolder/file.java").createNewFile();
        new File(source + "/newfolder/newfolderztext2").mkdirs();
        new File(source + "/newfolder/newfolderztext2/text.pdf").createNewFile();
        new File(source + "/newfolder/newfolderztext2/text.txt").createNewFile();
        new File(source + "/newfolder/newfolderztext2/file.xml").createNewFile();
        new File(source + "/newfolder/newfolderztext2/file.java").createNewFile();
    }

    @Before
    public void init() throws IOException {
        createTestFilesStructure();
        new File(source + "/newfolder/newfolderztext2/file.java").createNewFile();
    }

    public String[] generateArgsByMask() {
        String[] args = new String[7];
        args[0] = "-d";
        args[1] = "--" + source;
        args[2] = "-n";
        args[3] = "--zte";
        args[4] = "--m";
        args[5] = "-o";
        args[6] = "--" + outputFile;
        return args;
    }

    public String[] generateArgsByFullName() {
        String[] args = new String[7];
        args[0] = "-d";
        args[1] = "--" + source;
        args[2] = "-n";
        args[3] = "--newfolderztext2";
        args[4] = "--f";
        args[5] = "-o";
        args[6] = "--" + outputFile;
        return args;
    }

    public String[] generateArgsByRegularExpression() {
        String[] args = new String[7];
        args[0] = "-d";
        args[1] = "--" + source;
        args[2] = "-n";
        args[3] = "--.*[0-9]$";
        args[4] = "--r";
        args[5] = "-o";
        args[6] = "--" + outputFile;
        return args;
    }


    @Test
    public void shouldReturnOneResultWhenSearchByMask() throws IOException {
        Args.main(generateArgsByMask());
        Args args = new Args();
        FileSearch fileSearch = new FileSearch(args);
        fileSearch.search();

        BufferedReader br = new BufferedReader(new FileReader(outputFile));
        StringBuilder sb = new StringBuilder();
        String read = br.readLine();
        while (read != null) {
            sb.append(read + ls);
            read = br.readLine();
        }
        br.close();

        String actual = sb.toString();

        sb.setLength(0);
        sb.append(new File(source + "/newfolder/newfolderztext2").getAbsolutePath())
                .append(ls);


        String expected = sb.toString();
        assertThat(actual, is(expected));
    }


    @Test
    public void shouldReturnOneResultWhenSearchByFullName() throws IOException {
        Args.main(generateArgsByFullName());
        Args args = new Args();
        FileSearch fileSearch = new FileSearch(args);
        fileSearch.search();

        BufferedReader br = new BufferedReader(new FileReader(outputFile));
        StringBuilder sb = new StringBuilder();
        String read = br.readLine();
        while (read != null) {
            sb.append(read + ls);
            read = br.readLine();
        }
        br.close();

        String actual = sb.toString();

        sb.setLength(0);
        sb.append(new File(source + "/newfolder/newfolderztext2").getAbsolutePath())
                .append(ls);

        String expected = sb.toString();
        assertThat(actual, is(expected));

    }

    @Test
    public void shouldReturnOneResultWhenSearchByRegularExpression() throws IOException {
        Args.main(generateArgsByRegularExpression());
        Args args = new Args();
        FileSearch fileSearch = new FileSearch(args);
        fileSearch.search();

        BufferedReader br = new BufferedReader(new FileReader(outputFile));
        StringBuilder sb = new StringBuilder();
        String read = br.readLine();
        while (read != null) {
            sb.append(read + ls);
            read = br.readLine();
        }
        br.close();

        String actual = sb.toString();

        sb.setLength(0);
        sb.append(new File(source + "/newfolder/newfolderztext2").getAbsolutePath())
                .append(ls);

        String expected = sb.toString();
        assertThat(actual, is(expected));
    }

}