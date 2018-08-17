package org.javaclasses.calculator.impl.function;

import org.javaclasses.calculator.impl.AbstractStateAcceptor;

public class FunctionStateAcceptor extends AbstractStateAcceptor<FunctionElement> {

    private final FunctionParser parser = new FunctionParser();

    @Override
    protected FunctionParser getParser() {
        return parser;
    }
}
