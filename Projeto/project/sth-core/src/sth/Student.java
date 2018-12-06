package sth;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;

import sth.Survey.State;
import sth.exceptions.DisciplineLimitExceededException;
import sth.exceptions.DuplicateDisciplineException;
import sth.exceptions.DuplicateIdException;
import sth.exceptions.DuplicateSurveyException;
import sth.exceptions.FinalizedSurveyException;
import sth.exceptions.NoSuchDisciplineNameException;
import sth.exceptions.NoSuchProjectNameException;
import sth.exceptions.NoSuchSurveyException;
import sth.exceptions.NotMatchingCourseException;
import sth.exceptions.OutOfRangeIdException;
import sth.exceptions.ProjectAlreadyClosedException;
import sth.exceptions.ProjectNotClosedException;
import sth.exceptions.SurveyNotClosedException;
import sth.exceptions.SurveyNotOpenException;
import sth.exceptions.SurveyWithAnswersException;

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
	

	public Discipline getDiscipline(String discipline) throws NoSuchDisciplineNameException{
		for (Discipline d : _disciplines){
			if (discipline.equals(d.getDisciplineName())) {
				return d;
			}
		}
		throw new NoSuchDisciplineNameException();
	}

	public Discipline getDiscipline(int i){
		return _disciplines.get(i);
	}

	public Discipline getCourseDiscipline(String discipline) throws NoSuchDisciplineNameException{
		return _course.getDiscipline(discipline);
	}

	public int getDisciplinesNumber(){
		return _disciplines.size();
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
	
	@Override
	public void answerSurvey(String discipline, String project, int hours, String comment) throws NoSuchDisciplineNameException, NoSuchProjectNameException, SurveyNotOpenException {
		Discipline d = getDiscipline(discipline);
		SurveyAnswer answer = new SurveyAnswer(hours, comment);
		d.submitSurveyAnswer(this, project, answer);
	}

	@Override
	public void createSurvey(String discipline, String project) throws UnsupportedOperationException, NoSuchDisciplineNameException, NoSuchProjectNameException, DuplicateSurveyException, ProjectAlreadyClosedException {
		Discipline dis = getCourseDiscipline(discipline);
		dis.createSurvey(_course, project);
	}
	
	public void cancelSurvey(String discipline, String project) throws UnsupportedOperationException, NoSuchDisciplineNameException,NoSuchProjectNameException, SurveyWithAnswersException, FinalizedSurveyException, NoSuchSurveyException {
		Discipline dis = getCourseDiscipline(discipline);
		dis.cancelSurvey(project);	
	}	
	
	public void openSurvey(String discipline, String project) throws UnsupportedOperationException, NoSuchDisciplineNameException,NoSuchProjectNameException, ProjectNotClosedException, SurveyNotClosedException, FinalizedSurveyException, NoSuchSurveyException {
		Discipline dis = getCourseDiscipline(discipline);
		dis.openSurvey(project);	
	}

	@Override
	public void closeSurvey(String discipline, String project)throws UnsupportedOperationException, NoSuchDisciplineNameException,NoSuchProjectNameException, FinalizedSurveyException, NoSuchSurveyException, SurveyNotOpenException {
	Discipline dis = getCourseDiscipline(discipline);
		dis.closeSurvey(project);	
	}

	public void finalizeSurvey(String discipline, String project) throws UnsupportedOperationException, NoSuchDisciplineNameException,NoSuchProjectNameException, SurveyNotClosedException, NoSuchSurveyException {
		Discipline dis = getCourseDiscipline(discipline);
		dis.finalizeSurvey(project);
	}
	
	@Override
	public String showDisciplineSurveys(String discipline) throws UnsupportedOperationException {
		if (isRepresentative()) {
			try {
				Discipline d = getCourseDiscipline(discipline);
				return d.showDisciplineSurveys(this);
			} catch (NoSuchDisciplineNameException e) {
				return "";
			}
		}
		else {
			return super.showDisciplineSurveys(discipline);
		}
	}

	public String showSurveyResults(String discipline, String project) throws NoSuchDisciplineNameException, NoSuchProjectNameException, NoSuchSurveyException {
		Discipline d = getDiscipline(discipline);
		Project p = d.getProject(project);
		if (p.submittedProject(getId())) {
			return p.showSurveyResults(this);
		}
		else {
			throw new NoSuchProjectNameException();
		}
	}

	@Override
	public void deliverProject(String discipline, String project, String submission) throws NoSuchDisciplineNameException , NoSuchProjectNameException, ProjectAlreadyClosedException {
		getDiscipline(discipline).deliverProject(this, project, submission);
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
	public String accept(PersonVisitor visitor){
		 return visitor.showStudent(this);
	}

	@Override
	public void addToNotificationList(String discipline, String project)
			throws UnsupportedOperationException, NoSuchDisciplineNameException, NoSuchProjectNameException, NoSuchSurveyException {
		getDiscipline(discipline).addToNotificationList(this, project);
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