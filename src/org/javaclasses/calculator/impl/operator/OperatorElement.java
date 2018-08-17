package org.javaclasses.calculator.impl.operator;

import org.javaclasses.calculator.CalculationException;
import org.javaclasses.calculator.impl.AbstractMathExpressionElement;
import org.javaclasses.calculator.impl.MathExpressionElement;
import org.javaclasses.calculator.impl.MathExpressionElementVisitor;
import org.javaclasses.calculator.impl.MathExpressionPosition;

public class OperatorElement extends AbstractMathExpressionElement {

    private final BinaryOperator operator;

    public OperatorElement(MathExpressionPosition position, BinaryOperator operator) {
        super(position);
        this.operator = operator;
    }

    public BinaryOperator getOperator() {
        return operator;
    }

    @Override
    public void accept(MathExpressionElementVisitor visitor) throws CalculationException {
        visitor.visit(this);
    }
}
