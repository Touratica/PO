package sth;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;

import sth.Survey.State;
import sth.exceptions.DisciplineLimitExceededException;
import sth.exceptions.DuplicateDisciplineException;
import sth.exceptions.DuplicateIdException;
import sth.exceptions.NotMatchingCourseException;
import sth.exceptions.OutOfRangeIdException;

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
	

	public Discipline getDiscipline(String discipline){
		for (Discipline d : _disciplines){
			if (discpline.equals(d.getDisciplineName())) {
				return d;
			}
		}
		return null;
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

	public void submitProject(String discipline, String project, String submission){
		for (Discipline d : disciplines)
			if (discipline.equals(d.getDisciplineName())){
				d.submitProject(this, project, submission);
			}
		// FIXME throw exception NAO INSCRITO
	}
	
	public void submitSurveyAnswer(String discipline, String project, int hours, String comment){
		Discipline d = getDiscipline(discipline);
		SurveyAnswer answer = new SurveyAnswer(hours, comment);
		d.submitSurveyAnswer(this, project, answer);

		// FIXME se o student nao tiver disciplina mandar excecao 
	}

	public void createSurvey(Discipline discipline, Project project) {
		project.createSurvey(discipline, project);
	}

	public void cancelSurvey(Survey survey) {
		survey.cancel();
	}

	public void openSurvey(Survey survey) {
		survey.open();
	}

	public void closeSurvey(Survey survey) {
		survey.close();
	}

	public String showSurveyResults(String discipline, String project){
		Discipline d = getDiscipline(discipline);
		if (d != null){
			Project p = d.getProject(project); 
			return p.showSurveyResults(this); // FIXME I think this this is Student 
		}
		return null; // FIXME em vez de retornar null mandar excecao q disciplina n existe	

	}

	@Override
	public boolean isRepresentative() {
		return _course.getRepresentatives().containsKey(this.getId());
	}

	@Override
	public boolean isStudent() {
		return true;
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