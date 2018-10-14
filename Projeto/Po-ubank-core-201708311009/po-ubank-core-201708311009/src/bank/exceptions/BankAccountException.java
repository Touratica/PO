package bank.exceptions;

/**
 * Basic exception class for bank operations. Should not be instantiated
 * directly (thus, the abstract qualifier).
 */
public abstract class BankAccountException extends Exception {
  /** Serial number. */
  private static final long serialVersionUID = 201608231557L;

  /** The account */
  private final int _account;

  /**
   * @param account
   */
  public BankAccountException(int account) {
    _account = account;
  }

  /**
   * @return the account
   */
  public int getAccount() {
    return _account;
  }

}
