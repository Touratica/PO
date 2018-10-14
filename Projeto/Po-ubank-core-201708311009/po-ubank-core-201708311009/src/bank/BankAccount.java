package bank;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import bank.exceptions.BalanceTooLowException;
import bank.exceptions.InvalidDepositException;
import bank.exceptions.InvalidWithdrawalException;
import bank.exceptions.NegativeAmountException;

/**
 * This class represents the relationship between a bank and its customers
 * (account holders). An account belongs to one or more holders. Each account
 * has one current account and 0 (zero) or more savings accounts.
 */
public class BankAccount implements Comparable<BankAccount>, Serializable {

  /**
   * Serial number for serialization.
   */
  private static final long serialVersionUID = 201608231523L;

  /** This account's number. */
  private int _id;

  /** The current account associated with this account. */
  private CurrentAccount _currentAccount;

  /** The list of savings accounts associated with this account. */
  private List<SavingsAccount> _savingsAccounts;

  /** The holders of this account. */
  private List<Holder> _holders;

  /**
   * Constructor for class Account.
   * 
   * @param id
   *          account id
   * @param amount
   *          initial amount for the current account.
   */
  public BankAccount(int id, double amount) {
    _id = id;
    _currentAccount = new CurrentAccount(amount);
    _savingsAccounts = new ArrayList<SavingsAccount>();
    _holders = new ArrayList<Holder>();
  }

  /**
   * Constructor for class Account (from String array).
   * 
   * @param id
   *          account id
   * @param init
   *          account's parameters
   */
  public BankAccount(int id, String[] init) {
    this(id, Double.parseDouble(init[1]));
  }

  /**
   * @return the account number.
   */
  public final int getId() {
    return _id;
  }

  /**
   * Compares the number of this account with the number of the account passed
   * as argument. This method may be used when sorting accounts.
   * 
   * @param account
   *          account to be compared with the current one
   * @return -1, if the account number is lower than the other account's; 0, if
   *         the account numbers are the same; 1, if the account number is
   *         greater than the other account's.
   * @see java.lang.Comparable
   *
   * @see java.lang.Comparable#compareTo(java.lang.Object)
   */
  @Override
  public int compareTo(BankAccount account) {
    int number = account.getId();
    return (_id < number ? -1 : (_id == number ? 0 : 1));
  }

  /**
   * Tests whether two accounts are equal. Two accounts are considered equal
   * when they have the same account number.
   * 
   * @param obj
   *          object to be compared (probably, but not necessarily, another
   *          account).
   * @return true, if the two object have the same number; false, otherwise.
   */
  @Override
  public boolean equals(Object obj) {
    return (obj instanceof BankAccount) && ((BankAccount) obj).getId() == _id;
  }

  /**
   * Makes a deposit in the current account.
   * 
   * @param amount
   *          the amount to be deposited.
   * @throws InvalidDepositException
   *           if it is not possible to make the deposit.
   * @throws NegativeAmountException
   *           if the amount is not positive.
   */
  public void deposit(double amount) throws InvalidDepositException, NegativeAmountException {
    _currentAccount.deposit(amount);
  }

  /**
   * Performs a withdrawal.
   * 
   * @param amount
   *          the amount to be withdrawn.
   * @throws InvalidWithdrawalException
   *           if it is not possible to withdraw the intended amount.
   * @throws NegativeAmountException
   *           if the amount is not positive.
   * @throws BalanceTooLowException
   */
  public void withdraw(double amount)
      throws InvalidWithdrawalException, NegativeAmountException, BalanceTooLowException {
    _currentAccount.withdraw(amount);
  }

  /**
   * @return the amount in the current account.
   */
  public double getCurrentAccountBalance() {
    return _currentAccount.getBalance();
  }

  /**
   * Returns the total balance for the account. This balance includes the
   * current account balance and the balances of all the savings accounts. The
   * following implementation uses an implicit iterator, but the same actions
   * could be performed using an explicit one.
   * 
   * @return the account balance.
   */
  public double getBalance() {
    double total = getCurrentAccountBalance();
    for (SavingsAccount sa : _savingsAccounts)
      total += sa.getBalance();
    return total;
  }

  /**
   * Returns the holders of the account.
   * 
   * @return a list with the holders of the account.
   */
  public List<Holder> getHolders() {
    return Collections.unmodifiableList(_holders);
  }

  /**
   * Create a savings account.
   * 
   * @param amount
   * @param rate
   * @param time
   * @throws BalanceTooLowException
   * @throws NegativeAmountException
   * @throws InvalidWithdrawalException
   */
  public final void addSavingsAccount(float amount, float rate, int time)
      throws InvalidWithdrawalException, NegativeAmountException, BalanceTooLowException {
    withdraw(amount);
    _savingsAccounts.add(new SavingsAccount(amount, rate, time, this));
  }

  /**
   * Returns the savings accounts associated with the bank account.
   * 
   * @param id
   * @return the savings account
   */
  public SavingsAccount getSavingsAccount(int id) {
    for (SavingsAccount account : _savingsAccounts)
      if (account.getId() == id)
        return account;
    return null;
  }

  /**
   * Returns the savings accounts associated with the bank account.
   * 
   * @return a list of savings deposits or null (if there are no savings
   *         deposits.
   */
  public List<SavingsAccount> getSavingsAccounts() {
    return Collections.unmodifiableList(_savingsAccounts);
  }

  /**
   * Remove a given savings account.
   * 
   * @param account
   *          the savings account to be removed.
   * @return true if the account owns this savings account.
   */
  public final boolean removeSavingsAccount(SavingsAccount account) {
    return _savingsAccounts.remove(account);
  }

  /**
   * Add a given savings account to the holdings.
   * 
   * @param account
   *          the new savings account.
   */
  public final void addSavingsAccount(SavingsAccount account) {
    _savingsAccounts.add(account);
  }

  /**
   * Add an account holder.
   * 
   * @param holder
   *          the holder to be added.
   */
  public void addHolder(Holder holder) {
    _holders.add(holder);
  }

  /**
   * Verifies whether an account can be removed. An account can be removed only
   * when its total balance is 0 (zero).
   * 
   * @return true, if the account can be removed; false, otherwise.
   */
  public final boolean canBeRemoved() {
    return getBalance() == 0;
  }

  /**
   * String representation of the account: presents the account number and the
   * total balance.
   * 
   * @return a string representation of the account.
   */
  @SuppressWarnings("nls")
  @Override
  public String toString() {
    return "<account number='" + _id + "' balance='" + getBalance() + "'/>";
  }

}
