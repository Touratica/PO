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

	public addProfessor(Professor prof) throws NoSuchPersonIdException{
		if (idExists(p.getId()))
			_professors.add(p);
		else throw new NoSuchPersonIdException(p.getId());
	}

	public addStudent(Student student) throws NoSuchPersonIdException, StudentLimitExceededException{
		if (idExists(student.getId()))
			if (_students.size() < MAX_STUDENTS)
				_students.add(student);
			else throw new StudentLimitExceededException(student);
		else throw new NoSuchPersonIdException(student.getId());
	}

	public void addProject(Project project) throws DuplicateProjectException {
		for (Project existentProject: _projects) {
			if (project.equals(existentProject)) {
				throw new DuplicateProjectException();
			}
		}
		_projects.add(project);
	}

	@Override
	public boolean equals(Object o){
		if (o instanceof Discipline){
			Discipline d = (Discipline) o;
			return _name == o.getDisciplineName();
		}
		return false;
	}

	@Override
	public String toString() {
		return  _name;
	}
    
}