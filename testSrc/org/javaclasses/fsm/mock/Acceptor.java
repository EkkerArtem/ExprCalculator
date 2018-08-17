package org.javaclasses.fsm.mock;

import org.javaclasses.fsm.StateAcceptor;

public enum Acceptor implements StateAcceptor<StateStream, StringBuffer, Exception> {
    START(null), FIRST(State.FIRST), SECOND(State.SECOND), THIRD(State.THIRD), FOURTH(State.FOURTH), FIFTH(State.FIFTH), FINISH(null);

    private final State acceptorValue;

    Acceptor(State acceptorValue) {
        this.acceptorValue = acceptorValue;
    }

    @Override
    public boolean accept(StateStream stream, StringBuffer s) throws Exception {
        State newValue = stream.getNewValue();

        if (newValue == null) return acceptorValue == null;

        if (newValue.equals(acceptorValue)) {
            s.append(acceptorValue);
            return true;
        }
        return false;
    }

}
