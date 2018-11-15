package sth;

//FIXME import other classes if needed

import java.io.BufferedReader;
import java.io.FileReader;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.TreeMap;

import java.io.Serializable;

import sth.exceptions.BadEntryException;
import sth.exceptions.DuplicateCourseException;
import sth.exceptions.InvalidCourseSelectionException;
import sth.exceptions.NoSuchPersonIdException;

/**
 * School implementation.
 */
public class School implements Serializable {

	/** Serial number for serialization. */
	private static final long serialVersionUID = 201811151733L;

	//FIXME define object fields (attributes and, possibly, associations)
	
	/** School's people. */
	private Map<Integer, Person> _people = new HashMap<Integer, Person>();

	/** School's disciplines */
	private Map <Course, Disciplines> _courses = new TreeMap<Course, Disciplines>();

	/** People counter. */
	private int _nextId = 100000;

	//FIXME implement constructors if needed
	
	/**
	 * Imports School information from text file.
	 * 
	 * @param filename
	 * @throws BadEntryException
	 * @throws IOException
	 */
	void importFile(String filename) throws IOException, BadEntryException {
		//FIXME implement text file reader
		int lineno = 0;
		try {
			BufferedReader in = new BufferedReader(new FileReader(filename));
			String s;

			while ((s = in.readLine()) != null) {
				String line  = new String(s.getBytes(), "UTF-8");
				lineno++;
				if (line.charAt(0) == '#') {
					// FIXME: implement get disciplines agarrar o ponteiro para a ultima pessoa lida??
				}
				String[] split = line.split("\\|");
				switch (split[0]) {
					case "ALUNO":
						addStudent(split);
						break;
				
					case "DELEGADO":
						addRepresentative(split);
						break;
				
					case "FUNCIONÁRIO":
						addAdministrative(split);
						break;
				
					case "PROFESSOR":
						addProfessor(split);
						break;
				
					default:
						throw new BadEntryException("Não exite pessoa do tipo" + split[0]);
						break;
				}
			}
			in.close();
		} catch (FileNotFoundExeception e) {

		} catch (IOException e){

		}


	}
	

	/** idExists : checks if  the given id already exists in school */
	public bollean idExists(int id){
		if (_people.get(id) == null)
			return False;
		return True;
	}
	/** courseExists : checks if  the given course already exists in school */
	public bollean courseExists(Course course){
		if (_courses.get(couse) == null)
			return False;
		return True;
	}

	/** addCourse: adds a course that doesn't exist in school */
	public void addCourse(Course course, Disciplines disciplines)
	throws DuplicateCourseException, NullPointerException{
		if (!courseExists(course))
			if(course != null)
				_courses.put(course,disciplines);
			else throw NullPointerException();
		else throw new DuplicateCourseException(course);

	}

	public void addStudent(String[] s) throws NotMatchingCourseException, DuplicateIdException, OutOfRangeIdException {
		Student new_student = new RegularStudent(s[1],s[2],s[3]);
		_people.put(new_student.getId(), new_student);

	}

	public void addRepresentative(String[] s){
		
	}

	public void addAdministrative(String[] s){
		
	}
	public void addProfessor(String[] s){
		
	}



}
