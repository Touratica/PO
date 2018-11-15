package sth;

import java.util.List;
import java.util.ArrayList;

/**
 * The Disciplines class.
 */
public class Disciplines {

    private List<Discipline> _disciplines = new ArrayList<Discipline>();
    
    //FIXME add discipline throws DuplicateDisciplineException
    public void addDiscipline(Discipline discipline){
        for(Discipline d: _disciplines)
            if  (d.equals(discipline))
                //FIXME throw exception 
        _disciplines.add(d);
    }

	/**
	 * @return disciplines - this function is used 
     * only within a corresponding course.
 	 */
	public Discipline getDisciplines() {
		return _disciplines;
	}
}