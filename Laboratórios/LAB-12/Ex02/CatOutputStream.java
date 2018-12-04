import java.io.IOException;

/**
 * CatOutputStream
 */
public interface CatOutputStream {

	public void put(Cat cat) throws IOException;
	public void close() throws IOException;
}