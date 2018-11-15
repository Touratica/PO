package sth;

/**
 * StudentDecorator
 */
public abstract class StudentDecorator extends Person implements Student {

	private static final long serialVersionUID = 201811151750L;

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