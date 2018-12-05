package sth;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.io.Serializable;

import sth.exceptions.DuplicateProjectException;
import sth.exceptions.NoSuchPersonIdException;
import sth.exceptions.NoSuchProjectNameException;
import sth.exceptions.ProjectAlreadyClosedException;
import sth.exceptions.StudentLimitExceededException;

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

	public void addProject(Project project) {
		_projects.put(project.getName(), project);
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
		return getProject(project) != null;
	}

	public String showProject(String project){
		return _name + " - " + project + getProject(project).showSubmissions();
	}

	public void deliverProject(Person person, String project, String submission){
		getProject(project).deliverProject(person, submission);
		// FIXME se o projeto nao existir manda excecao

	}
	public void createProject(String project){ // FIXME mandar excecoes aqui ?
		Project p = new Project(_name, project);
		_projects.put(project, p);
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

	public void submitSurveyAnswer(Student student, String project, SurveyAnswer answer) throws NoSuchProjectNameException{ 
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
		String str = "";
		for (Map.Entry<String, Project> entry: _projects.entrySet()) {
			str += entry.getValue().showSurvey(person) + "\n";
		}
		return str;
	}

	public void createSurvey(Course course, String project) throws NoSuchProjectNameException {
		Project proj = getProject(project);
		proj.createSurvey(course, this);
	}

	public void cancelSurvey(String project) throws NoSuchProjectNameException {
		Project proj = getProject(project);
		proj.cancelSurvey();
	}
	
	public void openSurvey(String project) throws NoSuchProjectNameException {
		Project proj = getProject(project);
		proj.openSurvey();
	}
	
	public void closeSurvey(String project) throws NoSuchProjectNameException {
		Project proj = getProject(project);
		proj.closeSurvey();
	}
	
	public void finalizeSurvey(String project) throws NoSuchProjectNameException {
		Project proj = getProject(project);
		proj.finalizeSurvey();
	}
	
	public void addToNotificationList(Person person, String project) throws NoSuchProjectNameException {
		getProject(project).addToNotificationList(person);
	}

	public void removeFromNotificationList(Person person, String project) throws NoSuchProjectNameException {
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