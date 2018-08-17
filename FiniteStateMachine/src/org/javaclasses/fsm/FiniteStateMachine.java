package org.javaclasses.fsm;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public abstract class FiniteStateMachine<
        State extends Enum,
        Input,
        Output,
        Acceptor extends StateAcceptor<Input, Output, Error>,
        Error extends Exception> {

    private final Map<State, StateRecord> stateRegistry = new HashMap<>();

    protected void registerTransition(State state, Set<State> transitions, Acceptor acceptor) {

        if (stateRegistry.containsKey(state)) {

            throw new IllegalArgumentException("State is already registered: " + state);
        }

        stateRegistry.put(state, new StateRecord(transitions, acceptor));
    }

    protected void run(State startState, Input input, Output output) throws Error {

        State currentState = startState;

        while (!stateRegistry.get(currentState).transitions.isEmpty()) {

            currentState = moveForward(currentState, input, output);

            if (currentState == null) {

                deadlock(currentState, input, output);
            }
        }
    }

    protected abstract void deadlock(State state, Input input, Output output)
            throws Error;

    private State moveForward(State state, Input input, Output output) throws Error {

        for (State possibleTransition : stateRegistry.get(state).transitions) {

            if (stateRegistry.get(possibleTransition).acceptor.accept(input, output)) {
                return possibleTransition;
            }
        }

        return null;
    }

    private class StateRecord {
        private final Set<State> transitions;
        private final Acceptor acceptor;

        private StateRecord(Set<State> transitions, Acceptor acceptor) {
            this.transitions = transitions;
            this.acceptor = acceptor;
        }
    }
}
