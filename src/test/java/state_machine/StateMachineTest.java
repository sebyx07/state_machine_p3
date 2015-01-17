package state_machine;

import org.junit.Before;
import org.junit.Test;
import state.IState;
import state.State;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class StateMachineTest {

    private Integer[] state0Parents = {};
    private IState state0 = new State(0, "0");

    private Integer[] state1Parents = {0, 10};
    private IState state1 = new State(1, "1");

    private Integer[] state2Parents = {1};
    private IState state2 = new State(2, "2");

    private Integer[] state10Parents = {0};
    private IState state10 = new State(10, "10");

    private StateMachine stateMachine;

    @Before
    public void setUp() throws Exception{
        stateMachine = new StateMachine();
    }

    @Test
    public void testAddState() throws Exception {
        stateMachine.addState(state0, Arrays.asList(state0Parents));
        stateMachine.addState(state10, Arrays.asList(state10Parents));
        assertEquals(stateMachine.getStates().size(), 2);
    }

    @Test
    public void testAddStateInvalid(){
        try{
            stateMachine.addState(state10, Arrays.asList(state10Parents));
            fail("It should have failed");
        } catch (StateMachineException e) {

        }
    }

    @Test
    public void testTransitionToState() throws Exception {
        stateMachine.addState(state0, Arrays.asList(state0Parents));
        stateMachine.addState(state10, Arrays.asList(state10Parents));

        stateMachine.setCurrentState(state0);
        stateMachine.transitionToState(state10.getId());

        assertEquals(stateMachine.getCurrentState(), state10);
    }

    @Test
    public void testTransitionToStateInvalid() {
        try {
            stateMachine.addState(state0, Arrays.asList(state0Parents));
            stateMachine.addState(state10, Arrays.asList(state10Parents));
            stateMachine.addState(state1, Arrays.asList(state1Parents));
            stateMachine.addState(state2, Arrays.asList(state2Parents));

            stateMachine.setCurrentState(state0);
            stateMachine.transitionToState(state2.getId());
            fail("It should have failed");
        } catch (StateMachineException e) {

        }

    }
}