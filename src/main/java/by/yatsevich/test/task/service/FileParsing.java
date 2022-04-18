package by.yatsevich.test.task.service;

public interface FileParsing {

    String convertDigitToString(int digit, int digitPosition, int currentDegree);

    String convertDegreeOfNumberToString(int digit, int degreeOfNumber);

    static FileParsing getInstance(){
        return FileParsingImpl.INSTANCE;
    }

}
