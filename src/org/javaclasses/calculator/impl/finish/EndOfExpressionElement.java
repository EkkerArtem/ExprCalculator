package org.javaclasses.calculator.impl.finish;

import org.javaclasses.calculator.CalculationException;
import org.javaclasses.calculator.impl.AbstractMathExpressionElement;
import org.javaclasses.calculator.impl.MathExpressionElement;
import org.javaclasses.calculator.impl.MathExpressionElementVisitor;
import org.javaclasses.calculator.impl.MathExpressionPosition;

public class EndOfExpressionElement extends AbstractMathExpressionElement {

    public EndOfExpressionElement(MathExpressionPosition position) {
        super(position);
    }

    @Override
    public void accept(MathExpressionElementVisitor visitor) throws CalculationException {
        visitor.visit(this);
    }
}
