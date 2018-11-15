package sth;

import java.util.ArrayList;

import sth.exceptions.DuplicateDisciplineException;
import sth.exceptions.NotMatchingCourseException;

/**
 * The RegularStudent class.
 */
public class RegularStudent extends Person implements Student {

	private static final long serialVersionUID = 201811151746L;

	/** Students's course */
	private Course _course;

	/** Disciplines taken by the student. */
	private ArrayList<Discipline> _disciplines = new ArrayList<Discipline>(); 

	/** Student's constructors */
	
	public RegularStudent(int id, int phone_nr, String name) throws DuplicateIdException, OutOfRangeIdException{
		this.setId(id);
		this.setPhoneNumber(phone_nr);
		this.setName(name);
	}

	public void setCourse(Course course) throws NotMatchingCourseException{
		if (_course == null)
			_course = course;
		if (_course!=course) throw new NotMatchingCourseException();
	}

	
	public void addDiscipline(Discipline discipline)throws DuplicateDisciplineException, DisciplineLimitExceeded{
		for (Discipline d: _disciplines)
			if (d.equals(discipline))
				throw new DuplicateDisciplineException(discipline);
		if (_disciplines.size() < 6)
			_disciplines.add(discipline);
		else throw new DisciplineLimitExceeded(discipline,this);
	}
	
	/**
	 * @return the student's course.
	 */
	public Course showCourse(){
		return _course;
	}

}