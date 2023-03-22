package controller;


import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;

import controller.observer.OfferListObservable;
import controller.observer.OfferListObserver;
import dao.OfferDAO;
import model.Offer;
import model.tableModels.OfferTableModel;
import view.viewPanel.OffersPanel;


/**
 * 
 * @author ikojic000
 *
 *         The PonudeController class manages the functionality of the
 *         PonudePanel, including displaying, searching, deleting and viewing
 *         PDF files of offers. It implements the OfferListObserver interface,
 *         to get notified when the list of offers is updated.
 */
public class OffersController implements OfferListObserver {
	
	private ArrayList<Offer> offerList = new ArrayList<Offer>();
	private OffersPanel ponudePanel;
	private OfferDAO offerDAO;
	private OfferTableModel tableModel;
	
	/**
	 * 
	 * Constructs a new instance of the PonudeController class with the specified
	 * PonudePanel.
	 * 
	 * @param ponudePanel the panel to be managed by this controller
	 */
	public OffersController( OffersPanel ponudePanel ) {
		
		this.ponudePanel = ponudePanel;
		this.offerDAO = new OfferDAO();
		this.offerList = getAllOffersList();
		OfferListObservable.getInstance().addObserver( this );
		
	}
	
	
	/**
	 * 
	 * Returns the Offer object at the specified row index of the current
	 * tableModel.
	 * 
	 * @param row the row index of the selected offer
	 * @return the Offer object at the specified row index
	 */
	public Offer getSelectedOffer( int row ) {
		
		return tableModel.getOfferList().get( row );
		
	}
	
	
	/**
	 * 
	 * Retrieves all the offers from the database and updates the offerList.
	 * 
	 * @return the updated offerList
	 */
	private ArrayList<Offer> getAllOffersList() {
		
		offerList = offerDAO.getAllOffers();
		return offerList;
		
	}
	
	
	/**
	 * 
	 * Sets the table data to display the offerList using the OfferTableModel.
	 */
	public void setTableData() {
		
		tableModel = new OfferTableModel( offerList );
		ponudePanel.getTable().setModel( tableModel );
		
	}
	
	
	/**
	 * 
	 * Sets the table data to display only the offers matching the search query, or
	 * all offers
	 * 
	 * if the search field is empty.
	 */
	public void setSearchData() {
		
		String search = ponudePanel.getTxtSearch().getText();
		
		if ( search == null || search.isEmpty() ) {
			
			tableModel.setOfferList( offerList );
			
		} else {
			
			ArrayList<Offer> searchList = offerList.stream()
					.filter( o -> o.getTitle().toLowerCase().contains( search.toLowerCase() ) )
					.collect( Collectors.toCollection( ArrayList::new ) );
			
			tableModel.setOfferList( searchList );
			
		}
		
	}
	
	
	/**
	 * 
	 * Deletes the selected offer from the database and the file system, and updates
	 * the tableModel and offerList accordingly.
	 * 
	 * @param row the row index of the selected offer to be deleted
	 */
	public void deleteOffer( int row ) {
		
		Offer offer = getSelectedOffer( row );
		deleteFile( new File( offer.getUrl() ) );
		offerDAO.deleteOffer( offer );
		
		tableModel.getOfferList().remove( offer );
		
		offerList.remove( offer );
		tableModel.setOfferList( offerList );
		tableModel.fireTableDataChanged();
		
		ponudePanel.clearAll();
		
	}
	
	
	/**
	 * 
	 * Opens the PDF file of the selected offer using the default application
	 * registered to handle PDF files.
	 * 
	 * @param row the row index of the selected offer to be viewed
	 */
	public void viewPDF( int row ) {
		
		Offer offer = getSelectedOffer( row );
		
		try {
			
			Desktop.getDesktop().open( new File( offer.getUrl() ) );
			
		} catch ( IOException e ) {
			
			e.printStackTrace();
			
		}
		
	}
	
	
	/**
	 * 
	 * Deletes the given file from the file system, if it exists.
	 * 
	 * @param file The file to be deleted.
	 */
	public void deleteFile( File file ) {
		
		if ( file.exists() ) {
			
			file.delete();
			System.out.println( "File deleted successfully." );
			
		} else {
			
			System.out.println( "File not found." );
			
		}
		
	}
	
	
	/**
	 * 
	 * Updates the offer list by retrieving all offers from the DAO and notifying
	 * the table model to update its data.
	 */
	@Override
	public void updateOfferList() {
		
		this.offerList = getAllOffersList();
		tableModel.setOfferList( offerList );
		
	}
	
}
