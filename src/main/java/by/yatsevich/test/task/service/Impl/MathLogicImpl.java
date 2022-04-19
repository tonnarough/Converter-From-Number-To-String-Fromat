package by.yatsevich.test.task.service.Impl;

import by.yatsevich.test.task.entity.Number;
import by.yatsevich.test.task.service.FileParsing;
import by.yatsevich.test.task.service.MathLogic;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public enum MathLogicImpl implements MathLogic {
    INSTANCE;

    private static final String SPACE = " ";
    private static final String EMPTY_STRING = "";
    private static final String MINUS = "минус";
    private static final String REGEX_REMOVING_UNNECESSARY_SPACE = "[\\s]{2,}";

    private final FileParsing fileParsing = FileParsing.getInstance();

    public String getNumberInString(BigInteger number) {

        StringBuilder stringFormatOfNumber = new StringBuilder();
        Number currentNumber = new Number(number);

        if (!currentNumber.isPositive()){

            stringFormatOfNumber.append(MINUS);

        }

        int currentDegree = currentNumber.getNumberOfDegree();
        int numberOfDigits = currentNumber.getNumberOfDigits();
        int numberOfDigitsInHighDegree = currentNumber.getNumberDigitsOfHighestDegree();

        List<Integer> digitsInOneDegree = new ArrayList<>();

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

        return stringFormatOfNumber.toString().replaceAll(REGEX_REMOVING_UNNECESSARY_SPACE, SPACE);
    }

    private StringBuilder convertingNumbersToStringFormatByDegrees(List<Integer> digits, int currenDegree) {

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

    private List<Integer> transformationNumbersInOneDegree(List<Integer> digits) {

        List<Integer> numbersAfterTransformation = new ArrayList<>();

        int numberInCurrentDegre = Integer.parseInt(digits
                .stream()
                .map(Object::toString)
                .collect(Collectors.joining(EMPTY_STRING)));

        boolean isDigitsMultipleOfTen = numberInCurrentDegre % 10 == 0;
        boolean isDigitsBetweenTenTwenty = numberInCurrentDegre % 100 > 10 && numberInCurrentDegre % 100 < 20;

        if (digits.size() == 2 && (isDigitsBetweenTenTwenty || isDigitsMultipleOfTen)){

            numbersAfterTransformation.add(numberInCurrentDegre);
            return numbersAfterTransformation;

        } else if (digits.size() == 3 && (isDigitsBetweenTenTwenty || isDigitsMultipleOfTen)) {

            numbersAfterTransformation.add(numberInCurrentDegre / 100);
            numbersAfterTransformation.add(numberInCurrentDegre % 100);
            return numbersAfterTransformation;

        } else {

            return digits;

        }

    }

}

