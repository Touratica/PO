package sth;

import java.io.Serializable;

/**
 * The Course class.
 */
public class Course	implements Serializable {

	private static final long serialVersionUID = 201811151736L;
	private String _name;

	public Course(String name){
		_name = name;
		
	}

	public String getNameCourse(){
		return _name;
	}
}