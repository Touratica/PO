package sth;

import sth.exceptions.BadEntryException;

/**
 * Every person in the school.
 */
public abstract class Person {
	
	/** The person's id number. */
	private int _id;

	/** The person's phone number. */
	private int _phoneNumber;

	/** The person's name. */
	private String _name;

	
	public void setId(int id) throws BadEntryException{
		if (IdExists(id)) 
			throw new BadEntryException("Id"); 
			//"Id Already Exists."
		if (id < 100000)
			throw new BadEntryException("Id");
			// "Id Lower than 100000."
		_id = id;
	}

	public void setPhoneNumber(int number){
		_phoneNumber = number;
	}

	public void setName(String name) throws BadEntryException{
		if (name == null) 
			throw new BadEntryException("Name");
		_name = name;

	}

	
}