/**
 * TextFormat
 */
public abstract class TextFormat implements TextItem {

	private TextItem _textItem;

	/**
	 * The TextFormat contructor.
	 * @param textItem
	 */
	public TextFormat(TextItem textItem) {
		_textItem = textItem;
	}

	/**
	 * @return the text item
	 */
	public TextItem getTextItem() {
		return _textItem;
	}

	@Override
	public String render() {
		return _textItem.render();
	}
}