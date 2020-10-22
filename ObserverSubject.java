import java.util.ArrayList;

/*
 * subject class for observer pattern
 */
public class ObserverSubject {
	protected ArrayList<FileObserver> observers;
	
	public ObserverSubject() {
		observers = new ArrayList<FileObserver>();
	}
	
	public void attach(FileObserver o) {
		observers.add(o);
	}
	
	public void detach(FileObserver o) {
		observers.remove(o);
	}
	
	protected void notifyObservers() {
		for (int i = 0; i < observers.size(); i++) {
			observers.get(i).update();
		}
	}
}
