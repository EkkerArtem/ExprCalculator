package org.javaclasses.calculator.impl.finish;

import org.javaclasses.calculator.impl.AbstractStateAcceptor;

public class FinishStateAcceptor extends AbstractStateAcceptor<EndOfExpressionElement> {

    private final EndOfExpressionParser parser = new EndOfExpressionParser();


    @Override
    protected EndOfExpressionParser getParser() {
        return parser;
    }
}
