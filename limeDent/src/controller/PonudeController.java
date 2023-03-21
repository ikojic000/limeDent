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
import view.viewPanel.PonudePanel;


public class PonudeController implements OfferListObserver {
	
	private ArrayList<Offer> offerList = new ArrayList<Offer>();
	private PonudePanel ponudePanel;
	private OfferDAO offerDAO;
	private OfferTableModel tableModel;
	
	/**
	 * @param ponudePanel
	 */
	public PonudeController( PonudePanel ponudePanel ) {
		
		this.ponudePanel = ponudePanel;
		this.offerDAO = new OfferDAO();
		this.offerList = getAllOffersList();
		OfferListObservable.getInstance().addObserver( this );
		
	}
	
	
	public Offer getSelectedOffer( int row ) {
		
		return tableModel.getOfferList().get( row );
		
	}
	
	
	private ArrayList<Offer> getAllOffersList() {
		
		offerList = offerDAO.getAllOffers();
		return offerList;
		
	}
	
	
	public void setTableData() {
		
		tableModel = new OfferTableModel( offerList );
		ponudePanel.getTable().setModel( tableModel );
		
	}
	
	
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
	
	
	public void deleteOffer( int row ) {
		
		Offer offer = getSelectedOffer( row );
		offerDAO.deleteOffer( offer );
		
		tableModel.getOfferList().remove( offer );
		
		offerList.remove( offer );
		tableModel.setOfferList( offerList );
		tableModel.fireTableDataChanged();
		
		ponudePanel.clearAll();
		
	}
	
	
	public void viewPDF( int row ) {
		
		Offer offer = getSelectedOffer( row );
		
		try {
			
			Desktop.getDesktop().open( new File( offer.getUrl() ) );
			
		} catch ( IOException e ) {
			
			e.printStackTrace();
			
		}
		
	}
	
	
	@Override
	public void updateOfferList() {
		
		this.offerList = getAllOffersList();
		tableModel.setOfferList( offerList );
		
	}
	
}
