package sth;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import sth.exceptions.DuplicateDisciplineException;
import sth.exceptions.DuplicateIdException;
import sth.exceptions.DuplicateProjectException;
import sth.exceptions.NoSuchDisciplineNameException;
import sth.exceptions.NoSuchProjectNameException;
import sth.exceptions.NoSuchSurveyException;
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

	/**
	 * @return the courses lectured by the professor.
	 */
	public Map<String, ArrayList<Discipline>> getDisciplines() {
		return _disciplines;
	}
	
	public ArrayList<Discipline> getDiscipline(String disciplineName){
		ArrayList<Discipline> disciplines = new ArrayList<Discipline>();
		for (Map.Entry<String, ArrayList<Discipline>> entry: _disciplines.entrySet()) {
			for (Discipline discipline: entry.getValue()) {
				if (discipline.getDisciplineName().equals(disciplineName)) {
					disciplines.add(discipline);
				}
			}
		}
		return disciplines;
	}

	@Override
	public void createProject(String disciplineName, String projectName) throws NoSuchDisciplineNameException, DuplicateProjectException {
		// this method is optimized to when multiple courses have the same discipline and some of those have the project, it searches for one that doesnt have that project and puts it there 
		ArrayList<Discipline> _disciplines = getDiscipline(disciplineName);
		if (_disciplines.size() == 0) {
			throw new NoSuchDisciplineNameException();
		}
		for (Discipline d : _disciplines) {
			if (!d.hasProject(projectName)) {
				d.createProject(projectName);
				return;
			} 
		}
		throw new DuplicateProjectException();
	}
	
	@Override
	public void closeProject(String discipline, String project) throws NoSuchProjectNameException, NoSuchDisciplineNameException, ProjectAlreadyClosedException {
		int i = 0;
		ArrayList<Discipline> disciplines = getDiscipline(discipline);
		if (disciplines.size() != 0) {
			for (Discipline dis: disciplines) {
				i++;
				try {
					dis.closeProject(project);
					return;
				} catch (NoSuchProjectNameException e) {
					if (i != disciplines.size()) {
						continue;
					}
					throw e;
				}
			}
		}
		else {
			throw new NoSuchDisciplineNameException();
		}
	}
	
	@Override
	public String showProjectSubmissions(String discipline, String project)
			throws NoSuchDisciplineNameException, NoSuchProjectNameException {
		ArrayList<Discipline> _disciplines = getDiscipline(discipline);
		if (_disciplines.size() == 0){
			throw new NoSuchDisciplineNameException();
		}
		for (Discipline d : _disciplines){
			if (d.hasProject(project)){
				return d.showProject(project);
			}
		}
		throw new NoSuchProjectNameException();
	}

	@Override
	public ArrayList<String> showDisciplineStudents(String discipline) throws NoSuchDisciplineNameException {
		ArrayList<String> studentsVisited = new ArrayList<String>();
		PersonVisitor visitor = new PersonVisitor();
		for (Map.Entry<String, ArrayList<Discipline>> entry: _disciplines.entrySet()) {
			for (Discipline dis: entry.getValue()) {
				if (dis.getDisciplineName().equals(discipline)) {
					for (Student s: dis.showStudents()){
						studentsVisited.add(s.accept(visitor));
					}
					return studentsVisited;
				}
			}
		}
		throw new NoSuchDisciplineNameException();
	}

	public String showSurveyResults(String discipline, String project)throws NoSuchDisciplineNameException, NoSuchProjectNameException, NoSuchSurveyException {
		List<Discipline> _disciplines = getDiscipline(discipline);
		if (_disciplines.size() == 0) {
			throw new NoSuchDisciplineNameException();
		}
		for (Discipline d : _disciplines) {
			if (d.hasProject(project)) {
				Project p = d.getProject(project);
				return p.showSurveyResults(this);
			}
		}
		throw new NoSuchProjectNameException();
	}
	
	@Override
	public boolean isProfessor() {
		return true;
	}

	@Override
	public String accept(PersonVisitor visitor){
		return visitor.showProfessor(this);
	}

	@Override
	public void addToNotificationList(String discipline, String project)
			throws UnsupportedOperationException, NoSuchDisciplineNameException, NoSuchProjectNameException, NoSuchSurveyException {
		int i = 0;
		ArrayList<Discipline> disciplines = getDiscipline(discipline);
		for (Discipline dis : disciplines) {
			try {
				i++;
				dis.addToNotificationList(this, project);
				return;
			} catch (NoSuchProjectNameException | NoSuchSurveyException e) {
				if (i != disciplines.size()) {
					continue;
				}
				throw e;
			}
		}
	}

	@Override
	public void removeFromNotificationList(String discipline, String project)
			throws NoSuchDisciplineNameException, NoSuchProjectNameException, NoSuchSurveyException {
		int i = 0;
		ArrayList<Discipline> disciplines = getDiscipline(discipline);
		for (Discipline dis: disciplines) {
			try {
				i++;
				dis.removeFromNotificationList(this, project);
				return;
			} catch (NoSuchProjectNameException | NoSuchSurveyException e) {
				if (i != disciplines.size()) {
					continue;
				}
				throw e;
			}
		}
	}
	/*
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
	*/
}