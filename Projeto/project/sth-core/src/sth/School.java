package sth;

//FIXME import other classes if needed

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

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

/**
 * School implementation.
 */
public class School implements Serializable {

	/** Serial number for serialization. */
	private static final long serialVersionUID = 201811151733L;

	//FIXME define object fields (attributes and, possibly, associations)
	
	/** School's people. */
	private Map<Integer, Student> _students = new HashMap<Integer, Student>();
	private Map<Integer, Professor> _professors = new HashMap<Integer, Professor>();
	private Map<Integer, Administrative> _administratives = new HashMap<Integer, Administrative>();

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
			System.out.println(split[1]);
			
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
					in.mark(1);
					int c;
					while (true) {
						c = in.read();
						if (c == '#') {
							in.reset();
							s = in.readLine();
							line = new String(s.getBytes(), "UTF-8");
							split = line.split("\\|");
							split[0] = split[0].replaceAll("#\\ ", "");
							try {
								Course course = addCourse(split[0]);
								Discipline discipline = addDiscipline(split[0], split[1]);
								professor.addCourseDiscipline(course, discipline);

							} catch (DuplicateCourseException e) {
								// do nothing- its fine to import courses with the same name
							}
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
	public Course addCourse(String courseName) throws DuplicateCourseException {
		for (Map.Entry<Course, Disciplines> entry: _courses.entrySet()) {
			if (entry.getKey().getNameCourse() == courseName) {
				return entry.getKey();
			}	
		}
		
		Course course = new Course(courseName);
		_courses.put(course, new Disciplines());
		return course;
	}

	/**
	 * Adds a discipline to course where it didn't exist.
	 * @param courseName
	 * @param disciplineName
	 * @return the discipline
	 * @throws DuplicateDisciplineException
	 * @throws InexistentCourseException
	 */
	public Discipline addDiscipline(String courseName, String disciplineName) throws DuplicateDisciplineException, InexistentCourseException {
		for (Map.Entry<Course, Disciplines> entry: _courses.entrySet()) {
			if (entry.getKey().getNameCourse() == courseName) {
				Discipline discipline = new Discipline(disciplineName);
				entry.getValue().addDiscipline(discipline);
				return discipline;
			}
		}
		throw new InexistentCourseException();
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
			return newStudent;
		}
		return null;
	}

	/**
	 *  Creates new administrative.
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
			return newProfessor;
		}
		return null;
	}

	/**
	 * Reads information about a student to be added
	 * 
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
		in.mark(1);
		int c;
		while (true) {
			c = in.read();
			if (c == '#') {
				in.reset();
				s = in.readLine();
				line = new String(s.getBytes(), "UTF-8");
				split = line.split("\\|");
				split[0] = split[0].replaceAll("#\\ ", "");
				try {
					if (student.getCourse() != null && split[0] != student.getCourse().getNameCourse()) {
						throw new NotMatchingCourseException();
					}
					Course course = addCourse(split[0]);
					Discipline discipline = addDiscipline(split[0], split[1]);
					student.setCourse(course);
					student.addDiscipline(discipline);
		
				} catch (DuplicateCourseException e) {
					// do nothing- its fine to import courses with the same name
				} catch (NotMatchingCourseException e) {
					e.printStackTrace();
				}
			} else {
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
}
