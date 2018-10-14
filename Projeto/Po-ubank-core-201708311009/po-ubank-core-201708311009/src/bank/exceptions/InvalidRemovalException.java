package bank.exceptions;

/**
 * Exception for unsuccessful removal operations.
 */
public class InvalidRemovalException extends BankAccountException {
  /** Serial number. */
  private static final long serialVersionUID = 201608231557L;

  /** The amount in the account */
  private final double _amount;

  /**
   * @param account
   * @param amount
   */
  public InvalidRemovalException(int account, double amount) {
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