package by.yatsevich.test.task.service;

import by.yatsevich.test.task.exception.FileParsingException;
import by.yatsevich.test.task.service.Impl.FileParsingImpl;

public interface FileParsing {

    String convertDigitToString(int digit, int digitPosition, int currentDegree) throws FileParsingException;

    String convertDegreeOfNumberToString(int digit, int degreeOfNumber) throws FileParsingException;

    static FileParsing getInstance(){
        return FileParsingImpl.INSTANCE;
    }

}
