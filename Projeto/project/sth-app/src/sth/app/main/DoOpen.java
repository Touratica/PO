package sth.app.main;

import java.io.FileNotFoundException;
import java.io.IOException;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.Input;
import sth.SchoolManager;

/**
 * 4.1.1. Open existing document.
 */
public class DoOpen extends Command<SchoolManager> {

	/** The name of the file to be open. */
	private Input<String> _filename;

	/**
	 * @param receiver
	 */
	public DoOpen(SchoolManager receiver) {
		super(Label.OPEN, receiver);
		_filename = _form.addStringInput(Message.openFile());
	}

	/** @see pt.tecnico.po.ui.Command#execute() */
	@Override
	public final void execute() {
		try {
			_form.parse(); // clears previous input
			_receiver.open(_filename.value());
		} catch (FileNotFoundException fnfe) {
			_display.popup(Message.fileNotFound());
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
	}

}
