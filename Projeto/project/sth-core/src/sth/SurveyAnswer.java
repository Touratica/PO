package sth;

public class SurveyAnswer{
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