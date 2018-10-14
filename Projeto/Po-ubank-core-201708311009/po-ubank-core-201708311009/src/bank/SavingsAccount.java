package bank;

import bank.exceptions.InvalidDepositException;
import bank.exceptions.InvalidWithdrawalException;
import bank.exceptions.NegativeAmountException;

/**
 * Class for savings accounts. Savings accounts have a minimum time period.
 */
public class SavingsAccount extends DepositAccount {

  /** Serial number for serialization. */
  private static final long serialVersionUID = 201608231540L;

  /** The account's time period. */
  private int _time;

  /** The bank account associated with this savings account. */
  private BankAccount _account;

  /**
   * Constructor.
   * 
   * @param amount
   *          the initial balance.
   * @param rate
   *          the account's interest rate.
   * @param time
   *          the account's time period.
   * @param account
   *          the associated bank account.
   */
  public SavingsAccount(double amount, double rate, int time, BankAccount account) {
    super(rate, amount);
    _time = time;
    _account = account;
  }

  /**
   * Get the account's time period.
   * 
   * @return the account's time period.
   */
  final public int getTime() {
    return _time;
  }

  /**
   * Close savings account. The balance of the account is added to the bank
   * account associated with the savings account (which is then removed from the
   * list of savings accounts). This method considers interest only after the
   * time associated with the savings account has finished (otherwise, only the
   * initial balance is deposited in the bank account).
   */
  public void close() {
    try {
      if (Calendar.getCalendar().daysSince(getInitialDate()) >= _time)
        _account.deposit(getBalance() * (1 + getRate()));
      else
        _account.deposit(getBalance());
    } catch (InvalidDepositException e) {
      // never happens
    } catch (NegativeAmountException e) {
      // never happens: _balance is never negative.
    }
  }

  /**
   * Savings accounts can only be initialized: they cannot be the object of
   * deposit operations.
   * 
   * @param amount
   *          the amount to be deposited (ignored).
   * @throws InvalidDepositException
   *           since it is not possible to make deposits in savings accounts.
   */
  @Override
  public void deposit(double amount) throws InvalidDepositException {
    throw new InvalidDepositException(_account.getId(), amount);
  }

  /**
   * Withdrawals cannot be performed on savings accounts.
   * 
   * @param amount
   *          the amount to be withdrawn (ignored).
   * @throws InvalidWithdrawalException
   *           since it is not possible to perform withdrawals from savings
   *           accounts.
   */
  @Override
  public void withdraw(double amount) throws InvalidWithdrawalException {
    throw new InvalidWithdrawalException(getId(), amount);
  }

  /**
   * @see java.lang.Object#toString()
   */
  @Override
  @SuppressWarnings("nls")
  public String toString() {
    return "<savings-account initial='" + getInitialDate() + "' timelimit='" + _time + "' balance='"
        + getBalance() + "'/>";
  }

}
