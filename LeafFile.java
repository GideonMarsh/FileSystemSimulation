
/*
 * Leaf class for composite pattern
 * 
 * Each file has attributes: name, size, parent
 * LeafFiles cannot add, have, or remove children
 */
public class LeafFile implements FileComponent{
	private String name;
	private int size;
	private FileComponent parent;
	
	public LeafFile(String name, int size, FileComponent parent) {
		this.name = name;
		this.size = size;
		this.parent = parent;
	}
	
	public String getName() {
		return name;
	}
	
	public int getSize() {
		return size;
	}
	
	public void setSize(int newSize) {
		size = newSize;
	}
	
	public FileComponent getParent() {
		return parent;
	}
	
	public FileComponent getChild(String name) {
		return null;
	}
	
	public void add(FileComponent file) {}
	
	public void accept(Visitor v) {
		v.visitFile(this);
	}
	
	public void delete() {}
}
