package state;

import transition.ITransition;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by sebi on 1/17/2015.
 */
public class State implements IState {

    private Integer id;
    private List<ITransition> transitions;
    private String name;

    public State(Integer id, String name){
        this.id = id;
        this.name = name;
        transitions = new ArrayList<ITransition>();
    }

    public State(Integer id, String name, List<ITransition> transitions){
        this.id = id;
        this.name = name;
        this.transitions = transitions;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<ITransition> getTransitions() {
        return transitions;
    }

    @Override
    public Boolean canChangeToState(IState state) {

        if(this.equals(state)){
            return true;
        }

        for(ITransition transition : transitions){
            if(transition.getFinalState().equals(state)){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof State)) return false;

        State state = (State) o;

        if (!id.equals(state.id)) return false;
        if (transitions != null ? !transitions.equals(state.transitions) : state.transitions != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + (transitions != null ? transitions.hashCode() : 0);
        return result;
    }
}
