import java.util.Map;
import java.util.TreeMap;
import java.io.Serializable;

public class CatRegistry implements Serializable {
	private Map<String, Cat> _registry = new TreeMap<String, Cat>();

	public Cat get(String name) {
		return _registry.get(name);
	}

	public void put(Cat cat) {
		_registry.put(cat.getName(), cat);
	}
}