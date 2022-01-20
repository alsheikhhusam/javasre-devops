package org.example.calculator;

public class SimpleCalculator implements Calculator {

    /**
     * @author Husam Alsheikh
     * @param x a positive or negative whole number
     * @param y a positive or negative whole number
     * @return the sum of x and y
     */
    @Override
    public int add(int x, int y) { return x + y; }

    /**
     * @author Husam Alsheikh
     * @param x a positive or negative whole number
     * @param y a positive or negative whole number
     * @return the difference of x and y
     */
    @Override
    public int subtract(int x, int y) { return x - y; }

    /**
     * @author Husam Alsheikh
     * @param x a positive or negative whole number
     * @param y a positive or negative whole number
     * @return the result of x multiplied with y
     */
    @Override
    public int multiply(int x, int y) { return x * y; }

    /**
     * @author Husam Alsheikh
     * @param x a positive or negative whole number
     * @param y a positive or negative whole number
     * @return the result of x divided by y
     */
    @Override
    public int divide(int x, int y) { return x / y; }
}
