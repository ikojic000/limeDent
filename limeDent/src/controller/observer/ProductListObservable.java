package controller.observer;


import java.util.ArrayList;


/**
 *
 * @author ikojic000
 *
 *         ProductListObservable is a class that serves as an observable object
 *         in the observer design pattern. It allows observers to register
 *         themselves and receive notifications when there is a change to the
 *         product list.
 *
 */
public class ProductListObservable {
	
	private static ProductListObservable instance;
	private ArrayList<ProductListObserver> observers = new ArrayList<>();
	
	/**
	 *
	 * Constructs a new ProductListObservable object.
	 */
	private ProductListObservable() {
		
	}
	
	
	/**
	 *
	 * Returns the singleton instance of ProductListObservable, creating it if it
	 * does not exist.
	 *
	 * @return the singleton instance of ProductListObservable
	 */
	public static synchronized ProductListObservable getInstance() {
		
		if ( instance == null ) {
			
			instance = new ProductListObservable();
			
		}
		
		return instance;
		
	}
	
	
	/**
	 *
	 * Registers an observer with the ProductListObservable.
	 *
	 * @param observer the observer to be registered
	 */
	public void addObserver( ProductListObserver observer ) {
		
		observers.add( observer );
		
	}
	
	
	/**
	 *
	 * Unregisters an observer from the ProductListObservable.
	 *
	 * @param observer the observer to be unregistered
	 */
	public void removeObserver( ProductListObserver observer ) {
		
		observers.remove( observer );
		
	}
	
	
	/**
	 *
	 * Notifies all registered observers that there has been a change to the product
	 * list.
	 */
	public void notifyObservers() {
		
		for ( ProductListObserver observer : observers ) {
			
			observer.updateProductList();
			
		}
		
	}
	
}
