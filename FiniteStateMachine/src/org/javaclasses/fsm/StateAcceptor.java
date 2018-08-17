package org.javaclasses.fsm;

public interface StateAcceptor<Input, Output, Error extends Exception> {

    boolean accept(Input input, Output output) throws Error;
}
