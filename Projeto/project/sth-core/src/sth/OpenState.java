package sth;

import sth.exceptions.SurveyNotClosedException;

public class OpenState extends Survey.State {
    public OpenState(Survey survey) {
        survey.super();
    }

    /** 
     * Submits an answer to the survey.
     * @param student the student making the submission
     * @param answer the answer to be submitted
    */
    @Override
    public void submitAnswer(Student student, SurveyAnswer answer) {
        getSurvey().addResult(answer);
        getSurvey().addStudent(student);
    }

    /**
     * Cancels the survey if no answer has been submitted.
     * @throws SurveyWithAnswersException
     */
    @Override
    public void cancel() throws SurveyWithAnswersException {
        if (getSurvey().getAnswersNumber() == 0) {
			getSurvey().remove();
		} else {
			throw new SurveyWithAnswersException();
		}     
    }

    /**
     * Throws an exception, open surveys can't be open.
     * @throws SurveyNotClosedException
     */
    public void open() throws SurveyNotClosedException {
        throw new SurveyNotClosedException();
    }

    public void close(){
        setState(new ClosedState(getSurvey()));
    }

    /**
     * Throws an exception, open surveys can't be finalized.
     * @throws SurveyNotClosedException
     */
    public void finalize() throws SurveyNotClosedException {
        throw new SurveyNotClosedException();
    }

    public String renderResults(Person p) {
        return super.renderResults(p) + " (aberto)";
    }

    @Override
    public String notifyState() {
        return "open";
    }
}