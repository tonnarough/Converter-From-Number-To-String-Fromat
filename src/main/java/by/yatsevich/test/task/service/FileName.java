package by.yatsevich.test.task.service;

import java.io.File;

public enum FileName {

    UNITS("numerals" + File.separator + "units"),
    TENS("numerals" + File.separator + "tens"),
    HUNDREDS("numerals" + File.separator + "hundreds"),
    FROM_TEN_TO_TWENTY("numerals" + File.separator + "fromTenToTwenty"),
    POWERS_OF_NUMBER("numerals" + File.separator + "degreesOfNumber");

    private final String fileName;

    FileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

}
