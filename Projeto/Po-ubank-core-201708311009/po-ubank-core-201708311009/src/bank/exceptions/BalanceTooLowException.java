package bank.exceptions;

/**
 * Balance is lower than amount being withdrawn.
 */
public class BalanceTooLowException extends BankAccountException {
  /** Serial number. */
  private static final long serialVersionUID = 201010181631L;

  /** Current balance */
  private final double _balance;

  /** The amount in the account */
  private final double _amount;

  /**
   * @param account
   * @param balance
   * @param amount
   */
  public BalanceTooLowException(int account, double balance, double amount) {
    super(account);
    _balance = balance;
    _amount = amount;
  }

  /**
   * @return the balance
   */
  public double getBalance() {
    return _balance;
  }

  /**
   * @return the invalid amount
   */
  public double getAmount() {
    return _amount;
  }
}
