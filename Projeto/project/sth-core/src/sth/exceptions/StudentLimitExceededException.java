package sth.exceptions;

public class StudentLimitExceededException extends Exception{
    private static final long serialVersionUID = 201811151652L;
    private Student _student;

    public StudentLimitExceededException(Student student){
        _student = student;
    }

}