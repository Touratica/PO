public class Span extends Element {

	@Override
	public String render(int lvl, String identation) {
		String txt = "";
		for (int i = 0; i < lvl; i++) {
			txt += identation;
		}
		txt += "<span>TEXT</span>";
		return txt;
	}
}