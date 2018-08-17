package org.javaclasses.calculator.impl;

import org.javaclasses.calculator.CalculationException;
import org.javaclasses.calculator.MathExpressionCalculator;
import org.javaclasses.calculator.impl.bracers.ClosingBracerStateAcceptor;
import org.javaclasses.calculator.impl.bracers.OpeningBracerStateAcceptor;
import org.javaclasses.calculator.impl.delimiter.DelimiterAcceptor;
import org.javaclasses.calculator.impl.finish.FinishStateAcceptor;
import org.javaclasses.calculator.impl.function.FunctionStateAcceptor;
import org.javaclasses.calculator.impl.number.NumberStateAcceptor;
import org.javaclasses.calculator.impl.operator.OperatorStateAcceptor;
import org.javaclasses.fsm.FiniteStateMachine;

import java.util.EnumSet;

import static org.javaclasses.calculator.impl.State.*;
import static org.javaclasses.calculator.impl.State.DELIMITER;
import static org.javaclasses.calculator.impl.State.FUNCTION;

public class FsmCalculator extends FiniteStateMachine<
        State,
        MathExpressionReader,
        CalculationContext,
        CalculationStateAcceptor,
        CalculationException> implements MathExpressionCalculator {


    public FsmCalculator() {

        registerTransition(START, EnumSet.of(NUMBER, OPENING_BRACER, FUNCTION), null);
        registerTransition(NUMBER, EnumSet.of(OPERATOR, CLOSING_BRACER, DELIMITER, FINISH), new NumberStateAcceptor());
        registerTransition(OPERATOR, EnumSet.of(NUMBER, OPENING_BRACER, FUNCTION), new OperatorStateAcceptor());
        registerTransition(OPENING_BRACER, EnumSet.of(NUMBER, OPENING_BRACER), new OpeningBracerStateAcceptor());
        registerTransition(CLOSING_BRACER, EnumSet.of(CLOSING_BRACER, OPERATOR, FINISH), new ClosingBracerStateAcceptor());
        registerTransition(FUNCTION,EnumSet.of(CLOSING_BRACER, NUMBER, FUNCTION), new FunctionStateAcceptor());
        registerTransition(DELIMITER,EnumSet.of(NUMBER), new DelimiterAcceptor());
        registerTransition(FINISH, EnumSet.noneOf(State.class), new FinishStateAcceptor());
    }

    @Override
    public double calculate(String expression) throws CalculationException {

        CalculationContext context = new CalculationContext();

        run(START, new MathExpressionReader(expression), context);

        return context.getResult();
    }

    @Override
    protected void deadlock(State state, MathExpressionReader reader,
                            CalculationContext context) throws CalculationException {

        throw new CalculationException("Incorrect math expression.", reader.getParsePosition().getValue());
    }
}
