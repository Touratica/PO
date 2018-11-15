package sth;

import java.util.ArrayList;

/**
 * Professor
 */
public class Professor extends Person {

	/** The disciplines lectured by the professor. */
	//private ArrayList<PairCourseDiscipline> _disciplines = new ArrayList<PairCourseDiscipline>();
	private Map <Course, Disciplines> _courses = new TreeMap<Course, Disciplines>();

	//FIXME addDiscipline pode lancar excecao
	public void addCourseDiscipline(Course c, Discipline d){
		if (_courses.containsKey(c))
			((Disciplines)(_courses.get(c))).addDiscipline(d);
		else  _courses.put(c,d);
	}
}