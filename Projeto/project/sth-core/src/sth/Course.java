package sth;

import java.io.Serializable;
import java.util.TreeMap;
import java.util.ArrayList;
import java.util.Map;
import sth.exceptions.AlreadyRepresentativeException;
import sth.exceptions.DuplicateDisciplineException;
import sth.exceptions.RepresentativeNumberExceeded;

/**
 * The Course class.
 */
public class Course	implements Serializable {

	/** Serial number for serialization. */
	private static final long serialVersionUID = 201811152338L;
	
	private String _name;
	private Map<Integer, Student> _representatives = new TreeMap<Integer, Student>();
	private Map<String, Discipline> _disciplines = new TreeMap<String, Discipline>();
	private static final int MAX_REPRESENTATIVES = 7;

	public Course(String name) {
		_name = name;
	}

	public String getName() {
		return _name;
	}

	/**
	 * @return the the course's representatives list
	 */
	public Map<Integer, Student> getRepresentatives() {
		return _representatives;
	}

	/**
	 * @return the course's disciplines
	 */
	public Map<String, Discipline> getDisciplines() {
		return _disciplines;
	}

	public void setRepresentative(Student student) throws AlreadyRepresentativeException, RepresentativeNumberExceeded {
		if (_representatives.containsKey(student.getId())) {
			throw new AlreadyRepresentativeException();
		}
		else if (_representatives.size() >= MAX_REPRESENTATIVES) {
			throw new RepresentativeNumberExceeded();
		}
		else {
			_representatives.put(student.getId(), student);
		}
	}

	public void addDiscipline(Discipline discipline) throws DuplicateDisciplineException {
		if (!hasDiscipline(discipline)) {
			_disciplines.put(discipline.getDisciplineName(), discipline);
		}
		else {
			throw new DuplicateDisciplineException(discipline);
		}
	}

	public boolean hasDiscipline(Discipline discipline) {
		for (Map.Entry<String, Discipline> entry: _disciplines.entrySet()) {
			if (entry.getKey().equals(discipline.getDisciplineName())) {
				return true;
			}
		}
		return false;
	}

	@Override 
	public boolean equals(Object o) {
		if (o instanceof Course){
			Course course = (Course) o;
			return course.getName().equals(_name);
		}
		return false;
	}

	@Override
	public String toString() {
		return _name ;
	}
    
}