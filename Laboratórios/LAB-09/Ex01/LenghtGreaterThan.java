public class LenghtGreaterThan extends IntegerComparisonOperator {

	public LenghtGreaterThan(int reference) {
		super(reference);
	}

	@Override
	public boolean ok(String str) {
		return str.length() > _reference;
	}
}