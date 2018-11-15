package sth;

import java.io.Serializable;
import java.util.ArrayList;

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
}