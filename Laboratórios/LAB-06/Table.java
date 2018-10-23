import java.util.Comparator;

public class Table implements Comparable<Table>, Iterable{
	public final static Comparator<Table> MAX_COMPARATOR = new MaxComparator();
	public final static Comparator<Table> LENGHT_COMPARATOR = new LenghtComparator();

	private static class MaxComparator implements Comparator<Table> {
		@Override
		public int compare(Table t1, Table t2) {
			return t1.getMax() - t2.getMax();
		}
	}

	private static class LenghtComparator implements Comparator<Table> {
		@Override
		public int compare(Table t1, Table t2) {
			return t1.getLenght() - t2.getLenght();
		}
	}

	public Iterator getForwardIterator() {
		return new ForwardIterator();
	}
	public Iterator getReverseIterator() {
		return new ReverseIterator();
	}

	private class ForwardIterator implements Iterator {
		int place = 0;
		public boolean hasNext() {
			return place < values.length;
		}
		public int next() {
			return values[place++];
		}
	}

	private class ReverseIterator implements Iterator {
		int place = 0;

		public boolean hasNext() {
			return place < values.length;
		}

		public int next() {
			return values[values.length - (1 + place++)];
		}
	}

	int values[];

	/**
	 * @param nInts number of integers to store.
	 */
	public Table(int nInts) {
		this.values = new int[nInts];
	}

	/**
	 * FIXME: insert checks to ensure position is within range.
	 * 
	 * @param position position to define
	 * @return value at position
	 */
	public int getValue(int position) {
		return this.values[position];
	}

	/**
	 * FIXME: insert checks to ensure position is within range.
	 * 
	 * @param position position to define
	 * @param value    value to set
	 */
	public void setValue(int position, int value) {
		this.values[position] = value;
	}

	/**
	 * Set all positions to the same value.
	 * 
	 * @param value value to set
	 */
	public void setAll(int value) {
		for (int position = 0; position < this.values.length; position++)
			this.values[position] = value;
	}

	/**
	 * @param predicate the predicate to validate.
	 * @return true, if the predicate is valid for at least one position; false,
	 *         otherwise.
	 */
	public boolean contains(SelectionPredicate predicate) {
		for (int position = 0; position < this.values.length; position++)
			if (predicate.ok(this.values[position]))
				return true;
		return false;
	}

	public int getSum() {
		int sum = 0;
		for (int i: this.values) {
			sum += i;
		}
		return sum;
	}

	public int getMax() {
		int max = this.values[0];
		for (int i: this.values) {
			if (i > max) {
				max = i;
			}
		}
		return max;
	}

	public int getLenght() {
		return this.values.length;
	}

	@Override
	public int compareTo(Table other) {
		return this.getSum() - other.getSum();
	}
}