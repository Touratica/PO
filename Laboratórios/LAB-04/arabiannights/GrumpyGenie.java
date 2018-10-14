package arabiannights;

public class GrumpyGenie extends Genie {
	public GrumpyGenie(int wishes) {
		super(wishes);
	}

	public boolean grantWish() {
		if (this.getGrantedWishes() == 0) {
			this.incGrantedWishes(1);
			return true;
		}
		return false;
	}
}