package sth;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * The project Survey class.
 */
public class Survey implements Subject {

	// FIXME: Implement Survey class
	private List<Observer> _observers = new ArrayList<Observer>();
	private int _totalWorkHours;
	private String _comment;
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

	public void submitAnswer(int hours, String comment){
		_totalWorkHours = hours;
		_comment = comment;
	}
	
	public void getResults(){
		//FIXME
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