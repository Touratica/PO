package sth;

//FIXME import other classes if needed

import sth.io.Serializable;

import java.io.DataInputStream;
import java.io.FileInputStream;

import sth.exceptions.BadEntryException;
import sth.exceptions.InvalidCourseSelectionException;
import sth.exceptions.NoSuchPersonIdException;

/**
 * School implementation.
 */
public class School implements Serializable {

	/** Serial number for serialization. */
	private static final long serialVersionUID = 201810051538L;

	//FIXME define object fields (attributes and, possibly, associations)

	//FIXME implement constructors if needed
	
	/**
	 * @param filename
	 * @throws BadEntryException
	 * @throws IOException
	 */
	void importFile(String filename) throws IOException, BadEntryException {
		//FIXME implement text file reader
		DataInputStream in = new DataInputStream(new BufferedInputStream(new FileInputStream(filename)));
		String function;
		
		switch (function = in.readUTF()) {
			case "FUNCIONÁRIO":
				
				break;
		
			case "DELEGADO":

				break;

			case "ALUNO":

				break;

			case "DOCENTE":

				break;

			default:
				throw new BadEntryException(entrySpecification);
				break;
		}

	}
	
	//FIXME implement other methods

}
