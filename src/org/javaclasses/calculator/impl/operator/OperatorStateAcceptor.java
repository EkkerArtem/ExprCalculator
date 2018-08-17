package org.javaclasses.calculator.impl.operator;

import org.javaclasses.calculator.impl.AbstractStateAcceptor;

public class OperatorStateAcceptor extends AbstractStateAcceptor<OperatorElement> {

    private final OperatorParser parser = new OperatorParser();

    @Override
    protected OperatorParser getParser() {
        return parser;
    }
}
