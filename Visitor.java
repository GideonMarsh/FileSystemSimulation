
/*
 * Interface for visitor pattern classes
 */
public interface Visitor {
	void visitFile(LeafFile file);
	void visitDir(Directory dir);
}
