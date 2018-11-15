package sth;

/**
 * StudentDecorator
 */
public abstract class StudentDecorator implements Student {

	/** The student that will get decorated. */
	private Student _student;

	/**
	 * The StudentDecorator constructor.
	 * @param student that will get decorated
	 */
	public StudentDecorator(Student student) {
		_student = student;
	}

	// FIXME: Every method on student must be here.
}