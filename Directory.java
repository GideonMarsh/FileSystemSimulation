
import java.util.ArrayList;

/*
 * Composite class for composite pattern
 * 
 * Each directory has attributes: name, parent, children
 * Children are stored in ArrayList
 */
public class Directory implements FileComponent {
	private String name;
	private FileComponent parent;
	private ArrayList<FileComponent> children;
	
	public Directory(String name, FileComponent parent) {
		this.name = name;
		this.parent = parent;
		children = new ArrayList<FileComponent>();
	}

	public String getName() {
		return name;
	}

	public FileComponent getParent() {
		return parent;
	}

	public FileComponent getChild(String name) {
		for (int i = 0; i < children.size(); i++) {
			if (children.get(i).getName() != null && children.get(i).getName().contentEquals(name)) {
				return children.get(i);
			}
		}
		return null;
	}

	public void add(FileComponent file) {
		children.add(file);
	}
	
	public void accept(Visitor v) {
		v.visitDir(this);
	}
	
	public void delete() {}

	// get arraylist of all children, used in visitor classes
	public ArrayList<FileComponent> getChildren() {
		return children;
	}
}
