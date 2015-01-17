package transition;

import state.IState;

/**
 * Created by sebi on 1/17/2015.
 */
public interface ITransition {
    IState getInitialState();
    IState getFinalState();
}
