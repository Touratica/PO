public class Or extends BinaryOperator {

	public Or(Validator firstValidator, Validator secondValidator) {
		super(firstValidator, secondValidator);
	}

	@Override
	public boolean ok(String str) {
		return _firstValidator.ok(str) || _secondValidator.ok(str);
	}
}