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
import sth.exceptions.NotMatchingCourseException;
import sth.exceptions.OutOfRangeIdException;
import sth.exceptions.ProjectAlreadyClosedException;
import sth.exceptions.RepresentativeNumberExceeded;

//FIXME import other classes if needed

/**
 * The façade class.
 */
public class SchoolManager {

	//FIXME add object attributes if needed
	/** The university to be managed. */
	private School _school = new School();

	/** The default file to save the school's state. */
	private String _filename;

	/** */
	private int _loggedId;

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
		_loggedId = id;
	}

	
	/**
	 * @return true when the currently logged in person is an administrative
	 */
	public boolean hasAdministrative() {
		return _school.getAdministratives().containsKey(_loggedId);

	}

	/**
	 * @return true when the currently logged in person is a professor
	 */
	public boolean hasProfessor() {
		return _school.getProfessors().containsKey(_loggedId);

	}

	/**
	 * @return true when the currently logged in person is a student
	 */
	public boolean hasStudent() {
		return _school.getStudents().containsKey(_loggedId);

	}

	/**
	 * @return true when the currently logged in person is a representative
	 */
	public boolean hasRepresentative() {
		return _school.getStudents().get(_loggedId).isRepresentative();
	}

	//FIXME implement other methods (in general, one for each command in sth-app)

	/**
	 * 
	 * @return true if the save file has been set already. 
	 */
	public Boolean isFileSet() {
		return _filename != null;
	}

	/**
	 * @param _filename the _filename to set
	 */
	public void setFilename(String filename) {
		_filename = filename;
	}

	public void open(String _filename) throws ClassNotFoundException, FileNotFoundException, IOException {
		ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(_filename)));
		_school = (School) in.readObject();
		in.close();
		
	}
	
	public void save() throws IOException{
		ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(_filename)));
		out.writeObject(_school);
		out.close();
	}

	public void changePhoneNumber(int phoneNumber) {
		if (hasStudent()) {
			_school.getStudents().get(_loggedId).setPhoneNumber(phoneNumber);
		}
		else if (hasProfessor()) {
			_school.getProfessors().get(_loggedId).setPhoneNumber(phoneNumber);
		}
		else if (hasAdministrative()) {
			_school.getAdministratives().get(_loggedId).setPhoneNumber(phoneNumber);
		}
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
		if (_school.getStudents().containsKey(_loggedId)) {
			return _school.getStudents().get(_loggedId).toString();
		}
		else if (_school.getProfessors().containsKey(_loggedId)) {
			return _school.getProfessors().get(_loggedId).toString();
		}
		else if (_school.getAdministratives().containsKey(_loggedId)) {
			return _school.getAdministratives().get(_loggedId).toString();
		}
		else {
			return null;
		}
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

	public void closeProject(String discipline, String project) throws NoSuchProjectNameException, ProjectAlreadyClosedException {
		Professor prof = (Professor) _school.getProfessors().get(_loggedId);
		for (Map.Entry<String, ArrayList<Discipline>> entry: prof.getDisciplines().entrySet()) {
			for (Discipline d: entry.getValue()) {
				if (discipline.equals(d.getDisciplineName())) {
					for (Project p: d.getProjects()) {
						if (p.getName().equals(project)) {
							p.close();
							return;
						}
					}
				}
			}
		}
		throw new NoSuchProjectNameException();
	}

	public void createProject(String discipline, String project) throws DuplicateProjectNameException {

		Professor prof = (Professor) _school.getProfessors().get(_loggedId);
		for (Map.Entry<String, ArrayList<Discipline>> entry : prof.getDisciplines().entrySet()) {
			for (Discipline d : entry.getValue()) {
				if (discipline.equals(d.getDisciplineName())) {
					for (Project p : d.getProjects()) {
						if (p.getName().equals(project)) {
							throw new DuplicateProjectNameException();
							
						}
					}
					d.addProject(new Project(project, d.getDisciplineName() + " - " + project));
					return;
				}
			}
		}
	}
}
