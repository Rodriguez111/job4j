package ru.job4j.inputoutput.filemanager.messages;

public enum MessageType {
    WRONG_DIR("Sorry, wrong directory name"),
    CAN_NOT_GO_UP ("This is root directory"),
    TOO_LARGE_FILE ("Sorry, file size is too large"),
    FILE_ALREADY_EXISTS ("File already exists in current directory"),
    FILE_TRANSFER_START ("File transfer started..."),
    FILE_UPLOAD_SUCCESS ("File was successfully uploaded");



    final String message;

    MessageType(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
