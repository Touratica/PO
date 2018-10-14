package bank;

import java.io.Serializable;

import bank.exceptions.BalanceTooLowException;
import bank.exceptions.InvalidDepositException;
import bank.exceptions.InvalidWithdrawalException;
import bank.exceptions.NegativeAmountException;

/**
 * This is an abstract class representing a deposit account. Subclasses refine
 * this class in accordance with the type of service provided by the bank. In
 * its most abstract form, a deposit account keeps track of the account's
 * balance, interest rate, deposit date and a deposit number. This number
 * uniquely identifies deposit accounts.
 */
public abstract class DepositAccount implements Serializable {

  /** Serial number for serialization. */
  private static final long serialVersionUID = 201608231528L;

  /** The shared counter for numbering deposit accounts. */
  private static int _nextId = 0;

  /** The deposit account balance. */
  private double _balance;

  /** The deposit account's creation date. */
  private long _initialDate;

  /** The deposit account id. */
  private int _id;

  /** The deposit account's interest rate. */
  private double _rate;

  /**
   * Initialize a deposit account with a given interest rate and a given
   * initial amount. The number is taken from the internal shared counter
   * _nextNumber. The date is set to the current day.
   * 
   * @param rate
   *            the deposit account interest rate.
   * @param amount
   *            the deposit account's initial balance.
   */
  public DepositAccount(double rate, double amount) {
    _balance = amount;
    _id = _nextId++;
    _rate = rate;
    _initialDate = Calendar.getCalendar().today();
  }

  /**
   * @param amount
   *            the amount to be deposited.
   * @throws InvalidDepositException
   *             if it not possible to perform the deposit.
   * @throws NegativeAmountException
   *             if the amount is not positive.
   */
  abstract public void deposit(double amount) throws InvalidDepositException,
      NegativeAmountException;

  /**
   * @param amount
   *            the amount to be withdrawn.
   * @throws InvalidWithdrawalException
   *             if the withdrawal is not possible.
   * @throws NegativeAmountException
   *             if the amount is not positive.
   * @throws BalanceTooLowException
   */
  public abstract void withdraw(double amount) throws InvalidWithdrawalException,
      NegativeAmountException, BalanceTooLowException;

  /**
   * Add to the current balance (protected!).
   * 
   * @param amount
   *          the amount to increase (or decrease).
   */
  protected void addBalance(double amount) {
    _balance += amount;
  }

  /**
   * Returns the current balance.
   * 
   * @return the current balance.
   */
  public double getBalance() {
    return _balance;
  }

  /**
   * Returns the date of the deposit.
   * 
   * @return the date of the deposit.
   */
  final public long getInitialDate() {
    return _initialDate;
  }

  /**
   * Return the account rate.
   * 
   * @return the account rate
   */
  public final double getRate() {
    return _rate;
  }

  /**
   * Return the deposit account id.
   * 
   * @return the deposit account id
   */
  public final int getId() {
    return _id;
  }

  /** @see java.lang.Object#equals(java.lang.Object) */
  @Override
  public final boolean equals(Object depacc) {
    return depacc instanceof DepositAccount && ((DepositAccount) depacc).getId() == _id;
  }

}
