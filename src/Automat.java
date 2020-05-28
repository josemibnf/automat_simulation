import java.util.ArrayList;
import java.util.StringTokenizer;
import java.io.*;

public class Automat{
    
	ArrayList<State> states = new ArrayList<State>();
    State initial;
    

    //CONSTRUCTOR
    public Automat(ArrayList<State> states){
        this.states=states;
        initial = states.get(0);
    }

   
    public void addState(State st){
        states.add(st);
    }

    //READ AUTOMAT
    public static Automat readAutomat(){
        try{
            BufferedReader buff = new BufferedReader(new FileReader("automata.txt"));
        
            ArrayList<State> stateList = new ArrayList<State>();
            int id=0;  String st;
            while ((st = buff.readLine()) != null){
                Automat.readState(id,new StringTokenizer(st), stateList);
                id++;
            }
            buff.close();
            return new Automat(stateList);
        }  catch (IOException e) {return null;}
    }

    // READ STATE
    private static void readState(int id, StringTokenizer st, ArrayList<State> stateList) {
       if(!Automat.haveID(id, stateList)){
           stateList.add(new State(id, null));
       }

        ArrayList<Transition> transaccionList = new ArrayList<Transition>();
        char[] token;
        while(st.hasMoreElements()){
            token = st.nextToken().toCharArray();
            if(token[0] == 'F'){
                Automat.getState(id, stateList).makeFinal();
            }else{
                if (!Automat.haveID(Character.getNumericValue(token[1]), stateList)) {
                    stateList.add(new State(Character.getNumericValue(token[1]), null));
                }
                State next = Automat.getState(Character.getNumericValue(token[1]), stateList);
                if(next == null){System.out.println("ERROR: el siguiente estado es nulo");}
                transaccionList.add(new Transition(token[0], next));
            }
        }
        Automat.getState(id, stateList).addTransaccionList(transaccionList);  //añadimos las nuevas transacciones (se supone que no habia antes).
        
    }

    // HAVE THIS ID??
    private static boolean haveID(int id, ArrayList<State> stateList){ 
        for(int i=0; i<stateList.size(); i++){
            if (stateList.get(i).haveID(id)){
                return true;
            } 
        }  
        return false;
    }

    // GET THIS STATE
    private static State getState(int id, ArrayList<State> stateList){ 
        for(int i=0; i<stateList.size(); i++){
            if (stateList.get(i).haveID(id)){
                return stateList.get(i);
            }  
        }    
        return null;
    }

    //¿CONTAINS WORD?
    public boolean containsWord(String word){
        return containsWord(word, this.initial);
    }

    private boolean containsWord(String word, State state){
        if(word.isEmpty()){
            if(state.isFinal){
                System.out.println("El estado "+ state.id +"  es final");
                return true;
            }else{
                System.out.println("El estado " + state.id + " no es final");
                return false;
            }
        }else{
            char w = word.toCharArray()[0];
            if(state.haveLetter(w)){
                return containsWord(word.substring(1, word.length()), state.getState(w));
            }else{
                System.out.println("ERROR: No existe la transicion "+ w +" en el estado "+ state);
                return false;
            }
        }
    } 
}