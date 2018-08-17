package org.javaclasses.calculator.impl.number;

import org.javaclasses.calculator.CalculationException;
import org.javaclasses.calculator.impl.AbstractMathExpressionElement;
import org.javaclasses.calculator.impl.MathExpressionElementVisitor;
import org.javaclasses.calculator.impl.MathExpressionPosition;

public class NumberElement extends AbstractMathExpressionElement {

    private final double value;

    public NumberElement(MathExpressionPosition position, double value) {
        super(position);
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    @Override
    public void accept(MathExpressionElementVisitor visitor) throws CalculationException {
        visitor.visit(this);
    }
}
