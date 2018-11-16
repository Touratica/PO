package sth;

import java.io.Serializable;
import java.util.ArrayList;

import sth.exceptions.AlreadyRepresentativeException;
import sth.exceptions.RepresentativeNumberExceeded;

/**
 * The Course class.
 */
public class Course	implements Serializable {

	private static final long serialVersionUID = 201811152338L;
	private String _name;
	private ArrayList<Student> _representatives = new ArrayList<Students>();

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
		else if (_representatives.size() >= 6) {
			throw new RepresentativeNumberExceeded();
		}
		else {
			_representatives.add(student);
		}
	}
}