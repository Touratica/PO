import java.util.Comparator;

/**
 * AgeComparator
 */
public class AgeComparator implements Comparator<Cat> {

	@Override
	public int compare(Cat a1, Cat a2) {
		return a1.getAge() - a2.getAge();
	}
}