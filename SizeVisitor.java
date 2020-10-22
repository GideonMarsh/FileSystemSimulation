
/*
 * Visitor class for size command functionality
 */
public class SizeVisitor implements Visitor {
	private int totalSize;
	
	public SizeVisitor() {
		totalSize = 0;
	}
	
	public int getTotalSize() {
		return totalSize;
	}
	
	public void visitFile(LeafFile file) {
		totalSize += file.getSize();
	}
	
	public void visitDir(Directory dir) {
		for (int i = 0; i < dir.getChildren().size(); i++) {
			dir.getChildren().get(i).accept(this);
		}
	}
}
