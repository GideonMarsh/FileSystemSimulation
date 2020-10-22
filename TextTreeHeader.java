import java.io.PrintWriter;
/*
 * Concrete decorator class for adding header to display
 */
public class TextTreeHeader extends TextTreeDecorator {

	public TextTreeHeader(PrintWriter out, TextTreeDisplay ttd) {
		super(out, ttd);
	}

	public void display() {
		displayHeader();
		ttd.display();
	}
	
	private void displayHeader() {
		out.println("This is the new file tree structure");
	}

}
