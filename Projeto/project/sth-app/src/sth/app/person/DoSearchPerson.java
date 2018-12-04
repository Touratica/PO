package sth.app.person;

import java.util.Collection;
import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.Input;
import sth.Person;
import sth.SchoolManager;
import sth.app.exceptions.NoSuchPersonException;

/**
 * 4.2.4. Search person.
 */
public class DoSearchPerson extends Command<SchoolManager> {

	Input<String> _name;

	/**
	 * @param receiver
	 */
	public DoSearchPerson(SchoolManager receiver) {
		super(Label.SEARCH_PERSON, receiver);
		_name = _form.addStringInput(Message.requestPersonName());
	}

	/** @see pt.tecnico.po.ui.Command#execute() */
	@Override
	public final void execute() {
		_form.parse();
		Collection<String> people = _receiver.searchPerson(_name.value());
		for (String person: people) {
			_display.addLine(person); 
		}
		_display.display();
		
	}

}
