
/*
 * Abstract builder interface for FileComponent builder classes
 */
public interface FileComponentBuilder {
	void setParameters(FileSystem fs, String[] parameters);
	FileComponent getFileComponent();
}
