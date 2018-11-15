package sth.exceptions;

public class NonSupportedIdException extends Exception{
    private static final long serialVersionUID = 201811151639L;
    private int _id;

  //id is either more than 6 digits long or its value is lower than 100000
  public NonSupportedIdException(int id) {
    _id = id;
  }

  public int getId() {
    return _id;
  }
    
}