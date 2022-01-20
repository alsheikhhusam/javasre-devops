package org.example;

import org.example.calculator.Calculator;
import org.example.calculator.SimpleCalculator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CalculatorTest {
    private Calculator calc;
    private SimpleCalculator calc2 = new SimpleCalculator();
    @Before
    public void methodInit() {
        calc = new SimpleCalculator();
    }

    @Test
    public void addTwoNums() {
        int expected = 4;
        int actual = calc.add(2, 2);
        Assert.assertEquals("Add did not return the correct sum", expected, actual);
    }

    @Test
    public void addWithNegatives() {
        int expected = -2;
        int actual = calc.add(-4, 2);
        Assert.assertEquals("Add did not return the correct sum", expected, actual);
    }

    @Test
    public void subtractTwoNums() {
        int expected = 2;
        int actual = calc.subtract(6, 4);
        Assert.assertEquals("Subtract did not return the correct sum", expected, actual);
    }

    @Test
    public void subtractWithNegatives() {
        int expected = 4;
        int actual = calc.subtract(2, -2);
        Assert.assertEquals("Subtract did not return the correct sum", expected, actual);
    }

    @Test
    public void multiplyTwoNums() {
        int expected = 10;
        int actual = calc.multiply(5, 2);
        Assert.assertEquals("Multiply did not return the correct sum", expected, actual);
    }

    @Test
    public void multiplyWithNegatives(){
        int expected = -20;
        int actual = calc.multiply(10, -2);
        Assert.assertEquals("Multiply did not return the correct sum", expected, actual);
    }

    @Test
    public void divideTwoNums() {
        int expected = 5;
        int actual = calc.divide(10, 2);
        Assert.assertEquals("Divide did not return the correct sum", expected, actual);
    }

    @Test(expected = ArithmeticException.class)
    public void divideByZero(){
        calc.divide(10, 0);
        Assert.fail("Divide did not throw Arithmetic Exception");
    }
}
