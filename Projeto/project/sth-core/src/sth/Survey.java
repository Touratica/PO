package sth;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.TreeMap;
import java.util.List;
import java.util.Map;
import sth.exceptions.FinalizedSurveyException;
import sth.exceptions.ProjectNotClosedException;
import sth.exceptions.SurveyNotClosedException;
import sth.exceptions.SurveyNotOpenException;
import sth.exceptions.SurveyWithAnswersException;

/**
 * The project Survey class.
 */
public class Survey implements Serializable, Subject {

	/** Serial number for serialization. */
	private static final long serialVersionUID = 201812060156L;

	private List<Observer> _observers = new ArrayList<Observer>();
	private List<SurveyAnswer> _results = new ArrayList<SurveyAnswer>();

	/** The students who answered the survey. */
	private List<Student> _students = new ArrayList<Student>();
	
	private Project _project; 
	private Discipline _discipline;
	private State _state;
	
	public abstract class State implements Serializable {

		/** Serial number for serialization. */
		private static final long serialVersionUID = 201812060157L;

		protected void setState(State state){
			_state = state;
		}
		protected Survey getSurvey() {
			return Survey.this;
		}

		public void submitAnswer(Student s, SurveyAnswer answer) throws SurveyNotOpenException {
			throw new SurveyNotOpenException();
		}
		public abstract void cancel() throws SurveyWithAnswersException, FinalizedSurveyException;
		public abstract void open() throws ProjectNotClosedException, SurveyNotClosedException, FinalizedSurveyException;
		public abstract void close() throws SurveyNotOpenException, FinalizedSurveyException;
		public abstract void finalize() throws SurveyNotClosedException;
		public String renderResults(Person p){
			return  _discipline.getDisciplineName() + " - " +  _project.getName();
		}
		public String renderSurvey(Person p){
			return renderResults(p);
		}
		public abstract String notifyState();
	}

	public Survey(Course course, Discipline discipline, Project project) {
		_discipline = discipline;
		_project = project;
		_state = new CreatedState(this);
		for (Map.Entry<Integer, Student> entry: discipline.getStudents().entrySet()) {
			registerObserver(entry.getValue());
		}
		for (Map.Entry<Integer, Professor> entry : discipline.getProfessors().entrySet()) {
			registerObserver(entry.getValue());
		}
		for (Map.Entry<Integer, Student> entry: course.getRepresentatives().entrySet()) {
			registerObserver(entry.getValue());
		}
	}

	protected void setState(State s){
		_state.setState(s);
	}

	public String renderResults(Person p){
		return _state.renderResults(p);
	}

	public int getAnswersNumber(){
		return _results.size();
	}
	
	public SurveyAnswer getAnswer(int i){
		return _results.get(i);
	}

	public String getDisciplineName(){
		return _discipline.getDisciplineName();
	}

	public int getMaxHours(){
		int answersNumber = getAnswersNumber();
		int maxHours = getAnswer(0).getWorkHours();
		for (int i=1; i < answersNumber ; i++){
			SurveyAnswer answer = getAnswer(i);
			if (answer.getWorkHours() > maxHours)
				maxHours = answer.getWorkHours();
		}
		return maxHours;
	}

	public int getMinHours(){
		int answersNumber = getAnswersNumber();
		int minHours = getAnswer(0).getWorkHours();;
		for (int i=1; i<answersNumber ; i++){
			SurveyAnswer answer = getAnswer(i);
			if (answer.getWorkHours() < minHours)
				minHours = answer.getWorkHours();
		}
		return minHours;
	}

	public int getAverageHours(){
		int answersNumber = getAnswersNumber();
		int averageHours=0;
		for (int i=0; i<answersNumber; i++){
			averageHours+=getAnswer(i).getWorkHours();
		}
		return (int) averageHours/answersNumber;
	}

	public int getSubmissionsNumber(){
		return _project.getSubmissionsNumber();
	}

	public Project getProject(){
		return _project;
	}

	public void addResult(SurveyAnswer answer){
		_results.add(answer);
	}

	public void addStudent(Student student){
		_students.add(student);
	}

	public void remove() throws SurveyWithAnswersException {
		if (_results.size() == 0) {
			_project.removeSurvey();
		}
		else {
			throw new SurveyWithAnswersException();
		}
	}

	public void cancel() throws SurveyWithAnswersException, FinalizedSurveyException {
		_state.cancel();
	}

	public void open() throws ProjectNotClosedException, SurveyNotClosedException, FinalizedSurveyException {
		_state.open();
	}

	public void close() throws SurveyNotOpenException, FinalizedSurveyException {
		_state.close();
	}

	public void finalize() throws SurveyNotClosedException {
		_state.finalize();
	}

	public boolean isOpen() {
		return _state.notifyState().equals("open");
	}

	public boolean isFinal() {
		return _state.notifyState().equals("final");
	}

	public void submitAnswer(Student student, SurveyAnswer answer) throws SurveyNotOpenException {
		for (Student s : _students)
			if (Student.ID_COMPARATOR.compare(student, s) == 0) 
				return ; // posterior answers should be ignored
		_state.submitAnswer(student, answer);
	}
	
	public List<SurveyAnswer> getResults() {
		return _results;
	}

	public String renderSurvey(Person p){
		return _state.renderSurvey(p);
	}

	@Override
	public void registerObserver(Observer o) {
		if (_observers.indexOf(o) < 0) {
			_observers.add(o);
		}
	}

	@Override
	public void removeObserver(Observer o) {
		int i = _observers.indexOf(o);
     	if (i >= 0) {
			_observers.remove(i);
		}
	}

	@Override
	public void notifyObservers() {
		try {
			for (Observer observer: _observers) {
				observer.update(_discipline.getDisciplineName(), _project.getName(), this);
			}
		}
		// Exception thrown whenever administrative tries to update
		catch (UnsupportedOperationException e) {
			e.printStackTrace();
		}
	}
}