package org.example.calculator;

public class SimpleCalculator implements Calculator{

    /**
     * @author Richard Madol
     * @param x some whole number either positive or negative
     * @param  y some....
     * @return the desired output
     */
    
    @Override
    public int add(int x, int y) {
        return x + y;
    }

    @Override
    public int subtract(int x, int y) {

        return x - y;
    }

    @Override
    public int multiply(int x, int y) {
        return x * y;
    }

    @Override
    public int divide(int x, int y) {
        return x / y;
    }
}
