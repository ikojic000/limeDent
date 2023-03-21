package controller.observer;


import java.util.ArrayList;


/**
 * 
 * @author ikojic000
 * 
 *         OfferListObservable is a class that serves as an observable object in
 *         the observer design pattern. It allows observers to register
 *         themselves and receive notifications when there is a change to the
 *         offer list.
 *
 */
public class OfferListObservable {
	
	private static OfferListObservable instance;
	private ArrayList<OfferListObserver> observers = new ArrayList<>();
	
	/**
	 * Constructs a new OfferListObservable object.
	 */
	private OfferListObservable() {
		
	}
	
	
	/**
	 * 
	 * Returns the singleton instance of OfferListObservable, creating it if it does
	 * not exist.
	 * 
	 * @return the singleton instance of OfferListObservable
	 */
	public static synchronized OfferListObservable getInstance() {
		
		if ( instance == null ) {
			
			instance = new OfferListObservable();
			
		}
		
		return instance;
		
	}
	
	
	/**
	 * 
	 * Registers an observer with the OfferListObservable.
	 * 
	 * @param observer the observer to be registered
	 */
	public void addObserver( OfferListObserver observer ) {
		
		observers.add( observer );
		
	}
	
	
	/**
	 * 
	 * Unregisters an observer from the OfferListObservable.
	 * 
	 * @param observer the observer to be unregistered
	 */
	public void removeObserver( OfferListObserver observer ) {
		
		observers.remove( observer );
		
	}
	
	
	/**
	 * 
	 * Notifies all registered observers that there has been a change to the offer
	 * list.
	 */
	public void notifyObservers() {
		
		for ( OfferListObserver observer : observers ) {
			
			observer.updateOfferList();
			
		}
		
	}
	
}
