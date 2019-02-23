package ru.job4j.inputoutput.filemanager;

import java.io.File;

/**
 * Responsible for uploading and downloading files
 */

public interface FileLoad {
    void sendFile(File file);

    void receiveFile(File file);
}
