public class App {

	public static final String IDENTATION = "  ";
	public static void main(String[] args) {
		Page page = new Page();
		Paragraph p1 = new Paragraph();
		Paragraph p2 = new Paragraph();
		Span s1 = new Span();
		Span s2 = new Span();
		Image img = new Image();

		p1.addElement(s1);
		p1.addElement(s2);
		p2.addElement(img);
		page.addElement(p1);
		page.addElement(p2);

		System.out.println(page.render(0, IDENTATION));
	}
}