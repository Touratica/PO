package sth;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import sth.exceptions.DuplicateDisciplineException;
import sth.exceptions.DuplicateIdException;
import sth.exceptions.OutOfRangeIdException;

/**
 * Professor
 */
public class Professor extends Person {

	private static final long serialVersionUID = 201811151743L;

	/** The disciplines lectured by the professor. */
	private Map <String, ArrayList<Discipline>> _disciplines = new TreeMap<String, ArrayList<Discipline>>();

	public Professor(int id, int phoneNumber, String name) throws DuplicateIdException, OutOfRangeIdException {
		super(id, phoneNumber, name);
	}

	//add Discipline may throw this exception from the addDiscipline
	public void addDiscipline(Course course, Discipline discipline) throws DuplicateDisciplineException {
		if (_disciplines.containsKey(course.getName())) {
			_disciplines.get(course.getName()).add(discipline);
		}
		else {
			ArrayList<Discipline> disciplines = new ArrayList<Discipline>();
			disciplines.add(discipline);
			_disciplines.put(course.getName(), disciplines);
		}
	}

	/**
	 * @return the courses lectured by the professor.
	 */
	public Map<String, ArrayList<Discipline>> getDisciplines() {
		return _disciplines;
	}

	@Override
	public String toString() {
		String s = "DOCENTE|" + super.toString() ;
		for (Map.Entry<String, ArrayList<Discipline>> entry : _disciplines.entrySet())
			for (Discipline discipline : entry.getValue())
				s+= "\n* " + entry.getKey() + " - " + discipline.toString();
		return s;
	}
    

}