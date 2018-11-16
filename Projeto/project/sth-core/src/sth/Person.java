package sth;

import java.io.Serializable;
import java.text.Collator;
import java.util.Comparator;

import sth.exceptions.BadEntryException;
import sth.exceptions.DuplicateIdException;
import sth.exceptions.OutOfRangeIdException;

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

	/** The Person class comparators. */
	public final static Comparator<Person> NAME_COMPARATOR = new NameComparator();
	public final static Comparator<Person> ID_COMPARATOR = new IdComparator();

	/**
	 * The 
	 * 
	 * @param id
	 * @param phoneNumber
	 * @param name
	 * @throws DuplicateIdException
	 * @throws OutOfRangeIdException
	 */
	public Person(int id, int phoneNumber, String name) throws DuplicateIdException, OutOfRangeIdException {
		this.setId(id);
		this.setPhoneNumber(phoneNumber);
		this.setName(name);
	}

	public void setId(int id) throws DuplicateIdException, OutOfRangeIdException{
		if (IdExists(id)) 
			throw new DuplicateIdException(id); 
			//"Id Already Exists."
		if (id < 100000)
			throw new OutOfRangeIdException(id);
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

	/**
	 * @return the person's name.
	 */
	public String getName() {
		return _name;
	}

	/**
	 * @return the person's id.
	 */
	public int getId() {
		return _id;
	}

	private static class NameComparator implements Comparator<Person> {
	
		@Override
		public int compare(Person person1, Person person2) {
			return Collator.getInstance(locale).compare(person1.getName(), person2.getName());
		}
	}

	private static class IdComparator implements Comparator<Person> {

		@Override
		public int compare(Person person1, Person person2) {
			return person1.getId() - person2.getId();
		}
	}
}