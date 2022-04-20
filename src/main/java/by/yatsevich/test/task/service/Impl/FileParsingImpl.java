package by.yatsevich.test.task.service.Impl;

import by.yatsevich.test.task.service.FileName;
import by.yatsevich.test.task.service.FileParsing;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * A singleton class using enum.
 * Class contain methods for: converting digit to string,
 * converting degree of number to string,
 * converter for digit and degree to string,
 * determining declination of number degree,
 * file selection.
 */

public enum FileParsingImpl implements FileParsing {
    INSTANCE;

    private static final String REGEX_TO_DETECT_RIGHT_LINE = "^%s-\\S++";
    private static final String REGEX_TO_HIGHLIGHT_NUMERAL = "^%s-";
    private static final String EMPTY_STRING = "";
    private static final String COMMA = ",";
    private static int previousDigit;

    /**
     * Choosing the necessary declination of the digit.
     * @param digit
     * @param digitPosition hundreds, tens or units.
     * @param currentDegree
     * @return digit in string format.
     */
    @Override
    public String convertDigitToString(int digit, int digitPosition, int currentDegree) {

        previousDigit = digit;

        if ((digit == 1 || digit == 2) && digitPosition == 1 && currentDegree == 2) {

            return converter(digit, digitPosition).split(COMMA)[1];

        } else {

            return converter(digit, digitPosition).split(COMMA)[0];

        }
    }

    /**
     * Converter degree to string format.
     * @param digit
     * @param degreeOfNumber
     * @return degree in string format.
     */
    @Override
    public String convertDegreeOfNumberToString(int digit, int degreeOfNumber) {

        String[] degreesOfNumberInStringFormat = converter(digit, degreeOfNumber).split(COMMA);

        if (degreesOfNumberInStringFormat.length == 1) {

            return EMPTY_STRING;

        } else {

            return determineDeclinationDegreeOfNumber(previousDigit, degreesOfNumberInStringFormat);

        }
    }

    /**
     * Search for the required string representation of a number or degree in the desired file.
     * @param digit
     * @param digitPosition
     * @return
     */
    private String converter(int digit, int digitPosition) {

        String regExToDetectRightLine = String.format(REGEX_TO_DETECT_RIGHT_LINE, digit);
        String regExToHighlightNumeral = String.format(REGEX_TO_HIGHLIGHT_NUMERAL, digit);
        String stringFormat = EMPTY_STRING;

        try (Scanner scanner = new Scanner(fileSelection(digit, digitPosition))) {

            while (scanner.hasNextLine()) {

                String currentLine = scanner.nextLine();

                if (Pattern
                        .compile(regExToDetectRightLine)
                        .matcher(currentLine)
                        .matches()) {

                    stringFormat = currentLine.replaceAll(regExToHighlightNumeral, EMPTY_STRING);

                }
            }
        } catch (FileNotFoundException e) {

            e.printStackTrace();

        }

        return stringFormat;
    }

    /**
     * Determining the correct declension of the degree of a number.
     * @param digit
     * @param declinations a set of degrees with different declension,
     * from which need to choose the correct one.
     * @return correct declension of the degree.
     */
    private String determineDeclinationDegreeOfNumber(int digit, String[] declinations) {

        if (digit == 1) {

            return declinations[0];

        } else if (digit > 1 && digit < 5) {

            return declinations[1];

        } else {

            return declinations[2];

        }

    }

    /**
     * 
     * @param digit
     * @param digitPosition hundreds, tens or units or degrees.
     * @return file with specific numerals.
     */
    private File fileSelection(int digit, int digitPosition) {

        switch (digitPosition) {
            case 1:
                return new File(FileName.UNITS.getFileName());
            case 2:
                if (digit > 10 && digit < 20) {

                    return new File(FileName.FROM_TEN_TO_TWENTY.getFileName());

                }
                return new File(FileName.TENS.getFileName());
            case 3:
                return new File(FileName.HUNDREDS.getFileName());
            default:
                return new File(FileName.POWERS_OF_NUMBER.getFileName());
        }

    }

}

