import java.io.PrintWriter;

/*
 * Decorator interface for concrete decorator classes
 */
public abstract class TextTreeDecorator extends TextTreeDisplay {
	protected TextTreeDisplay ttd;

	public TextTreeDecorator(PrintWriter out, TextTreeDisplay ttd) {
		super(out, new TreeNode(""));
		this.ttd = ttd;
	}
	
	public abstract void display();

}
