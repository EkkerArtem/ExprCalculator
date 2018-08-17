package org.javaclasses.fsm.mock;

import org.javaclasses.fsm.StateAcceptor;

public enum Acceptor implements StateAcceptor<IntegerStream, StringBuffer, Exception> {
    START(null), FIRST(1), SECOND(2), FINISH(null);

    private final Integer acceptorValue;

    Acceptor(Integer acceptorValue) {
        this.acceptorValue = acceptorValue;
    }

    @Override
    public boolean accept(IntegerStream stream, StringBuffer s) throws Exception {
        Integer newValue = stream.getNewValue();

        if (newValue == null) return acceptorValue == null;

        if (newValue.equals(acceptorValue)) {
            s.append(acceptorValue);
            return true;
        }
        return false;
    }

}
