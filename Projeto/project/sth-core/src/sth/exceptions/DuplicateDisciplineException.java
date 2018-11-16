package sth.exceptions;

import sth.Discipline;

public class DuplicateDisciplineException extends Exception{

    private static final long serialVersionUID = 201811151706L;

    private Discipline _discipline;

    public DuplicateDisciplineException(Discipline discipline) {
        _discipline = discipline;
    }

    public Discipline getDiscipline() {
        return _discipline;
    }
}