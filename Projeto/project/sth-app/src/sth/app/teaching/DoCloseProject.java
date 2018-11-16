package sth.app.teaching;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import sth.SchoolManager;
import sth.app.exceptions.NoSuchProjectException;
import sth.exceptions.NoSuchProjectNameException;

//FIXME import other classes if needed

/**
 * 4.3.2. Close project.
 */
public class DoCloseProject extends Command<SchoolManager> {

	private Input<String> _discipline;
	private Input<String> _project; 

	/**
	 * @param receiver
	 */
	public DoCloseProject(SchoolManager receiver) {
		super(Label.CLOSE_PROJECT, receiver);
		_discipline = _form.addStringInput(Message.requestDisciplineName());
		_project = _form.addStringInput(Message.requestProjectName());
	}

	/** @see pt.tecnico.po.ui.Command#execute() */
	@Override
	public final void execute() throws DialogException {
		_form.parse();
		try {
			_receiver.closeProject(_discipline, _project);
		} catch (NoSuchProjectNameException e) {
			throw new NoSuchProjectException(_discipline, _project);
		}	
	}

}
