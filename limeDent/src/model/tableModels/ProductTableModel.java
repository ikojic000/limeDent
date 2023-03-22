package model.tableModels;


import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import model.Product;


/**
 *
 * @author ikojic000
 *
 *         This class extends AbstractTableModel to provide a model for
 *         displaying data in a table for Product objects. It implements the
 *         necessary methods to define the structure of the table, as well as
 *         how the data is accessed and edited.
 *		
 */
public class ProductTableModel extends AbstractTableModel {
	
	private static final long serialVersionUID = 3231129840644420927L;
	private ArrayList<Product> productList;
	private String[] columnNames = { "ID" , "\u0160ifra" , "Naziv" , "Cijena" };
	boolean[] canEdit = new boolean[] { false , false , false , false };
	
	/**
	 *
	 * Constructs a ProductTableModel with a given ArrayList of Product objects.
	 *
	 * @param productList an ArrayList of Product objects to display in the table
	 */
	public ProductTableModel( ArrayList<Product> productList ) {
		
		this.productList = productList;
		
	}
	
	
	/**
	 *
	 * Returns the number of rows in the table, which is the size of the
	 * productList.
	 *
	 * @return the number of rows in the table
	 */
	@Override
	public int getRowCount() {
		
		return productList.size();
		
	}
	
	
	/**
	 *
	 * Returns the number of columns in the table, which is the length of the
	 * columnNames array.
	 *
	 * @return the number of columns in the table
	 */
	@Override
	public int getColumnCount() {
		
		return columnNames.length;
		
	}
	
	
	/**
	 *
	 * Returns the name of the specified column.
	 *
	 * @param column the index of the column to retrieve the name for
	 * @return the name of the specified column
	 */
	@Override
	public String getColumnName( int column ) {
		
		return columnNames[column];
		
	}
	
	
	/**
	 *
	 * Returns whether a cell is editable or not.
	 *
	 * @param rowIndex    the row index of the cell to check
	 * @param columnIndex the column index of the cell to check
	 * @return true if the cell is editable, false otherwise
	 */
	@Override
	public boolean isCellEditable( int rowIndex , int columnIndex ) {
		
		return canEdit[columnIndex];
		
	}
	
	
	/**
	 *
	 * Returns the value at the specified cell.
	 *
	 * @param rowIndex    the row index of the cell to retrieve the value from
	 * @param columnIndex the column index of the cell to retrieve the value from
	 * @return the value at the specified cell
	 */
	@Override
	public Object getValueAt( int rowIndex , int columnIndex ) {
		
		Product product = productList.get( rowIndex );
		
		switch (columnIndex) {
			
			case 0:
				return product.getId();
			
			case 1:
				return product.getCode();
			
			case 2:
				return product.getName();
			
			case 3:
				return product.getPrice();
			
			default:
				return null;
			
		}
		
	}
	
	
	/**
	 *
	 * Returns the list of Product objects stored in the table model.
	 *
	 * @return the list of Product objects stored in the table model
	 */
	public ArrayList<Product> getProductList() {
		
		return productList;
		
	}
	
	
	/**
	 *
	 * Sets the list of Product objects stored in the table model and fires a table
	 * data change event.
	 *
	 * @param productList - the list of Product objects to be set
	 */
	public void setProductList( ArrayList<Product> productList ) {
		
		this.productList = productList;
		fireTableDataChanged();
		
	}
	
}
