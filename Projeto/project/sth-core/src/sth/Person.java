package sth;

import java.io.Serializable;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import sth.exceptions.BadEntryException;
import sth.exceptions.DuplicateIdException;
import sth.exceptions.OutOfRangeIdException;

/**
 * Every person in the school.
 */
public abstract class Person implements Serializable, Observer {

	private static final long serialVersionUID = 201811151741L;
	
	/** The person's id number. */
	private int _id;

	/** The person's phone number. */
	private int _phoneNumber;

	/** The person's name. */
	private String _name;

	private List<String> _notifications = new ArrayList<String>();

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

	public void setId(int id) throws DuplicateIdException, OutOfRangeIdException {
		_id = id;
	}

	public void setPhoneNumber(int number){
		_phoneNumber = number;
	}

	public void setName(String name){
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

	/**
	 * @return the _notifications
	 */
	public List<String> getNotifications() {
		return _notifications;
	}

	public void addNotification(String notification) {
		_notifications.add(notification);
	}

	public boolean isAdministrative() {
		return false;
	}

	public boolean isProfessor() {
		return false;
	}

	public boolean isRepresentative() {
		return false;
	}

	public boolean isStudent() {
		return false;
	}

	@Override
	public void update(String discipline, String project, Survey survey) {
		String notification = "";
		if (survey.isOpen()) {
			notification += "Pode preencher inquérito do projecto " + project + " da disciplina " + discipline;
		} else if (survey.isFinal()) {
			notification += "Resultados do inquérito do projecto " + project + " da disciplina " + discipline;
		} else {
			throw new UnsupportedOperationException();
		}
		addNotification(notification);
	}

	public String showNotifications() {
		String notifications = "";
		for (String str: _notifications) {
			notifications += str + "\n";
		}
		return notifications;
	}

	@Override
	public String toString() {
		return  _id + "|" + _phoneNumber + "|" +  _name;
	}
	
	private static class NameComparator implements Comparator<Person> {

		@Override
		public int compare(Person person1, Person person2) {
			return Collator.getInstance().compare(person1.getName(), person2.getName());
		}
	}

	private static class IdComparator implements Comparator<Person> {

		@Override
		public int compare(Person person1, Person person2) {
			return person1.getId() - person2.getId();
		}
	}
}