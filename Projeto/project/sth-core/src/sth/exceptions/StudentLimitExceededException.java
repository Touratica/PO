package sth.exceptions;

import sth.Student;

public class StudentLimitExceededException extends Exception {
	
	/** Serial number for serialization. */
	private static final long serialVersionUID = 201811151652L;
	
	private Student _student;

	public StudentLimitExceededException(Student student){
		_student = student;
	}

}