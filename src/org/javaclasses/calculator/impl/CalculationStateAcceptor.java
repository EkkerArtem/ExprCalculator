package org.javaclasses.calculator.impl;

import org.javaclasses.calculator.CalculationException;
import org.javaclasses.fsm.StateAcceptor;

public interface CalculationStateAcceptor extends StateAcceptor<
        MathExpressionReader, CalculationContext, CalculationException> {

    @Override
    boolean accept(MathExpressionReader reader, CalculationContext context)
            throws CalculationException;
}
