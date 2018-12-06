package sth.app.main;

import java.io.FileNotFoundException;
import java.io.IOException;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import sth.SchoolManager;
import sth.app.exceptions.NoSuchPersonException;
import sth.exceptions.NoSuchPersonIdException;

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
	public final void execute() throws DialogException {
		try {
			_form.parse(); // clears previous input
			_receiver.open(_filename.value());
			_display.popup(_receiver.showNotifications());
		} catch (FileNotFoundException fnfe) {
			_display.popup(Message.fileNotFound());
		} catch (NoSuchPersonIdException e) {
			throw new NoSuchPersonException(e.getId());			
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
	}

}
