package org.javaclasses.calculator.impl;

import org.javaclasses.calculator.CalculationException;

public interface MathExpressionElement {

    void accept(MathExpressionElementVisitor visitor) throws CalculationException;

    MathExpressionPosition getPosition();
}
