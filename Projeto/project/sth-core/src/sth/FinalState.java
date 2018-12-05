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
    public String renderResults(Person p){
        
        Survey survey = getSurvey(); //this way we only get the survey one time
        
        if (p.isProfessor()){
            
            String submissions ="* Número de submissões: " + survey.getSubmissionsNumber();
            
            String answers = "* Número de respostas: " + survey.getAnswersNumber();

            String hours = "* Tempos de resolução (horas) (mínimo, médio, máximo): "+ survey.getMinHours() + ", " + survey.getAverageHours() + ", " + survey.getMaxHours();

            return super.render(p) + "\n" + submissions + "\n" + answers + "\n" + hours ;

        } else if (p.isStudent()){
            String answers = "* Número de respostas: " + survey.getAnswersNumber();

            String hours = "* Tempos médio (horas): "+ survey.getAverageHours();

            return super.render(p) + "\n" + answers + "\n" + hours;
        }
        return null; // FIXME mandar execao 
    }

    @Override
    public String renderSurvey(Person p) {
        Survey survey = getSurvey();
        return survey.getDisciplineName() + " - " + survey.getAnswersNumber() + " - " + survey.getAverageHours() + " horas";
    }


    @Override
    public String notifyState() {
        return "final";
    }
}