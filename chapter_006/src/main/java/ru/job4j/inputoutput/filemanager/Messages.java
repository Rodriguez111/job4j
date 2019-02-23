package ru.job4j.inputoutput.filemanager;

public enum Messages {
    WRONG_DIR("ERROR! Sorry, wrong directory name."),
    WRONG_FILE ("ERROR! Sorry, wrong file name."),
    WRONG_COMMAND ("ERROR! Unknown command."),
    CAN_NOT_GO_UP ("ERROR! This is root directory."),
    TOO_LARGE_FILE ("ERROR! Sorry, file size is too large."),
    FILE_ALREADY_EXISTS ("WARNING! File already exists in current directory. Overwrite? y - overwrite, any other key - do not."),
    FILE_DOES_NOT_EXIST ("ERROR! File does not exist."),
    FILE_TRANSFER_START ("INFO! File transfer started..."),
    FILE_UPLOAD_SUCCESS ("OK! File was successfully uploaded."),
    FILE_DOWNLOAD_SUCCESS ("OK! File was successfully downloaded."),
    CLIENT_REJECTED ("INFO! Client rejected to execute command."),
    EXIT_COMMAND_SERVER ("INFO! Exit command received from client, server is shutting down."),
    MENU_REQUEST ("List of files and menu request."),
    END_OF_FRAME ("*");

    final String message;

    Messages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
