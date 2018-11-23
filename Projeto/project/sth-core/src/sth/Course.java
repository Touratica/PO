package sth;

import java.io.Serializable;
import java.util.ArrayList;

import sth.exceptions.AlreadyRepresentativeException;
import sth.exceptions.RepresentativeNumberExceeded;

/**
 * The Course class.
 */
public class Course	implements Serializable {

	/** Serial number for serialization. */
	private static final long serialVersionUID = 201811152338L;
	
	private String _name;
	private ArrayList<Student> _representatives = new ArrayList<Student>();
	private static final int MAX_REPRESENTATIVES = 7;

	public Course(String name){
		_name = name;
	}

	public String getNameCourse(){
		return _name;
	}

	/**
	 * @return the the course's representatives list
	 */
	public ArrayList<Student> getRepresentatives() {
		return _representatives;
	}

	public void setRepresentative(Student student) throws AlreadyRepresentativeException, RepresentativeNumberExceeded {
		if (_representatives.contains(student)) {
			throw new AlreadyRepresentativeException();
		}
		else if (_representatives.size() >= MAX_REPRESENTATIVES) {
			throw new RepresentativeNumberExceeded();
		}
		else {
			_representatives.add(student);
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