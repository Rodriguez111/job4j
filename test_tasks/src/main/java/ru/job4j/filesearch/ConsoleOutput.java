package ru.job4j.filesearch;

import java.io.File;
import java.util.List;

public class ConsoleOutput implements Output {
    @Override
    public void output(List<File> fileList) {
        fileList.stream().forEach(System.out::println);
    }
}
