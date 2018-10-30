import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

public class Album {
	private TreeMap<Integer, Card> cards = new TreeMap<Integer, Card>();

	public List<Card> getAll() {
		List<Card> lst = new LinkedList<Card>();
		lst.addAll(this.cards.values());
		Collections.sort(lst);
		return lst;
	}

	public void add(Card card) {
		this.cards.put(card.getId(), card);

	}
	public void remove(int id) {
		this.cards.remove(id);
	}

	public int size() {
		return this.cards.size();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Album) {
			Album album = (Album) obj;
			return this.size() == album.size();
		}
		return false;
	}
}