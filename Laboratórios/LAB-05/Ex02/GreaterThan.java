public class GreaterThan implements SelectionPredicate {
	private int value;

	public GreaterThan(int value) {
		this.value = value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	@Override
	public boolean ok(int value) {
		return value > this.value;
	}
}