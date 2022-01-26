package org.example;

import org.example.calculator.Calculator;
import org.example.calculator.SimpleCalculator;
import org.junit.*;

public class SimpleCalculatorTest {
    private Calculator cal;
    private SimpleCalculator cal2 = new SimpleCalculator();

    @Before
    public void methodInit() {
        System.out.println("Before each test method");
        cal = new SimpleCalculator();
    }

    @After
    public void methodTeardown() {
        System.out.println("After each test method");
    }

    @BeforeClass
    public static void classInit() {
        System.out.println("Before ALL tests");
    }

    @AfterClass
    public static void classTeardown() {
        System.out.println("After ALL tests");
    }

    @Test
    public void shouldSumTwoNumbers() {
        int expected = 4;
        int actual = cal.add(2, 2);
        Assert.assertEquals("Add did not return the correct sum", expected, actual);
        cal2.add(10, 10);
    }

    @Test
    public void shouldSubtractTwoNumbers() {
        int expected = 3;
        int actual = cal.subtract(4, 1);
        Assert.assertEquals("Subtract did not return the correct subtracted value", expected, actual);
        cal2.subtract(8,5);
    }

    @Test
    public void ShouldMultiplyTwoNumbers() {
        int expected = 6;
        int actual = cal.multiply(3, 2);
        Assert.assertEquals("Multiplication did not return the correct multiplication value", expected, actual);
        cal2.multiply(6, 4);
    }

    @Test
    public void ShouldDivideTwoNumbers() {
        int expected = 2;
        try {
            int actual = cal.divide(4, 2);
            Assert.assertEquals("Division did not return the expected value", expected, actual);
        } catch (ArithmeticException e) {
            System.out.println("Cannot divide by 0");
        }
        cal2.divide(8, 2);
    }

    @Test
    public void shouldSumTwoNegativeNumbers() {
        cal.add(-3, -3);
    }
}
