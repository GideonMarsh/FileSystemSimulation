import java.io.PrintWriter;
import java.util.ArrayList;

/*
 * Adapter class
 * Also the concrete component of the decorator pattern
 * 
 * Allows file system structure to be displayed using TextTreeDisplay class
 * 
 * Changes the createTextNode and getChildren methods to use the directory file structure
 * "Tricks" those methods into believing they still use the TreeNode class
 */
public class DirectoryTreeDisplay extends TextTreeDisplay{
	private FileSystem fs;
	private FileComponent currentFile;
	
	public DirectoryTreeDisplay(PrintWriter out, FileSystem fs) {
		super(out, new TreeNode(fs.getRoot().getName()));
		this.fs = fs;
		currentFile = fs.getRoot();
	}
	
	protected String createTextNode(TreeNode n, String indent) {
		// set currentFile to node with same name as n
		// if node isn't a child of currentDir, go to parent directory and check siblings
		// loop until node with that name found (if none found then error)
		boolean isRoot = false;
		while (currentFile.getChild(n.attributes.get(0)) == null) {
			currentFile = currentFile.getParent();
			// If we've gone all the way up the file tree, check if we're looking for the root
			if (currentFile == null && n.attributes.get(0).contentEquals(fs.getRoot().getName())) {
				currentFile = fs.getRoot();
				isRoot = true;
				break;
			}
		}
		if (!isRoot) {
			currentFile = currentFile.getChild(n.attributes.get(0));
		}
		
		String line = currentFile.getName();
		SizeVisitor sv = new SizeVisitor();
		currentFile.accept(sv);
		line = line + " " + sv.getTotalSize();
		return indent + line;
	}
	
	protected ArrayList<TreeNode> getChildren(TreeNode n) {
		// Make sure currentFile is set correctly
		while (! currentFile.getName().contentEquals(n.attributes.get(0))) {
			currentFile = currentFile.getParent();
		}
		
		// Return TreeNode arrayList with names of children of currentFile
		ArrayList<TreeNode> children = new ArrayList<TreeNode>();
		LSVisitor lv = new LSVisitor();
		currentFile.accept(lv);
		
		// If currentFile is a LeafFile the only result of ls is itself, so no children
		// Also, if currentFile is empty directory, no children
		if (! (lv.getOutput().size() == 0) && ! lv.getOutput().get(0)[0].contentEquals(currentFile.getName())) {
			// If currentFile is a Directory, fill the children arrayList with the names of its children
			for (int i = 0; i < lv.getOutput().size(); i++) {
				children.add(new TreeNode(lv.getOutput().get(i)[0]));
			}
		}
		
		// if currentFile is a LeafFile, children will be empty
		return children;
	}
}
