import java.util.ArrayList;

public abstract class Container extends Element {

	protected ArrayList<Element> _elements = new ArrayList<Element>();

	public void addElement(Element e) {
		_elements.add(e);
	}
}