/**
 * Definition of a predator cat.
 * 
 * Note that, like for mice and birds, we could have defined a PredatorAnimal
 * class for factoring the code common to both cats and dogs. We chose not to do
 * it to illustrate how individual classes must implement methods otherwise not
 * provided (compare "run" defined in Animal).
 */
public class Cat extends NamedAnimal implements Predator {
 
	/**
	 * The catch rate.
	 */
	private final int CATCH_RATE = 5;
 
	/**
	 * Base energy level: 500. Run spending: 25.
	 * 
	 * @param name
	 */
	public Cat(String name) {
		super(name, 5, 500, 25);
	}
 
	/**
	 * We should probably check for large decrease values. Nevertheless, in this
	 * case, for simplicity, we will let the energy go negative and, later on,
	 * the cat can recover after a nap.
	 * 
	 * @param energyDecrease
	 */
	public void attacked(int energyDecrease) {
		this.increaseEnergy(-energyDecrease);
	}
 
 
	/**
	 * @see Predator#caught(Prey)
	 */
	public boolean caught(Prey prey) {
		this.run();
		prey.run();
		if (0 == (int) (CATCH_RATE * Math.random())) {
			return true;
		}
		prey.escaped();
		return false;
	}
 
	/**
	 * @see Predator#eat(Prey)
	 */
	public void eat(Prey prey) {
		if (this.caught(prey)) {
			this.increaseEnergy(prey.drain());
		}
	}
 
	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Cat: " + super.toString();
	}
}