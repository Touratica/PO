public class EqualTo implements SelectionPredicate {
	private int value;

	public EqualTo(int value) {
		this.value = value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	@Override
	public boolean ok(int value) {
		return value == this.value;
	}
}