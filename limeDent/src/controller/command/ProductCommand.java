package controller.command;


import model.Product;


/**
 * @author ikojic000
 * 
 *         ProductCommand is an interface that represents a command for
 *         manipulating Product objects. It contains methods for executing,
 *         undoing and redoing the command, as well as getting the product
 *         information and the product itself.
 *		
 */
public interface ProductCommand {
	
	/**
	 * Executes the command.
	 */
	void execute();
	
	/**
	 * Undoes the command.
	 */
	void undo();
	
	/**
	 * Redoes the command.
	 */
	void redo();
	
	/**
	 * 
	 * Returns a String containing information about the Product object associated
	 * with this command.
	 * 
	 * @return a String containing the product information
	 */
	String getProductInfo();
	
	/**
	 * 
	 * Returns the Product object associated with this command.
	 * 
	 * @return the Product object
	 */
	Product getProduct();
	
}
