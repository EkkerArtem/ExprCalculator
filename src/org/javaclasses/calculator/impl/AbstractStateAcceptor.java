package org.javaclasses.calculator.impl;

import org.javaclasses.calculator.CalculationException;

import java.util.Optional;

public abstract class AbstractStateAcceptor<Element extends MathExpressionElement>
        implements CalculationStateAcceptor {

    @Override
    public boolean accept(MathExpressionReader reader, CalculationContext context)
            throws CalculationException {


        MathExpressionElementParser<Element> parser = getParser();

        Optional<Element> result = parser.parse(reader);

        if (result.isPresent()) {
            result.get().accept(context);
        }

        return result.isPresent();
    }

    protected abstract MathExpressionElementParser<Element> getParser();
}
