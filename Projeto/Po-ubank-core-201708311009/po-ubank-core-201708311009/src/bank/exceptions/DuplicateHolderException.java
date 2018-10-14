package bank.exceptions;

/**
 * This exception represents a duplicate holder problem.
 */
public class DuplicateHolderException extends AccountHolderException {
  
	/** Serial number for serialization. */
	private static final long serialVersionUID = 201608231538L;

	/**
	 * @param id
	 * @param name
	 */
	public DuplicateHolderException(int id, String name) {
		super(id, name);
	}
}
