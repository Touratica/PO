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
	private static final int MAX_STUDENTS = 300;
	private Map<Integer, Professor> _professors = new HashMap<Integer, Professor>();
	private Map<Integer, Student> _students = new HashMap<Integer, Student>();
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
		if (_students.size() < MAX_STUDENTS)
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

	public Project getProject(String project){
		return _projects.get(project);

	}

	public void submitProject(Student student, String project, String submission){
		getProject(project).submitProject(id, submission);
		// FIXME se o projeto nao existir manda excecao

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

	public void submitSurveyAnswer(Student student, String project, SurveyAnswer answer){ 
		// FIXME se a disciplina nao tiver o projeto
		Project p = getProject(project);
		p.submitSurveyAnswer(student, answer);

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