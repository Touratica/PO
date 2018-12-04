package sth;

public class ClosedState extends Survey.State{
    public ClosedState(Survey survey){
        survey.super();
    }
    public void cancel(){
        setState(new OpenState(getSurvey()));
    }

    public void open(){
        setState(new OpenState(getSurvey()));
    }

    public void close(){ 
        //do nothing; already closed
    }

    public void finalize(){
        setState(new FinalState(getSurvey()));
    }

    public String render(){
        return super.render() + " (fechado)";
    }
    
    @Override
    public String notifyState() {
        return "closed";
    }
}