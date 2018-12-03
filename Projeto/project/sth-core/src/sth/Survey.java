package sth;

import java.util.ArrayList;
import java.util.TreeMap;
import java.util.List;
import java.util.Map;

/**
 * The project Survey class.
 */
public class Survey implements Subject {

	// FIXME: Implement Survey class
	private List<Observer> _observers = new ArrayList<Observer>();
	private Map<Student,SurveyAnswer> _results = new TreeMap<Student,SurveyAnswer>();
	private String _project;
	private String _discipline;
	private State _state;
	
	public abstract class State {

		protected void setState(State state){
			_state = state;
		}
		protected getSurvey() {
			return Survey.this;
		}

		public abstract void cancel();
		public abstract void open();
		public abstract void close();
		public abstract void finalize();

	}

<<<<<<< HEAD
	public Survey() {
		_state = new CreatedState(this);
	}

	public void cancel() {
=======
	public Survey(String discipline, String project){
		_discipline = discipline;
		_project = project;
		_state = new CreatedState(this);
	}

	protected void setState(State s){
		_state.setState(s);
	}

	public void cancel(){
>>>>>>> cd623015646d8a0fa3545373a0e0ecb6f4389164
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
		_results.put(student, answer);	
	}
	
	public Map<Student, SurveyAnswer> getResults() {
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
			observer.update(_discipline, _project, _state); // FIXME Add arguments to update method
		}
	}
}