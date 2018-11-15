package sth.exceptions;

public class DuplicateCourseException extends Exception{

    private static final long serialVersionUID = 201811151638L;

    private Course _course;

    public DuplicateCourseException(Course course) {
        _course = course;
    }

    public Course getCourse() {
        return _course;
    }
}