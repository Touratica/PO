package sth;

import sth.exceptions.DuplicateIdException;
import sth.exceptions.OutOfRangeIdException;

/**
 * Any non-student, non-professor person.
 */
public class Administrative extends Person {
	
	/** Serial number for serialization. */
	private static final long serialVersionUID = 201811151735L;

    public Administrative(int id, int phoneNumber, String name) throws DuplicateIdException, OutOfRangeIdException {
		super(id, phoneNumber, name);
	}
  
	@Override
	public boolean isAdministrative() {
		return true;
	}
	
	@Override
	public String toString() {
		return  "FUNCIONÁRIO|" + super.toString() ;
	}
	
	@Override
	public void update(String discipline, String project, Survey survey) {
		throw new UnsupportedOperationException();
	}
}