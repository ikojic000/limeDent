package model.tableModels;


import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import controller.observer.OfferTblPreviewObserver;
import controller.observer.PatientListObserver;
import model.OfferTblPreviewData;


/**
 * 
 * @author ikojic000
 * 
 *         A model for the preview table of an offer that extends
 *         AbstractTableModel. This class defines the data and functionality for
 *         the preview table that displays the product name, price, quantity,
 *         total price, discount, and total price with discount of each product
 *         in the offer. The class implements the necessary methods from the
 *         AbstractTableModel class to define the structure and data of the
 *         table.
 *
 */
public class OfferTblPreviewModel extends AbstractTableModel {
	
	private static final long serialVersionUID = 9069481942874598372L;
	private ArrayList<OfferTblPreviewData> tblPreviewData;
	private String[] columnNames = { "Naziv" , "Cijena" , "Količina" , "Ukupno" , "Popust" , "Ukupno s popustom" };
	boolean[] canEdit = new boolean[] { false , false , true , false , true , false };
	private ArrayList<OfferTblPreviewObserver> observers = new ArrayList<OfferTblPreviewObserver>();
	
	/**
	 * 
	 * Constructs an OfferTblPreviewModel object with the given table preview data.
	 * 
	 * @param tblPreviewData an ArrayList of OfferTblPreviewData objects that
	 *                       represent the data for each row in the preview table
	 */
	public OfferTblPreviewModel( ArrayList<OfferTblPreviewData> tblPreviewData ) {
		
		this.tblPreviewData = tblPreviewData;
		
	}
	
	
	/**
	 * 
	 * Returns the number of rows in the table, which is equal to the size of
	 * tblPreviewData.
	 * 
	 * @return the number of rows in the table
	 */
	@Override
	public int getRowCount() {
		
		return tblPreviewData.size();
		
	}
	
	
	/**
	 * 
	 * Returns the number of columns in the table, which is equal to the length of
	 * columnNames.
	 * 
	 * @return the number of columns in the table
	 */
	@Override
	public int getColumnCount() {
		
		return columnNames.length;
		
	}
	
	
	/**
	 * 
	 * Returns the name of the column at the specified index.
	 * 
	 * @param column the index of the column
	 * @return the name of the column at the specified index
	 */
	@Override
	public String getColumnName( int column ) {
		
		return columnNames[column];
		
	}
	
	
	/**
	 * 
	 * Returns whether a cell at the specified row and column is editable.
	 * 
	 * @param rowIndex    the row index of the cell
	 * @param columnIndex the column index of the cell
	 * @return true if the cell is editable, false otherwise
	 */
	@Override
	public boolean isCellEditable( int rowIndex , int columnIndex ) {
		
		return canEdit[columnIndex];
		
	}
	
	
	/**
	 * 
	 * Returns the class of the object in the specified column, which is the class
	 * of the object returned by getValueAt(0, columnIndex).
	 * 
	 * @param columnIndex the index of the column
	 * @return the class of the object in the specified column
	 */
	@Override
	public Class<?> getColumnClass( int columnIndex ) {
		
		return getValueAt( 0 , columnIndex ).getClass();
		
	}
	
	
	/**
	 * 
	 * Returns the value of the cell at the specified row and column.
	 * 
	 * @param rowIndex    the row index of the cell
	 * 
	 * @param columnIndex the column index of the cell
	 * 
	 * @return the value of the cell at the specified row and column
	 */
	@Override
	public Object getValueAt( int rowIndex , int columnIndex ) {
		
		OfferTblPreviewData data = tblPreviewData.get( rowIndex );
		
		switch (columnIndex) {
			
			case 0:
				return data.getProduct().getName();
			
			case 1:
				return data.getProduct().getPrice();
			
			case 2:
				return data.getPieces();
			
			case 3:
				return data.getTotal();
			
			case 4:
				return data.getDiscount();
			
			case 5:
				return data.getTotalWithDiscount();
			
			default:
				return null;
			
		}
		
	}
	
	
	/**
	 * 
	 * Sets the value of the cell at the specified row and column indexes in the
	 * table preview model. If the specified column is 2, the pieces value of the
	 * corresponding OfferTblPreviewData object in the model is updated with the
	 * integer value cast from the specified Object value, and the table cell is
	 * updated. If the specified column is 4, the discount value of the
	 * corresponding OfferTblPreviewData object in the model is updated with the
	 * integer value cast from the specified Object value, and the table cell is
	 * updated. If the specified column is not 2 or 4, an IllegalArgumentException
	 * is thrown.
	 * 
	 * @param value       the value to set
	 * @param rowIndex    the row index of the cell
	 * @param columnIndex the column index of the cell
	 * @throws IllegalArgumentException if the specified column index is invalid
	 */
	@Override
	public void setValueAt( Object value , int rowIndex , int columnIndex ) {
		
		OfferTblPreviewData data = tblPreviewData.get( rowIndex );
		
		switch (columnIndex) {
			
			case 2:
				data.setPieces( (int) value );
				fireTableCellUpdated( rowIndex , columnIndex );
				break;
			
			case 4:
				data.setDiscount( (int) value );
				fireTableCellUpdated( rowIndex , columnIndex );
				break;
			
			default:
				throw new IllegalArgumentException( "Invalid column index" );
			
		}
		
		fireTableCellUpdated( rowIndex , columnIndex );
		notifyObservers();
		
	}
	
	
	/**
	 * 
	 * Registers an observer.
	 * 
	 * @param observer the observer to be registered
	 */
	public void addObserver( OfferTblPreviewObserver observer ) {
		
		observers.add( observer );
		
	}
	
	
	/**
	 * 
	 * Unregisters an observer.
	 * 
	 * @param observer the observer to be unregistered
	 */
	public void removeObserver( OfferTblPreviewObserver observer ) {
		
		observers.remove( observer );
		
	}
	
	
	/**
	 * 
	 * Notifies all registered observers that there has been a change to the offer
	 * tblPreview model.
	 */
	public void notifyObservers() {
		
		for ( OfferTblPreviewObserver observer : observers ) {
			
			observer.tableModelDataChanged();
			
		}
		
	}
	
}
