public class simulacionAF {
    public static void main(String args[]) {
        String palabra = "aba";
        Automat automata = Automat.readAutomat();
        simulacionAF.seeAutomat(automata);

        System.out.println("                         ");
        if(automata.containsWord(palabra)){System.out.println("\nSi esta");}
        else{System.out.println("\nNo esta");}
    }

    public static void seeAutomat(Automat automata){
        for(int i=0; i<automata.states.size(); i++){
            for(int j=0; j<automata.states.get(i).transitions.size(); j++){
                System.out.println("El siguiente estado desde el estado "+ automata.states.get(i).id+ " con la transicion "+ automata.states.get(i).transitions.get(j).t_id+ " es " + automata.states.get(i).transitions.get(j).nextState.id);
            }
            System.out.println("El estado "+ automata.states.get(i).id + " es final: "+ automata.states.get(i).isFinal);
            System.out.println("Aqui se acaba el estado "+ automata.states.get(i).id);
        }
        System.out.println("Aqui se acaba el automata");
        System.out.println("                         ");
    }
}