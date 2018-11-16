package sth;

import java.io.Serializable;
import java.util.ArrayList;

import sth.exceptions.DuplicateDisciplineException;

/**
 * The Disciplines class.
 */
public class Disciplines implements Serializable{

    private static final long serialVersionUID = 201811151740L;

    private ArrayList<Discipline> _disciplines;

    public Disciplines(){
        _disciplines = new ArrayList<Discipline>();

    }
    
    public void addDiscipline(Discipline discipline) throws DuplicateDisciplineException{
        for(Discipline d: _disciplines)
            if (d.equals(discipline))
                throw new DuplicateDisciplineException(discipline);
        _disciplines.add(d);
    }

	/**
	 * @return disciplines - this function is used 
     * only within a corresponding course.
 	 */
	public ArrayList<Discipline> getDisciplines() {
		return _disciplines;
    }
    
   
    
}