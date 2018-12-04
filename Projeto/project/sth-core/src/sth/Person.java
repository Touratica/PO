package sth;

import java.io.Serializable;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import sth.exceptions.BadEntryException;
import sth.exceptions.DuplicateIdException;
import sth.exceptions.DuplicateProjectException;
import sth.exceptions.NoSuchDisciplineNameException;
import sth.exceptions.NoSuchProjectNameException;
import sth.exceptions.OutOfRangeIdException;
import sth.exceptions.ProjectAlreadyClosedException;

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

	public int getPhoneNumber(){
		return _phoneNumber;
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

	public void closeProject(String discipline, String Project) throws NoSuchProjectNameException, NoSuchDisciplineNameException, ProjectAlreadyClosedException, UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}

	public void createProject(String discipline, String Project) throws NoSuchDisciplineNameException, DuplicateProjectException, UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}

	public ArrayList<String> showDisciplineStudents(String discipline) throws UnsupportedOperationException, NoSuchDisciplineNameException {
		throw new UnsupportedOperationException();
	}

	public void deliverProject(String discipline, String project, String submission) throws UnsupportedOperationException, NoSuchDisciplineNameException, NoSuchProjectNameException, ProjectAlreadyClosedException{
		throw new UnsupportedOperationException();
	}

	public void answerSurvey(String discipline, String project , int hours, String comment) throws UnsupportedOperationException{
		throw new UnsupportedOperationException();
	}

	public void createSurvey(String discipline, String project) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}

	
	/*@Override
	public String toString() {
		return  _id + "|" + _phoneNumber + "|" +  _name;
	}
	*/

	public String accept(PersonVisitor visitor){
		return visitor.showPerson(this);
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