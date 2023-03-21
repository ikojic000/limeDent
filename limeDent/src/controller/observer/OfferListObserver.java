package controller.observer;


/**
 * @author ikojic000
 * 
 *         OfferListObserver is an interface that represents an observer in the
 *         observer design pattern. It contains a method for updating the
 *         observer when the offer list changes.
 * 		
 */
public interface OfferListObserver {
	
	/**
	 * Updates the observer with the latest changes to the offer list.
	 */
	void updateOfferList();
	
}
