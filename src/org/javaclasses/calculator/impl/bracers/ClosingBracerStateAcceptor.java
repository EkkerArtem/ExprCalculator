package org.javaclasses.calculator.impl.bracers;

import org.javaclasses.calculator.impl.AbstractStateAcceptor;

public class ClosingBracerStateAcceptor extends AbstractStateAcceptor<ClosingBracerElement> {

    private final ClosingBracerParser parser = new ClosingBracerParser();


    @Override
    protected ClosingBracerParser getParser() {
        return parser;
    }
}
