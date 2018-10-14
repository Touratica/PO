package bank.exceptions;

/**
 * Exception for unsuccessful deposit operations.
 */
public class InvalidDepositException extends BankAccountException {
  
  /** Serial number. */
  private static final long serialVersionUID = 201608231557L;

  /** The amount in the account */
  private final double _amount;

  /**
   * @param account
   * @param amount
   */
  public InvalidDepositException(int account, double amount) {
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