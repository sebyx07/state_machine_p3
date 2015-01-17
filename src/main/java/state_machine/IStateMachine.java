package state_machine;

import state.IState;

import java.util.List;
import java.util.Map;

/**
 * Created by sebi on 1/17/2015.
 */
public interface IStateMachine {
    void addState(IState state, List<Integer> parentIds) throws StateMachineException;
    void addState(IState state, int parentIds[]) throws StateMachineException;
    IState getCurrentState();
    void setCurrentState(IState state);
    void transitionToState(Integer id) throws StateMachineException;
    Map<Integer, IState> getStates();
}
