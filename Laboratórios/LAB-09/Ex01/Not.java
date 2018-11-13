public class Not extends UnaryOperator {

	public Not(Validator validator) {
		super(validator);
	}

	@Override
	public boolean ok(String str) {
		return !_validator.ok(str);
	}
}