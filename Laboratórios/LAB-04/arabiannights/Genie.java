package arabiannights;

public abstract class Genie {
	private int wishLimit;
	private int grantedWishes = 0;
	
	protected Genie(int wishes) {
		this.wishLimit = wishes;
	}
	
	public int getWishLimit() {
		return this.wishLimit;
	}
	
	public int getGrantedWishes() {
		return this.grantedWishes;
	}	
	
	public void incGrantedWishes(int n) {
		this.grantedWishes += n;
	}

	public abstract boolean grantWish();
	
	public abstract int getRemainingWishes();
}