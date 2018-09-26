public class Cat {
	private static final int BASE_ENERGY = 500;
	private String name;
	private int energy = BASE_ENERGY;

	public Cat(String name) {
		this.name = name;
	}

	public int getEnergy() {
		return this.energy;
	}

	public String getName() {
		return this.name;
	}

	public boolean run() {
		if (this.energy < 25) {
			return false;
		}
		this.energy -= 25;
		return true;
	}

	public void sleep() {
		this.energy = BASE_ENERGY;
	}

	public boolean isExausted() {
		return this.energy == 0;
	}
	
	public void eatMouse(Mouse mouse) {
		if (this.caughtMouse(mouse)) {
			this.energy += mouse.drain();
		}
	}
	
	public void eatBird(Bird bird) {
		if (this.caughtBird(bird)) {
			this.energy += bird.drain();
		}
	}

	public boolean caughtMouse(Mouse mouse) {
		this.run();
		if (!mouse.run()) {
			return true;
		}

		if (0 == (int) (5 * Math.random())) {
			return true;
		}
		if (this.isExausted()) {
			this.sleep();
		}
		mouse.escaped();
		return false;
	}

	public boolean caughtBird(Bird bird) {
		this.run();
		if (!bird.run()) {
			return true;
		}

		if (0 == (int) (10 * Math.random())) {
			return true;
		}
		if (this.isExausted()) {
			this.sleep();
		}
		bird.escaped();
		return false;
	}

	public void attacked(int energy) {
		this.energy -= energy;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof Cat) {
			Cat cat = (Cat) o;
			return this.name.equals(cat.getName()) && this.energy == cat.getEnergy();
		}
		return false;
	}

	@Override
	public String toString() {
		return this.name + " (cat) (" + this.energy + ")";
	}
}
