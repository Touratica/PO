package sth.exceptions;

public class DisciplineLimitExceeded extends Exception{
      /** Serial number for serialization. */
  private static final long serialVersionUID = 201811151858L;

  private Discipline _discipline;
  private RegularStudent _student;

  public DisciplineLimitExceeded(Discipline d, RegularStudent s){
      _discipline = d;
      student = s;
  }

    
}