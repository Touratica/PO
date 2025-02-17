package sth;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import javax.naming.LimitExceededException;

import java.io.Serializable;
import sth.exceptions.AlreadyRepresentativeException;
import sth.exceptions.BadEntryException;
import sth.exceptions.DisciplineLimitExceededException;
import sth.exceptions.DuplicateCourseException;
import sth.exceptions.DuplicateDisciplineException;
import sth.exceptions.DuplicateIdException;
import sth.exceptions.InexistentCourseException;
import sth.exceptions.NoSuchPersonIdException;
import sth.exceptions.NotMatchingCourseException;
import sth.exceptions.OutOfRangeIdException;
import sth.exceptions.RepresentativeNumberExceeded;
import sth.exceptions.StudentLimitExceededException;

/**
 * School implementation.
 */
public class School implements Serializable {

	/** Serial number for serialization. */
	private static final long serialVersionUID = 201811151733L;
	
	/** School's people. */
	private Map<Integer, Student> _students = new HashMap<Integer, Student>();
	private Map<Integer, Professor> _professors = new HashMap<Integer, Professor>();
	private Map<Integer, Administrative> _administratives = new HashMap<Integer, Administrative>();

	/** School's disciplines */
	private Map <String, Course> _courses = new TreeMap<String, Course>();

	/** People counter. */
	private int _nextId = 100000;
	
	/**
	 * Imports School information from text file.
	 * 
	 * @param filename
	 * @throws BadEntryException
	 * @throws IOException
	 * @throws BadEntryException
	 * @throws AlreadyRepresentativeException
	 * @throws RepresentativeNumberExceeded
	 * @throws DuplicateDisciplineException
	 * @throws DuplicateIdException
	 * @throws OutOfRangeIdException
	 * @throws InexistentCourseException
	 * @throws NotMatchingCourseException
	 * @throws DisciplineLimitExceededException
	 */
	void importFile(String filename) throws IOException, BadEntryException, AlreadyRepresentativeException, RepresentativeNumberExceeded, DuplicateDisciplineException,DuplicateIdException, OutOfRangeIdException, InexistentCourseException, NotMatchingCourseException, DisciplineLimitExceededException {
		BufferedReader in = new BufferedReader(new FileReader(filename));
		String s;
		String[] split; 

		while ((s = in.readLine()) != null) {
			String line  = new String(s.getBytes(), "UTF-8");
			split = line.split("\\|");
			switch (split[0]) {
				
				case "ALUNO":
					readStudent(in, split);
					break;
				
				case "DELEGADO":
					Student student = readStudent(in, split);
					student.getCourse().setRepresentative(student);
					break;

				case "FUNCIONÁRIO":
					addAdministrative(split);

					break;
			
				case "DOCENTE":
					Professor professor = addProfessor(split);
					int c;
					while (true) {
						in.mark(1);
						c = in.read();
						if (c == '#') {
							in.reset();
							s = in.readLine();
							line = new String(s.getBytes(), "UTF-8");
							split = line.split("\\|");
							split[0] = split[0].replaceAll("#\\ ", "");
							Course course = addCourse(split[0]);
							Discipline discipline = new Discipline(split[1], 300);
							course.addDiscipline(discipline);
							// grabs the discipline from the course with this name
							discipline = course.getDisciplines().get(discipline.getDisciplineName());
							professor.addDiscipline(course, discipline);
						} else {
							break;
						}
					}
					in.reset();
					break;
			
				default:
					throw new BadEntryException("Não exite pessoa do tipo" + split[0]);
			}
		}
		in.close();
	}
	/** Check if the id exists.
	 * @param id the id to check.
	 * @return true if there's someone with that id already.
	 */
	public Boolean idExists(int id){
		return _students.containsKey(id) || _professors.containsKey(id) || _administratives.containsKey(id);
	}

	/** 
	 * Adds a course that doesn't exist in school.
	 * 
	 * @param courseName
	 * @return the course 
	 */
	public Course addCourse(String courseName) {
		for (Map.Entry<String, Course> entry: _courses.entrySet()) {
			if (entry.getKey().equals(courseName)) {
				return entry.getValue();
			}	
		}
		Course course = new Course(courseName);
		_courses.put(courseName, course);
		return course;
	}

	/**
	 * Creates new student
	 * 
	 * @param s the array with the student's type, id, phone number and name
	 * @return the student created
	 * @throws NotMatchingCourseException
	 * @throws DuplicateIdException
	 * @throws OutOfRangeIdException
	 */
	public Student addStudent(String[] s) throws NotMatchingCourseException, DuplicateIdException, OutOfRangeIdException {
		int id;
		if (verifyId(id = Integer.parseInt(s[1]))) {
			Student newStudent = new Student(id, Integer.parseInt(s[2]), s[3]);
			_students.put(newStudent.getId(), newStudent);
			if (id > _nextId) {
				_nextId = id;
			}
			return newStudent;
		}
		return null;
	}

	/**
	 * Creates new administrative.
	 * @param s the array with the administrative's type, id, phone number and name
	 * @return the created administrative
	 * @throws DuplicateIdException
	 * @throws OutOfRangeIdException
	 */
	public Administrative addAdministrative(String[] s) throws DuplicateIdException, OutOfRangeIdException {
		int id;
		if (verifyId(id = Integer.parseInt(s[1]))) {
			Administrative newAdministrative = new Administrative(id, Integer.parseInt(s[2]), s[3]);
			_administratives.put(newAdministrative.getId(), newAdministrative);
			if (id > _nextId) {
				_nextId = id;
			}
			return newAdministrative;
		}
		return null;
	}

