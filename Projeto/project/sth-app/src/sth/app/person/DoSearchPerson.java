package sth.app.person;

import java.util.Collection;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.Input;
import sth.Person;
import sth.SchoolManager;
import sth.app.exceptions.NoSuchPersonException;

//FIXME import other classes if needed

/**
 * 4.2.4. Search person.
 */
public class DoSearchPerson extends Command<SchoolManager> {

	//FIXME add input fields if needed
	Input<String> _name;

	/**
	 * @param receiver
	 */
	public DoSearchPerson(SchoolManager receiver) {
		super(Label.SEARCH_PERSON, receiver);
		_name = _form.addIntegerInput(Message.requestPersonName());
	}

	/** @see pt.tecnico.po.ui.Command#execute() */
	@Override
	public final void execute() {
		try {
			_form.parse();
			Collection<Person> people = _receiver.searchPerson(_name);
			for (Person person: people) {
				_display.addLine(person.toString());
			}
			
		} catch (NoSuchPersonException e) {
			e.printStackTrace();
		}
	}

}
