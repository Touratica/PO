package sth;

import java.io.Serializable;
import java.util.Map;

import sth.exceptions.ProjectAlreadyClosedException;

import java.util.HashMap;

/**
 * The Project class.
 */
public class Project implements Serializable {

	private static final long serialVersionUID = 201811151744L;

	private String _name;
	private String _description;
	private Map<Integer, String> _submissions = new HashMap<Integer, String>();
	private Boolean _open = true;
	private Survey _survey;


	public Project(String name, String description){
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

	public void createSurvey(Discipline discipline, Project project){
		_survey = new Survey(discipline, project);
	}

	/**
	 * Closes project.
	 */
	public void close() throws ProjectAlreadyClosedException {
		if (!isOpen()) {
			throw new ProjectAlreadyClosedException();
		} else {
			_open = false;
			_survey.setState(new OpenState(this));
		}
	}

	public void submitProject(int id, String submission) throws ProjectAlreadyClosedException {
		if (!isOpen()) {
			throw new ProjectAlreadyClosedException();
		}
		else {
			if (submittedProject(id)) {
				_submissions.replace(id, submission);
			}
			else {
				_submissions.put(id, submission);
			}
		} 
	}

	public void submitSurveyAnswer(Student student, SurveyAnswer answer){
		if (submittedProject(student.getId()))
			_survey.submitAnswer(student, answer);
		// FIXME mandar execao para o aluno que tenta responder a um projeto q nao fez
	}

	public void registerSurveyObserver(Observer o) {
		_survey.registerObserver(o);
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