package sth;

import sth.exceptions.ProjectNotClosedException;
import sth.exceptions.SurveyNotClosedException;
import sth.exceptions.SurveyNotOpenException;
import sth.exceptions.SurveyWithAnswersException;

public class CreatedState extends Survey.State {

	/** Serial number for serialization. */
	private static final long serialVersionUID = 201812060204L;
	
	public CreatedState(Survey survey) {
		survey.super(); 
	}

	/** 
	 * Removes the created survey.
	*/
	@Override
	public void cancel() throws SurveyWithAnswersException {
		getSurvey().remove();
	}
	
	/**
	 * Opens the survey if it's associated project has been closed.
	 * @throws ProjectNotClosedException
	 */
	@Override
	public void open() throws ProjectNotClosedException {   
		if (getSurvey().getProject().isOpen()) {
			throw new ProjectNotClosedException();
		}
		else {
			setState(new OpenState(getSurvey()));
			getSurvey().notifyObservers();
		}
	}

	/**
	 * Throws an exception, created projects can't be closed.
	 * @throws SurveyNotOpenException
	 */
	@Override
	public void close() throws SurveyNotOpenException {
		throw new SurveyNotOpenException();
	}
	
	/**
	 * Throws an exception, created projects can't be finalized.
	 * @throws SurveyNotOpenException
	 */
	@Override
	public void finalize() throws SurveyNotClosedException {
		throw new SurveyNotClosedException();
	}

	@Override
	public String renderResults(Person p){
		return super.renderResults(p) + " (por abrir)";
	}

	@Override
	public String notifyState() {
		return "created";
	}
	
}