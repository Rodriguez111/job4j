package ru.job4j.inputoutput.filemanager;
/**
 * Responsible for browsing directories.
 */

import java.io.File;

public interface FileBrowse {
    boolean goDown(File currentDir, File directory);
    boolean goUp(File root, File currentDirectory);

}
