package sth;

/**
 * Any non-student, non-professor person.
 */
public class Administrative extends Person {
    private static final long serialVersionUID = 201811151735L;

    public Administrative(int id, int phoneNumber, String name) throws DuplicateIdException, OutOfRangeIdException {
		super(id, phoneNumber, name);
  }
  
  @Override
	public String toString() {
		return  "FUNCIONÁRIO|" + super.toString() ;
	}
    	
}