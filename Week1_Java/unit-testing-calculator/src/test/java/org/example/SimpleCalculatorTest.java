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
    public void shouldSumTwoNegativeNumbers() {
        cal.add(-3, -3);
    }
}
