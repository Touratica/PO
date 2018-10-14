package bank;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import bank.exceptions.DuplicateHolderException;
import bank.exceptions.InvalidRemovalException;

/**
 * Banks have clients and accounts. New clients can open new accounts or join
 * existing accounts.
 */

public class Bank implements Serializable {
  /** Serial number for serialization. */
  private static final long serialVersionUID = 201608231523L;

  /** Bank name. */
  private String _name;

  /** Account holders. */
  private Map<Integer, Holder> _holders = new HashMap<Integer, Holder>();

  /** Bank accounts. */
  private Map<Integer, BankAccount> _accounts = new HashMap<Integer, BankAccount>();

  /** Account counter. */
  private int _accountNumber = 0;

  /**
   * Constructor.
   * 
   * @param name
   *          bank name.
   */
  public Bank(String name) {
    _name = name;
  }

  /**
   * @return the name of the bank.
   */
  public final String getName() {
    return _name;
  }

  /** @see java.lang.Object#equals(java.lang.Object) */
  @Override
  public final boolean equals(Object o) {
    return o instanceof Bank && _name.equals(((Bank) o).getName());
  }

  /**
   * Create and register account.
   * 
   * @param amount
   *          initial balance for the account.
   * 
   * @return the new account.
   */
  public BankAccount createAccount(double amount) {
    BankAccount account = new BankAccount(_accountNumber++, amount);
    _accounts.put(account.getId(), account);
    return account;
  }

  /**
   * Remove an account. An account can only be removed if its total balance is
   * zero. If it is not possible to remove an account, an exception is thrown.
   * 
   * @param account
   *          the account to be removed.
   * @throws InvalidRemovalException
   */
  public final void removeAccount(BankAccount account) throws InvalidRemovalException {
    if (account.canBeRemoved())
      _accounts.remove(account.getId());
    else
      throw new InvalidRemovalException(account.getId(), account.getBalance());
  }

  /**
   * Get the account with the given number.
   * 
   * @param id
   *          the account number.
   * 
   * @return the account or null if the number does not correspond to a valid
   *         account.
   */
  public final BankAccount getAccount(int id) {
    return _accounts.get(id);
  }

  /**
   * Return all the accounts as an unmodifiable collection.
   * 
   * @return a collection with all the accounts.
   */
  public Collection<BankAccount> getAccounts() {
    return Collections.unmodifiableCollection(_accounts.values());
  }

  /**
   * Register an account holder.
   * @param name 
   * @param id 
   * @throws DuplicateHolderException
   */
  public void addHolder(String name, int id) throws DuplicateHolderException {
    if (_holders.containsKey(id))
      throw new DuplicateHolderException(id, name);
    Holder holder = new Holder(id, name);
    _holders.put(id, holder);
  }

  /**
   * Remove an account holder.
   * 
   * @param id
   *          the account holder's id.
   * 
   * @return true, if the holder was removed; false, otherwise.
   */
  public final boolean removeHolder(int id) {
    Holder holder = getHolder(id);
    if (holder != null) {
      _holders.remove(id);
      return true;
    }
    return false;
  }

  /**
   * Return all the account holders as an unmodifiable collection.
   * 
   * @return a collection with all the account holders.
   */
  public Collection<Holder> getHolders() {
    return Collections.unmodifiableCollection(_holders.values());
  }

  /**
   * Get the account holder with the given number.
   * 
   * @param id
   *          the account holder's number.
   * 
   * @return the account holder or null if the number does not correspond to a
   *         valid account holder.
   */
  public Holder getHolder(int id) {
    return _holders.get(id);
  }

}
