import java.util.ArrayList;

public class Figure extends PageElement {

	ArrayList<Image> _images = new ArrayList<Image>();
	TextBlock _caption = new TextBlock("");

	public addImage(Image img) {
		_images.add(img);
	}

	public addCaption(String str) {
		_caption = str;
	}

	@Override
	public String render() {
		String text = "<figure>\n\t";
		for (Image image: _images) {
			text += image.render() + "\n\t";
		}
		text += _caption.render() + "\n";
		text += "</figure>";
		return text;
	}
}