package sth;

import java.util.ArrayList;

import java.io.Serializable;

import sth.exceptions.DuplicateProjectException;
import sth.exceptions.NoSuchPersonIdException;
import sth.exceptions.StudentLimitExceededException;

/**
 * The Discipline class.
 */
public class Discipline implements Serializable{

	/** Serial number for serialization. */
	private static final long serialVersionUID = 201811151734L;

	/** The discipline's name. */
	private String _name;

	/** The maximum number of students per discipline. */
	private static final int MAX_STUDENTS = 300;
	private ArrayList<Professor> _professors = new ArrayList<Professor>();
	private ArrayList<Student> _students = new ArrayList<Student>();
	private ArrayList<Project> _projects = new ArrayList<Project>();

	public Discipline(String name){
			_name = name;
	}

	public String getDisciplineName(){
		return _name;
	}

	public void addProfessor(Professor prof) {
			_professors.add(prof);
	}

	public void addStudent(Student student) throws StudentLimitExceededException{
		if (_students.size() < MAX_STUDENTS)
			_students.add(student);
		else throw new StudentLimitExceededException(student);
	}

	public void addProject(Project project) {
		_projects.add(project);
	}

	/**
	 * @return the discipline associated projects
	 */
	public ArrayList<Project> getProjects() {
		return _projects;
	}

	@Override
	public boolean equals(Object o){
		if (o instanceof Discipline){
			Discipline d = (Discipline) o;
			return _name == d.getDisciplineName();
		}
		return false;
	}

	@Override
	public String toString() {
		return  _name;
	}
    
}