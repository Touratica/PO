package sth;

import java.util.List;
import java.util.ArrayList;

import java.io.Serializable;

import sth.exceptions.NoSuchPersonIdException;
import sth.exceptions.StudentLimitExceededException;

/**
 * The Discipline class.
 */
public class Discipline implements Serializable{

	private static final long serialVersionUID = 201811151734L;
	private String _name;
	private int _max_students = 300;
	private List<Professor> _professors = new ArrayList<Professor>();
	private List<RegularStudent> _students = new ArrayList<RegularStudent>();  

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

	public addStudent(RegularStudent student) throws NoSuchPersonIdException, StudentLimitExceededException{
		if (idExists(student.getId()))
			if (_students.size() < _max_students)
				_students.add(student);
			else throw new StudentLimitExceededException(student);
		else throw new NoSuchPersonIdException(student.getId());
	}

	@Override
	public boolean equals(Object o){
		if (o instanceof Discipline){
			Discipline d = (Discipline) o;
			return _name == o.getDisciplineName();
		}
		return false;
	}
}