import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class App {
	public static void main(String[] args) {
		int max = 0;
		String maxStr = "";

		try {
			BufferedReader bReader = new BufferedReader(new FileReader(args[0]));
			String line = null;

			while ((line = bReader.readLine()) != null) {
				if (max < line.length()) {
					max = line.length();
					maxStr = line;
				}
			}
			System.out.print(max + " - " + maxStr);
			bReader.close();
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}