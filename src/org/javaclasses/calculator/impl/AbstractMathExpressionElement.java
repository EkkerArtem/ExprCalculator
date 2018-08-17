package org.javaclasses.calculator.impl;

public abstract class AbstractMathExpressionElement implements MathExpressionElement {

    private final MathExpressionPosition position;

    public AbstractMathExpressionElement(MathExpressionPosition position) {
        this.position = position;
    }

    @Override
    public MathExpressionPosition getPosition() {
        return position;
    }
}
