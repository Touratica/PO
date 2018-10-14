package bank;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import bank.exceptions.DuplicateHolderException;

/**
 * BankIO handles all IO operations concerning bank instances. This way, bank
 * instances don't have to care about IO specificities. The approach is very
 * simple and could be easily improved, in particular, to separate concerns
 * relative to different IO modalities.
 */
public class BankIO {

  /**
   * Carrega o estado anterior da aplicacao que estava guardado num dado
   * ficheiro.
   *
   * @param file
   *          o nome do ficheiro com os dados serializados.
   *
   * @throws IOException
   *           caso aconteca algum erro durante a leitura do estado.
   * @return um objecto Telele com dados os recuperados do file.
   * @throws ClassNotFoundException
   **/
  public static Bank load(String file) throws IOException, ClassNotFoundException {
    ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));

    Bank bank = (Bank) in.readObject();
    in.close();

    return bank;
  }

  /**
   * @param bank
   * @param file
   * @throws IOException
   **/
  public static void save(Bank bank, String file) throws IOException {
    ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
    out.writeObject(bank);
    out.close();
  }

  /**
   * Simple function to parse basic information about accounts and holders.
   * 
   * Lines are of the form:
   * 
   * ACCOUNT:account_id HOLDER:holder_id:holder_name
   *
   * Example:
   * 
   * HOLDER:615:Manuel ACCOUNT:150 ACCOUNT:210
   * 
   * @param bank
   *          the bank to receive the new information
   * @param file
   *          input file
   */
  @SuppressWarnings("nls")
  public static void addFromInputFile(Bank bank, String file) {
    int lineno = 0;
    try {
      BufferedReader in = new BufferedReader(new FileReader(file));
      String s;

      while ((s = in.readLine()) != null) {
        String line = new String(s.getBytes(), "UTF-8");
        lineno++;
        if (line.charAt(0) == '#')
          continue;
        String[] split = line.split(":");
        if (split[0].equals("HOLDER"))
          try {
            bank.addHolder(split[2], Integer.parseInt(split[1]));
          } catch (DuplicateHolderException ih) {
            System.err.println(ih);
          }
        else if (split[0].equals("ACCOUNT"))
          bank.createAccount(Float.parseFloat(split[1]));
      }
      
      in.close();
    } catch (FileNotFoundException e) {
      System.out.println("File not found: " + file + ": " + e);
    } catch (IOException e) {
      System.out.println("IO error: " + file + ": " + lineno + ": line " + e);
    }
  }

}
