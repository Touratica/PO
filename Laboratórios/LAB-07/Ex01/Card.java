/**
 * The card class
 */
public class Card implements Comparable<Card>{
	/**
	 * The card's number.
	 */
	private int id;

	/**
	 * The card's image.
	 */
	private Image image;

	/**
	 * The card's contructor.
	 * @param id
	 * @param image
	 */
	public Card(int id, Image image) {
		this.id = id;
		this.image = image;
	}

	/**
	 * @return the card's number
	 */
	public int getId() {
		return this.id;
	}

	/**
	 * @return the card's image
	 */
	public Image getImage() {
		return this.image;
	}

	@Override
	public int compareTo(Card o) {
		return this.id - o.getId();
	}
}