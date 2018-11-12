import java.io.ObjectOutputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;

import java.io.ObjectInputStream;
import java.io.BufferedInputStream;
import java.io.FileInputStream;

import java.io.IOException;

public class App {
	public static void main(String[] args) {
		// (1) cria um registo e dois gatos
		CatRegistry registry = new CatRegistry();
		Cat c1 = new Cat("Pantufa", 9);
		Cat c2 = new Cat("Tareco", 1);
		// (2) insere os gatos no registo
		registry.put(c1);
		registry.put(c2);

		try {
			// (3) guarda o registo em disco
			ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("cats.dat")));
			oos.writeObject(registry);
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			// (4) recupera o registo a partir do disco
			ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream("cats.dat")));
			CatRegistry previousRegistry = (CatRegistry) ois.readObject();
			ois.close();
			// (5) recupera os dois gatos a partir do registo
			Cat recCat1 = previousRegistry.get("Pantufa");
			Cat recCat2 = previousRegistry.get("Tareco");
			// (6) apresenta os gatos na saída (System.out)
			System.out.println(recCat1);
			System.out.println(recCat2);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}