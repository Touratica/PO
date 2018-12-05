package sth.app.representative;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import sth.SchoolManager;

//FIXME import other classes if needed

/**
 * 4.5.3. Open survey.
 */
public class DoOpenSurvey extends Command<SchoolManager> {

  private Input<String> _discipline;
	private Input<String> _project; 

  /**
   * @param receiver
   */
  public DoOpenSurvey(SchoolManager receiver) {
    super(Label.OPEN_SURVEY, receiver);
    _discipline = _form.addStringInput(Message.requestDisciplineName());
		_project = _form.addStringInput(Message.requestProjectName());
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() throws DialogException {
    _form.parse();
    _receiver.openSurvey(_discipline.value(), _project.value());
  }

}
