# FileSystemSimulation
A simulation of a file system that utilizes multiple design patterns and reads commands from a text script

The file Project1UML.png shows the class relationships and the design patterns they utilize

Here are the design patterns that are used and their purpose:

Singleton pattern; used in FileSystem.java
Ensures that only one FileSystem object can be created and that all other classes reference this single object

Composite pattern; used in FileComponent.java, LeafFile.java, Directory.java, and FileComponentProxy.java
Allows for the components of the file system to be created in an arbitrarily shaped tree structure using polymorphism. FileComponent.java is the interface for file components. LeafFile.java and Directory.java implement this interface and serve as the files and folders of the file system respectively. LeafFiles are the leaves of the tree, and Directories contain other FileComponents (either leaf files or directories). FileComponentProxy.java serves as the proxy for leaf files and directories (see proxy pattern below)

Visitor pattern; used in Visitor.java, DeleteVisitor.java, SizeVisitor.java, LSVisitor.java, ExitVisitor.java, and ResizeVisitor.java
Allows for the operations of the file system to be independent of the file system objects. Each visitor class represents one of the operations that the file system can perform on file components. File components contain a method accept(Visitor) which accepts an arbitrary visitor object and calls the visitor object's visit method for this type of file. Visitor objects have different visit methods for the different file components (leaf files and directories) which perform different operations on them based on the type of visitor object it is. Visitor.java is the interface for visitor classes. DeleteVisitor.java, SizeVisitor.java, LSVisitor.java, ExitVisitor.java, and ResizeVisitor.java implement this interface and perform delete, get size, ls, exit, and resize operations respectively. Doing this separates the file system operations from the file system objects, which heightens cohesion and lowers coupling, making adding new operations or file system component types in the future much easier.

Builder pattern; used in ScriptReader.java, FileComponentBuilder.java, FileBuilder.java, and DirectoryBuilder.java
Allows for the file system to be created in an arbitrary structure based on the script. FileComponentBuilder.java is the interface for the builder classes. FileBuilder.java and DirectoryBuilder.java implement this interface and are used to create LeafFiles and Directories respectively. Hides the creation process of the file components for low coupling, making changing the creation process in the future much easier.

Proxy pattern; used in FileComponentProxy.java
Allows for each file component object to be referenced by a proxy. Used when the delete operation is performed; instead of immediately deleting the file component object, the proxy simply cuts off access to its object. The file system continues functioning as if the object was deleted, but the actual deletion is saved for after the script is completed. This prevents the costly delete operation from being performed while the script is being read, and prevents the file system structure from needing to be rearranged during run time. A proxy object is automatically created for each file component object in the builder classes.

Observer pattern; used in ObserverSubject.java, FileObserver.java, ResizeVisitor.java, and TextTreeDisplay.java
Allows for certain classes to maintain a list of observer objects that detect changes in the observed class and automatically react. ObserverSubject.java serves as the interface for subjects of observation, and FileObserver.java is the interface for classes that observe those subjects. ResizeVisitor.java is the concrete subject that is being observed, and TextTreeDisplay.java is the concrete observer. When the ResizeVisitor object is used to perform the resize operation, TextTreeDisplay automatically detects this change and redisplays the tree. Increases cohesion and lowers coupling, making it easier to modify the observers and subjects independently of one another or to add new types of subjects and observers. 

Decorator pattern; used in TextTreeDisplay.java, DirectoryTreeDisplay.java, TextTreeDecorator.java, TextTreeHeader.java, and TextTreeFooter.java
Allows for the display of a tree structure to add arbitrary display elements without changing the base display. TextTreeDisplay.java serves as the main class for displaying a tree structure in text form. DirectoryTreeDisplay.java is used to adapt TextTreeDisplay for use with the file system (see Adapter pattern below). TextTreeDecorator is the interface for decorator objects. TextTreeHeader.java and TextTreeFooter.java implement this interface and specify the decorator classes for displaying a header and footer respectively. This separates different display elements, increasing cohesion and allowing for new display elements to be created easily. Also allows for different applications to use different display elements as needed.

Adapter pattern; used in DirectoryTreeDisplay.java and FileSystem.java
Allows for a class that isn't designed to work with another class to be compatible. The TextTreeDisplay class is designed to work with its internal TreeNode class, and is incompatible with our file system. DirectoryTreeDisplay.java serves as the adapter for FileSystem.java (the adaptee) so that TextTreeDisplay can display the file system using its preexisting methods. 

#### Created for an Advanced Software Engineering course. Received commendation and high marks from professor.
"I haven't seen such an outstanding and clean source code for a long while. Great job! Anytime you need a rec letter, remind me about your project." - Dr. Shih-Hsi Liu, CSU Fresno Computer Science Department
