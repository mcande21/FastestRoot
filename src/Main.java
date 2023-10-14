public class Main {

    public static void main(String[] args) {
        UnitedStates us = new UnitedStates();

        // print out all of the states
        //us.printStates();
        System.out.println(us.findPath("NY", "CA"));

        for(State s: us.getSolution("CA")){
            System.out.println(s);
        }
    }
}
