package bank.exceptions;

/**
 * Exception for unsuccessful withdrawal operations.
 */
public class InvalidWithdrawalException extends BankAccountException {
  /** Serial number. */
  private static final long serialVersionUID = 201608231557L;

  /** The amount in the account */
  private final double _amount;

  /**
   * @param account
   * @param amount
   */
  public InvalidWithdrawalException(int account, double amount) {
    super(account);
    _amount = amount;
  }

  /**
   * @return the amount
   */
  public double getAmount() {
    return _amount;
  }
}
