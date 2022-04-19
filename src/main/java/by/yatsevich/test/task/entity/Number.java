package by.yatsevich.test.task.entity;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Number {

    private final BigInteger number;
    private int numberOfDigits;
    private int numberOfDegree;
    private int numberDigitsOfHighestDegree;
    private final boolean isPositive;
    private final List<Integer> digits = new ArrayList<>();

    public Number(BigInteger number) {

        if (number.signum() < 0){

            this.number = number.negate();
            isPositive = false;

        }else{

            this.number = number;
            isPositive = true;

        }

        setNumberOfDigits();
        setDigits(this.number);
        setNumberOfDegree();
        setNumberDigitsOfHighestDegree();

    }

    public boolean isPositive() {
        return isPositive;
    }

    public List<Integer> getDigits() {
        return digits;
    }

    private void setDigits(BigInteger number) {

        for (int i = 0; i < numberOfDigits; i++) {

            digits.add(number.remainder(BigInteger.TEN).intValue());
            number = number.divide(BigInteger.TEN);

        }

    }

    public int getNumberOfDigits() {
        return numberOfDigits;
    }

    private void setNumberOfDigits() {

        this.numberOfDigits = String.valueOf(number).length();

    }

    public int getNumberOfDegree() {
        return numberOfDegree;
    }

    private void setNumberOfDegree() {

        if (numberOfDigits % 3 == 0) {

            this.numberOfDegree = numberOfDigits / 3;

        } else {

            this.numberOfDegree = numberOfDigits / 3 + 1;

        }

    }

    public int getNumberDigitsOfHighestDegree() {
        return numberDigitsOfHighestDegree;
    }

    private void setNumberDigitsOfHighestDegree() {

        if (getNumberOfDigits() % 3 == 0) {

            this.numberDigitsOfHighestDegree = 3;

        } else {

            this.numberDigitsOfHighestDegree = getNumberOfDigits() % 3;

        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Number number1 = (Number) o;
        return numberOfDigits == number1.numberOfDigits && numberOfDegree == number1.numberOfDegree
                && numberDigitsOfHighestDegree == number1.numberDigitsOfHighestDegree
                && isPositive == number1.isPositive && Objects.equals(number, number1.number)
                && Objects.equals(digits, number1.digits);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, numberOfDigits, numberOfDegree, numberDigitsOfHighestDegree,
                isPositive, digits);
    }

    @Override
    public String toString() {

        StringBuilder numberToString = new StringBuilder();

        numberToString.append("Number{")
                .append("number=").append(number)
                .append(", numberOfDigits=").append(numberOfDigits)
                .append(", numberOfDegree=").append(numberOfDegree)
                .append(", numberDigitsOfHighestDegree=").append(numberDigitsOfHighestDegree)
                .append(", isPositive=").append(isPositive)
                .append(", digits=").append(digits).append('}');

        return numberToString.toString();

    }

}

