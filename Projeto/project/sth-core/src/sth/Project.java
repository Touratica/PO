package sth;

import java.io.Serializable;
import java.util.Map;
import java.util.SortedSet;

import sth.exceptions.DuplicateSurveyException;
import sth.exceptions.FinalizedSurveyException;
import sth.exceptions.NoSuchProjectNameException;
import sth.exceptions.NoSuchSurveyException;
import sth.exceptions.ProjectAlreadyClosedException;
import sth.exceptions.ProjectNotClosedException;
import sth.exceptions.SurveyNotClosedException;
import sth.exceptions.SurveyNotOpenException;
import sth.exceptions.SurveyWithAnswersException;

import java.util.TreeMap;

/**
 * The Project class.
 */
public class Project implements Serializable {

	/** Serial number for serialization. */
	private static final long serialVersionUID = 201812060158L;

	private String _name;
	private String _description;
	private Map<Integer, String> _submissions = new TreeMap<Integer, String>();
	private Boolean _open = true; //tells if a project is open
	private Survey _survey;

	public Project(String name, String description) {
		_name = name;
		_description = description;
	}
	
	/**
	 * @return the project's name
	 */
	public String getName() {
		return _name;
	}

	/**
	 * @return the project's description
	 */
	public String getDescription() {
		return _description;
	}

	/**
	 * @return the project's submissions
	 */
	public Map<Integer, String> getSubmissions() {
		return _submissions;
	}

	public String showSubmissions() {
		String s = "";
		for(Map.Entry<Integer,String> entry: _submissions.entrySet()){
			s+= "\n* " + entry.getKey() + " - " + entry.getValue();
		}
		return s;
	}

	/**
	 * @return the number of project's submissions
	 */
	public int getSubmissionsNumber() {
		return _submissions.size();
	}

	/**
	 * @return the _opened
	 */
	public Boolean isOpen() {
		return _open;
	}

	public void setSurvey(Survey survey){
		_survey = survey;
	}

	public void removeSurvey() {
		_survey = null;
	}

	public boolean submittedProject(int id) {
		return _submissions.containsKey(id);
	}
	
	public void createSurvey(Course course, Discipline discipline) throws DuplicateSurveyException, ProjectAlreadyClosedException {
		if (!isOpen()) {
			throw new ProjectAlreadyClosedException();
		}
		else if (_survey == null) {
			_survey = new Survey(course, discipline, this);
		}
		else {
			throw new DuplicateSurveyException();
		}
	}
	
	public void cancelSurvey() throws SurveyWithAnswersException, FinalizedSurveyException, NoSuchSurveyException {
		if (_survey != null) {
			_survey.cancel();
		}
		else {
			throw new NoSuchSurveyException();
		}
	}
	
	public void openSurvey() throws ProjectNotClosedException, SurveyNotClosedException, FinalizedSurveyException, NoSuchSurveyException {
		if (_survey != null) {
			_survey.open();
		}
		else {
			throw new NoSuchSurveyException();
		}
	}
	
	public void closeSurvey() throws SurveyNotOpenException, FinalizedSurveyException, NoSuchSurveyException {
		if (_survey != null) {
			_survey.close();
		} else {
			throw new NoSuchSurveyException();
		}
	}

	public void finalizeSurvey() throws SurveyNotClosedException, NoSuchSurveyException {
		if (_survey != null) {
			_survey.finalize();
		} else {
			throw new NoSuchSurveyException();
		}
	}
	
	/**
	 * Closes project.
	 */
	public void close(){
		_open = false;
		if (_survey != null)
			_survey.setState(new OpenState(_survey));
	}
	
	public void deliverProject(Person person, String submission) throws ProjectAlreadyClosedException {
		if (!isOpen()) {
			throw new ProjectAlreadyClosedException();
		}
		else {
			if (submittedProject(person.getId())) {
				_submissions.replace(person.getId(), submission);
			}
			else {
				_submissions.put(person.getId(), submission);
			}
		} 
	}
	
	public void submitSurveyAnswer(Student student, SurveyAnswer answer) throws NoSuchProjectNameException, SurveyNotOpenException {
		if (submittedProject(student.getId()))
			if (hasSurvey()){
				_survey.submitAnswer(student, answer);
			}
			else {
				throw new SurveyNotOpenException();
			}
		else {
			throw new NoSuchProjectNameException();
		}
	}
	
	public void registerSurveyObserver(Observer o) {
		_survey.registerObserver(o);
	}

	public void addToNotificationList(Person person) throws NoSuchSurveyException {
		if (_survey != null) {
			_survey.registerObserver(person);
		}
		else {
			throw new NoSuchSurveyException();
		}
	}

	public void removeFromNotificationList(Person person) throws NoSuchSurveyException {
		if (_survey != null) {
			_survey.removeObserver(person);
		}
		else {
			throw new NoSuchSurveyException();
		}
	}

	public String showSurveyResults(Person person) throws NoSuchSurveyException {
		if (_survey != null) {
			return _survey.renderResults(person);
		} else {
			throw new NoSuchSurveyException();
		}
	}

	public String showSurvey(Person person) throws NoSuchSurveyException {
		if (_survey != null){
			return _survey.renderSurvey(person);
		}
		else {
			throw new NoSuchSurveyException();
		}
	}
	
	public boolean hasSurvey(){
		return _survey != null;
	}

	@Override
	public String toString() {
		return _name;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Project) {
			Project project = (Project) obj;
			return _name.equals(project.getName());
		}
		return false;
	}
}