
/*
 * Singleton class to manage the file system
 * Keeps track of the root directory and the current directory
 * 
 * Also the adaptee of the adapter pattern
 */
public class FileSystem {
	private static FileSystem fs;
	
	// Pointer to root directory - should not change
	private FileComponent root;
	
	// Pointer to current directory
	private FileComponent currentDirectory;
	
	public FileComponent getRoot() {
		return root;
	}
	
	public FileComponent getCurrentDir() {
		return currentDirectory;
	}
	
	public void setDir(FileComponent dir) {
		currentDirectory = dir;
	}
	
	// When file system created, root is instantiated as empty directory
	// and current directory is set to root
	private FileSystem() {
		root = currentDirectory = new FileComponentProxy(new Directory("root", null));
	}
	
	// instance method for singleton pattern
	public static FileSystem instance() {
		if (fs == null) {
			fs = new FileSystem();
		}
		return fs;
	}
}
