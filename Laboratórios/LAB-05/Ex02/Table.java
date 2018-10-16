public class Table {
	private int values[];

	public Table(int n) {
		this.values = new int[n];
	}

	public int getValue(int index) {
		// if (index >= 0 && index < this.values.length) {
			return this.values[index];
		// }
	}

	public void setValue(int index, int value) {
		if (index >= 0 && index < this.values.length) {
			 this.values[index] = value;
		}
	}

	public void setAll(int value) {
		for (int i = 0; i < this.values.length; i++) {
			this.values[i] = value;
		}
	}

	public boolean contains(SelectionPredicate predicate) {
		for (int i = 0; i < this.values.length; i++) {
			if (predicate.ok(this.values[i])) {
				return true;
			}
		}
		return false;
	}
}