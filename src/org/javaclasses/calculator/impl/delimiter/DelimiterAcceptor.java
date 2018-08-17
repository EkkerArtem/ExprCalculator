package org.javaclasses.calculator.impl.delimiter;

import org.javaclasses.calculator.impl.AbstractStateAcceptor;

public class DelimiterAcceptor extends AbstractStateAcceptor<DelimiterElement> {

    private final DelimiterParser parser = new DelimiterParser();


    @Override
    protected DelimiterParser getParser() {
        return parser;
    }
}
