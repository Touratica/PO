package sth;

public class FinalState extends Survey.State{
    public FinalState(Survey survey){
        survey.super();
    }
    public void cancel() throws FinalizedSurveyException{
        throw new FinalizedSurveyException();
    }
    public void open(){ // FIXME mandar throw ler enunciado 

    }
    public void close(){
        // FIXME mandar throw 
    }

    public void finalize(){
        // do nothing, already finalized
    }
    public String render(){
        return super.render() + "\n";
    }

    @Override
    public String notifyState() {
        return "final";
    }
}