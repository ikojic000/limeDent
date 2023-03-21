package controller.observer;


import java.util.ArrayList;


/**
 * @author ikojic000
 * 
 *         PatientListObservable is a class that serves as an observable object
 *         in the observer design pattern. It allows observers to register
 *         themselves and receive notifications when there is a change to the
 *         patient list.
 * 
 */
public class PatientListObservable {
	
	private static PatientListObservable instance;
	private ArrayList<PatientListObserver> observers = new ArrayList<>();
	
	/**
	 * Constructs a new PatientListObservable object.
	 */
	private PatientListObservable() {
		
	}
	
	
	/**
	 * Returns the singleton instance of PatientListObservable, creating it if it
	 * does not exist.
	 * 
	 * @return the singleton instance of PatientListObservable
	 */
	public static synchronized PatientListObservable getInstance() {
		
		if ( instance == null ) {
			
			instance = new PatientListObservable();
			
		}
		
		return instance;
		
	}
	
	
	/**
	 * Registers an observer with the PatientListObservable.
	 * 
	 * @param observer the observer to be registered
	 */
	public void addObserver( PatientListObserver observer ) {
		
		observers.add( observer );
		
	}
	
	
	/**
	 * Unregisters an observer from the PatientListObservable.
	 * 
	 * @param observer the observer to be unregistered
	 */
	public void removeObserver( PatientListObserver observer ) {
		
		observers.remove( observer );
		
	}
	
	
	/**
	 * Notifies all registered observers that there has been a change to the patient
	 * list.
	 */
	public void notifyObservers() {
		
		for ( PatientListObserver observer : observers ) {
			
			observer.updatePatientList();
			
		}
		
	}
	
}
