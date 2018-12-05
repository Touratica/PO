package sth;

import java.io.Serializable;
import java.util.Map;
import java.util.SortedSet;

import sth.exceptions.DuplicateSurveyException;
import sth.exceptions.FinalizedSurveyException;
import sth.exceptions.NoSuchProjectNameException;
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

	private static final long serialVersionUID = 201811151744L;

	private String _name;
	private String _description;
	private Map<Integer, String> _submissions = new TreeMap<Integer, String>();
	private Boolean _open = true; //tells if a project is open
	private Survey _survey;

	public Project(String discipline, String name){
		_name = name;
		_description = discipline + " - " + name;
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

	public String showSubmissions(){
		String s = "";
		for(Map.Entry<Integer,String> entry: _submissions.entrySet()){
			s+= "\n* " + entry.getKey() + " - " + entry.getValue();
		}
		return s;
	}

	/**
	 * @return the number of project's submissions
	 */
	public int getSubmissionsNumber(){
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

	public void removeSurvey(){
		_survey = null;
	}

	public boolean submittedProject(int id){
		return _submissions.containsKey(id);
	}
	
	public void createSurvey(Course course, Discipline discipline) throws DuplicateSurveyException {
		if (_survey != null) {
			_survey = new Survey(course, discipline, this);
		}
		else {
			throw new DuplicateSurveyException();
		}
	}
	
	public void cancelSurvey() throws SurveyWithAnswersException, FinalizedSurveyException {
		_survey.cancel();
	}
	
	public void openSurvey() throws ProjectNotClosedException, SurveyNotClosedException, FinalizedSurveyException {
		_survey.open();
	}
	
	public void closeSurvey() throws SurveyNotOpenException, FinalizedSurveyException {
		_survey.close();
	}

	public void finalizeSurvey() throws SurveyNotClosedException {
		_survey.finalize();
	}
	
	/**
	 * Closes project.
	 */
	public void close(){
		_open = false;
		if (_survey != null)
		_survey.setState(new OpenState(this));
	}
	
	public void deliverProject(Person person, String submission) throws ProjectAlreadyClosedException {
		if (!isOpen()) {
			throw new ProjectAlreadyClosedException();
		}
		else {
			if (submittedProject(person.getId())) {
				_submissions.replace(id, submission);
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

	public void addToNotificationList(Person person) {
		_survey.registerObserver(person);
	}

	public void removeFromNotificationList(Person person) {
		_survey.removeOberserver(person);
	}

	
	public String showSurvey(Person person) {
		return _survey.renderSurvey(person);
	}
	
	public boolean hasSurvey(){
		return _survey != null;
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