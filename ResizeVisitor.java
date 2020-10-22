
/*
 * Visitor class for resize command functionality
 * 
 * Also the concrete observer class for the observer pattern
 */
public class ResizeVisitor extends ObserverSubject implements Visitor {
	private int newSize;
	private boolean success;
	
	public ResizeVisitor() {
		success = false;
	}
	
	public void setNewSize(int newSize) {
		this.newSize = newSize;
	}
	
	public boolean resizeSuccessful() {
		return success;
	}
	
	public void visitFile(LeafFile file) {
		file.setSize(newSize);
		success = true;
		notifyObservers();
	}
	
	public void visitDir(Directory dir) {
		success = false;
	}
}
