package sth;

import sth.exceptions.FinalizedSurveyException;

public class FinalState extends Survey.State {
    public FinalState(Survey survey) {
        survey.super();
    }
    
    /**
     * Throws an exception, finalized surveys can't be canceled.
     * @throws FinalizedSurveyException
     */
    @Override
    public void cancel() throws FinalizedSurveyException {
        throw new FinalizedSurveyException();
    }

    /**
     * Throws an exception, finalized surveys can't be open.
     * 
     * @throws FinalizedSurveyException
     */
    @Override
    public void open() throws FinalizedSurveyException {
        throw new FinalizedSurveyException();
    }

    /**
     * Throws an exception, finalized surveys can't be closed.
     * 
     * @throws FinalizedSurveyException
     */
    @Override
    public void close() throws FinalizedSurveyException {
        throw new FinalizedSurveyException();
    }

    /**
     * Does nothing, survey is already finalized.
     */
    @Override
    public void finalize() {}

    public String renderResults(Person p) throws UnsupportedOperationException {
        Survey survey = getSurvey(); //this way we only get the survey one time
        
        if (p.isProfessor()) {
            
            String submissions ="* Número de submissões: " + survey.getSubmissionsNumber();
            
            String answers = "* Número de respostas: " + survey.getAnswersNumber();

            String hours = "* Tempos de resolução (horas) (mínimo, médio, máximo): "+ survey.getMinHours() + ", " + survey.getAverageHours() + ", " + survey.getMaxHours();

            return super.renderResults(p) + "\n" + submissions + "\n" + answers + "\n" + hours ;

        } else if (p.isStudent()){
            String answers = "* Número de respostas: " + survey.getAnswersNumber();

            String hours = "* Tempos médio (horas): "+ survey.getAverageHours();

            return super.renderResults(p) + "\n" + answers + "\n" + hours;
        }
        throw new UnsupportedOperationException(); 
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