package state_machine;

import state.IState;

import transition.ITransition;
import transition.Transition;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sebi on 1/17/2015.
 */
public class StateMachine implements IStateMachine {

    private Map<Integer, IState> states;
    private IState currentState;

    public StateMachine(){
        this.states = new HashMap<Integer, IState>();
    }

    public void addState(IState state, int parentIds[]) throws StateMachineException{
        List<Integer> intList = new ArrayList<Integer>();
        for (int index = 0; index < parentIds.length; index++)
        {
            intList.add(parentIds[index]);
        }
        addState(state, intList);
    }

    public void addState(IState state, List<Integer> parentIds) throws StateMachineException{
        states.put(state.getId(), state);

        for(Integer parentId : parentIds){
            IState parent = states.get(parentId);
            if(parent != null){

                ITransition transition = new Transition(parent, state);

                parent.getTransitions().add(transition);
            }else{
                throw new StateMachineException("parent not found");
            }
        }
    }

    public IState getCurrentState(){
        return currentState;
    }

    public void setCurrentState(IState state){
        currentState = state;
    }

    public void transitionToState(Integer id) throws StateMachineException{
        IState newState = states.get(id);
        if(newState == null){
            throw new StateMachineException("state not found");
        }

        if(!currentState.canChangeToState(newState)){
            throw new StateMachineException("cannot change to state " + newState.getName());
        }

        currentState = newState;
    }

    public Map<Integer, IState> getStates(){
        return states;
    }
}
