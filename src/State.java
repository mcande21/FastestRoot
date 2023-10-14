import java.util.ArrayList;
import java.util.List;

public class State {
    private String name;
    private String code;
    private boolean visited;

    // starts out as null
    private State previous;

    private List<State> neighbors;

    public State(String name, String code) {
        // set the name and code member variables and create an empty ArrayList
        this.name = name;
        this.code = code;
        neighbors = new ArrayList<>();
        visited = false;
    }

    public boolean isVisited() {return visited;}
    public void visit() {visited = true;}
    public String getName() {
        return this.name;
    }

    public String getCode() {
        return this.code;
    }

    public int getNeighborCount() {
        return neighbors.size();
    }

    public State getNeighbor(int i) {
        return neighbors.get(i);
    }

    public void addNeighbor(State s) {
        neighbors.add(s);
    }

    /**
     * Check to see if an object is equal to this state.  This is only true
     * iff (1) Object o is the same object as this instance or (2) o is a State
     * object and has the same code.
     * @param o another State object
     * @return true if the object is equivalent
     */
    public boolean equals(Object o) {
        if (o == this) {
            // this is the exact same object
            return true;
        }

        if(!(o instanceof State)) {
            // wrong type
            return false;
        }

        // we know that o is a State
        // so it is safe to cast o to type State
        State s = (State) o;

        return s.code.equals(this.code);
    }

    public void setPrev(State curr) {
        // Use this to help with bakctracking
        previous = curr;

    }
    public State getPrevious(){
        return previous;
    }

    public String toString() {
        return name + " (" + code + ")";
    }
}
