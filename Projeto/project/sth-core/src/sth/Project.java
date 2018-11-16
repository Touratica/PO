package sth;

import java.io.Serializable;
import java.util.Map;

import sth.exceptions.ProjectAlreadyClosedException;

import java.util.HashMap;

/**
 * The Project class.
 */
public class Project implements Serializable {

	private static final long serialVersionUID = 201811151744L;

	private String _name;
	private String _description;
	private Map<Integer, String> _submissions = new HashMap<Integer, String>();
	private Boolean _open = true; 


	public Project(String name, String description){
		_name = name;
		_description = description;
	}
	
	// FIXME: Implement Project class
	
	/**
	 * @return the project's name
	 */
	public String getName() {
		return _name;
	}

	/**
	 * @return the project's description
	 */
	public String getDescription() {
		return _description;
	}

	/**
	 * @return the project's submissions
	 */
	public Map<Integer, String> get_submissions() {
		return _submissions;
	}

	/**
	 * @return the _opened
	 */
	public Boolean isOpen() {
		return _open;
	}

	/**
	 * Closes project.
	 */
	public void close() throws ProjectAlreadyClosedException {
		if (!isOpen()) {
			throw new ProjectAlreadyClosedException();
		} else {
			_open = false;
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Project) {
			Project project = (Project) obj;
			return _name == project.getName();
		}
		return false;
	}

}