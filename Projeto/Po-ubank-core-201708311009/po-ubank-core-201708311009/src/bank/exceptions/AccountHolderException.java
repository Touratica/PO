package bank.exceptions;

/**
 * Basic exception class for holder-related problems. Should not be instantiated
 * directly (thus, the abstract qualifier).
 */
public abstract class AccountHolderException extends Exception {
  /** Serial number. */
  private static final long serialVersionUID = 201608231557L;

  /** The holder id */
  private final int _id;

  /** The holder name */
  private String _name;

  /**
   * @param id
   * @param name 
   */
  public AccountHolderException(int id, String name) {
    _id = id;
    _name = name;
  }

  /**
   * @return the holder id
   */
  public int getHolderId() {
    return _id;
  }

  /**
   * @return the holder name
   */
  public String getHolderName() {
    return _name;
  }

}
