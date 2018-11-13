public abstract class BinaryOperator extends Validator {

	protected Validator _firstValidator;
	protected Validator _secondValidator;

	public BinaryOperator(Validator firstValidator, Validator secondValidator) {
		_firstValidator = firstValidator;
		_secondValidator = secondValidator;
	}
}