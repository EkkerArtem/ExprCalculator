package org.javaclasses.calculator.impl.operator;

public interface BinaryOperator {

    double execute(double leftOperand, double rightOperand);

    int getPriority();
}
