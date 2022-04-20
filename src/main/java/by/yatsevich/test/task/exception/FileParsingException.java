package by.yatsevich.test.task.exception;

public class FileParsingException extends Exception {

    private static final String REGEX_REMOVING_UNNECESSARY_PART_OF_MESSAGE = "\\([^()]*\\)";
    private static final String EMPTY_STRING = "";

    public FileParsingException() {
    }

    public FileParsingException(String message) {
        super(message);
    }

    public FileParsingException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileParsingException(Throwable cause) {
        super(cause);
    }

    @Override
    public String getMessage() {
        return super.getMessage().replaceAll(REGEX_REMOVING_UNNECESSARY_PART_OF_MESSAGE, EMPTY_STRING);
    }
}
