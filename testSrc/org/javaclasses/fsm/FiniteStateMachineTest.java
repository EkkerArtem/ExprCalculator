package org.javaclasses.fsm;

import org.javaclasses.fsm.mock.Acceptor;
import org.javaclasses.fsm.mock.IntegerStream;
import org.javaclasses.fsm.mock.State;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.EnumSet;

import static org.javaclasses.fsm.mock.State.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class FiniteStateMachineTest {

    private FiniteStateMachine<State, IntegerStream, StringBuffer, Acceptor, Exception> stateMachine =
            new FiniteStateMachine<State, IntegerStream, StringBuffer, Acceptor, Exception>() {

                @Override
                protected void deadlock(State state, IntegerStream integerStream, StringBuffer stringBuffer) throws Exception {
                    throw new Exception("test");
                }

                {
                    registerTransition(START, EnumSet.of(FIRST,SECOND,FINISH), Acceptor.START);
                    registerTransition(FIRST, EnumSet.of(SECOND), Acceptor.FIRST);
                    registerTransition(SECOND, EnumSet.of(FIRST, FINISH), Acceptor.SECOND);
                    registerTransition(FINISH, EnumSet.noneOf(State.class), Acceptor.FINISH);
                }

            };


    private IntegerStream data = Mockito.mock(IntegerStream.class);

    @Test
    void testStateTransitions() throws Exception{

        when(data.getNewValue()).thenReturn(1).thenReturn(2).thenReturn(1).thenReturn(2).thenReturn(null);
        StringBuffer result = new StringBuffer();
        stateMachine.run(START, data, result);
        assertEquals(result.toString(), "1212", "State machine doesn't change states correctly");
    }

    @Test
    void testInvalidTransition(){
        assertThrows(Exception.class, ()->{
            when(data.getNewValue()).thenReturn(1).thenReturn(null);
            stateMachine.run(START, data, new StringBuffer());
            fail("Exception in states hasn't been thrown");
        });
    }
}
