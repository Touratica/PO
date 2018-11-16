package sth.exceptions;

import sth.Discipline;
import sth.Student;

public class DisciplineLimitExceededException extends Exception{
      /** Serial number for serialization. */
  private static final long serialVersionUID = 201811151858L;

  private Discipline _discipline;
  private Student _student;

  public DisciplineLimitExceededException(Discipline d, Student s){
      _discipline = d;
      _student = s;
  }

    
}