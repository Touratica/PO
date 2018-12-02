package sth;

/**
 * Subject
 */
public interface Subject {

	public void registerObserver(Observer o);
	public void removeOberserver(Observer o);
	public void notifyObservers();
}