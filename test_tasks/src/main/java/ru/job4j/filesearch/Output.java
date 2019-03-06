package ru.job4j.filesearch;

import java.io.File;
import java.util.List;

public interface Output {
    void output(List<File> fileList);
}
