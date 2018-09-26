public class Mouse {
	private static final int BASE_ENERGY = 50;
	private int energy = BASE_ENERGY;

	public int getEnergy() {
		return this.energy;
	}

	public boolean run() {
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
		if (o instanceof Mouse) {
			Mouse mouse = (Mouse) o;
			return this.energy == mouse.getEnergy();
		}
		return false;
	}

	@Override
	public String toString() {
		return "mouse (" + this.energy + ")";
	}
}
