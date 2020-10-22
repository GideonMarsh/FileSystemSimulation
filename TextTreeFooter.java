import java.io.PrintWriter;
/*
 * Concrete decorator class for adding footer to display
 */
public class TextTreeFooter extends TextTreeDecorator {

	public TextTreeFooter(PrintWriter out, TextTreeDisplay ttd) {
		super(out, ttd);
	}

	public void display() {
		ttd.display();
		displayFooter();
	}
	
	private void displayFooter() {
		out.println("File was resized successfully");
	}

}
