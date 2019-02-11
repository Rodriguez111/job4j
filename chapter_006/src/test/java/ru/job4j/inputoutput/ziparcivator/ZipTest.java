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

  private List<File> sourceFiles;
  private List<String> extensions;
  private List<File> actual;
  private List<File> expected;
  private Search search;
  private ZipInputStream zipInputStream;
  private  ZipEntry zipEntry;

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
    public void shouldReturnTheSameListOfFiles() throws IOException {
       actual = new ArrayList<>();
        zip.zip();
           zipInputStream = new ZipInputStream(new FileInputStream(destination));
            while ((zipEntry = zipInputStream.getNextEntry()) != null) {
                actual.add(new File(zipEntry.getName()));
            }
        search = new Search();
        extensions = new ArrayList<>();
        extensions.add("java");
        extensions.add("xml");
        sourceFiles = search.files(source, extensions, false);
        expected = new ArrayList<>();
        for (File eachFile : sourceFiles) {
            expected.add(new File(Paths.get(source).relativize(Paths.get(eachFile.getPath())).toString()));
        }
        assertThat(actual, is(expected));
    }

}