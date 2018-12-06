package sth;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.io.Serializable;
import java.text.Collator;
import sth.exceptions.DuplicateProjectException;
import sth.exceptions.DuplicateSurveyException;
import sth.exceptions.FinalizedSurveyException;
import sth.exceptions.NoSuchPersonIdException;
import sth.exceptions.NoSuchProjectNameException;
import sth.exceptions.NoSuchSurveyException;
import sth.exceptions.ProjectAlreadyClosedException;
import sth.exceptions.ProjectNotClosedException;
import sth.exceptions.StudentLimitExceededException;
import sth.exceptions.SurveyNotClosedException;
import sth.exceptions.SurveyNotOpenException;
import sth.exceptions.SurveyWithAnswersException;

/**
 * The Discipline class.
 */
public class Discipline implements Serializable {

	/** Serial number for serialization. */
	private static final long serialVersionUID = 201811151734L;

	/** The discipline's name. */
	private String _name;

	/** The maximum number of students per discipline. */
	private static final int MAXSTUDENTS = 300;
	private Map<Integer, Professor> _professors = new HashMap<Integer, Professor>();
	private Map<Integer, Student> _students = new TreeMap<Integer, Student>();
	private Map<String, Project> _projects = new TreeMap<String, Project>();

	public Discipline(String name){
		_name = name;
	}

	public String getDisciplineName(){
		return _name;
	}

	/**
	 * @return the _students
	 */
	public Map<Integer, Student> getStudents() {
		return _students;
	}
	
	/**
	 * @return the _professors
	 */
	public Map<Integer, Professor> getProfessors() {
		return _professors;
	}

	public void addProfessor(Professor prof) {
		_professors.put(prof.getId(), prof);
	}

	public void addStudent(Student student) throws StudentLimitExceededException{
		if (_students.size() < MAXSTUDENTS)
			_students.put(student.getId(), student);
		else throw new StudentLimitExceededException(student);
	}

	/**
	 * @return the discipline associated projects
	 */
	public Map<String, Project> getProjects() {
		return _projects;
	}

	public Project getProject(String project) throws NoSuchProjectNameException{
		Project p = _projects.get(project);
		if (p != null)
			return p;
		else throw new NoSuchProjectNameException();
	}

	public boolean hasProject(String project){
		return _projects.containsKey(project);
	}

	public String showProject(String project) throws NoSuchProjectNameException {
		return _name + " - " + project + getProject(project).showSubmissions();
	}

	public void deliverProject(Person person, String project, String submission) throws NoSuchProjectNameException, ProjectAlreadyClosedException {
		getProject(project).deliverProject(person, submission);
	}
	
	public void createProject(String project) throws DuplicateProjectException {
		if (hasProject(project)) {
			throw new DuplicateProjectException();
		}
		else {
			Project p = new Project(project, _name + " - " + project);
			_projects.put(project, p);
		}
	}

	public void closeProject(String project) throws NoSuchProjectNameException, ProjectAlreadyClosedException {
		Project proj;
		if ((proj = _projects.get(project)) != null) {
			proj.close();
		}
		else {
			throw new NoSuchProjectNameException();
		}
	}

	public void registerSurveyObserver(Observer o) {
		for (Map.Entry<String, Project> entry: _projects.entrySet()) {
			entry.getValue().registerSurveyObserver(o);
		}
	}

	public void submitSurveyAnswer(Student student, String project, SurveyAnswer answer) throws NoSuchProjectNameException, SurveyNotOpenException { 
		Project p = getProject(project);
		p.submitSurveyAnswer(student, answer);

	}

	public ArrayList<Student> showStudents() {
		ArrayList<Student> students = new ArrayList<Student>();
		for (Map.Entry<Integer, Student> entry: _students.entrySet()) {
			students.add(entry.getValue());
		}
		return students;
	}

	public String showDisciplineSurveys(Person person) {
		ArrayList<String> disciplines = new ArrayList<String>();
		for (Map.Entry<String, Project> entry: _projects.entrySet()) {
			try {
				disciplines.add(entry.getValue().showSurvey(person));
			} catch (NoSuchSurveyException e) {
				continue;
			}
		}
		disciplines.sort(Collator.getInstance());
		String str = "";
		for (String discipline: disciplines) {
			str += discipline + "\n";
		}
		return str;
	}

	public void createSurvey(Course course, String project) throws NoSuchProjectNameException , DuplicateSurveyException {
		Project proj = getProject(project);
		proj.createSurvey(course, this);
	}

	public void cancelSurvey(String project) throws NoSuchProjectNameException, SurveyWithAnswersException, FinalizedSurveyException, NoSuchSurveyException {
		Project proj = getProject(project);
		proj.cancelSurvey();
	}
	
	public void openSurvey(String project) throws NoSuchProjectNameException, ProjectNotClosedException, SurveyNotClosedException, FinalizedSurveyException, NoSuchSurveyException {
		Project proj = getProject(project);
		proj.openSurvey();
	}
	
	public void closeSurvey(String project) throws NoSuchProjectNameException, FinalizedSurveyException, NoSuchSurveyException, SurveyNotOpenException {
		Project proj = getProject(project);
		proj.closeSurvey();
	}
	
	public void finalizeSurvey(String project) throws NoSuchProjectNameException, SurveyNotClosedException, NoSuchSurveyException {
		Project proj = getProject(project);
		proj.finalizeSurvey();
	}
	
	public void addToNotificationList(Person person, String project) throws NoSuchProjectNameException, NoSuchSurveyException {
		getProject(project).addToNotificationList(person);
	}

	public void removeFromNotificationList(Person person, String project) throws NoSuchProjectNameException, NoSuchSurveyException {
		getProject(project).removeFromNotificationList(person);
	}
	

	@Override
	public boolean equals(Object o){
		if (o instanceof Discipline){
			Discipline d = (Discipline) o;
			return _name.equals(d.getDisciplineName());
		}
		return false;
	}

	@Override
	public String toString() {
		return  _name;
	}
    
}