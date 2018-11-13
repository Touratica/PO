public class Page extends Container {

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