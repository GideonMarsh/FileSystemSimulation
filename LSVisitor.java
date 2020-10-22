import java.util.ArrayList;
/*
 * Visitor class for ls command functionality
 * 
 * stores ls output as an array of strings
 * Files are arrays with 2 elements: name and size
 * Directories are arrays with 1 element: name
 */
public class LSVisitor implements Visitor {
	private ArrayList<String[]> output;
	private boolean showChildren;
	
	public LSVisitor() {
		output = new ArrayList<String[]>();
		showChildren = true;
	}
	
	public ArrayList<String[]> getOutput() {
		return output;
	}
	
	public void visitFile(LeafFile file) {
		String[] s = {file.getName(), "" + file.getSize()};
		output.add(s);
	}
	
	public void visitDir(Directory dir) {
		if (showChildren) {
			showChildren = false;
			for (int i = 0; i < dir.getChildren().size(); i++) {
				dir.getChildren().get(i).accept(this);
			}
		}
		else {
			String[] s = {dir.getName()};
			output.add(s);
		}
	}
}
