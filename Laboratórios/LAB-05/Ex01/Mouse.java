/**
 * A mouse is a simple prey.
 * 
 * Note that class AnimalPrey only exists to factor code common to known preys
 * (mice and birds). A prey class may choose to implement those methods
 * directly.
 */
public class Mouse extends AnimalPrey {
	/**
	 * Base energy level: 50. Run spending: 2.
	 */
	public Mouse() {
		super(50, 2);
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Mouse: " + super.toString();
	}
}