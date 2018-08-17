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
                "Single bracers is not calculated correctly.");
    }

    @Test
    void testOperationPriority() throws CalculationException {

        double result = calculator.calculate("10/5+7");
        Assertions.assertEquals(9, result,
                "Operation priority is not calculated correctly.");
    }

    @Test
    void testOperationPriorityWithBracers() throws CalculationException {

        double result = calculator.calculate("2*12/(3+(2/2))");
        Assertions.assertEquals(6, result,
                "Operation priority with bracers is not calculated correctly.");
    }

    @Test
    void testPIExpression()  throws CalculationException{

        double result = calculator.calculate("2+pi()");
        Assertions.assertEquals(5.14, result,0.000000000000001,
                "Pi is not calculated correctly.");
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

    @Test
    void testMaxExpression()  throws CalculationException{

        double result = calculator.calculate("max(26/2 , 12/2)");
        Assertions.assertEquals(13, result,
                "Max is not calculated correctly.");
    }

    @Test
    void testMinExpression()  throws CalculationException{

        double result = calculator.calculate("min(10*3 , 12*2)");
        Assertions.assertEquals(24, result,
                "Min is not calculated correctly.");
    }

    @Test
    void testMinAndMaxExpression()  throws CalculationException{

        double result = calculator.calculate("min(10*3 , 12*2)+max(26/2 , 12/2)/3");
        Assertions.assertEquals(28.33, result,0.03,
                "Min and max is not calculated correctly.");
    }

    @Test
    void testMinMaxWithExpressionInside()  throws CalculationException{

        double result = calculator.calculate("(min(10*3 , 12*2)+max(26/2 , 12/2))/3");
        Assertions.assertEquals(37.0/3.0, result,
                "Min and max with expression inside is not calculated correctly.");
    }

    @Test
    void testMaxInsideMinExpression()  throws CalculationException{

        double result = calculator.calculate("min(10*3 , max(26/2 , 12/2))");
        Assertions.assertEquals(13, result,
                "Max inside min is not calculated correctly.");
    }

    @Test
    void testMultipleExpressionsAsArgumentToFunction() throws CalculationException{
        double result = calculator.calculate("max(16/4 , (2+6)+4)");
        Assertions.assertEquals(12, result,
                "Multiple expression as function arguments is not calculated correctly.");
    }

    @Test
    void smokeTest() throws CalculationException {
        double result = calculator.calculate("max(max(pi() , sqrt(100)) , pi())");
        Assertions.assertEquals(10, result,
                "Functions aren't calculating together.");
    }

    @Test
    void testSqrtWithExpression() throws CalculationException {
        final double result = calculator.calculate("sqrt(2*2+15+pi()+1+16*2+12)");
        Assertions.assertEquals(8.193900170248599, result,
                "Sqrt with expression inside is not calculated correctly.");
    }

    @Test
    void testTwiFunctionsResult() throws CalculationException {
        final double result = calculator.calculate("sqrt(100)+pi())");
        Assertions.assertEquals(13.14, result,
                "Sqrt + pi don't work together");
    }

}