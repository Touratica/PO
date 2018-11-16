package sth;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

import sth.exceptions.BadEntryException;
import sth.exceptions.ImportFileException;
import sth.exceptions.NoSuchPersonIdException;

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
		} catch (IOException | BadEntryException e) {
			throw new ImportFileException(e);
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
		//FIXME implement predicate
	}

	/**
	 * @return true when the currently logged in person is a professor
	 */
	public boolean hasProfessor() {
		//FIXME implement predicate
	}

	/**
	 * @return true when the currently logged in person is a student
	 */
	public boolean hasStudent() {
		//FIXME implement predicate
	}

	/**
	 * @return true when the currently logged in person is a representative
	 */
	public boolean hasRepresentative() {
		//FIXME implement predicate
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

	public void open(String _filename) {
		try {
			ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(_filename)));
			_school = (School) in.readObject();

		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void save() {
		ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(_filename)));
		out.writeObject(_school);
		out.close();
	}

	public void changePhoneNumber(int phoneNumber) {
		_school.getPeople().get(_loggedId).setPhoneNumber(phoneNumber);
	}

	public ArrayList<Person> searchPerson(String name) {
		ArrayList<Person> filteredPeople = new ArrayList<Person>();
		for (Map.Entry<Integer, Person> entry: _school.getPeople().entrySet()) {
			if (entry.getValue().getName().toLowerCase().contains(name.toLowerCase())) {
				filteredPeople.add(entry.getValue());
			}
		}
		Collections.sort(filteredPeople, Person.NAME_COMPARATOR);

		return filteredPeople;
	}
}
