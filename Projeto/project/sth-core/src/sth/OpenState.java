package sth;

import sth.exceptions.SurveyAlreadyOpenedException;

public class OpenState extends Survey.State{
    public OpenState(Survey survey){
        survey.super();
    }
    public void submitAnswer(Student student, SurveyAnswer answer){
        getSurvey().addResult(answer);
        getSurvey().addStudent(student);
    }

    public void cancel() throws SurveyWithAnswersException{
        if (getSurvey().getAnswersNumber() == 0){
			getSurvey().remove();
		} else {
			throw new SurveyWithAnswersException();
		}     
    }

    public void open(){ // FIXME mandar throw de already open
        throw new SurveyAlreadyOpenedException();
    }

    public void close(){
        setState(new ClosedState(getSurvey()));
    }

    public void finalize(){
        // FIXME mandar throw 
    }

    @Override
    public String notifyState() {
        return "open";
    }
}