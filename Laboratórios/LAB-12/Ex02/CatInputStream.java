import java.io.IOException;

/**
 * CatInputStream
 */
public interface CatInputStream {

	public Cat get() throws IOException;
	public void close() throws IOException;
}