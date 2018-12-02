import java.util.ArrayList;

/**
 * Region
 */
public class Region extends Taxpayer {

	private ArrayList<Company> _companies = new ArrayList<Company>();

	public Region() {
		int count = (int) (Math.random() * 100);
		for (int i = 0; i < count; i++) {
			_companies.add(new Company());
		}
	}

	public int size() {
		return _companies.size();
	}

	public Company getCompany(int index) {
		return _companies.get(index);
	}

	@Override
	public double accept(FriendlyIRS irs) {
		return irs.taxRegion(this);
	}
}