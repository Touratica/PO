package sth;

/**
 * Observer
 */
public interface Observer {

	public void update(String discipline, String project, Survey.State state); // FIXME Add arguments to update method
}