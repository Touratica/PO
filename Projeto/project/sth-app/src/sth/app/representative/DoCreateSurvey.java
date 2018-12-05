package sth.app.representative;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import sth.SchoolManager;


/**
 * 4.5.1. Create survey.
 */
public class DoCreateSurvey extends Command<SchoolManager> {

  private Input<String> _discipline;
	private Input<String> _project; 

  /**
   * @param receiver
   */
  public DoCreateSurvey(SchoolManager receiver) {
    super(Label.CREATE_SURVEY, receiver);
    _discipline = _form.addStringInput(Message.requestDisciplineName());
		_project = _form.addStringInput(Message.requestProjectName());
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() throws DialogException {
    _form.parse();
    _receiver.createSurvey(_discipline.value(), _project.value());
  }

}
