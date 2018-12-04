package sth.app.person;

import java.util.Collection;

import pt.tecnico.po.ui.Command;
import sth.Person;
import sth.SchoolManager;

/**
 * 4.2.3. Show all persons.
 */
public class DoShowAllPersons extends Command<SchoolManager> {

  /**
   * @param receiver
   */
  public DoShowAllPersons(SchoolManager receiver) {
    super(Label.SHOW_ALL_PERSONS, receiver);
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() {
    Collection<Person> people = _receiver.showAllPeople();
    for (Person person: people) {
      _display.addLine(person.toString());
    }
    _display.display();
  }
}
