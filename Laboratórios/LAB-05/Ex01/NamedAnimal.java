public abstract class NamedAnimal extends Animal {
	private String name;
	
	public NamedAnimal(String name, int baseEnergy, int energy, int runEnergy) {
		super(baseEnergy, energy, runEnergy);
		this.name = name;
	}
	public NamedAnimal(String name, int baseEnergy, int runEnergy) {
		super(baseEnergy, runEnergy);
		this.name = name;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof NamedAnimal) {
			NamedAnimal animal = (NamedAnimal) obj;
			return super.equals(obj) && this.name.equals(animal.getName());
		}
		return false;
	}
}