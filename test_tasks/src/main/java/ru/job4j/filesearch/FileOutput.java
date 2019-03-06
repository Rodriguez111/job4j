package ru.job4j.filesearch;

import ru.job4j.filesearch.exceptions.ExceptionsHandler;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class FileOutput implements Output {
    private final ExceptionsHandler eh = new ExceptionsHandler();
    private final File outputFile;

    public FileOutput(File outputFile) {
        this.outputFile = outputFile;
    }

    @Override
    public void output(List<File> fileList) {

      try (FileWriter fileWriter = new FileWriter(outputFile)) {
          fileList.forEach(file -> eh.handleException(() -> fileWriter.write(file.getAbsolutePath() + System.lineSeparator())));
      } catch (IOException e) {
          e.printStackTrace();
      }
    }
}
