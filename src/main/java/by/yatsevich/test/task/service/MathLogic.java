package by.yatsevich.test.task.service;

import by.yatsevich.test.task.service.Impl.MathLogicImpl;

import java.math.BigInteger;

public interface MathLogic {

    String getNumberInString(BigInteger number);

    static MathLogic getInstance(){
        return MathLogicImpl.INSTANCE;
    }

}
