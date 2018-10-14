package arabiannights;

public class FriendlyGenie extends Genie {
	public FriendlyGenie(int wishes) {
		super(wishes);
	}

	public int getRemainingWishes() {
		return this.getWishLimit() - this.getGrantedWishes();
	}

	public boolean grantWish() {
		if (this.getRemainingWishes() > 0) {
			this.incGrantedWishes(1);
			return true;
		}
		return false;
	}
}