class Cat extends Animal {
	private Integer _age;

	public Cat(int age, double weight) {
		super(weight);
		_age = age;
	}

	public Integer getAge() {
		return _age;
	}

	public String toString() {
		return super.toString() + " e idade " + _age;
	}
}