	/**
	 * Creates new professor
	 * @param s the array with the professor's type, id, phone number and name
	 * @return the created professor
	 * @throws DuplicateIdException
	 * @throws OutOfRangeIdException
	 */
	public Professor addProfessor(String[] s) throws DuplicateIdException, OutOfRangeIdException {
		int id;
		if (verifyId(id = Integer.parseInt(s[1]))) {
			Professor newProfessor = new Professor(id, Integer.parseInt(s[2]), s[3]);
			_professors.put(newProfessor.getId(), newProfessor);
			if (id > _nextId) {
				_nextId = id;
			}
			return newProfessor;
		}
		return null;
	}

	/**	
	 * @param in input from file
	 * @param split the array with the student's type, id, phone number and name
	 * @return the student 
	 * @throws NotMatchingCourseException
	 * @throws DuplicateIdException
	 * @throws OutOfRangeIdException
	 * @throws IOException
	 * @throws DuplicateDisciplineException
	 * @throws InexistentCourseException
	 * @throws DisciplineLimitExceededException
	 */
	public Student readStudent(BufferedReader in, String[] split) throws NotMatchingCourseException, DuplicateIdException, OutOfRangeIdException, IOException, DuplicateDisciplineException,InexistentCourseException, DisciplineLimitExceededException{
		Student student = addStudent(split);
		String s, line;
		int c;
		
		while (true) {
			in.mark(1);
			c = in.read();
			if (c == '#') {
				in.reset();
				s = in.readLine();
				line = new String(s.getBytes(), "UTF-8");
				split = line.split("\\|");
				split[0] = split[0].replaceAll("#\\ ", "");

				try {
					if (student.getCourse() != null && !split[0].equals(student.getCourse().getName())) {
						throw new NotMatchingCourseException();
					}
					Course course = addCourse(split[0]);
					Discipline discipline = new Discipline(split[1], 300);
					course.addDiscipline(discipline);
					student.setCourse(course);
					discipline = course.getDisciplines().get(discipline.getDisciplineName());
					student.addDiscipline(discipline);
					try {
						discipline.addStudent(student);
					} catch (StudentLimitExceededException e){}
				} catch (NotMatchingCourseException e) {
					e.printStackTrace();
					break;
				}
			}
			else {
				break;
			}
		}
		in.reset();
		return student;
	}

	/**
	 * @return the school administratives
	 */
	public Map<Integer, Administrative> getAdministratives() {
		return _administratives;
	}

	/**
	 * @return the school professors
	 */
	public Map<Integer, Professor> getProfessors() {
		return _professors;
	}

	/**
	 * @return the school students
	 */
	public Map<Integer, Student> getStudents() {
		return _students;
	}

	/**
	 * Verify if id is within valid parameters
	 * @param id
	 * @return true if valid
	 * @throws DuplicateIdException
	 * @throws OutOfRangeIdException
	 */
	public Boolean verifyId(int id) throws DuplicateIdException, OutOfRangeIdException {
		if (idExists(id))
			throw new DuplicateIdException(id);
		// "Id Already Exists."
		if (id < 100000)
			throw new OutOfRangeIdException(id);
		// "Id Lower than 100000."
		return true;
	}

	/**
	 * 
	 * @return everyone in the school
	 */
	public ArrayList<String> showAllPeople() {
		ArrayList<Person> peopleList = new ArrayList<Person>();
		ArrayList<String> peopleVisited = new ArrayList<String>();
		PersonVisitor visitor = new PersonVisitor();
		for (Map.Entry<Integer, Administrative> entry : getAdministratives().entrySet()) {
			peopleList.add(entry.getValue());
		}
		for (Map.Entry<Integer, Professor> entry : getProfessors().entrySet()) {
			peopleList.add(entry.getValue());
		}
		for (Map.Entry<Integer, Student> entry : getStudents().entrySet()) {
			peopleList.add(entry.getValue());
		}
		Collections.sort(peopleList, Person.ID_COMPARATOR);

		for (Person p: peopleList){
			peopleVisited.add(p.accept(visitor));
		}
		return peopleVisited;
	}

	/**
	 * 
	 * @param name the name to be searched for
	 * @return everyone in the school whose name contains the search term
	 */
	public ArrayList<String> searchPerson(String name) {
		ArrayList<Person> filteredPeople = new ArrayList<Person>();
		ArrayList<String> peopleVisited = new ArrayList<String>();
		PersonVisitor visitor = new PersonVisitor();
		for (Map.Entry<Integer, Administrative> entry : getAdministratives().entrySet()) {
			if (entry.getValue().getName().toLowerCase().contains(name.toLowerCase())) {
				filteredPeople.add(entry.getValue());
			}
		}
		for (Map.Entry<Integer, Professor> entry : getProfessors().entrySet()) {
			if (entry.getValue().getName().toLowerCase().contains(name.toLowerCase())) {
				filteredPeople.add(entry.getValue());
			}
		}
		for (Map.Entry<Integer, Student> entry : getStudents().entrySet()) {
			if (entry.getValue().getName().toLowerCase().contains(name.toLowerCase())) {
				filteredPeople.add(entry.getValue());
			}
		}
		Collections.sort(filteredPeople, Person.NAME_COMPARATOR);
		
		for (Person p: filteredPeople){
			peopleVisited.add(p.accept(visitor));
		}
		return peopleVisited;
	}

	/**
	 * Checks if a person exists in the school.
	 * @param id of the person to be checked for
	 * @return true if person exists
	 */
	public boolean containsPerson(int id) {
		return (_students.containsKey(id) || _professors.containsKey(id) || _administratives.containsKey(id));
	}

	public Person getPerson(int id){
		Person person = _students.get(id);
		if (person == null){
			person = _professors.get(id);
			if (person == null){
				person = _administratives.get(id);
			}
		}
		return person;
	}
}
