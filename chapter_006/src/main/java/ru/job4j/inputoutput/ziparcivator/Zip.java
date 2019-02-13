package ru.job4j.inputoutput.ziparcivator;

/**
 * 1. Задана директория проекта, например: c:\project\job4j\
 * 2. В качестве ключей передаются расширения файлов, которые не должны попасть в архив.
 * 3. Архив должен сохранять структуру проекта.
 * 4. Запуск проекта java -jar pack.jar -d c:\project\job4j\ -e java.xml -o project.zip
 *
 * java -jar pack.jar - Это собранный jar.
 *
 * -d - directory - которую мы ходим архивировать
 * -e - exclude - исключить файлы *.xml
 * -o - output - во что мы архивируем.
 *
 * У вас должен быть класс new Args(agrs)
 *
 * с методами directory() extensions() output();
 *
 * 5. Для архивации использовать классы https://docs.oracle.com/javase/7/docs/api/java/util/zip/ZipOutputStream.html
 */


import ru.job4j.inputoutput.Search;
import java.io.*;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    private static String source;
    private static String outputZip;
    private static List<String> extensions;


    public static void main(String[] args) {
        Args arguments = new Args(args);
        source = arguments.directory();
        outputZip = arguments.output();
        extensions = arguments.extensions();
    }



    public void zip() {
        try (ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(outputZip))) {
            Search filesSearch = new Search();
            List<File> listOfFilesToZip = filesSearch.files(source, extensions, false);
            for (File eachFile : listOfFilesToZip) {
                ZipEntry zipEntry = new ZipEntry(Paths.get(source).relativize(Paths.get(eachFile.getPath())).toString());
                zipOutputStream.putNextEntry(zipEntry);
                FileInputStream fis = new FileInputStream(eachFile);
                write(fis, zipOutputStream);
        }

      }  catch (IOException e) {
            e.printStackTrace();
      }
    }

  private void write(InputStream is, OutputStream os) {
            try {
                byte[] buffer = new byte[1024];
                int bytesRead = is.read(buffer);
                while (bytesRead != -1) {
                    os.write(buffer, 0, bytesRead);
                    bytesRead = is.read(buffer);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
  }


}
