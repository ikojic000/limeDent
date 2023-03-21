package model;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 * 
 * @author ikojic000
 * 
 *         This class represents an Offer object that contains the details of an
 *         offer made by an author for a patient. The Offer class has the
 *         following properties: id, title, authorID, authorName, patientName,
 *         date, notes, total, and url. The Offer class provides various getters
 *         and setters for accessing and modifying the properties of an Offer
 *         object.
 * 
 */
public class Offer {
	
	private Integer id;
	private String title;
	private Integer authorID;
	private String authorName;
	private String patientName;
	private LocalDateTime date;
	private String notes;
	private BigDecimal total;
	private String url;
	
	/**
	 * 
	 * This constructor creates an Offer object with the specified id.
	 * 
	 * @param id The id of the Offer.
	 */
	public Offer( Integer id ) {
		
		this.id = id;
		
	}
	
	
	/**
	 * 
	 * This constructor creates an Offer object with the specified properties.
	 * 
	 * @param title       The title of the Offer.
	 * @param authorID    The id of the author who made the Offer.
	 * @param patientName The name of the patient for whom the Offer is made.
	 * @param date        The date on which the Offer was made.
	 * @param total       The total amount of the Offer.
	 * @param notes       Any additional notes about the Offer.
	 */
	public Offer( String title , Integer authorID , String patientName , LocalDateTime date , BigDecimal total ,
				  String notes ) {
		
		this.title = title;
		this.authorID = authorID;
		this.patientName = patientName;
		this.date = date;
		this.total = total;
		this.notes = notes;
		
	}
	
	
	/**
	 * 
	 * This constructor creates an Offer object with the specified properties.
	 * 
	 * @param id          The id of the Offer.
	 * @param title       The title of the Offer.
	 * @param authorID    The id of the author who made the Offer.
	 * @param authorname  The name of the author who made the Offer.
	 * @param patientName The name of the patient for whom the Offer is made.
	 * @param date        The date on which the Offer was made.
	 * @param url         The URL of the Offer.
	 */
	public Offer( int id , String title , int authorID , String authorname , String patientName , LocalDateTime date ,
				  String url ) {
		
		this.id = id;
		this.title = title;
		this.authorID = authorID;
		this.authorName = authorname;
		this.patientName = patientName;
		this.date = date;
		this.url = url;
		
	}
	
	
	/**
	 * 
	 * Returns the unique identifier for this offer.
	 * 
	 * @return the unique identifier for this offer
	 */
	public Integer getId() {
		
		return id;
		
	}
	
	
	/**
	 * 
	 * Sets the unique identifier for this offer.
	 * 
	 * @param id the unique identifier for this offer
	 */
	public void setId( Integer id ) {
		
		this.id = id;
		
	}
	
	
	/**
	 * 
	 * Returns the title of this offer.
	 * 
	 * @return the title of this offer
	 */
	public String getTitle() {
		
		return title;
		
	}
	
	
	/**
	 * 
	 * Sets the title of this offer.
	 * 
	 * @param title the title of this offer
	 */
	public void setTitle( String title ) {
		
		this.title = title;
		
	}
	
	
	/**
	 * 
	 * Returns the ID of the author who created this offer.
	 * 
	 * @return the ID of the author who created this offer
	 */
	public Integer getAuthorID() {
		
		return authorID;
		
	}
	
	
	/**
	 * 
	 * Sets the ID of the author who created this offer.
	 * 
	 * @param authorID the ID of the author who created this offer
	 */
	public void setAuthorID( Integer authorID ) {
		
		this.authorID = authorID;
		
	}
	
	
	/**
	 * 
	 * Returns the name of the author who created this offer.
	 * 
	 * @return the name of the author who created this offer
	 */
	public String getAuthorName() {
		
		return authorName;
		
	}
	
	
	/**
	 * 
	 * Sets the name of the author who created this offer.
	 * 
	 * @param authorName the name of the author who created this offer
	 */
	public void setAuthorName( String authorName ) {
		
		this.authorName = authorName;
		
	}
	
	
	/**
	 * 
	 * Returns the name of the patient associated with this offer.
	 * 
	 * @return the name of the patient associated with this offer
	 */
	public String getPatientName() {
		
		return patientName;
		
	}
	
	
	/**
	 * 
	 * Sets the name of the patient associated with this offer.
	 * 
	 * @param patientName the patient name to set
	 */
	public void setPatientName( String patientName ) {
		
		this.patientName = patientName;
		
	}
	
	
	/**
	 * 
	 * Returns the date of this offer.
	 * 
	 * @return the date of this offer
	 */
	public LocalDateTime getDate() {
		
		return date;
		
	}
	
	
	/**
	 * 
	 * Returns the formatted date string of this offer using the pattern "dd-MM-yyyy
	 * HH:mm".
	 * 
	 * @return the formatted date string of this offer
	 */
	public String getDateFormatted() {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern( "dd-MM-yyyy HH:mm" );
		return getDate().format( formatter );
		
	}
	
	
	/**
	 * 
	 * Sets the date of this offer.
	 * 
	 * @param date the date to set
	 */
	public void setDate( LocalDateTime date ) {
		
		this.date = date;
		
	}
	
	
	/**
	 * 
	 * Returns the notes for this offer.
	 * 
	 * @return the notes for this offer
	 */
	public String getNotes() {
		
		return notes;
		
	}
	
	
	/**
	 * 
	 * Sets the notes for this offer.
	 * 
	 * @param notes the notes to set
	 */
	public void setNotes( String notes ) {
		
		this.notes = notes;
		
	}
	
	
	/**
	 * 
	 * Returns the total amount of this offer.
	 * 
	 * @return the total amount of this offer
	 */
	public BigDecimal getTotal() {
		
		return total;
		
	}
	
	
	/**
	 * 
	 * Sets the total amount for this offer.
	 * 
	 * @param total the total amount to set
	 */
	public void setTotal( BigDecimal total ) {
		
		this.total = total;
		
	}
	
	
	/**
	 * 
	 * Returns the URL of this offer.
	 * 
	 * @return the URL of this offer
	 */
	public String getUrl() {
		
		return url;
		
	}
	
	
	/**
	 * 
	 * Sets the URL for this offer.
	 * 
	 * @param url the URL to set
	 */
	public void setUrl( String url ) {
		
		this.url = url;
		
	}
	
}
