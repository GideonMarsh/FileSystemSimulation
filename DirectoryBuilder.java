
/*
 * Concrete builder class for creating directories
 * Automatically creates a proxy with the directory
 */
public class DirectoryBuilder implements FileComponentBuilder {
	String name;
	FileComponent parent;
	
	public DirectoryBuilder() {
		name = null;
		parent = null;
	}
	
	
	public void setParameters(FileSystem fs, String[] parameters) {
		name = parameters[1];
		parent = fs.getCurrentDir();
	}
	
	// Returns created component
	public FileComponent getFileComponent() {
		return new FileComponentProxy(new Directory(name, parent));
	}
}
