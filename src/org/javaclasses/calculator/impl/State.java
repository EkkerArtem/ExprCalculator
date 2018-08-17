package org.javaclasses.calculator.impl;

public enum State {

    START,
    NUMBER,
    OPERATOR,
    OPENING_BRACER,
    CLOSING_BRACER,
    FUNCTION,
    DELIMITER,
    FINISH
}
