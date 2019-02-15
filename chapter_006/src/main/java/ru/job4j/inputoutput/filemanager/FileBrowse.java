package ru.job4j.inputoutput.filemanager;
/**
 * Responsible for browsing directories.
 */

import java.io.File;
import java.util.List;

public interface FileBrowse {
    List<File> goDown(File directory);
    List<File> goUp(File directory);

}
