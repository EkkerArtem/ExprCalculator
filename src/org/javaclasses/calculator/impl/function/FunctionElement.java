package org.javaclasses.calculator.impl.function;

import org.javaclasses.calculator.CalculationException;
import org.javaclasses.calculator.impl.AbstractMathExpressionElement;
import org.javaclasses.calculator.impl.MathExpressionElementVisitor;
import org.javaclasses.calculator.impl.MathExpressionPosition;

public class FunctionElement extends AbstractMathExpressionElement {

    private final Function operator;

    public FunctionElement(MathExpressionPosition position, Function operator) {
        super(position);
        this.operator = operator;
    }

    public Function getFunction() {
        return operator;
    }

    @Override
    public void accept(MathExpressionElementVisitor visitor) throws CalculationException {
        visitor.visit(this);
    }
}
