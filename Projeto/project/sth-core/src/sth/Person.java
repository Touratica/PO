package sth;

import java.io.Serializable;

import sth.exceptions.BadEntryException;
import sth.exceptions.DuplicateIdException;
import sth.exceptions.NonSupportedIdException;

/**
 * Every person in the school.
 */
public abstract class Person implements Serializable {

	private static final long serialVersionUID = 201811151741L;
	
	/** The person's id number. */
	private int _id;

	/** The person's phone number. */
	private int _phoneNumber;

	/** The person's name. */
	private String _name;

	
	public void setId(int id) throws DuplicateIdException, NonSupportedIdException{
		if (IdExists(id)) 
			throw new DuplicateIdException(id); 
			//"Id Already Exists."
		if (id < 100000)
			throw new NonSupportedIdException(id);
			// "Id Lower than 100000."
		_id = id;
	}

	public void setPhoneNumber(int number){
		_phoneNumber = number;
	}

	public void setName(String name){
		if (name == null) 
			throw new NullPointerException();
		_name = name;

	}

	
}