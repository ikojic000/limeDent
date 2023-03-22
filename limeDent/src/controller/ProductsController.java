package controller;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Stack;
import java.util.stream.Collectors;

import controller.command.DeleteProductCommand;
import controller.command.ProductCommand;
import controller.observer.ProductListObservable;
import dao.ProductDAO;
import model.Product;
import model.tableModels.ProductTableModel;
import view.viewPanel.ProductsPanel;


/**
 * 
 * @author ikojic000
 *
 *         UslugeController class handles the business logic for the UslugePanel
 *         GUI. It manages the ArrayList of Product objects, the
 *         ProductTableModel for the JTable, the ProductDAO for database
 *         operations and the undoStack for undo functionality.
 */
public class ProductsController {
	
	private ArrayList<Product> productList = new ArrayList<Product>();
	private ProductTableModel tableModel;
	private ProductsPanel uslugePanel;
	private ProductDAO productDAO;
	private Stack<ProductCommand> undoStack = new Stack<ProductCommand>();
	
	/**
	 * 
	 * Constructor for UslugeController class that initializes UslugePanel and
	 * ProductDAO objects. It also gets the list of products from the database and
	 * sets it to the productList.
	 * 
	 * @param uslugePanel the UslugePanel GUI
	 */
	public ProductsController( ProductsPanel uslugePanel ) {
		
		this.uslugePanel = uslugePanel;
		this.productDAO = new ProductDAO();
		this.productList = getProductList();
		
	}
	
	
	/**
	 * 
	 * Method that returns the selected Product object from the table.
	 * 
	 * @param row the row index of the selected Product object
	 * @return the selected Product object
	 */
	public Product getSelectedProduct( int row ) {
		
		return tableModel.getProductList().get( row );
		
	}
	
	
	/**
	 * 
	 * Method that retrieves the list of products from the database using the
	 * ProductDAO object.
	 * 
	 * @return the list of products retrieved from the database
	 */
	private ArrayList<Product> getProductList() {
		
		productList = productDAO.getAllProducts();
		return productList;
		
	}
	
	
	/**
	 * 
	 * Method that sets the table data to the ProductTableModel and displays it in
	 * the JTable.
	 */
	public void setTableData() {
		
		tableModel = new ProductTableModel( productList );
		uslugePanel.getTable().setModel( tableModel );
		
	}
	
	
	/**
	 * 
	 * Method that filters the table data based on the search string entered by the
	 * user. If the search string is empty or null, it displays all the products in
	 * the table.
	 */
	public void setSearchData() {
		
		String search = uslugePanel.getTxtSearch().getText();
		
		if ( search == null || search.isEmpty() ) {
			
			tableModel.setProductList( productList );
			
		} else {
			
			ArrayList<Product> searchList = productList.stream()
					.filter( p -> p.getName().toLowerCase().contains( search.toLowerCase() )
							|| p.getCode().toString().toLowerCase().contains( search.toLowerCase() ) )
					.collect( Collectors.toCollection( ArrayList::new ) );
			
			tableModel.setProductList( searchList );
			
		}
		
	}
	
	
	/**
	 * 
	 * Method that adds the new product to the database using the ProductDAO object.
	 * It also updates the productList and tableModel, clears the fields in the GUI
	 * and scrolls the JTable to display the newly added product.
	 * 
	 * @param product the new Product object to be added to the database
	 */
	public void addProduct( Product product ) {
		
		productDAO.addProduct( product );
		
		getProductList();
		
		setTableData();
		
		uslugePanel.clearAll();
		
		uslugePanel.getTable().scrollRectToVisible(
				uslugePanel.getTable().getCellRect( uslugePanel.getTable().getRowCount() - 1 , 0 , true ) );
		
		ProductListObservable.getInstance().notifyObservers();
		
	}
	
	
	/**
	 * 
	 * Deletes the product at the specified row in the table. Removes the product
	 * from the product list and updates the table data. Also creates a command
	 * object to undo the deletion and adds it to the undo stack.
	 * 
	 * @param row the row index of the product to delete
	 */
	public void deleteProduct( int row ) {
		
		Product product = getSelectedProduct( row );
		
		ProductCommand deleteProductCommand = new DeleteProductCommand( product );
		deleteProductCommand.execute();
		undoStack.push( deleteProductCommand );
		
		tableModel.getProductList().remove( product );
		productList.remove( product );
		tableModel.setProductList( productList );
		tableModel.fireTableDataChanged();
		
		ProductListObservable.getInstance().notifyObservers();
		
		uslugePanel.clearAll();
		
	}
	
	
	/**
	 * 
	 * Updates the product at the specified row in the table with the values entered
	 * in the UI. Sets the new name and price of the product and updates the
	 * database. Also updates the table data to show the updated product
	 * information.
	 * 
	 * @param row the row index of the product to update
	 */
	public void updateProduct( int row ) {
		
		Product product = getSelectedProduct( row );
		
		product.setName( uslugePanel.getTxtNazivArtikla().getText() );
		product.setPrice( BigDecimal
				.valueOf( Double.parseDouble( uslugePanel.getTxtCijenaArtikla().getText().replace( "," , "." ) ) ) );
		
		productDAO.updateProduct( product );
		tableModel.setProductList( productList );
		tableModel.fireTableDataChanged();
		
		ProductListObservable.getInstance().notifyObservers();
		
		uslugePanel.clearAll();
		
	}
	
	
	/**
	 * 
	 * Undoes the most recent product deletion by executing the corresponding undo
	 * command object. Adds the deleted product back to the product list and updates
	 * the table data. If there are no more undo commands in the undo stack, returns
	 * a message indicating that there is nothing to undo.
	 * 
	 * @return a message indicating whether the undo was successful or not
	 */
	public String undoCommandAction() {
		
		String data = "";
		
		if ( !undoStack.isEmpty() ) {
			
			ProductCommand command = undoStack.pop();
			command.undo();
			
			data = "Vratili ste izbrisanu uslugu - " + command.getProductInfo();
			
			productList.add( command.getProduct() );
			tableModel.fireTableDataChanged();
			uslugePanel.clearAll();
			
			uslugePanel.getTable().scrollRectToVisible(
					uslugePanel.getTable().getCellRect( uslugePanel.getTable().getRowCount() - 1 , 0 , true ) );
			
		} else {
			
			data = "Nemate što poništiti...";
			
		}
		
		return data;
		
	}
	
}
