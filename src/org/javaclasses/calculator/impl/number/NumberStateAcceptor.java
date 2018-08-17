package org.javaclasses.calculator.impl.number;

import org.javaclasses.calculator.impl.AbstractStateAcceptor;

public class NumberStateAcceptor extends AbstractStateAcceptor<NumberElement> {

    private final NumberParser parser = new NumberParser();

    @Override
    protected NumberParser getParser() {
        return parser;
    }
}
