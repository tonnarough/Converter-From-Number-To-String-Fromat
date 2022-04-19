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

    private final FileParsing fileParsing = FileParsing.getInstance();

    public StringBuilder getNumberInString(BigInteger number) {

        StringBuilder stringFormatOfNumber = new StringBuilder();
        Number currentNumber = determiningPropertiesOfNumber(number);
        int currentDegree = currentNumber.getNumberOfDegree();
        int numberOfDigits = currentNumber.getNumberOfDigits();
        int numberOfDigitsInHighDegree = currentNumber.getNumberDigitsOfHighestOrder();
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

        return stringFormatOfNumber;
    }

    private StringBuilder convertingNumbersToStringFormatByDegrees(List<Integer> digits, int currenDegree) {

        StringBuilder stringFormatOfNumberInOneDegree = new StringBuilder();
        List<Integer> digitsAfterTransformation = transformationNumbersInOneDegree(digits);


        if (digits.size() == digitsAfterTransformation.size()) {

            for (int i = digits.size(); i > 0; i--) {

                if (digits.get(digits.size() - i) == 0){
                    continue;
                }

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

        if (numberInCurrentDegre > 10 && numberInCurrentDegre < 20) {

            numbersAfterTransformation.add(numberInCurrentDegre);
            return numbersAfterTransformation;

        } else if (numberInCurrentDegre >= 100 && numberInCurrentDegre % 100 > 10 && numberInCurrentDegre % 100 < 20) {

            numbersAfterTransformation.add(numberInCurrentDegre / 100);
            numbersAfterTransformation.add(numberInCurrentDegre % 100);
            return numbersAfterTransformation;

        } else {

            return digits;

        }

    }

    private Number determiningPropertiesOfNumber(BigInteger number) {

        Number currentNumber = new Number(number);
        currentNumber.setNumberOfDigits(String.valueOf(number).length());

        for (int i = 0; i < currentNumber.getNumberOfDigits(); i++) {

            currentNumber.setDigits(number.remainder(BigInteger.TEN).intValue());
            number = number.divide(BigInteger.TEN);

        }

        if (currentNumber.getNumberOfDigits() % 3 == 0) {

            currentNumber.setNumberOfDegree(currentNumber.getNumberOfDigits() / 3);

        } else {

            currentNumber.setNumberOfDegree((currentNumber.getNumberOfDigits() / 3) + 1);

        }
        currentNumber.setNumberDigitsOfHighestOrder(currentNumber.getNumberOfDigits() % 3);

        return currentNumber;
    }

}

