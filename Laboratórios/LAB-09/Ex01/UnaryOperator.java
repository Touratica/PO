public abstract class UnaryOperator extends Validator {

	protected Validator _validator;

	public UnaryOperator(Validator validator) {
		_validator = validator;
	}
}