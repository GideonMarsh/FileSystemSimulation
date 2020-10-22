import java.util.ArrayList;
import java.io.*;
/*
 * Displays a tree in text format
 * Builds the tree structure and can display it
 * Stores the structure as an ArrayList of Strings before displaying
 * 
 * Designed to work with the internal TreeNode class, needs adapter to work with our project
 * 
 * Also the concrete subject for the observer pattern
 * Also the component of the decorator pattern
 */
public class TextTreeDisplay implements FileObserver {
	
	/*
	 * The class that TextTreeDisplay usually uses to create its tree structure
	 * 
	 * By using the adapter pattern, we have our file system mimic this node object
	 * to build a tree for our file structure
	 */
	public static class TreeNode {
		public ArrayList<TreeNode> children;
		public ArrayList<String> attributes;
		
		public TreeNode(String name) {
			children = new ArrayList<TreeNode>();
			attributes = new ArrayList<String>();
			attributes.add(name);
		}
	}
	
	private ArrayList<String> textTree;
	protected PrintWriter out;
	protected TreeNode rootNode;
	
	public TextTreeDisplay(PrintWriter out, TreeNode rootNode) {
		this.out = out;
		this.rootNode = rootNode;
		textTree = new ArrayList<String>();
	}
	
	public void display() {
		buildTree();
		for (int i = 0; i < textTree.size(); i++) {
			out.println(textTree.get(i));
		}
	}
	
	private void buildTree() {
		textTree.clear();
		buildTreeRec(rootNode, "");
	}
	
	private void buildTreeRec(TreeNode n, String indent) {
		textTree.add(createTextNode(n, indent));
		for (int i = 0; i < getChildren(n).size(); i++) {
			buildTreeRec(getChildren(n).get(i), indent + "   ");
		}
	}
	
	protected String createTextNode(TreeNode n, String indent) {
		String line = n.attributes.get(0);
		for (int i = 1; i < n.attributes.size(); i++) {
			line = line + " " + n.attributes.get(i);
		}
		return indent + line;
	}
	
	protected ArrayList<TreeNode> getChildren(TreeNode n) {
		return n.children;
	}
	
	public void update() {
		display();
	}
	
	/*
	// Test Driver for TextTreeDisplay to verify it works on its own
	public static void main(String[] args) {
		PrintWriter out;
		String outFile = "TextTreeDisplayTest.txt";
		
		//Create TreeNode tree
		TreeNode a = new TreeNode("root");
		TreeNode b = new TreeNode("1");
		TreeNode c = new TreeNode("1-1");
		TreeNode d = new TreeNode("1-2");
		TreeNode e = new TreeNode("1-2-1");
		TreeNode f = new TreeNode("2");
		TreeNode g = new TreeNode("2-1");
		b.attributes.add("anotherAttribute");
		e.attributes.add("E");
		a.children.add(b);
		a.children.add(f);
		b.children.add(c);
		b.children.add(d);
		d.children.add(e);
		f.children.add(g);
		
		try {
			out = new PrintWriter(new BufferedWriter(new FileWriter(outFile)));
			
			//Create TextTreeDisplay object
			TextTreeDisplay tree = new TextTreeDisplay(out);
			tree.buildTree(a);
			tree.display();
			
			out.close();
			System.out.println("Test success");
		}
		catch (IOException io) {
			System.err.println("Test failed");
		}
	}*/
}
