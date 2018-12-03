package sth.exceptions;

public class OutOfRangeIdException extends Exception {
	
	/** Serial number for serialization. */
	private static final long serialVersionUID = 201811151639L;
	
	private int _id;

	//id is either more than 6 digits long or its value is lower than 100000
	public OutOfRangeIdException(int id) {
		_id = id;
	}

	public int getId() {
		return _id;
	}
		
}