
import java.io.*;
import java.util.Scanner;

/*
 * Director for builder pattern and driver for whole project
 * 
 * Reads a plaintext script and runs commands on the file system
 * uses a builder object for creation of files and directories
 * 
 * The commands in the script must be spelled correctly, all lowercase, and delineated with spaces
 * 
 * SET INPUT AND OUTPUT FILE PATHS CORRECTLY BEFORE RUNNING PROGRAM
 * Program reads inputs from script file with name in INPUTFILENAME constant
 * and writes output to file with name in OUTPUTFILENAME constant
 */
public class ScriptReader {
	private static final String INPUTFILENAME = "script2.txt";
	private static final String OUTPUTFILENAME = "output.txt";
	
	// Reference to file system object (for pointer to current and root directories)
	private static FileSystem fs;
	private static PrintWriter out;
	private static TextTreeDisplay ttd;
	
	// The driver for the program
	public static void main(String[] args) {
		// Read and parse script, and output result
		try {
			out = new PrintWriter(new BufferedWriter(new FileWriter(OUTPUTFILENAME)));
			fs = FileSystem.instance();
			// Tree display with header and footer decorators
			// Prints the tree structure to the output file when the resize command is called
			ttd = new TextTreeHeader(out, new TextTreeFooter(out, new DirectoryTreeDisplay(out, fs)));
			parseScript(INPUTFILENAME);
		
			out.close();
		}
		catch (IOException e) {
			System.err.println("Error: could not write to file");
		}
	}
	
	// Function to read and parse script and execute commands
	public static void parseScript(String inFile) {
		Scanner in;
		
		// Create the resize visitor
		ResizeVisitor rsv = new ResizeVisitor();
		// Set the text tree display (with decorators) as an observer to the resize visitor
		rsv.attach(ttd);
		
		/*
		 * Other visitor objects should also be created here instead of in the switch statement
		 * I would have fixed this if I had more time
		 * The program works the way it is now, but fixing this would have been better design
		 */
		
		try {
			// Create scanner to read the script file
			in = new Scanner(new File(inFile));
			FileComponentBuilder fb;
			
			String line;
			String[] command;
			while (in.hasNextLine()) {
				line = in.nextLine();
				
				// Print command to output file for clarity
				out.print('\n' + fs.getCurrentDir().getName() + "> ");
				out.println(line);
				
				// Read line from script and parse it
				command = line.split("\\s");
				
				// All script commands must be accounted for in this switch statement
				switch(command[0]) {
				case "mkdir":
					// Uses builder pattern to create directory and proxy while hiding creation process
					fb = new DirectoryBuilder();
					fb.setParameters(fs, command);
					fs.getCurrentDir().add(fb.getFileComponent());
					out.println("Subdirectory \"" + command[1] + "\" created");
					break;
				case "create":
					// Uses builder pattern to create file and proxy while hiding creation process
					fb = new FileBuilder();
					fb.setParameters(fs, command);
					fs.getCurrentDir().add(fb.getFileComponent());
					out.println("File \"" + command[1] + "\" created");
					break;
				case "cd":
					if (command[1].contentEquals("..")) {
						// cd ..
						// If at root (or in error situation where parent directory is null)
						// current directory will be set to the root
						fs.setDir(fs.getCurrentDir().getParent());
						if (fs.getCurrentDir() == null) {
							fs.setDir(fs.getRoot());
						}
						out.println("Directory changed to " + fs.getCurrentDir().getName());
					}
					else {
						// cd subdirectory
						FileComponent newDir = fs.getCurrentDir().getChild(command[1]);
						if (newDir == null || newDir instanceof LeafFile) {
							out.println(command[1] + " is not a valid directory");
						}
						else {
							fs.setDir(newDir);
							out.println("Directory changed to " + command[1]);
						}
					}
					break;
				case "del":
					FileComponent delFile = fs.getCurrentDir().getChild(command[1]);
					if (delFile == null) {
						out.println(command[1] + " is not a valid file or directory");
					}
					else {
						delFile.accept(new DeleteVisitor());
						out.println(command[1] + " deleted");
					}
					break;
				case "size":
					FileComponent sizeFile = fs.getCurrentDir().getChild(command[1]);
					if (sizeFile == null) {
						out.println(command[1] + " is not a valid file or directory");
					}
					else {
						SizeVisitor sv = new SizeVisitor();
						sizeFile.accept(sv);
						out.println(sv.getTotalSize());
					}
					break;
				case "ls":
					FileComponent lsFile = fs.getCurrentDir();
					if (command.length > 1) {
						lsFile = fs.getCurrentDir().getChild(command[1]);
					}
					if (lsFile == null) {
						out.println(command[1] + " is not a valid file or directory");
					}
					else {
						LSVisitor lv = new LSVisitor();
						lsFile.accept(lv);
						for (int i = 0; i < lv.getOutput().size(); i++) {
							if (lv.getOutput().get(i).length > 1) {
								out.println(lv.getOutput().get(i)[0] + " " + lv.getOutput().get(i)[1]);
							}
							else {
								out.println(lv.getOutput().get(i)[0]);
							}
						}
					}
					break;
				case "resize":
					FileComponent ResizeFile = fs.getCurrentDir().getChild(command[1]);
					if (ResizeFile == null) {
						out.println(command[1] + " is not a valid file");
					}
					else {
						rsv.setNewSize(Integer.parseInt(command[2]));
						
						ResizeFile.accept(rsv);
						if (rsv.resizeSuccessful()) {
							out.println("Size of " + command[1] + " set to " + command[2]);
						}
						else {
							out.println(command[1] + " is not a valid file");
						}
					}
					break;
				case "exit":
					fs.getRoot().accept(new ExitVisitor());
					break;
				default: out.println(command[0] + " is not a valid command");
				}
			}
			
			in.close();
			System.out.println("Script read successfully");
		}
		catch (IOException e) {
			System.err.println("Error: could not read from script file");
		}
	}
}
