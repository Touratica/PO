import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UTFDataFormatException;

public class App {
	public static void main(String[] args) {
		Cat g = new Cat(2, 4, "Bernardo");
		try {
			ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("raw.dat")));

			out.writeInt(g.get_age());
			out.writeDouble(g.get_weight());
			out.writeUTF(g.get_name());

			out.close();

			ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream("raw.dat")));

			Cat g2 = new Cat(in.readInt(), in.readDouble(), in.readUTF());

			in.close();

			System.out.println(g2.get_age() + " " + g2.get_weight() + " " + g2.get_name());
		} catch (EOFException e) {
			e.printStackTrace();
		} catch (UTFDataFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}