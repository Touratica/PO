package sth;

import java.util.ArrayList;

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
	
	public Student(int id, int phone_nr, String name){
		this.setId(id);
		this.setPhoneNumber(phone_nr);
		this.setName(name);
	}

	//FIXME adicionar excecoes aqui
	public void setCourse(Course course){
		if (_course == null)
			_course = course;
	}

	//FIXME adicionar excecoes aqui
	public void addDiscipline(Discipline d){
		if (_disciplines.size() < 6)
        	_disciplines.add(d);
	}
	
	/**
	 * @return the student's course.
	 */
	public Course showCourse(){
		return _course;
	}

}