public class Paragraph extends Container {

	@Override
	public String render(int lvl, String identation) {
		String txt = "";
		for (int i = 0; i < lvl; i++) {
			txt += identation;
		}
		txt += "<p>\n";
		for (Element e: _elements) {
			txt += e.render(lvl+1, identation) + "\n";
		}
		for (int i = 0; i < lvl; i++) {
			txt += identation;
		}
		txt += "</p>";
		return txt;
	}
}