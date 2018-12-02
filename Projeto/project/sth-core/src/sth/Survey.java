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
	private State _state;
	
	public abstract class State{

		protected void setState(State state){
			_state = state;
		}
		protected getSurvey(){
			return Survey.this;
		}

		public abstract void cancel();
		public abstract void open();
		public abstract void close();
		public abstract void finalize();
	}

	public Survey(){
		_state = new CreatedState(this);
	}

	public void cancel(){
		_state.cancel();
	}

	public void open(){
		_state.open();
	}

	public void close(){
		_state.close();
	}

	public void finalize(){
		_state.finalize();
	}

	public void submitAnswer(Student student, SurveyAnswer answer){
		_results.put(student, answer);	
	}
	
	public Map<Student, SurveyAnswer> getResults(){
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
			observer.update(); // FIXME Add arguments to update method
		}
	}
}