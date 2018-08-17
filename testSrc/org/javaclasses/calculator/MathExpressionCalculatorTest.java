package org.javaclasses.calculator;

import org.javaclasses.calculator.impl.FsmCalculator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MathExpressionCalculatorTest {

    private final MathExpressionCalculator calculator = new FsmCalculator();

    @Test
    void testSingleNumber() throws CalculationException {

        double result = calculator.calculate("4");
        Assertions.assertEquals(4, result,
                "Single number is not calculated correctly.");
    }

    @Test
    void testPlusBinaryOperator() throws CalculationException {

        double result = calculator.calculate("-2+3");
        Assertions.assertEquals(1, result,
                "Plus operator is not calculated correctly.");
    }


    @Test
    void testMinusBinaryOperator() throws CalculationException {

        double result = calculator.calculate("-2-3");
        Assertions.assertEquals(-5, result,
                "Minus operator is not calculated correctly.");
    }

    @Test
    void testSingleBracers() throws CalculationException {

        double result = calculator.calculate("2-(5+7)");
        Assertions.assertEquals(-10, result,
                "Minus operator is not calculated correctly.");
    }

    @Test
    void testSngleBracers() throws CalculationException {

        double result = calculator.calculate("10/5+7");
        Assertions.assertEquals(9, result,
                "Minus operator is not calculated correctly.");
    }

    @Test
    void testSnleBracers() throws CalculationException {

        double result = calculator.calculate("2*12/(3+(2/2))");
        Assertions.assertEquals(6, result,
                "Minus operator is not calculated correctly.");
    }

    @Test
    void testPIExpression()  throws CalculationException{

        double result = calculator.calculate("2+pi()");
        Assertions.assertEquals(5.14, result,0.000000000000001,
                "Minus operator is not calculated correctly.");
    }

    @Test
    void testNotClosedBracer() {

        try {
            calculator.calculate("(5+7");

            Assertions.fail("Required exception was not thrown.");

        } catch (CalculationException e) {

            Assertions.assertEquals(4, e.getErrorPosition(),
                    "Error position is not correct.");
        }
    }
}