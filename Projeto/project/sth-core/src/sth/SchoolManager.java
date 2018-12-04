package sth;

import java.io.BufferedOutputStream;
import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

import javax.sound.midi.SysexMessage;

import sth.exceptions.BadEntryException;
import sth.exceptions.DuplicateProjectNameException;
import sth.exceptions.ImportFileException;
import sth.exceptions.NoSuchPersonIdException;
import sth.exceptions.NoSuchProjectNameException;


import sth.exceptions.AlreadyRepresentativeException;
import sth.exceptions.DisciplineLimitExceededException;
import sth.exceptions.DuplicateCourseException;
import sth.exceptions.DuplicateDisciplineException;
import sth.exceptions.DuplicateIdException;
import sth.exceptions.DuplicateProjectException;
import sth.exceptions.InexistentCourseException;
import sth.exceptions.NoSuchDisciplineNameException;
import sth.exceptions.NotMatchingCourseException;
import sth.exceptions.OutOfRangeIdException;
import sth.exceptions.ProjectAlreadyClosedException;
import sth.exceptions.RepresentativeNumberExceeded;

//FIXME import other classes if needed

/**
 * FIXME The façade class. 300
 */
public class SchoolManager {

	//FIXME add object attributes if needed
	/** The university to be managed. */
	private School _school = new School();

	/** The default file to save the school's state. */
	private String _filename;

	/** The logged in person */
	private Person _loggedPerson;

	//FIXME implement constructors if needed
	
	/**
	 * @param datafile
	 * @throws ImportFileException
	 * @throws InvalidCourseSelectionException
	 */
	public void importFile(String datafile) throws ImportFileException {
		try {
			_school.importFile(datafile);
		} catch (IOException | BadEntryException  e) {
			throw new ImportFileException(e);
		} catch (Exception e) {

		}
	}

	/**
	 * @param id
	 * @throws NoSuchPersonIdException
	 */
	public void login(int id) throws NoSuchPersonIdException {
		if ((_loggedPerson = _school.getStudents().get(id)) != null) {
			return;
		}
		else if ((_loggedPerson = _school.getProfessors().get(id)) != null) {
			return;
		}
		else if ((_loggedPerson = _school.getAdministratives().get(id)) != null) {
			return;
		}
		else {
			throw new NoSuchPersonIdException(id);
		}
	}

	
	/**
	 * @return true when the currently logged in person is an administrative
	 */
	public boolean hasAdministrative() {
		return _loggedPerson.isAdministrative();
	}

	/**
	 * @return true when the currently logged in person is a professor
	 */
	public boolean hasProfessor() {
		return _loggedPerson.isProfessor();
	}

	/**
	 * @return true when the currently logged in person is a student
	 */
	public boolean hasStudent() {
		return _loggedPerson.isStudent();
	}

	/**
	 * @return true when the currently logged in person is a representative
	 */
	public boolean hasRepresentative() {
		return _loggedPerson.isRepresentative();
	}

	/**
	 * 
	 * @return true if the save file has been set already. 
	 */
	public boolean isFileSet() {
		return _filename != null;
	}

	/**
	 * @param _filename the _filename to set
	 */
	public void setFilename(String filename) {
		_filename = filename;
	}

