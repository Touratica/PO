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

/**
 * The façade class.
 */
public class SchoolManager {

	/** The university to be managed. */
	private School _school = new School();

	/** The default file to save the school's state. */
	private String _filename;

	/** The logged in person */
	private Person _loggedPerson;
	
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
		if (newSchool.getStudents().containsKey(_loggedPerson.getId()) || newSchool.getProfessors().containsKey(_loggedPerson.getId()) || newSchool.getAdministratives().containsKey(_loggedPerson.getId())) {
			_school = newSchool;
		}
		else {
			throw new NoSuchPersonIdException(_loggedPerson.getId());
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

	public ArrayList<String> searchPerson(String name) {
		return _school.searchPerson(name);
	}

	public String showPerson() {
		PersonVisitor visitor = new PersonVisitor();
		return _loggedPerson.accept(visitor);
	}

	public ArrayList<String> showAllPeople() { 
		return _school.showAllPeople();
	}

	public void closeProject(String discipline, String project) throws NoSuchProjectNameException, ProjectAlreadyClosedException, NoSuchDisciplineNameException {
		try {
			_loggedPerson.closeProject(discipline, project);
		} catch (UnsupportedOperationException e) {
			e.printStackTrace();
		}
	}

	public void createProject(String discipline, String project) throws DuplicateProjectNameException, NoSuchDisciplineNameException {
		try {
			_loggedPerson.createProject(discipline, project);
		} catch (UnsupportedOperationException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<String> showDisciplineStudents (String disciplineName) throws NoSuchDisciplineNameException {
		try {
			return _loggedPerson.showDisciplineStudents(discipline);
		} catch (UnsupportedOperationException e) {
			e.printStackTrace();
		}
	}

	public void deliverProject(String discipline, String project, String submission) throws NoSuchDisciplineNameException, NoSuchProjectNameException, ProjectAlreadyClosedException {
		try {
			_loggedPerson.deliverProject(discipline, project, submission);
		} catch (UnsupportedOperationException e) {
			e.printStackTrace();
		}
	}

	public String showNotifications() {
		return _loggedPerson.showNotifications();
	}
	public String showProjectSubmissions(String discipline, String project){
		if (hasProfessor())
			return _loggedPerson.showSubmissions(discipline , project);
		return null; // FIXME manda excecao
	}

	public String showSurveyResults(String discipline, String project){
		if (hasStudent() || hasProfessor())
			return _loggedPerson.showSurveyResults(discipline,project);
		return null; // FIXME manda excecao
	}

	public void answerSurvey(String discipline, String project , int hours, String comment)throws UnsupportedOperationException{
		_loggedPerson.answerSurvey(discipline,project,hours,comment);

	}

	public void createSurvey(String discipline, String project) {
		_loggedPerson.createSurvey(discipline, project);
	}
}
