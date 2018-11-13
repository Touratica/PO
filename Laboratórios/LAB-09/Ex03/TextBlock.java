public class TextBlock extends PageElement {

	private String _text;

	public TextBlock(String text) {
		_text = text;
	}

	@Override
	public String render() {
		return "<p>" + _text + "</p>";
	}
}