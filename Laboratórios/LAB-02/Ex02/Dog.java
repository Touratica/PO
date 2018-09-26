public class Dog {
	private static final int BASE_ENERGY = 1000;
	private String name;
	private int energy = BASE_ENERGY;

	public Dog(String name) {
		this.name = name;
	}

	public int getEnergy() {
		return this.energy;
	}

	public String getName() {
		return this.name;
	}

	public boolean run() {
		if (this.energy < 50) {
			return false;
		}
		this.energy -= 50;
		return true;
	}

	public void sleep() {
		this.energy = BASE_ENERGY;
	}

	public boolean isExausted() {
		return this.energy == 0;
	}

	public boolean caughtMouse(Mouse mouse) {
		run();
		if (!mouse.run()) {
			return true;
		}

		if (0 == (int) (25 * Math.random())) {
			return true;
		}
		if (this.isExausted()) {
			this.sleep();
		}
		mouse.escaped();
		return false;
	}

	public void eatMouse(Mouse mouse) {
		if (this.caughtMouse(mouse)) {
			this.energy += mouse.drain();
		}
	}

	public void attackCat(Cat cat) {
		this.energy -= 100;
		cat.attacked(25);
	}
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof Dog) {
			Dog dog = (Dog) o;
			return this.name.equals(dog.getName()) && this.energy == dog.getEnergy();
		}
		return false;
	}

	@Override
	public String toString() {
		return this.name + " (dog) (" + this.energy + ")";
	}
}
