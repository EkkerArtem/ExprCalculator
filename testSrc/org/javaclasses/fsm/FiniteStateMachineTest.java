package org.javaclasses.fsm;

import org.javaclasses.fsm.mock.Acceptor;
import org.javaclasses.fsm.mock.StateStream;
import org.javaclasses.fsm.mock.State;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.EnumSet;

import static org.javaclasses.fsm.mock.State.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class FiniteStateMachineTest {

    private FiniteStateMachine<State, StateStream, StringBuffer, Acceptor, Exception> stateMachine =
            new FiniteStateMachine<State, StateStream, StringBuffer, Acceptor, Exception>() {

                @Override
                protected void deadlock(State state, StateStream stateStream, StringBuffer stringBuffer) throws Exception {
                    throw new Exception("test");
                }

                {
                    registerTransition(START, EnumSet.of(FIRST,SECOND), Acceptor.START);
                    registerTransition(FIRST, EnumSet.of(SECOND), Acceptor.FIRST);
                    registerTransition(SECOND, EnumSet.of(THIRD), Acceptor.SECOND);
                    registerTransition(THIRD, EnumSet.of(FOURTH), Acceptor.THIRD);
                    registerTransition(FOURTH, EnumSet.of(FIFTH), Acceptor.FOURTH);
                    registerTransition(FIFTH, EnumSet.of(FINISH), Acceptor.FIFTH);
                    registerTransition(FINISH, EnumSet.noneOf(State.class), Acceptor.FINISH);
                }

            };


    private StateStream data = Mockito.mock(StateStream.class);

    @Test
    void testStateTransitions() throws Exception{

        when(data.getNewValue()).thenReturn(FIRST).thenReturn(SECOND)
                                .thenReturn(THIRD).thenReturn(FOURTH).thenReturn(FIFTH).thenReturn(null);
        StringBuffer result = new StringBuffer();
        System.out.println(result.toString());
        stateMachine.run(START, data, result);
        assertEquals(result.toString(), "FIRSTSECONDTHIRDFOURTHFIFTH", "State machine doesn't change states correctly");
    }

    @Test
    void testInvalidTransition(){
        assertThrows(Exception.class, ()->{
            when(data.getNewValue()).thenReturn(START).thenReturn(null);
            stateMachine.run(START, data, new StringBuffer());
            fail("Exception in states hasn't been thrown");
        });
    }

    @Test
    void testInvalidTransitionAfterValid(){
        assertThrows(Exception.class, ()->{
            when(data.getNewValue()).thenReturn(START).thenReturn(FIRST).thenReturn(FIFTH).thenReturn(null);
            stateMachine.run(START, data, new StringBuffer());
            fail("Exception in states hasn't been thrown");
        });
    }
}
