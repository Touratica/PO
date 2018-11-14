package sth;

/**
 * The PairCourseDiscipline class, with it's disciplines and corresponding course.
 */
public class PairCourseDiscipline {

	private Course _course;
	private Discipline _discipline;

	public PairCourseDiscipline(Course course, Discipline discipline) {
		_course = course;
		_discipline = discipline;
	}

	/**
	 * @return the pair's course
	 */
	public Course getCourse() {
		return _course;
	}

	/**
	 * @return the pair's discipline
	 */
	public Discipline getDiscipline() {
		return _discipline;
	}
}