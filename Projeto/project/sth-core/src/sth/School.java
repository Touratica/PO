package sth;

//FIXME import other classes if needed

import sth.exceptions.BadEntryException;
import sth.exceptions.InvalidCourseSelectionException;
import sth.exceptions.NoSuchPersonIdException;
import sth.io.Serializable;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

/**
 * School implementation.
 */
public class School implements Serializable {

	/** Serial number for serialization. */
	private static final long serialVersionUID = 201810051538L;

	//FIXME define object fields (attributes and, possibly, associations)
	
	/** The school's people. */
	private Map<Integer, Person> _people = new HashMap<Integer, Person>();

	/** People counter. */
	private int _peopleId = 0;

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
					// FIXME: implement get disciplines
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
		} catch (Exeception e) {

		}

	}
	
	//FIXME implement other methods

}
