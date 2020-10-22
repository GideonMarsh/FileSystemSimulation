
/*
 * Visitor class for del command functionality
 */
public class DeleteVisitor implements Visitor {
	
	public void visitFile(LeafFile file) {
		file.getParent().getChild(file.getName()).delete();
	}
	
	public void visitDir(Directory dir) {
		for (int i = 0; i < dir.getChildren().size(); i++) {
			dir.getChildren().get(i).accept(this);
		}
		dir.getParent().getChild(dir.getName()).delete();

	}
}
