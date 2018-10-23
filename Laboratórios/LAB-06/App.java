public class App {
	public static void main(String[] args) {
		Table t = new Table(3); // table with 3 integers

		t.setAll(90);
		t.setValue(2, 2);

		SelectionPredicate p1 = new GreaterThan(9);

		if (t.contains(p1))
			System.out.println("YES");
		else
			System.out.println("NO");

		SelectionPredicate p2 = new EqualTo(2);

		if (t.contains(p2))
			System.out.println("YES");
		else
			System.out.println("NO");
		
		Iterator it1 = t.getForwardIterator();
		Iterator it2 = t.getReverseIterator();

		while (it1.hasNext()) {
			System.out.println(it1.next());
		}
		while (it2.hasNext()) {
			System.out.println(it2.next());
		}
	}
}