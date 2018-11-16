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
import sth.exceptions.DuplicateDisciplineException;
import sth.exceptions.InvalidCourseSelectionException;
import sth.exceptions.NoSuchPersonIdException;
import sth.exceptions.NotMatchingCourseException;

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
				
					case "PROFESSOR":
						Professor professor = addProfessor(split);
						in.mark(1);
						int c;
						while ((c = in.read())) {
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
						break;
				}
			}
			in.close();
		} catch (FileNotFoundExeception e) {

		} catch (IOException e){

		}


	}
	

	/** idExists : checks if  the given id already exists in school */
	public Bollean idExists(int id){
		if (_people.containsKey(id))
			return true;
		return false;
	}

	/** 
	 * 	Adds a course that doesn't exist in school.
	 * 
	 * 	@param courseName
	 */
	public Course addCourse(String courseName) throws DuplicateCourseException {
		if(courseName != null) {
			for (Map.Entry<Course, Disciplines> entry: _courses.entrySet()) {
				if (entry.getKey().getNameCourse() == courseName) {
					return entry.getKey();
				}
			}
			Course course = new Course(courseName);
			_courses.put(course, new Disciplines());
			return course;
		}
	}

	public Discipline addDiscipline(String courseName, String disciplineName) throws DuplicateDisciplineException {
		for (Map.Entry<Course, Disciplines> entry: _courses.entrySet()) {
			if (entry.getKey().getNameCourse() == courseName) {
				Discipline discipline = new Discipline(disciplineName);
				entry.getValue().addDiscipline(discipline);
				return Discipline;
			}
		}
		throw new InexistentCourseException();
	}

	public Student addStudent(String[] s) throws NotMatchingCourseException, DuplicateIdException, OutOfRangeIdException {
		Student newStudent = new Student(s[1],s[2],s[3]);
		_people.put(newStudent.getId(), newStudent);
		return newStudent;
	}

	public Student addAdministrative(String[] s)
			throws DuplicateIdException, OutOfRangeIdException {
		Student newAdministrative = new Professor(s[1], s[2], s[3]);
		_people.put(newAdministrative.getId(), newAdministrative);
		return newAdministrative;
	}

	public Professor addProfessor(String[] s) {
		Professor newProfessor = new Professor(s[1],s[2],s[3]);
		_people.put(newProfessor.getId(), newProfessor);
		return newProfessor;
	}

	public Student readStudent(BufferedReader in, String[] split) {
		Student student = addStudent(split);
		String s, line;
		in.mark(1);
		int c;
		while((c = in.read())){
			if (c == '#') {
				in.reset();
				s = in.readLine();
				line = new String(s.getBytes(), "UTF-8");
				split = line.split("\\|");
				split[0] = split[0].replaceAll("#\\ ", "");
				try {
					if (student.getCourse() != null && split[0] != student.getCourse()) {
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
}
