
/*
 * Concrete builder class for creating files
 * 
 * Automatically creates a proxy for the file
 */
public class FileBuilder implements FileComponentBuilder{
	String name;
	int size;
	FileComponent parent;
	
	public FileBuilder() {
		name = null;
		size = 0;
		parent = null;
	}
	
	public void setParameters(FileSystem fs, String[] parameters) {
		name = parameters[1];
		size = Integer.parseInt(parameters[2]);
		parent = fs.getCurrentDir();
	}
	
	// Returns created component
	public FileComponent getFileComponent() {
		return new FileComponentProxy(new LeafFile(name, size, parent));
	}
}
