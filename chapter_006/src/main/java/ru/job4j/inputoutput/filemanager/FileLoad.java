package ru.job4j.inputoutput.filemanager;

import java.io.File;

/**
 * Responsible for uploading and downloading files
 */

public interface FileLoad {
    void uploadFile(File file);

    File downloadFile();
}
