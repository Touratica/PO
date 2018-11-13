public class Image extends Element {

	@Override
	public  String render(int lvl, String identation) {
		String txt = "";
		for (int i = 0; i < lvl; i++) {
			txt += identation;
		}
		txt += "<img src=\"IMG\"/>";
		return txt;
	}
}