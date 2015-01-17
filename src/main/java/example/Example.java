package example;

import state.IState;
import state.State;
import state_machine.IStateMachine;
import state_machine.StateMachine;
import state_machine.StateMachineException;

import java.lang.reflect.Field;
import java.util.*;

/**
 * Created by sebi on 1/17/2015.
 */
public class Example {

    //state machine
    private IStateMachine stateMachine;

    //initial state
    @annotations.State(initial = true)
    private IState initial = new State(0, "Initial");

    //first state
    @annotations.State(parents = {0})
    private IState first = new State(1, "First");

    //second state
    @annotations.State(parents = {0, 1})
    private IState second = new State(2, "Second");

    public Example(){
        stateMachine = new StateMachine();
    }

    //Cannot implement in SE
    //@TransitionTO(stateId = second.get(id));
    public void exampleMethod() throws StateMachineException {
        //do some work
        stateMachine.transitionToState(first.getId());
    }

    public void otherMethod() throws StateMachineException{
        //other method
        stateMachine.transitionToState(second.getId());
    }

    public void printState(){
        System.out.println(stateMachine.getCurrentState().getName());
    }

    public static void main(String[] args) {
        Example runner = new Example();
        Field[] fields = runner.getClass().getDeclaredFields();

        IStateMachine stateMachine1 = null;

        IState initialState = null;

        Map<IState, int[]> stateParents = new LinkedHashMap<IState, int[]>();

        for(Field field : fields){
            try {

                if(field.getType() == IStateMachine.class){

                    stateMachine1 = (IStateMachine) field.get(runner);

                }else if(field.getType() == IState.class){
                    annotations.State stateAnnotation = field.getAnnotation(annotations.State.class);
                    if(stateAnnotation != null){

                        stateParents.put((IState) field.get(runner), stateAnnotation.parents());

                        if(stateAnnotation.initial()){
                            initialState = (IState) field.get(runner);
                        }
                    }
                }

            }catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        stateMachine1.setCurrentState(initialState);

        for(IState state : stateParents.keySet()){
            try {
                stateMachine1.addState(state, stateParents.get(state));
            } catch (StateMachineException e) {
                e.printStackTrace();
            }
        }

        runner.printState();

        try{
            runner.exampleMethod();
            runner.printState();
            runner.otherMethod();
            runner.printState();
            runner.exampleMethod();
        } catch (StateMachineException e) {
            System.out.println(e);
        }
    }
}
