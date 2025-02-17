package sth.app.main;

import java.io.BufferedWriter;
import java.io.IOException;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.Input;
import sth.SchoolManager;

/**
 * 4.1.1. Save to file under current name (if unnamed, query for name).
 */
public class DoSave extends Command<SchoolManager> {
	
	Input<String> _filename;

	/**
	 * @param receiver
	 */
	public DoSave(SchoolManager receiver) {
		super(Label.SAVE, receiver);
		if (!receiver.isFileSet()) {
			_filename = _form.addStringInput(Message.newSaveAs());
		}
	}

	/** @see pt.tecnico.po.ui.Command#execute() */
	@Override
	public final void execute() {
		if (!_receiver.isFileSet()) {
			_form.parse(); // clears previous input
			_receiver.setFilename(_filename.value());
		}
		try {
			_receiver.save();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
