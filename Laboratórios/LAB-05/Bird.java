/**
 * A bird is a simple prey and is able to fly.
 * 
 * Note that class AnimalPrey only exists to factor code common to known preys
 * (mice and birds). A prey class may choose to implement those methods
 * directly.
 */
public class Bird extends AnimalPrey {
	/**
	 * Base energy level: 20. Run spending: 5.
	 */
	public Bird() {
		super(20, 5);
	}

	/**
	 * When a bird flies, the energy decreases by 2 units. This value could be
	 * defined as an attribute or as a constant.
	 * 
	 * @return whether the bird was able to fly.
	 */
	public boolean fly() {
		if (this.getEnergy() < 2) {
			return false;
		}
		this.increaseEnergy(-2);
		return true;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Bird: " + super.toString();
	}
}