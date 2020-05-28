import java.util.ArrayList;

public class State {
    int id;
    ArrayList<Transition> transitions = new ArrayList<Transition>();
    Boolean isFinal;

    //CONSTRUCCION
    public State (int e_id, ArrayList<Transition> transitions) {
        this.id=e_id;
        this.transitions=transitions;
        isFinal=false;
    }

    public void addTransaccion(Transition tnew){
        transitions.add(tnew);
    }

    public void addTransaccionList(ArrayList<Transition> transitions){
        this.transitions = transitions;
    }
    
    public void makeFinal(){
System.out.println("Hacemos al estado: "+ this.id +" final.");
        isFinal=true;
    }
    
    //HAVE LETTER
    public boolean haveLetter(char a){
        for(int i=0; i<transitions.size(); i++){
            if (this.transitions.get(i).haveLetter(a)){
                return true;
            }
        }
        return false;
    }

    //GET STATE
    public State getState(char a){
        if(this.haveLetter(a)){
            Transition t = this.getTransaccion(a);
            return t.nextState;
        }else{
            System.out.println("Error: El estado no tiene esa transaccion");
            return null; //En caso de no tener esa transaccion.
        }
    }

    private Transition getTransaccion(char a) {
        for (int i = 0; i < this.transitions.size(); i++) {
            if (this.transitions.get(i).t_id == a) {
                return this.transitions.get(i);
            }
        }
        
        System.out.println("Error:  al buscar la transaccion");
        return null; //se comprueba con anterioridad que
                    //nunca llegamos a este caso.
    }

    //IT HAS THIS ID??
    public boolean haveID(int id) {
        return this.id == id;
    }

}