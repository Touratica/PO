package sth;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.TreeMap;

import sth.exceptions.SurveyWithAnswersException;

import java.util.List;
import java.util.Map;

/**
 * The project Survey class.
 */
public class Survey implements Subject {

	// FIXME: Implement Survey class
	private List<Observer> _observers = new ArrayList<Observer>();
	private List<SurveyAnswer> _results = new ArrayList<SurveyAnswer>();
	private List<Student> _students = new ArrayList<Student>();
	private Project _project; 
	private String _discipline;
	private State _state;
	
	public abstract class State {

		protected void setState(State state){
			_state = state;
		}
		protected Survey getSurvey() {
			return Survey.this;
		}

		public void submitAnswer(Student s, SurveyAnswer answer){ // FIXME throw exception except when is open
		}
		public abstract void cancel();
		public abstract void open();
		public abstract void close();
		public abstract void finalize();

	}

	public Survey(String discipline, String project){
		_discipline = discipline;
		_project = project;
		_state = new CreatedState(this);
	}

	protected void setState(State s){
		_state.setState(s);
	}

	public int getAnswersNumber(){
		return _results.size();
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

	public void remove() throws SurveyWithAnswersException{
		if (_results.size() == 0){
			_project.removeSurvey();
		} else {
			throw new SurveyWithAnswersException();
		}
	}

	public void cancel(){
		_state.cancel();
	}

	public void open() {
		_state.open();
	}

	public void close() {
		_state.close();
	}

	public void finalize() {
		_state.finalize();
	}

	public void submitAnswer(Student student, SurveyAnswer answer) {
		for (Student s : _students)
			if (Student.ID_COMPARATOR.compare(student, s) == 0) 
				return ; //posterior answers should be ignored
		_state.submitAnswer(student, answer);
	}
	
	public List<SurveyAnswer> getResults() {
		return _results;
	}

	@Override
	public void registerObserver(Observer o) {
		_observers.add(o);
	}

	@Override
	public void removeOberserver(Observer o) {
		int i = _observers.indexOf(o);
     	if (i >= 0) {
			_observers.remove(i);
		}
	}

	@Override
	public void notifyObservers() {
		for (Observer observer: _observers) {
			observer.update(_discipline, _project.getName(), _state); // FIXME Add arguments to update method
		}
	}
}