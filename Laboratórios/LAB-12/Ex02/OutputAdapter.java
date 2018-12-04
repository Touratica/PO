import java.io.DataOutputStream;
import java.io.IOException;

/**
 * OutputAdapter
 */
public class OutputAdapter implements CatOutputStream {

	private DataOutputStream _out;
	
	public OutputAdapter(DataOutputStream out) {
		_out = out;
	}

	@Override
	public void put(Cat cat) throws IOException {
		_out.writeDouble(cat.getWeight());
		_out.writeInt(cat.getAge());
		_out.writeUTF(cat.getName());
	}

	@Override
	public void close() throws IOException {
		_out.close();
	}
}