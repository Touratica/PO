package sth.app.student;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import sth.SchoolManager;

//FIXME import other classes if needed

/**
 * 4.4.2. Answer survey.
 */
public class DoAnswerSurvey extends Command<SchoolManager> {

  private Input<String> _discipline;
  private Input<String> _project; 
  private Input<Integer> _hours; 
  private Input<String> _comment; 

  /**
   * @param receiver
   */
  public DoAnswerSurvey(SchoolManager receiver) {
    super(Label.ANSWER_SURVEY, receiver);
    _discipline = _form.addStringInput(Message.requestDisciplineName());
    _project = _form.addStringInput(Message.requestProjectName());
    _hours = _form.addStringInput(Message.requestProjectHours());
    _comment = _form.addStringInput(Message.requestComment());
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() throws DialogException {
    _form.parse();
    _receiver.answerSurvey(_discipline.value(), _project.value(), _hours.value(), _comment.value());

  }

}
