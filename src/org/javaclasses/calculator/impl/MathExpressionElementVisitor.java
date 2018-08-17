package org.javaclasses.calculator.impl;

import org.javaclasses.calculator.CalculationException;
import org.javaclasses.calculator.impl.bracers.ClosingBracerElement;
import org.javaclasses.calculator.impl.bracers.OpeningBracerElement;
import org.javaclasses.calculator.impl.delimiter.DelimiterElement;
import org.javaclasses.calculator.impl.finish.EndOfExpressionElement;
import org.javaclasses.calculator.impl.function.FunctionElement;
import org.javaclasses.calculator.impl.number.NumberElement;
import org.javaclasses.calculator.impl.operator.OperatorElement;

public interface MathExpressionElementVisitor {

    void visit(NumberElement element) throws CalculationException;

    void visit(EndOfExpressionElement element) throws CalculationException;

    void visit(OperatorElement element) throws CalculationException;

    void visit(OpeningBracerElement element) throws CalculationException;

    void visit(ClosingBracerElement element) throws CalculationException;

    void visit(DelimiterElement delimiterElement) throws CalculationException;

    void visit(FunctionElement functionElement) throws CalculationException;
}
