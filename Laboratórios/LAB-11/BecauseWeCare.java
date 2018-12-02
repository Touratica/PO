/**
 * BecauseWeCare
 */
public class BecauseWeCare extends FriendlyIRS {

	private final static int LOW = 1;

	private final static int POP = 1;

	@Override
	public double taxPerson(Person person) {
		return 1;
	}

	@Override
	public double taxCompany(Company company) {
		double tax = 0;
		for (int i = 0; i < company.size(); i++) {
			tax += company.getEmployee(i).accept(this);
		}
		if (company.size() < POP || tax < LOW) {
			tax *= 0.9;
		}
		return tax;
	}

	@Override
	public double taxRegion(Region region) {
		double tax = 0;
		for (int i = 0; i < region.size(); i++) {
			tax += region.getCompany(i).accept(this);
		}
		if (region.size() < POP || tax < LOW) {
			tax *= 0.9;
		}
		return tax;
	}
}