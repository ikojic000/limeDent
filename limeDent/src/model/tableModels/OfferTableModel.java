package model.tableModels;


import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import model.Offer;


/**
 *
 * @author ikojic000
 *
 *         The OfferTableModel class represents the table model for the Offer
 *         table in the UI. It extends the AbstractTableModel class and
 *         implements the methods required for the table model. It stores and
 *         manipulates the data related to the Offer table and displays it in
 *         the UI.
 *
 */
public class OfferTableModel extends AbstractTableModel {
	
	private static final long serialVersionUID = 7732894721444911556L;
	private ArrayList<Offer> offerList;
	private String[] columnNames = { "\u0160ifra" , "Naziv" , "Autor" , "Pacijent" , "Datum" , "" };
	boolean[] canEdit = new boolean[] { false , false , false , false , false , true };
	
	/**
	 *
	 * Constructs an OfferTableModel object with the given list of Offer objects.
	 *
	 * @param offerList - the list of Offer objects to be displayed in the table
	 */
	public OfferTableModel( ArrayList<Offer> offerList ) {
		
		this.offerList = offerList;
		
	}
	
	
	/**
	 *
	 * Returns the number of rows in the table.
	 *
	 * @return the number of rows in the table
	 */
	@Override
	public int getRowCount() {
		
		return offerList.size();
		
	}
	
	
	/**
	 *
	 * Returns the number of columns in the table.
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
	 * @param column the index of the column
	 * @return the name of the specified column
	 */
	@Override
	public String getColumnName( int column ) {
		
		return columnNames[column];
		
	}
	
	
	/**
	 *
	 * Returns a boolean value indicating whether the specified cell is editable.
	 *
	 * @param rowIndex    the row index of the cell
	 * @param columnIndex the column index of the cell
	 * @return true if the specified cell is editable, false otherwise
	 */
	@Override
	public boolean isCellEditable( int rowIndex , int columnIndex ) {
		
		return canEdit[columnIndex];
		
	}
	
	
	/**
	 *
	 * Returns the value at the specified cell.
	 *
	 * @param rowIndex    the row index of the cell
	 * @param columnIndex the column index of the cell
	 * @return the value at the specified cell
	 */
	@Override
	public Object getValueAt( int rowIndex , int columnIndex ) {
		
		Offer offer = offerList.get( rowIndex );
		
		switch (columnIndex) {
			
			case 0:
				return offer.getId();
			
			case 1:
				return offer.getTitle();
			
			case 2:
				return offer.getAuthorName();
			
			case 3:
				return offer.getPatientName();
			
			case 4:
				return offer.getDateFormatted();
			
			default:
				return null;
			
		}
		
	}
	
	
	/**
	 *
	 * Returns the list of Offer objects stored in the table model.
	 *
	 * @return the list of Offer objects stored in the table model
	 */
	public ArrayList<Offer> getOfferList() {
		
		return offerList;
		
	}
	
	
	/**
	 *
	 * Sets the list of Offer objects stored in the table model and fires a table
	 * data change event.
	 *
	 * @param offerList - the list of Offer objects to be set
	 */
	public void setOfferList( ArrayList<Offer> offerList ) {
		
		this.offerList = offerList;
		fireTableDataChanged();
		
	}
	
}
