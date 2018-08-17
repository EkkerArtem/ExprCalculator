package org.javaclasses.calculator.impl;

public class MathExpressionReader {

    private final String expression;
    private int parsePosition;

    public MathExpressionReader(String expression) {

        if (expression == null) {
            throw new NullPointerException("Null expression passed.");
        }

        this.expression = expression;
    }

    public MathExpressionPosition getParsePosition() {
        return new MathExpressionPosition(parsePosition);
    }

    public String getRemainingExpression() {
        return expression.substring(parsePosition);
    }

    public void movePosition(int value) {
        parsePosition += value;
    }

    public boolean hasMore() {
        return parsePosition < expression.length();
    }
}
