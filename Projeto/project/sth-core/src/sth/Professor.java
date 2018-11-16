package sth;

import java.util.Map;
import java.util.TreeMap;

import sth.exceptions.DuplicateDisciplineException;

/**
 * Professor
 */
public class Professor extends Person {

	private static final long serialVersionUID = 201811151743L;

	/** The disciplines lectured by the professor. */
	private Map <Course, Disciplines> _disciplines = new TreeMap<Course, Disciplines>();

	public Professor(int id, int phoneNumber, String name) throws DuplicateIdException, OutOfRangeIdException {
		super(id, phoneNumber, name);
	}

	//add Discipline may throw this exception from the addDiscipline
	public void addCourseDiscipline(Course c, Discipline discipline) throws DuplicateDisciplineException {
		if (_disciplines.containsKey(c))
			((Disciplines)(_disciplines.get(c))).addDiscipline(discipline);
		else  _disciplines.put(c,new Disciplines(discipline));
	}

	/**
	 * @return the courses lectured by the professor.
	 */
	public Map<Course, Disciplines> getCourses() {
		return _disciplines;
	}

}