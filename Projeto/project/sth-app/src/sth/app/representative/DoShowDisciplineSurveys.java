package sth.app.representative;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import sth.SchoolManager;

/**
 * 4.5.6. Show discipline surveys.
 */
public class DoShowDisciplineSurveys extends Command<SchoolManager> {

	private Input<String> _discipline;

	/**
	 * @param receiver
	 */
	public DoShowDisciplineSurveys(SchoolManager receiver) {
		super(Label.SHOW_DISCIPLINE_SURVEYS, receiver);
		_discipline = _form.addStringInput(Message.requestDisciplineName());
	}

	/** @see pt.tecnico.po.ui.Command#execute() */
	@Override
	public final void execute() throws DialogException {
		_form.parse();
		try {
			String s =_receiver.showDisciplineSurveys(_discipline.value()); 
			_display.addLine(s);
			_display.display();			
		} catch (UnsupportedOperationException e) {
			e.printStackTrace();
		}
	}
}
