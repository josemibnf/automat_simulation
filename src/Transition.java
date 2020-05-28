public class Transition{
    char t_id;
    State nextState;

    public Transition(char t_id, State nextState){
        this.t_id=t_id;
        this.nextState=nextState;
    }

    public boolean haveLetter(char a){
        return a==t_id; 
    }

}