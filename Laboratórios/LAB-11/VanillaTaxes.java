/**
 * VanillaTaxes
 */
public class VanillaTaxes extends FriendlyIRS {

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
		return tax;
	}

	@Override
	public double taxRegion(Region region) {
		double tax = 0;
		for (int i = 0; i < region.size(); i++) {
			tax += region.getCompany(i).accept(this);
		}
		return tax;
	}
}