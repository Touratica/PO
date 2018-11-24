package sth.app.teaching;

import java.util.Collection;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import pt.tecnico.po.ui.Messages;
import sth.SchoolManager;
import sth.Student;
import sth.app.exceptions.NoSuchDisciplineException;

//FIXME import other classes if needed

/**
 * 4.3.4. Show course students.
 */
public class DoShowDisciplineStudents extends Command<SchoolManager> {

	private Input<String> _discipline;

	/**
	 * @param receiver
	 */
	public DoShowDisciplineStudents(SchoolManager receiver) {
		super(Label.SHOW_COURSE_STUDENTS, receiver);
		_discipline = _form.addStringInput(Message.requestDisciplineName());
	}

	/** @see pt.tecnico.po.ui.Command#execute() */
	@Override
	public final void execute() throws DialogException {
		_form.parse();
		Collection<Student> students = _receiver.showDisciplineStudents(_discipline.value());
		if (students != null) {
			for (Student student: students) {
				_display.addLine(student.toString());
			}
		}
		else {
			throw new NoSuchDisciplineException(_discipline.value());
		}
		_display.display();
	}

}
