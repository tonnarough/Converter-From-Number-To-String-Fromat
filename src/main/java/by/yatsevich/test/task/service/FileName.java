package by.yatsevich.test.task.service;

public enum FileName {

    UNITS("units"),
    TENS("tens"),
    HUNDREDS("hundreds"),
    FROM_TEN_TO_TWENTY("fromTenToTwenty"),
    POWERS_OF_NUMBER("powersOfNumber");

    private final String fileName;

    FileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

}
