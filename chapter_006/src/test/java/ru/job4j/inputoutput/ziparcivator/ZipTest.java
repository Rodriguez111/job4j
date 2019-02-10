package ru.job4j.inputoutput.ziparcivator;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.inputoutput.Search;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class ZipTest {
  private String source = "c:/projects/job4j/";
    private String destination = "d:/project.zip";
  private Zip zip;

  @Before
  public void init() {
      String[] args = new String[7];
      args[0] = "-d";
      args[1] = source;
      args[2] = "-e";
      args[3] = "java";
      args[4] = "xml";
      args[5] = "-o";
      args[6] = destination;
      Zip.main(args);
      zip = new Zip();
  }



    @Test
    public void shouldReturnTheSameListOfFiles() {
        List<File> actual = new ArrayList<>();
        zip.zip();
        try {
            ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(destination));
            ZipEntry zipEntry = null;
            while ((zipEntry = zipInputStream.getNextEntry()) != null) {
                actual.add(new File(zipEntry.getName()));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Search search = new Search();
        List<String> extensions = new ArrayList<>();
        extensions.add("java");
        extensions.add("xml");
        List<File> sourceFiles = search.files(source, extensions, false);
        List<File> expected = new ArrayList<>();
        for (File eachFile : sourceFiles) {
            expected.add(new File(Paths.get(source).relativize(Paths.get(eachFile.getPath())).toString()));
        }
        assertThat(actual, is(expected));

    }

}