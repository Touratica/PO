import java.io.DataInputStream;
import java.io.IOException;

public class InputAdapter implements CatInputStream {

	private DataInputStream _in;

	public InputAdapter(DataInputStream in) {
		_in = in;
	}

	@Override
	public Cat get() throws IOException {
		double weight = _in.readDouble();
		int age = _in.readInt();
		String name = _in.readUTF();

		return new Cat(age, weight, name);
	}

	@Override
	public void close() throws IOException {
		_in.close();
	}
}