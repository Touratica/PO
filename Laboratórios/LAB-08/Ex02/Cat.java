public class Cat {
	private int _age;
	private double _weight;
	private String _name;

	public Cat(int age, double weight, String name) {
		_age = age;
		_weight = weight;
		_name = name;
	}

	/**
	 * @return the _age
	 */
	public int get_age() {
		return _age;
	}

	/**
	 * @return the _weight
	 */
	public double get_weight() {
		return _weight;
	}

	/**
	 * @return the _name
	 */
	public String get_name() {
		return _name;
	}
}