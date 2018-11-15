package sth;

import java.util.Map;
import java.util.TreeMap;

import sth.exceptions.DuplicateDisciplineException;

/**
 * Professor
 */
public class Professor extends Person{

	private static final long serialVersionUID = 201811151743L;

	/** The disciplines lectured by the professor. */
	private Map <Course, Disciplines> _courses = new TreeMap<Course, Disciplines>();

	//add Discipline may throw this exception from the addDiscipline
	public void addCourseDiscipline(Course c, Discipline discipline) throws DuplicateDisciplineException {
		if (_courses.containsKey(c))
			((Disciplines)(_courses.get(c))).addDiscipline(discipline);
		else  _courses.put(c,new Disciplines(discipline));
	}
}