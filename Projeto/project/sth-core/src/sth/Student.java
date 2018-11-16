package sth;

import java.util.ArrayList;

import sth.exceptions.DuplicateDisciplineException;
import sth.exceptions.NotMatchingCourseException;

/**
 * The Student class.
 */
public class Student extends Person {

	private static final long serialVersionUID = 201811151746L;

	/** Students's course */
	private Course _course;

	/** Disciplines taken by the student. */
	private ArrayList<Discipline> _disciplines = new ArrayList<Discipline>(); 

	/** The maximum number of disciplines per student. */
	private static final int MAX_DISCIPLINES = 6;

	public Student(int id, int phoneNumber, String name) throws DuplicateIdException, OutOfRangeIdException {
		super(id, phoneNumber, name);
	}

	/**
	 * @param course the course to set
	 */
	public void setCourse(Course course) throws NotMatchingCourseException {
		if (_course == null)
			_course = course;
		if (_course != course) throw new NotMatchingCourseException();
	}

	/**
	 * @return the student's course.
	 */
	public Course getCourse() {
		return _course;
	}
	
	public void addDiscipline(Discipline discipline) throws DuplicateDisciplineException, DisciplineLimitExceeded {
		for (Discipline d: _disciplines) {
			if (d.equals(discipline)) {
				throw new DuplicateDisciplineException(discipline);
			}
		}
		if (_disciplines.size() < MAX_DISCIPLINES) {
			_disciplines.add(discipline);
		}
		else {
			throw new DisciplineLimitExceeded(discipline,this);
		}
	}
	
	public void createSurvey(Project project) {
		// FIXME
	}

	public void deleteSurvey(Survey survey) {
		// FIXME
	}

	public void openSurvey(Survey survey) {
		// FIXME
	}

	public void closeSurvey(Survey survey) {
		// FIXME
	}

	public Boolean isRepresentative() {
		return _course.getRepresentatives().contains(this);
	}
}