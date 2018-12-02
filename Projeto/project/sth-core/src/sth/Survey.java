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