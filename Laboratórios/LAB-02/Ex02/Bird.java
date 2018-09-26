public class Bird {
	private static final int BASE_ENERGY = 20;
	private int energy = BASE_ENERGY;

	public int getEnergy() {
		return this.energy;
	}

	public boolean run() {
		if (this.energy < 5) {
			return false;
		}
		this.energy -= 5;
		return true;
	}

	public boolean fly() {
		if (this.energy < 2) {
			return false;
		}
		this.energy -= 2;
		return true;
	}

	public void sleep() {
		this.energy = BASE_ENERGY;
	}

	public boolean isExausted() {
		return this.energy == 0;
	}
	
	public int drain() {
		int energy = this.energy;
		this.energy = 0;
		return energy;
	}

	public void escaped() {
		if (this.isExausted()) {
			this.sleep();
		}
		this.energy += 5;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof Bird) {
			Bird bird = (Bird) o;
			return this.energy == bird.getEnergy();
		}
		return false;
	}

	@Override
	public String toString() {
		return "bird (" + this.energy + ")";
	}
}
