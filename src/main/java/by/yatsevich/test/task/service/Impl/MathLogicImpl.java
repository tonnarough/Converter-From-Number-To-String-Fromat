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

        StringBuilder stringBuilder = new StringBuilder();
        Number currentNumber = determiningPropertiesOfNumber(number);
        int currentDegree = currentNumber.getNumberOfClasses();
        int count = 0;
        List<Integer> digitsInOneDegree = new ArrayList<>();

        for (int i = currentNumber.getNumberOfDigits() - 1; i >= 0; i--) {

            if (i == 0) {

                count++;
                digitsInOneDegree.add(currentNumber.getDigits().get(i));

            }

            if (count == currentNumber.getNumberDigitsOfHighestOrder() && count != 0
                    && i == currentNumber.getNumberOfDigits() - 1 - currentNumber.getNumberDigitsOfHighestOrder()
                    || count % 3 == 0 && count != 0) {

                stringBuilder.append(convertingNumbersToStringFormatByClasses(digitsInOneDegree, currentDegree));
                stringBuilder.append(SPACE).append(fileParsing.convertDegreeOfNumberToString(currentDegree--, 0));
                digitsInOneDegree.clear();
                count = 0;

            }

            count++;
            digitsInOneDegree.add(currentNumber.getDigits().get(i));

        }

        return stringBuilder;
    }

    private StringBuilder convertingNumbersToStringFormatByClasses(List<Integer> digits, int currenDegree) {

        StringBuilder stringBuilder = new StringBuilder();
        List<Integer> digitsAfterTransformation = transformationNumbersInOneDegree(digits);

        if(digits.size() == digitsAfterTransformation.size()) {

            for (int i = digits.size(); i > 0; i--) {

                stringBuilder
                        .append(SPACE)
                        .append(fileParsing
                                .convertDigitToString(digits
                                        .get(digits.size() - i), i, currenDegree));

            }

        } else {

            for (int i = digitsAfterTransformation.size(); i > 0; i--) {

                stringBuilder
                        .append(SPACE)
                        .append(fileParsing
                                .convertDigitToString(digitsAfterTransformation
                                        .get(digitsAfterTransformation.size() - i), i + 1, currenDegree));

            }

        }

        return stringBuilder;
    }

    private List<Integer> transformationNumbersInOneDegree(List<Integer> digits) {

        List<Integer> numbersAfterDefinition = new ArrayList<>();

        int numberInCurrentDegre = Integer.parseInt(digits
                .stream()
                .map(Object::toString)
                .collect(Collectors.joining(EMPTY_STRING)));

        if (numberInCurrentDegre > 10 && numberInCurrentDegre < 20) {

            numbersAfterDefinition.add(numberInCurrentDegre);
            return numbersAfterDefinition;

        } else if(numberInCurrentDegre >= 100 && numberInCurrentDegre % 100 > 10 && numberInCurrentDegre % 100 < 20){

            numbersAfterDefinition.add(numberInCurrentDegre / 100);
            numbersAfterDefinition.add(numberInCurrentDegre % 100);
            return numbersAfterDefinition;

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

            currentNumber.setNumberOfClasses(currentNumber.getNumberOfDigits() / 3);

        } else {

            currentNumber.setNumberOfClasses((currentNumber.getNumberOfDigits() / 3) + 1);

        }
        currentNumber.setNumberDigitsOfHighestOrder(currentNumber.getNumberOfDigits() % 3);

        return currentNumber;
    }

}

