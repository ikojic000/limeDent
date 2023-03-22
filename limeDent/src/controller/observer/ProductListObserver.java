package controller.observer;


/**
 *
 * @author ikojic000
 *
 *         ProductListObserver is an interface that represents an observer in
 *         the observer design pattern. It contains a method for updating the
 *         observer when the product list changes.
 *		
 */
public interface ProductListObserver {
	
	/**
	 *
	 * Updates the observer with the latest changes to the product list.
	 */
	void updateProductList();
	
}
