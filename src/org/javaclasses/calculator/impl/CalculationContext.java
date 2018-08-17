package org.javaclasses.calculator.impl;

import org.javaclasses.calculator.CalculationException;
import org.javaclasses.calculator.impl.bracers.ClosingBracerElement;
import org.javaclasses.calculator.impl.bracers.OpeningBracerElement;
import org.javaclasses.calculator.impl.delimiter.DelimiterElement;
import org.javaclasses.calculator.impl.finish.EndOfExpressionElement;
import org.javaclasses.calculator.impl.function.Function;
import org.javaclasses.calculator.impl.function.FunctionElement;
import org.javaclasses.calculator.impl.number.NumberElement;
import org.javaclasses.calculator.impl.operator.BinaryOperator;
import org.javaclasses.calculator.impl.operator.OperatorElement;

import java.util.ArrayDeque;
import java.util.Deque;

public class CalculationContext implements MathExpressionElementVisitor {

    private class MasterStack {

        final Deque<Double> operands = new ArrayDeque<>();
        final Deque<BinaryOperator> operators = new ArrayDeque<>();
        final Deque<Integer> bracers = new ArrayDeque<>();

    }

    private final Deque<MasterStack> masterStack = new ArrayDeque<>();

    {
        masterStack.push(new MasterStack());
    }

    final Deque<Function> functions = new ArrayDeque<>();

    @Override
    public void visit(DelimiterElement element) throws CalculationException {
        popAllOperators();
    }

    @Override
    public void visit(FunctionElement element) throws CalculationException {
        functions.push(element.getFunction());
        masterStack.push(new MasterStack());
    }

    @Override
    public void visit(NumberElement element) {
        masterStack.peek().operands.push(element.getValue());
    }

    @Override
    public void visit(OperatorElement element) {
        int operandsBeforeBracers = masterStack.peek().bracers.isEmpty() ? 0 : masterStack.peek().bracers.peek();
        if (!masterStack.peek().operators.isEmpty()) {
            if (masterStack.peek().operators.peek().getPriority() > element.getOperator().getPriority() && masterStack.peek().operands.size() - 2 >= operandsBeforeBracers) {
                popTopOperator();
                visit(element);
                return;
            }
        }
        masterStack.peek().operators.push(element.getOperator());
    }

    @Override
    public void visit(EndOfExpressionElement element) throws CalculationException {

        if (!masterStack.peek().bracers.isEmpty()) {
            throw new CalculationException("Closing bracer expected.", element.getPosition().getValue());
        }

        popAllOperators();

        if (masterStack.peek().operands.size() != 1) {
            throw new IllegalStateException("Wrong size of operand stack: " + masterStack.peek().operands.size());
        }

    }

    @Override
    public void visit(OpeningBracerElement element) {
        masterStack.peek().bracers.push(masterStack.peek().operators.size());
    }

    @Override
    public void visit(ClosingBracerElement element) throws CalculationException {
        if (functions.size() != masterStack.peek().bracers.size() + functions.size()) {
            if (masterStack.peek().bracers.isEmpty()) {
                throw new CalculationException("Opening bracer expected.", element.getPosition().getValue());
            }
        }

        int requiredSize;

        if (masterStack.peek().bracers.size() != 0) {
            requiredSize = masterStack.peek().bracers.pop();
        } else requiredSize = 0;

        while (masterStack.peek().operators.size() > requiredSize) {
            popTopOperator();
        }

        if (!functions.isEmpty()) {
            calculateFunction();
        }
    }

    public double getResult() {
        return masterStack.peek().operands.pop();
    }

    private void popAllOperators() {
        while (!masterStack.peek().operators.isEmpty()) {
            popTopOperator();
        }
    }

    private void popTopOperator() {
        Double rightOperand = masterStack.peek().operands.pop();
        Double leftOperand = masterStack.peek().operands.pop();

        BinaryOperator binaryOperator = masterStack.peek().operators.pop();

        double result = binaryOperator.execute(leftOperand, rightOperand);

        masterStack.peek().operands.push(result);
    }

    private void calculateFunction() {
        Function function = functions.peek();
        double temp;
        double[] array = new double[masterStack.peek().operands.size()];
        if (masterStack.peek().operands.size() != 0) {
            for (int i = 0; i < masterStack.peek().operands.size() + 1; i++) {
                temp = masterStack.peek().operands.pop();
                array[i] = temp;
            }
            masterStack.pop();
            double result = function.execute(array);
            masterStack.peek().operands.push(result);
            functions.pop();
        } else {
            masterStack.pop();
            double result = function.execute();
            masterStack.peek().operands.push(result);
            functions.pop();
        }
    }
}
