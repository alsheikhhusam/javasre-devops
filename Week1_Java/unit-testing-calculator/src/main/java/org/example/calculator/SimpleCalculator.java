package org.example.calculator;

public class SimpleCalculator implements Calculator{

    /**
     * @author AugustDuet
     * @param x some whole number either positive or negative
     * @param  y some....
     * @return the sum
     */
    @Override
    public int add(int x, int y) {
        return x + y;
    }
}
