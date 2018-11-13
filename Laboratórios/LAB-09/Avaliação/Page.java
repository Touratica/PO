import java.util.ArrayList;

public class Page extends Element {

	private ArrayList<Element> _elements = new ArrayList<Element>();

	public void addElement(Element e) {
		_elements.add(e);
	}

	@Override
	public String render(int lvl, String identation) {
		String txt = "";
		for (int i = 0; i < lvl; i++) {
			txt += identation;
		}
		txt += "<page>\n";
		for (Element e: _elements) {
			txt += e.render(lvl+1, identation) + "\n";
		}
		for (int i = 0; i < lvl; i++) {
			txt += identation;
		}
		txt += "</page>";
		return txt;
	}
}