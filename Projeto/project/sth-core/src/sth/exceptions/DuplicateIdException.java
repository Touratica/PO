package sth.exceptions;

public class DuplicateIdException extends Exception {

    /** Serial number for serialization. */
    private static final long serialVersionUID = 201811151647L;

    private int _id;
    
    public DuplicateIdException(int id) {
        _id = id;
      }
    
      public int getId() {
        return _id;
      }
}