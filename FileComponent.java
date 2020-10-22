/*
 * Component class interface for composite pattern
 * 
 * Outlines shared interface for file and directory objects
 */
public interface FileComponent {
	void accept(Visitor v);
	String getName();
	FileComponent getParent();
	FileComponent getChild(String name);
	void add(FileComponent file);
	void delete();
}
