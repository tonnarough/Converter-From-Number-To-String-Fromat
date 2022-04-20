package by.yatsevich.test.task.main;

import by.yatsevich.test.task.service.MathLogic;
import java.math.BigInteger;

public class Main {

    public static void main(String[] args) {

        System.out.println(MathLogic.getInstance().getNumberInString(new BigInteger("0")));

    }

}
