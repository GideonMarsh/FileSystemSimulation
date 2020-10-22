/*
 * Proxy class for directories and files
 * All files and directories are created with a proxy, and are only accessed by first requesting
 * the component through that proxy.
 * 
 * When a FileComponent is deleted, the proxy prevents further access to it, causing it to
 * appear invisible
 */
public class FileComponentProxy implements FileComponent {
	private FileComponent realFile;
	private boolean deleted;
	
	public FileComponentProxy(FileComponent realFile) {
		this.realFile = realFile;
		deleted = false;
	}

	public void accept(Visitor v) {
		if (!deleted) {
			realFile.accept(v);
		}
	}

	public String getName() {
		if (!deleted) {
			return realFile.getName();
		}
		return null;
	}

	public FileComponent getParent() {
		if (!deleted) {
			return realFile.getParent();
		}
		return null;
	}

	public FileComponent getChild(String name) {
		if (!deleted) {
			return realFile.getChild(name);
		}
		return null;
	}

	public void add(FileComponent file) {
		if (!deleted) {
			realFile.add(file);
		}
	}
	
	public void delete() {
		realFile.delete(); // Called for posterity, delete method does nothing except in proxy
		deleted = true;
	}

}
