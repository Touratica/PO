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
		Person _lastImported;
		try {
			BufferedReader in = new BufferedReader(new FileReader(filename));
			String s;
			String[] split; 

			while ((s = in.readLine()) != null) {
				String line  = new String(s.getBytes(), "UTF-8");
				lineno++;
				if (line.charAt(0) == '#') {
					// FIXME: implement get disciplines agarrar o ponteiro para a ultima pessoa lida??
					split = line.split("\\|");
					split[0] = split[0].replaceAll("#\\ ", "");
					try{
					  addCourse(new Course(split[0]),new Discipline(split[1]));
					} catch(DuplicateCourseException e){} //do nothing- its fine to import courses with the same name 

					_lastImported.

				}
				split = line.split("\\|");
				switch (split[0]) {
					case "ALUNO":
						_lastImported = addStudent(split);
						break;
				
					case "DELEGADO":
						_lastImported = addRepresentative(split);
						break;
				
					case "FUNCIONÁRIO":
						_lastImported = addAdministrative(split);
						break;
				
					case "PROFESSOR":
						_lastImported = addProfessor(split);
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
	throws NullPointerException{
		if (!courseExists(course))
			if(course != null)
				_courses.put(course,disciplines);
			else throw new NullPointerException();
		else throw new DuplicateCourseException(course);
	}

	public RegularStudent addStudent(String[] s) throws NotMatchingCourseException, DuplicateIdException, OutOfRangeIdException {
		Student new_student = new RegularStudent(s[1],s[2],s[3]);
		_people.put(new_student.getId(), new_student);

	}

	public Student addRepresentative(String[] s){
		
	}

	public void addAdministrative(String[] s){
		
	}
	public Professor addProfessor(String[] s){
		
	}



}
