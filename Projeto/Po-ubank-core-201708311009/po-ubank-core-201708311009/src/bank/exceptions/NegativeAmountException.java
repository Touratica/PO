package bank.exceptions;

/**
 * Exception for negative amounts.
 */
public class NegativeAmountException extends BankAccountException {
  /** Serial number. */
  private static final long serialVersionUID = 201010181631L;

  /** Invalid amount */
  private final double _amount;

  /**
   * @param account
   * @param amount
   */
  public NegativeAmountException(int account, double amount) {
    super(account);
    _amount = amount;
  }

  /**
   * @return the negative amount
   */
  public double getAmount() {
    return _amount;
  }
}
