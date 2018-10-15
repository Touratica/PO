/**
 * Definition of the prey's interface.
 */
public interface Prey {
	/**
	 * Preys run.
	 */
	void run();
 
	/**
	 * Energy goes up for the prey in a narrow escape.
	 */
	void escaped();
 
	/**
	 * @return the energy level in this prey
	 */
	int drain(); 
}