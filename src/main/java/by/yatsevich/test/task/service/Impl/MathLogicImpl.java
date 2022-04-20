package by.yatsevich.test.task.service.Impl;

import by.yatsevich.test.task.entity.Number;
import by.yatsevich.test.task.exception.FileParsingException;
import by.yatsevich.test.task.service.FileParsing;
import by.yatsevich.test.task.service.MathLogic;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A singleton class using enum.
 * Class contains methods for: splitting the number is into degree,
 * processed of resulting number,
 * breaking number down into digits and degree.
 */

public enum MathLogicImpl implements MathLogic {
    INSTANCE;

    private static final String SPACE = " ";
    private static final String INVALID_NUMBER = "Нет такого числа...";
    private static final String EMPTY_STRING = "";
    private static final String MINUS = "минус";
    private static final String ZERO = "ноль";
    private static final String REGEX_REMOVING_UNNECESSARY_SPACE = "[\\s]{2,}";
    private static final String MESSAGE_IN_CASE_OF_EXCEPTION = "Файл %sне был найден.\n";
    private static final String NUMBER = "Число: ";

    private final FileParsing fileParsing = FileParsing.getInstance();

    /**
     * Method checked if number is valid, if equal to zero, if negative.
     * Then the number is divided by degrees.
     *
     * @param number that we want to convert to string format.
     * @return representation of a number in string format.
     */
    public String getNumberInString(BigInteger number) {

        StringBuilder stringFormatOfNumber = new StringBuilder();
        Number currentNumber = new Number(number);

        int currentDegree = currentNumber.getNumberOfDegree();
        int numberOfDigits = currentNumber.getNumberOfDigits();
        int numberOfDigitsInHighDegree = currentNumber.getNumberDigitsOfHighestDegree();

        List<Integer> digitsInOneDegree = new ArrayList<>();

        if (currentNumber.getDigits().stream().mapToInt(a -> a).sum() == 0
                && currentNumber.getDigits().size() > 1) {
            return INVALID_NUMBER;
        }

        if (number.intValue() == 0) return ZERO;

        if (currentNumber.isNegative()) stringFormatOfNumber.append(MINUS);

        try {

            for (int j = numberOfDigits - 1; j >= numberOfDigits - numberOfDigitsInHighDegree; j--) {

                digitsInOneDegree.add(currentNumber.getDigits().get(j));

            }

            stringFormatOfNumber.append(convertingNumbersToStringFormatByDegrees(digitsInOneDegree, currentDegree));
            stringFormatOfNumber.append(SPACE).append(fileParsing.convertDegreeOfNumberToString(currentDegree--, 0));
            digitsInOneDegree.clear();

            for (int i = numberOfDigits - 1 - numberOfDigitsInHighDegree; i > 0; i -= 3) {

                for (int k = i; k >= i - 2; k--) {
                    digitsInOneDegree.add(currentNumber.getDigits().get(k));
                }

                if (digitsInOneDegree.stream().mapToInt(a -> a).sum() == 0) {

                    currentDegree--;
                    digitsInOneDegree.clear();
                    continue;

                }

                stringFormatOfNumber.append(convertingNumbersToStringFormatByDegrees(digitsInOneDegree, currentDegree));
                stringFormatOfNumber.append(SPACE).append(fileParsing.convertDegreeOfNumberToString(currentDegree--, 0));
                digitsInOneDegree.clear();

            }
        } catch (FileParsingException e) {

            return String.format(MESSAGE_IN_CASE_OF_EXCEPTION, e.getMessage()) + NUMBER + number;

        }

        return stringFormatOfNumber.toString().replaceAll(REGEX_REMOVING_UNNECESSARY_SPACE, SPACE).trim();
    }

    /**
     * Passing the number by digit to
     *
     * @param digits       arrayList of digits in one degree.
     * @param currenDegree degree of digits in firs param.
     * @return representation of a arrayList of digits in string format.
     * @throws FileParsingException
     * @see FileParsingImpl#convertDigitToString(int digit, int digitPosition, int currentDegree).
     */
    private StringBuilder convertingNumbersToStringFormatByDegrees(List<Integer> digits, int currenDegree) throws FileParsingException {

        StringBuilder stringFormatOfNumberInOneDegree = new StringBuilder();
        List<Integer> digitsAfterTransformation = transformationNumbersInOneDegree(digits);

        if (digits.size() == digitsAfterTransformation.size()) {

            for (int i = digits.size(); i > 0; i--) {

                stringFormatOfNumberInOneDegree
                        .append(SPACE)
                        .append(fileParsing
                                .convertDigitToString(digits
                                        .get(digits.size() - i), i, currenDegree));

            }

        } else {

            for (int i = digitsAfterTransformation.size(); i > 0; i--) {

                stringFormatOfNumberInOneDegree
                        .append(SPACE)
                        .append(fileParsing
                                .convertDigitToString(digitsAfterTransformation
                                        .get(digitsAfterTransformation.size() - i), i + 1, currenDegree));

            }

        }

        return stringFormatOfNumberInOneDegree;
    }

    /**
     * Checking a number for the presence of numbers from 10 to 20.
     * If yes, then an array is returned: a higher-degree digit and a number from 10 to 20.
     * Or just number from 10 to 20.
     *
     * @param digits arrayList of digits in one degree.
     * @return converted array of digits.
     */
    private List<Integer> transformationNumbersInOneDegree(List<Integer> digits) {

        List<Integer> numbersAfterTransformation = new ArrayList<>();

        int numberInCurrentDegre = Integer.parseInt(digits
                .stream()
                .map(Object::toString)
                .collect(Collectors.joining(EMPTY_STRING)));

        boolean isDigitsBetweenTenTwenty = numberInCurrentDegre % 100 > 10 && numberInCurrentDegre % 100 < 20;

        if (isDigitsBetweenTenTwenty) {

            if (digits.size() == 2) {
                numbersAfterTransformation.add(numberInCurrentDegre);
                return numbersAfterTransformation;
            }

            numbersAfterTransformation.add(numberInCurrentDegre / 100);
            numbersAfterTransformation.add(numberInCurrentDegre % 100);
            return numbersAfterTransformation;

        }
        return digits;
    }

}

