package org.javaclasses.calculator.impl.bracers;

import org.javaclasses.calculator.impl.AbstractStateAcceptor;

public class OpeningBracerStateAcceptor extends AbstractStateAcceptor<OpeningBracerElement> {

    private final OpeningBracerParser parser = new OpeningBracerParser();

    @Override
    protected OpeningBracerParser getParser() {
        return parser;
    }
}
