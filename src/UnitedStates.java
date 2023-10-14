import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class UnitedStates {
    private ArrayList<State> states;

    public UnitedStates() {
        // 50 states + DC
        states = new ArrayList<>(51);

        // load in code file and fill up the states ArrayList
        loadCodesFile();
        loadNeighborsFile();
    }

    /**
     * Check to see if there is a path from the start state to the finish statr
     * @param start 2 letter abbreviation of the start state
     * @param finish 2 letter abbreviation of the target state
     * @return true if there is a path, false if there is no path
     */
    public boolean findPath(String start, String finish){
        // look up the begin and end states
        State begin = findState(start);
        State end = findState(finish);

        // create a stack to store our states as we search
        ArrayDeque<State> processing = new ArrayDeque<>();
        begin.visit();
        // add begin state to the stack/queue
        processing.addLast(begin);

        // loop until proccessing becomes empty
        while(!processing.isEmpty()) {
            // pop off the stack
            State curr = processing.removeFirst();

            for (int i = 0; i < curr.getNeighborCount(); i++) {
                State neighbor = curr.getNeighbor(i);
                if (!neighbor.isVisited()) {
                    // visit neighbor
                    neighbor.visit();
                    // set the previous state of the neighbor to curr
                    neighbor.setPrev(curr);
                    // push/offer
                    processing.addLast(neighbor);
                }
                if (neighbor.equals(end)) {
                    // the neighbor we just added is the target
                    // were done
                    return true;
                }
            }
        } // while()
        // no path
        return false;
    }
    public List<State> getSolution(String end) {
        List<State> solution = new LinkedList<>();
        // current state to process
        State curr = findState(end);

        while (curr != null) {
            // add current state to index 0
            solution.add(0, curr);
            curr = curr.getPrevious();
        }
        return solution;
    }
    private void loadNeighborsFile(){
        Scanner neighbors;

        try {
            // Create a scanner object from a file
            neighbors = new Scanner(new File("contiguous-usa.txt"));
            // NOTE: if we wanted to create a Scanner from user input:
            // new Scanner(System.in);
        } catch (FileNotFoundException e) {
            System.err.println("Could not find the adjacent states files");
            e.printStackTrace();
            System.exit(1);
            return;
        }

        // Loop over all of the lines in the file
        while(neighbors.hasNextLine()) {
            String line = neighbors.nextLine();
            String[] values = line.split(" ");

            /*
             * values[0] is one state code (e.g., PA)
             * values[1] is another state code (e.g., NY)
             *
             * TODO Add code to add these neighbor pairs
             *
             * (1) Find the state in states ArrayList that has the code
             * values[0]
             * (2) Add a neighbor to that state
             *     - values[1]
             *     - look up the state object for values[1]
             *
             * Do this in the opposite direction as well.
             */

            // TODO make sure findState works as expected
            State s1 = findState(values[0]);
            State s2 = findState(values[1]);

            s1.addNeighbor(s2);
            s2.addNeighbor(s1);
        }

        return;
    }

    /**
     * Return the State object from the states ArrayList that
     * has the correct two-letter code.
     * preconditions: code must exist in the states array
     *
     * @param code Two-letter abbreviation for a state
     * @return the State from the states ArrayList that is associated
     * with the code provided
     */
    private State findState1(String code) {
        // Precondition: code must exist in the states array
        // loop through thr array list and compare codes
        int i = 0;
        while(!states.get(i).getCode().equals(code)){
            // gp the tht next index
            i++;
        }
        // whats true here? We will get the index of the state we are looking for.
        // i is the index of state.
        return states.get(i);
    }


    /**
     * load in the information stored in codes.csv
     * create each state and add it to the states ArrayList
     */
    private void loadCodesFile() {
        // scanner can be used to read a file
        Scanner codes;

        try {
            // opened the file for reading with the scanner
            codes = new Scanner(new File("codes.csv"));
        } catch (FileNotFoundException e) {
            System.err.println("Could not find the codes file");
            e.printStackTrace();
            System.exit(1);
            return; // this line should never be reached
        }

        // if we get here, we have successfully opened the file

        // loop through all lines of the file to add states
        while (codes.hasNextLine()) {
            String line = codes.nextLine();
            String[] values = line.split(",");
            State s = new State(values[0], values [1]);
            states.add(s);
        }
        return;
    }

    public void printStates() {
        // loop through an ArrayList using an "enhanced" for loop
        // sometimes called for-in for-each loops
        for (State s : states) {
            System.out.printf("%s (%s):", s.getName(), s.getCode());
            for (int i=0; i<s.getNeighborCount(); i++) {
                if ( i == s.getNeighborCount()-1){
                    System.out.print(s.getNeighbor(i).getName());
                } else {
                System.out.printf("%s, ", s.getNeighbor(i).getName());
            }}
            System.out.println();
        }
    }

    private State findState2(String code) {
       // get the iterator object
       Iterator <State> iter = states.iterator();
       while (iter.hasNext()) {
           // get the next state
           State s = iter.next();
           if (s.getCode().equals(code)) {
               //return the matching state
               return s;
           }
       }
       // this will never run (out precondition)
        return null;
    }

    // use indexOf to retrive a state object
    private State findState(String code){
        // indexof uses .equals to compare objects
        // State's equals method only checks the code
        // make a dummy object for searching
        int idx = states.indexOf(new State(code, code));
        return states.get(idx);
    }




}
