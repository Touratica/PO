package sth.app.student;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import sth.SchoolManager;
import sth.app.exceptions.NoSuchDisciplineException;
import sth.app.exceptions.NoSuchProjectException;
import sth.exceptions.NoSuchDisciplineNameException;
import sth.exceptions.NoSuchProjectNameException;
import sth.exceptions.ProjectAlreadyClosedException;

//FIXME import other classes if needed

/**
 * 4.4.1. Deliver project.
 */
public class DoDeliverProject extends Command<SchoolManager> {

	private Input<String> _discipline;
	private Input<String> _project;
	private Input<String> _submission;

	/**
	 * @param receiver
	 */
	public DoDeliverProject(SchoolManager receiver) {
		super(Label.DELIVER_PROJECT, receiver);
		_discipline = _form.addStringInput(Message.requestDisciplineName());
		_project = _form.addStringInput(Message.requestProjectName());
		_submission = _form.addStringInput(Message.requestDeliveryMessage());
	}

	/** @see pt.tecnico.po.ui.Command#execute() */
	@Override
	public final void execute() throws DialogException {
		_form.parse();
		try {
			_receiver.deliverProject(_discipline.value(), _project.value(), _submission.value());
		} catch (NoSuchDisciplineNameException e) {
			throw new NoSuchDisciplineException(_discipline.value());
		} catch (NoSuchProjectNameException e) {
			throw new NoSuchProjectException(_discipline.value(), _project.value());
		} catch (ProjectAlreadyClosedException e) {
			e.printStackTrace();
		}
	}

}
