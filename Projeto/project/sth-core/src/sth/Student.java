package sth;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;

import sth.exceptions.DisciplineLimitExceededException;
import sth.exceptions.DuplicateDisciplineException;
import sth.exceptions.DuplicateIdException;
import sth.exceptions.NotMatchingCourseException;
import sth.exceptions.OutOfRangeIdException;

/**
 * The Student class.
 */
public class Student extends Person implements Observer {

	private static final long serialVersionUID = 201811151746L;

	/** Students's course */
	private Course _course;

	/** Disciplines taken by the student. */
	private ArrayList<Discipline> _disciplines = new ArrayList<Discipline>(); 

	/** The maximum number of disciplines per student. */
	private static final int MAX_DISCIPLINES = 6;

	/**
	 * 
	 * @param id
	 * @param phoneNumber
	 * @param name
	 * @throws DuplicateIdException
	 * @throws OutOfRangeIdException
	 */
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
	
	public void addDiscipline(Discipline discipline) throws DuplicateDisciplineException, DisciplineLimitExceededException {
		for (Discipline d: _disciplines) {
			if (d.equals(discipline)) {
				throw new DuplicateDisciplineException(discipline);
			}
		}
		if (_disciplines.size() < MAX_DISCIPLINES) {
			_disciplines.add(discipline);
		}
		else {
			throw new DisciplineLimitExceededException(discipline,this);
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

	public boolean isRepresentative() {
		return _course.getRepresentatives().containsKey(this.getId());
	}

	@Override
	public String toString() {
		String s = "";
		ArrayList<String> disciplines = new ArrayList<String>();
		for (Discipline d : _disciplines){
			disciplines.add(d.toString());
		}
		Collections.sort(disciplines, Collator.getInstance());
		if (this.isRepresentative())
			s+= "DELEGADO|" + super.toString();
		else s+= "ALUNO|" + super.toString();
		for (String d : disciplines) //disciplines is already sorted
			s+= "\n* " + _course.toString() + " - " + d;
		return s; 

	}
    
}