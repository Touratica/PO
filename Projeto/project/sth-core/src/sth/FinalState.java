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
        
        Survey survey = getSurvey(); //this way we only get the survey one time

        String submissions ="* Número de submissões: " + survey.getSubmissionsNumber();

        String answers = "* Número de respostas: " + survey.getAnswersNumber();

        String hours = "* Tempos de resolução (horas) (mínimo, médio, máximo): "+ survey.getMinHours() + ", " + survey.getAverageHours() + ", " + survey.getMaxHours();

        return super.render() + "\n" + submissions + "\n" + answers + "\n" + hours ;
    }

    @Override
    public String notifyState() {
        return "final";
    }
}