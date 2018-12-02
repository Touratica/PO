import java.util.ArrayList;

/**
 * Company
 */
public class Company extends Taxpayer {

	private ArrayList<Person> _employees = new ArrayList<Person>();

	public Company() {
		int count = (int) (Math.random() * 100);
		for (int i = 0; i < count; i++) {
			_employees.add(new Person());
		}
	}

	public int size() {
		return _employees.size();
	}

	public Person getEmployee(int index) {
		return _employees.get(index);
	}

	@Override
	public double accept(FriendlyIRS irs) {
		return irs.taxCompany(this);
	}
}