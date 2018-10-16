/**
 * Definition of a predator dog.
 * 
 * Note that, like for mice and birds, we could have defined a PredatorAnimal
 * class for factoring the code common to both cats and dogs. We chose not to do
 * it to illustrate how individual classes must implement methods otherwise not
 * provided (compare "run" defined in Animal).
 */
public class Dog extends NamedAnimal implements Predator {
	/**
	 * FIXME: the catch rate should not be fixed.
	 * 
	 * The catch rate.
	 */
	private final int CATCH_RATE = 10;
 
	/**
	 * Base energy level: 500. Run spending: 25.
	 * 
	 * @param name
	 */
	public Dog(String name) {
		super(name, 25, 1000, 50);
	}
 
	/**
	 * We assume that the dog is always able to attack the cat. The parameter to
	 * ''attacked'' is used to specify the amount of energy lost by the cat.
	 * Note that we are assuming that the degree of loss depends on the attacker
	 * (hence the value being defined in the dog class).
	 * 
	 * The energy values could be defined as attributes or as constants.
	 * 
	 * @param cat
	 *            the cat the dog attacks
	 */
	public void attackCat(Cat cat) {
		this.increaseEnergy(-100);
		cat.attacked(25);
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
		return "Dog: " + super.toString();
	}
}