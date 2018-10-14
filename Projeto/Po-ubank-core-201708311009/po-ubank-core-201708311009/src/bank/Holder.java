package bank;

import java.io.Serializable;
import java.util.Comparator;

import bank.exceptions.DuplicateHolderException;

/**
 * Class Holder contains information about holders of bank accounts. Each holder
 * is identified by a number. Note that this class is not comparable.
 */
public class Holder implements Serializable {

  /** Serial number for serialization. */
  private static final long serialVersionUID = 201608231530L;

  /**
   * NUMBER_COMPARATOR is an instance of an inner class that implements a holder
   * comparator defining a comparison method for holders based on their numbers.
   * 
   * It would probably be better to have a static method for accessing this
   * object.
   * 
   * @see java.util.Comparator
   */
  public static final Comparator<Holder> NUMBER_COMPARATOR = new Comparator<Holder>() {

    /*
     * Compare two holders by number.
     * 
     * @param h1 holder 1
     * 
     * @param h2 holder 2
     * 
     * @return -1 if h1.id < h2.id; 0 if h1.id = h2.id; 1 if h1.id > h2.id.
     */
    @Override
    public int compare(Holder h1, Holder h2) {
      int i1 = h1.getId();
      int i2 = h2.getId();
      return (i1 < i2 ? -1 : (i1 == i2 ? 0 : 1));
    }

  }; // NUMBER_COMPARATOR

  /**
   * NAME_COMPARATOR is an instance of an inner class that implements a holder
   * comparator defining a comparison method for holders based on their names.
   * 
   * It would probably be better to have a static method for accessing this
   * object.
   * 
   * @see java.util.Comparator
   */
  public static final Comparator<Holder> NAME_COMPARATOR = new Comparator<Holder>() {

    /**
     * Compare two holders by name (lexicographically and ignoring case).
     * 
     * @param h1
     *          holder 1
     * @param h2
     *          holder 2
     * @return -1 if h1.name comes before h2.name; 0 if h1.name is the same as
     *         h2.name; 1 if h1.name comes after h2.name.
     */
    @Override
    public int compare(Holder h1, Holder h2) {
      String n1 = h1.getName();
      String n2 = h2.getName();
      return n1.compareToIgnoreCase(n2);
    }

  }; // NAME_COMPARATOR

  /** The holder's id. */
  private int _id;

  /** The holder's name. */
  private String _name;

  /**
   * Constructor (initializes id and name).
   * 
   * @param id
   *          the holder's id.
   * @param name
   *          the holder's name.
   * @throws DuplicateHolderException
   */
  public Holder(int id, String name) throws DuplicateHolderException {
    _id = id;
    _name = name;
  }

  /**
   * Constructor (initializes from array of String).
   * 
   * @param init
   *          the holder's parameters.
   * @throws DuplicateHolderException 
   */
  public Holder(String[] init) throws DuplicateHolderException {
    this(Integer.parseInt(init[1]), init[2]);
  }

  /**
   * @return the holder's id.
   */
  public final int getId() {
    return _id;
  }

  /**
   * @return the holder's name.
   */
  public String getName() {
    return _name;
  }

  /**
   * Set the holder's name.
   * 
   * @param name
   *          the holder's new name.
   */
  public final void setName(String name) {
    _name = name;
  }

  /** @see java.lang.Object#equals(java.lang.Object) */
  @Override
  public boolean equals(Object holder) {
    return holder instanceof Holder && ((Holder) holder).getId() == _id;
  }

  /** @see java.lang.Object#toString() */
  @SuppressWarnings("nls")
  @Override
  public String toString() {
    return "<holder name='" + getName() + "' id='" + getId() + "'/>";
  }

}
