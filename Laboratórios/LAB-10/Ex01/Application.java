import java.util.*;

public class Application {

  public static void main(String args[]) {
    List<Cat> cats = new ArrayList<Cat>();
    cats.add(new Cat(1, 8));
    cats.add(new Cat(2, 7));
    cats.add(new Cat(3, 6));

    Collections.sort(cats);       // ordenação por peso (_weight)
    for (Cat cat: cats) System.out.println(cat);

	//... código em falta ...     // ordenação por idade (_age)
	Collections.sort(cats, AgeComparator);
    for (Cat cat: cats) System.out.println(cat);
  }

}