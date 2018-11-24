package sth;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import sth.exceptions.AlreadyRepresentativeException;
import sth.exceptions.RepresentativeNumberExceeded;

/**
 * The Course class.
 */
public class Course	implements Serializable {

	/** Serial number for serialization. */
	private static final long serialVersionUID = 201811152338L;
	
	private String _name;
	private Map<Integer, Student> _representatives = new HashMap<Integer, Student>();
	private static final int MAX_REPRESENTATIVES = 7;

	public Course(String name) {
		_name = name;
	}

	public String getNameCourse() {
		return _name;
	}

	/**
	 * @return the the course's representatives list
	 */
	public Map<Integer, Student> getRepresentatives() {
		return _representatives;
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
	@Override 
	public boolean equals(Object o) {
		if (o instanceof Course){
			Course course = (Course) o;
			return course.getNameCourse() == _name;
		}
		return false;
	}

	@Override
	public String toString() {
		return _name ;
	}
    
}