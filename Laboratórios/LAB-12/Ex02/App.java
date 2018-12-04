import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * App
 */
public class App {

	public static void main(String[] args) {
		Cat c1 = new Cat(1, 5.6, "Tareco");
		Cat c2 = new Cat(3, 6.9, "Benny");

		try {
			OutputAdapter out = new OutputAdapter(new DataOutputStream(new BufferedOutputStream(new FileOutputStream("cats.dat"))));

			out.put(c1);
			out.put(c2);

			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		InputAdapter in = new InputAdapter(
				new DataInputStream(new BufferedInputStream(new FileInputStream("cats.dat"))));
	}
}