package sth.app.representative;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import sth.SchoolManager;
import sth.app.exceptions.ClosingSurveyException;
import sth.app.exceptions.NoSuchDisciplineException;
import sth.app.exceptions.NoSuchProjectException;
import sth.app.exceptions.NoSurveyException;
import sth.exceptions.NoSuchDisciplineNameException;
import sth.exceptions.NoSuchProjectNameException;
import sth.exceptions.NoSuchSurveyException;
import sth.exceptions.SurveyNotClosedException;

/**
 * 4.5.5. Finish survey.
 */
public class DoFinishSurvey extends Command<SchoolManager> {

	private Input<String> _discipline;
	private Input<String> _project; 

	/**
	 * @param receiver
	 */
	public DoFinishSurvey(SchoolManager receiver) {
		super(Label.FINISH_SURVEY, receiver);
		_discipline = _form.addStringInput(Message.requestDisciplineName());
		_project = _form.addStringInput(Message.requestProjectName());
	}

	/** @see pt.tecnico.po.ui.Command#execute() */
	@Override
	public final void execute() throws DialogException {
		_form.parse();
		try {
			_receiver.finalizeSurvey(_discipline.value(), _project.value());
		} catch (NoSuchDisciplineNameException e) {
			throw new NoSuchDisciplineException(_discipline.value());
		} catch (NoSuchProjectNameException e) {
			throw new NoSuchProjectException(_discipline.value(), _project.value());
		} catch (NoSuchSurveyException e) {
			throw new NoSurveyException(_discipline.value(), _project.value());
		} catch (SurveyNotClosedException e) {
			throw new ClosingSurveyException(_discipline.value(), _project.value());
		}
	}

}