	public void open(String filename) throws ClassNotFoundException, FileNotFoundException, IOException, NoSuchPersonIdException {
		ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(filename)));
		setFilename(filename);
		School newSchool = (School) in.readObject();
		in.close();
		if (newSchool.getStudents().containsKey(_loggedId) || newSchool.getProfessors().containsKey(_loggedId) || newSchool.getAdministratives().containsKey(_loggedId)) {
			_school = newSchool;
		}
		else {
			throw new NoSuchPersonIdException(_loggedId);
		}
		
	}
	
	public void save() throws IOException{
		ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(_filename)));
		out.writeObject(_school);
		out.close();
	}

	public void changePhoneNumber(int phoneNumber) {
		_loggedPerson.setPhoneNumber(phoneNumber);
	}

	public ArrayList<Person> searchPerson(String name) {
		ArrayList<Person> filteredPeople = new ArrayList<Person>();
		for (Map.Entry<Integer, Administrative> entry: _school.getAdministratives().entrySet()) {
			if (entry.getValue().getName().toLowerCase().contains(name.toLowerCase())) {
				filteredPeople.add(entry.getValue());
			}
		}
		for (Map.Entry<Integer, Professor> entry : _school.getProfessors().entrySet()) {
			if (entry.getValue().getName().toLowerCase().contains(name.toLowerCase())) {
				filteredPeople.add(entry.getValue());
			}
		}
		for (Map.Entry<Integer, Student> entry : _school.getStudents().entrySet()) {
			if (entry.getValue().getName().toLowerCase().contains(name.toLowerCase())) {
				filteredPeople.add(entry.getValue());
			}
		}
		Collections.sort(filteredPeople, Person.NAME_COMPARATOR);

		return filteredPeople;
	}

	public String showPerson() {
		return _loggedPerson.toString();
	}

	public ArrayList<Person> showAllPeople() {
		ArrayList<Person> peopleList = new ArrayList<Person>();
		for (Map.Entry<Integer, Administrative> entry: _school.getAdministratives().entrySet()) {
			peopleList.add(entry.getValue());
		}
		for (Map.Entry<Integer, Professor> entry : _school.getProfessors().entrySet()) {
			peopleList.add(entry.getValue());
		}
		for (Map.Entry<Integer, Student> entry : _school.getStudents().entrySet()) {
			peopleList.add(entry.getValue());
		}
		Collections.sort(peopleList, Person.ID_COMPARATOR);	
		return peopleList;
	}

	public void closeProject(String discipline, String project) throws NoSuchProjectNameException, ProjectAlreadyClosedException, NoSuchDisciplineNameException {
		_loggedPerson.closeProject(discipline, project);
	}

	public void createProject(String discipline, String project) throws DuplicateProjectNameException, NoSuchDisciplineNameException {

		Professor prof = (Professor) _school.getProfessors().get(_loggedId);
		for (Map.Entry<String, ArrayList<Discipline>> entry : prof.getDisciplines().entrySet()) {
			for (Discipline d : entry.getValue()) {
				if (d.getDisciplineName().equals(discipline)) {
					if (d.getProjects().containsKey(project)) {
						throw new DuplicateProjectNameException();
					} else {
						d.addProject(new Project(project, d.getDisciplineName() + " - " + project));;
						return;
					}
				}
			}
		}
		throw new NoSuchDisciplineNameException();
	}

	public ArrayList<Student> showDisciplineStudents (String disciplineName){
		ArrayList<Student> disciplineStudents = new ArrayList<Student>();
		
		for (Map.Entry<String, ArrayList<Discipline>> entry: _school.getProfessors().get(_loggedId).getDisciplines().entrySet()) {
			for (Discipline discipline: entry.getValue()) {
				if (discipline.getDisciplineName().equals(disciplineName)){ 
					for (Map.Entry<Integer,Student> s : discipline.getStudents().entrySet()){
						disciplineStudents.add(s.getValue());
					}
					Collections.sort(disciplineStudents, Person.ID_COMPARATOR);
					return disciplineStudents;	
				}
			}
		} return null;
		
	}

	public void deliverProject(String discipline, String project, String submission) throws NoSuchDisciplineNameException, NoSuchProjectNameException, ProjectAlreadyClosedException {
		if (_school.getStudents().get(_loggedId).getCourse().getDisciplines().containsKey(discipline)) {
			if (_school.getStudents().get(_loggedId).getCourse().getDisciplines().get(discipline).getStudents().containsKey(_loggedId)) {
				if (_school.getStudents().get(_loggedId).getCourse().getDisciplines().get(discipline).getProjects().containsKey(project)) {
					_school.getStudents().get(_loggedId).getCourse().getDisciplines().get(discipline).getProjects().get(project).submitProject(_loggedId, submission);
				}
				else {
					throw new NoSuchProjectNameException();
				}
			}
			else {
				throw new NoSuchDisciplineNameException();
			}
		}
		else {
			throw new NoSuchDisciplineNameException();
		}
	}

	public String showNotifications() {
		return _loggedPerson.showNotifications();
	}
}
