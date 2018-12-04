package sth;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;
import sth.exceptions.DuplicateDisciplineException;
import sth.exceptions.DuplicateIdException;
import sth.exceptions.NoSuchDisciplineNameException;
import sth.exceptions.NoSuchProjectNameException;
import sth.exceptions.OutOfRangeIdException;
import sth.exceptions.ProjectAlreadyClosedException;

/**
 * Professor
 */
public class Professor extends Person implements Observer {

	private static final long serialVersionUID = 201811151743L;

	/** The disciplines lectured by the professor. */
	private Map <String, ArrayList<Discipline>> _disciplines = new TreeMap<String, ArrayList<Discipline>>();

	public Professor(int id, int phoneNumber, String name) throws DuplicateIdException, OutOfRangeIdException {
		super(id, phoneNumber, name);
	}

	//add Discipline may throw this exception from the addDiscipline
	public void addDiscipline(Course course, Discipline discipline) {
		if (_disciplines.containsKey(course.getName())) {
			if (!_disciplines.get(course.getName()).contains(discipline)) {
				_disciplines.get(course.getName()).add(discipline);
			}
		}
		else {
			ArrayList<Discipline> disciplines = new ArrayList<Discipline>();
			disciplines.add(discipline);
			_disciplines.put(course.getName(), disciplines);
		}
	}

	// FIXME create project ?

	/**
	 * @return the courses lectured by the professor.
	 */
	public Map<String, ArrayList<Discipline>> getDisciplines() {
		return _disciplines;
	}

	public void closeProject(String discipline, String project) throws NoSuchProjectNameException, NoSuchDisciplineNameException, ProjectAlreadyClosedException {
		int i = 0;
		for (Map.Entry<String, ArrayList<Discipline>> entry: _disciplines.entrySet()) {
			i++;
			try {
				for (Discipline dis: entry.getValue()) {
					if (dis.getDisciplineName().equals(discipline)) {
						dis.closeProject(project);
						return;
					}
				}
			} catch (NoSuchProjectNameException e) {
				if (i != _disciplines.size()) {
					continue;
				}
				throw e;
			}
		}
		throw new NoSuchDisciplineNameException();
	}

	@Override
	public boolean isProfessor() {
		return true;
	}

	@Override
	public String toString() {
		String s = "DOCENTE|" + super.toString();
		
		
		ArrayList<String> disciplines = new ArrayList<String>();
		for (Map.Entry<String, ArrayList<Discipline>> entry: _disciplines.entrySet()) {
			for (Discipline discipline: entry.getValue()) {
				disciplines.add("* " + entry.getKey() + " - " + discipline.getDisciplineName());
			}
		}
		Collections.sort(disciplines, Collator.getInstance());
		for (String str: disciplines) {
			s += "\n" + str;
		}

		return s;
	}
    

}