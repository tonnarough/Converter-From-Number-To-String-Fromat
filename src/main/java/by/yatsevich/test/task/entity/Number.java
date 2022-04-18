package by.yatsevich.test.task.entity;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Number {

    private final BigInteger number;
    private int numberOfDigits;
    private int numberOfClasses;
    private int numberDigitsOfHighestOrder;
    private final List<Integer> digits = new ArrayList<>();

    public Number(BigInteger number) {
        this.number = number;
    }

    public BigInteger getNumber() {
        return number;
    }

    public List<Integer> getDigits() {
        return digits;
    }

    public void setDigits(int digit) {
        digits.add(digit);
    }

    public int getNumberOfDigits() {
        return numberOfDigits;
    }

    public void setNumberOfDigits(int numberOfDigits) {
        this.numberOfDigits = numberOfDigits;
    }

    public int getNumberOfClasses() {
        return numberOfClasses;
    }

    public void setNumberOfClasses(int numberOfClasses) {
        this.numberOfClasses = numberOfClasses;
    }

    public int getNumberDigitsOfHighestOrder() {
        return numberDigitsOfHighestOrder;
    }

    public void setNumberDigitsOfHighestOrder(int numberDigitsOfHighestOrder) {

        if (numberDigitsOfHighestOrder == 0) {

            this.numberDigitsOfHighestOrder = 3;

        }else{

            this.numberDigitsOfHighestOrder = numberDigitsOfHighestOrder;

        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Number number1 = (Number) o;
        return numberOfDigits == number1.numberOfDigits && numberOfClasses == number1.numberOfClasses
                && numberDigitsOfHighestOrder == number1.numberDigitsOfHighestOrder
                && Objects.equals(number, number1.number) && Objects.equals(digits, number1.digits);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, numberOfDigits, numberOfClasses, numberDigitsOfHighestOrder, digits);
    }

    @Override
    public String toString() {
        return "Number{" +
                "number=" + number +
                ", numberOfDigits=" + numberOfDigits +
                ", numberOfClasses=" + numberOfClasses +
                ", numberDigitsOfHighestOrder=" + numberDigitsOfHighestOrder +
                ", digits=" + digits +
                '}';
    }

}

