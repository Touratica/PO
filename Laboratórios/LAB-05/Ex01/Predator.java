/**
 * Definition of the predator's interface.
 */
public interface Predator {
	/**
	 * Predators run.
	 */
	void run();

	/**
	 * Predators catch prey.
	 * 
	 * @param prey
	 *            the prey to be caught.
	 * @return whether the predator was able to catch the prey.
	 */
	boolean caught(Prey prey);

	/**
	 * @param prey the prey to be eaten.
	 */
	void eat(Prey prey);
}