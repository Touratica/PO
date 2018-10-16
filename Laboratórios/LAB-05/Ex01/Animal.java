public abstract class Animal {
	private final int baseEnergy;
	private int energy;
	private final int runEnergy;

	public Animal(int baseEnergy, int energy, int runEnergy) {
		this.baseEnergy = baseEnergy;
		this.energy = Math.min(baseEnergy, energy);
		this.runEnergy = Math.min(baseEnergy, runEnergy);
	}
	public Animal(int baseEnergy, int runEnergy) {
		this.baseEnergy = this.energy = baseEnergy;
		this.runEnergy = Math.min(baseEnergy, runEnergy);
	}

	/**
	 * @return the baseEnergy
	 */
	public int getBaseEnergy() {
		return this.baseEnergy;
	}

	/**
	 * @return the energy
	 */
	public int getEnergy() {
		return this.energy;
	}

	/**
	 * @param energy the energy to set
	 */
	public void setEnergy(int energy) {
		this.energy = energy;
	}

	/**
	 * @return the runEnergy
	 */
	public int getRunEnergy() {
		return this.runEnergy;
	}

	public void increaseEnergy(int value) {
		this.energy += value;
	}

	public void run() {
		if (this.energy < this.runEnergy) {
			return;
		}
		this.energy -= this.runEnergy;
	}
	
	public void sleep() {
		this.energy = this.baseEnergy;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Animal) {
			Animal animal = (Animal) obj;
			return this.baseEnergy == animal.getBaseEnergy() && this.energy == animal.getEnergy() && this.runEnergy == animal.getRunEnergy();
		}
		return false;
	}

	@Override
	public String toString() {
		return "base energy: " + this.baseEnergy + ", energy left: " + this.energy + ", spent running: " + this.runEnergy;
	}
}