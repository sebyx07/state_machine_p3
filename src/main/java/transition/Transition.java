package transition;

import state.IState;

/**
 * Created by sebi on 1/17/2015.
 */
public class Transition implements ITransition {

    private IState initialState;
    private IState finalState;

    public Transition(IState initialState, IState finalState){
        this.initialState = initialState;
        this.finalState = finalState;
    }

    @Override
    public IState getInitialState() {
        return initialState;
    }

    @Override
    public IState getFinalState() {
        return finalState;
    }
}

