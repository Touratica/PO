public abstract class AnimalPrey extends Animal implements Prey {
	public AnimalPrey(int baseEnergy, int energy, int runEnergy) {
		super(baseEnergy, energy, runEnergy);
	}

	public AnimalPrey(int baseEnergy, int runEnergy) {
		super(baseEnergy, runEnergy);
	}

	/**
	 * @see Prey#drain()
	 */
	@Override
	public int drain() {
		int energy = this.getEnergy();
		this.setEnergy(0);
		return energy;
	}

	/**
	 * @see Prey#escaped()
	 */
	@Override
	public void escaped() {
		this.increaseEnergy(5);
	}
}