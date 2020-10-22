
/*
 * Visitor class for exit command functionality
 * 
 * Responsible for deleting all FileComponents whose proxy marks them as deleted
 * This is found by checking if a directory has children but those children aren't allowing
 * access to their names (all FileComponents must have a valid name)
 */
public class ExitVisitor implements Visitor {

	public void visitFile(LeafFile file) {
		
	}
	
	public void visitDir(Directory dir) {
		for (int i = 0; i < dir.getChildren().size(); i++) {
			dir.getChildren().get(i).accept(this);
			if (dir.getChildren().get(i).getName() == null) {
				dir.getChildren().remove(i);
			}
		}
	}
}
