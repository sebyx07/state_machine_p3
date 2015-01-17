package state;

import transition.ITransition;

import java.util.List;

/**
 * Created by sebi on 1/17/2015.
 */
public interface IState {
    Integer getId();
    String getName();
    List<ITransition> getTransitions();
    Boolean canChangeToState(IState state);
}
