package org.javaclasses.calculator.impl.delimiter;

import org.javaclasses.calculator.CalculationException;
import org.javaclasses.calculator.impl.AbstractMathExpressionElement;
import org.javaclasses.calculator.impl.MathExpressionElementVisitor;
import org.javaclasses.calculator.impl.MathExpressionPosition;

public class DelimiterElement extends AbstractMathExpressionElement {

    public DelimiterElement(MathExpressionPosition position) {
        super(position);
    }

    @Override
    public void accept(MathExpressionElementVisitor visitor) throws CalculationException {
        visitor.visit(this);
    }
}
