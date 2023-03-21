package dao;


import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Database;
import model.Offer;


/**
 * 
 * @author ikojic000
 * 
 *         The OfferDAO class provides data access methods for Offer objects in
 *         the database. It provides methods to retrieve all offers, delete an
 *         offer and add a new offer.
 * 		
 */
public class OfferDAO {
	
	/**
	 * 
	 * Constructor for OfferDAO class.
	 */
	public OfferDAO() {
		
	}
	
	
	/**
	 * 
	 * Retrieves all offers from the database and returns them as an ArrayList of
	 * Offer objects.
	 * 
	 * @return ArrayList<Offer> allOffers - an ArrayList containing all the offers
	 *         retrieved from the database.
	 */
	public ArrayList<Offer> getAllOffers() {
		
		ArrayList<Offer> allOffers = new ArrayList<Offer>();
		
		String sql = "SELECT offer.id, offer.title, offer.authorID, CONCAT(user.ime, ' ', user.prezime) AS author, offer.patientName, offer.date, offer.url "
				+ "FROM offers offer " + "INNER JOIN users user ON offer.authorID = user.id "
				+ "ORDER BY offer.date DESC, offer.id, offer.title, offer.authorID, CONCAT(user.ime, ' ', user.prezime), offer.patientName, offer.url";
		
		Connection connection = Database.getDatabase().getConnection();
		
		try {
			
			PreparedStatement pst = connection.prepareStatement( sql );
			ResultSet rs = pst.executeQuery();
			
			while ( rs.next() ) {
				
				if ( isFileOnPC( new File( rs.getString( "url" ) ) ) ) {
					
					allOffers.add( new Offer( rs.getInt( "id" ) , rs.getString( "title" ) , rs.getInt( "authorID" ) ,
							rs.getString( "author" ) , rs.getString( "patientName" ) ,
							rs.getTimestamp( "date" ).toLocalDateTime() , rs.getString( "url" ) ) );
					
				}
				
			}
			
		} catch ( SQLException e ) {
			
			e.printStackTrace();
			
		}
		
		return allOffers;
		
	}
	
	
	/**
	 * 
	 * Deletes an offer from the database.
	 * 
	 * @param offer - the Offer object to be deleted.
	 */
	public void deleteOffer( Offer offer ) {
		
		String sql = "delete from offers where id= ?;";
		
		Connection connection = Database.getDatabase().getConnection();
		
		try {
			
			PreparedStatement pst = connection.prepareStatement( sql );
			pst.setInt( 1 , offer.getId() );
			pst.executeUpdate();
			
		} catch ( SQLException e ) {
			
			e.printStackTrace();
			
		}
		
	}
	
	
	/**
	 * 
	 * Adds a new offer to the database.
	 * 
	 * @param offer - the Offer object to be added.
	 */
	public void addOffer( Offer offer ) {
		
		String sql = "insert into offers (title, authorID, patientName, url) values (?, ?, ?, ?);";
		
		Connection connection = Database.getDatabase().getConnection();
		
		try {
			
			PreparedStatement pst = connection.prepareStatement( sql );
			pst.setString( 1 , offer.getTitle() );
			pst.setInt( 2 , offer.getAuthorID() );
			pst.setString( 3 , offer.getPatientName() );
			pst.setString( 4 , offer.getUrl() );
			pst.executeUpdate();
			
		} catch ( SQLException e ) {
			
		}
		
	}
	
	
	private boolean isFileOnPC( File file ) {
		
		return file.exists();
		
	}
	
}
