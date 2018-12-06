package sth;

import java.io.Serializable;

public class SurveyAnswer implements Serializable {

  /** Serial number for serialization. */
  private static final long serialVersionUID = 201812060159L;

  private String _comment;
  private int _totalWorkHours;
    
  public SurveyAnswer(int hours, String comment){
    _totalWorkHours = hours;
    _comment = comment;
  }
  
  public int getWorkHours(){
    return _totalWorkHours;
  }

  public String getComment(){
    return _comment;
  }

}