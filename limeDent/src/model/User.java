package model;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import controller.observer.UserObserver;


/**
 * 
 * @author ikojic000
 * 
 *         The User class represents a user of the application. It contains
 *         information about the user, such as their username, password, name,
 *         last name, email, phone number, and profile photo. It also provides
 *         methods for observing changes to the user's profile, and for hashing
 *         and salting the user's password.
 * 		
 */
public class User {
	
	private Integer id;
	private String username;
	private String password;
	private String name;
	private String lastName;
	private String mail;
	private String phone;
	private ImageIcon profilePhoto;
	private List<UserObserver> observers = new ArrayList<>();
	
	/**
	 * 
	 * Constructor for creating a User object with only an ID.
	 * 
	 * @param id the user's unique identifier
	 */
	public User( Integer id ) {
		
		this.id = id;
		
	}
	
	
	/**
	 * 
	 * Constructor for creating a User object with only a username and password.
	 * 
	 * @param username the user's username
	 * @param password the user's password
	 */
	public User( String username , String password ) {
		
		this.username = username;
		this.password = password;
		
	}
	
	
	/**
	 * 
	 * Constructor for creating a User object with a username, password, name, last
	 * name, email, and phone number.
	 * 
	 * @param username the user's username
	 * @param password the user's password
	 * @param name     the user's first name
	 * @param lastName the user's last name
	 * @param mail     the user's email address
	 * @param phone    the user's phone number
	 */
	public User( String username , String password , String name , String lastName , String mail , String phone ) {
		
		this.username = username;
		this.password = password;
		this.name = name;
		this.lastName = lastName;
		this.mail = mail;
		this.phone = phone;
		
	}
	
	
	/**
	 * 
	 * Constructor for creating a User object with an ID, username, password, name,
	 * last name, email, phone number, and profile photo.
	 * 
	 * @param id           the user's unique identifier
	 * @param username     the user's username
	 * @param password     the user's password
	 * @param name         the user's first name
	 * @param lastName     the user's last name
	 * @param mail         the user's email address
	 * @param phone        the user's phone number
	 * @param profilePhoto the user's profile photo
	 */
	public User( Integer id , String username , String password , String name , String lastName , String mail ,
				 String phone , ImageIcon profilePhoto ) {
		
		this.id = id;
		this.username = username;
		this.password = password;
		this.name = name;
		this.lastName = lastName;
		this.mail = mail;
		this.phone = phone;
		this.profilePhoto = profilePhoto;
		
	}
	
	
	/**
	 * 
	 * Returns the user's ID.
	 * 
	 * @return the user's ID
	 */
	public Integer getId() {
		
		return id;
		
	}
	
	
	/**
	 * 
	 * Sets the user's ID.
	 * 
	 * @param id the user's ID to set
	 */
	public void setId( Integer id ) {
		
		this.id = id;
		
	}
	
	
	/**
	 * 
	 * Returns the user's username.
	 * 
	 * @return the user's username
	 */
	public String getUsername() {
		
		return username;
		
	}
	
	
	/**
	 * 
	 * Sets user's username.
	 * 
	 * @param username the username to set
	 */
	public void setUsername( String username ) {
		
		this.username = username;
		
	}
	
	
	/**
	 * 
	 * Returns user's password.
	 * 
	 * @return the password
	 */
	public String getPassword() {
		
		return password;
		
	}
	
	
	/**
	 * 
	 * Sets user's password.
	 * 
	 * @param password the password to set
	 */
	public void setPassword( String password ) {
		
		this.password = password;
		
	}
	
	
	/**
	 * 
	 * Returns users name.
	 * 
	 * @return the name
	 */
	public String getName() {
		
		return name;
		
	}
	
	
	/**
	 * 
	 * Sets user's name.
	 * 
	 * @param name the name to set
	 */
	public void setName( String name ) {
		
		this.name = name;
		
	}
	
	
	/**
	 * 
	 * Returns user's last name .
	 * 
	 * @return the lastName
	 */
	public String getLastName() {
		
		return lastName;
		
	}
	
	
	/**
	 * 
	 * Sets user's last name.
	 * 
	 * @param lastName the lastName to set
	 */
	public void setLastName( String lastName ) {
		
		this.lastName = lastName;
		
	}
	
	
	/**
	 * 
	 * Returns user's email.
	 * 
	 * @return the mail
	 */
	public String getMail() {
		
		return mail;
		
	}
	
	
	/**
	 * 
	 * Sets user's mail.
	 * 
	 * @param mail the mail to set
	 */
	public void setMail( String mail ) {
		
		this.mail = mail;
		
	}
	
	
	/**
	 * 
	 * Returns user's phone number.
	 * 
	 * @return the phone
	 */
	public String getPhone() {
		
		return phone;
		
	}
	
	
	/**
	 * 
	 * Sets user's phone number.
	 * 
	 * @param phone the phone to set
	 */
	public void setPhone( String phone ) {
		
		this.phone = phone;
		
	}
	
	
	/**
	 * 
	 * Returns the user's profile photo.
	 * 
	 * @return the profilePhoto
	 */
	public ImageIcon getProfilePhoto() {
		
		return profilePhoto;
		
	}
	
	
	/**
	 * 
	 * Sets the users's profile photo.
	 * 
	 * @param profilePhoto the profilePhoto to set
	 */
	public void setProfilePhoto( ImageIcon profilePhoto ) {
		
		this.profilePhoto = profilePhoto;
		
	}
	
	
	/**
	 * 
	 * Adds a user observer to the list of observers.
	 * 
	 * @param observer the user observer to add
	 */
	public void addObserver( UserObserver observer ) {
		
		observers.add( observer );
		
	}
	
	
	/**
	 * 
	 * Removes a user observer from the list of observers.
	 * 
	 * @param observer the user observer to remove
	 */
	public void removeObserver( UserObserver observer ) {
		
		observers.remove( observer );
		
	}
	
	
	/**
	 * 
	 * Notifies all the user observers by calling their updateProfilePhoto() and
	 * updateUserInfo() methods.
	 */
	public void notifyObservers() {
		
		for ( UserObserver observer : observers ) {
			
			observer.updateProfilePhoto( this );
			observer.updateUserInfo( this );
			
		}
		
	}
	
//	Methods for Hashing and Salting User Password
	
	
	/**
	 * 
	 * Hashes the user's password using SHA-256 algorithm and a salt value.
	 * 
	 * @param password the password to hash
	 * @param salt     the salt value to use
	 * @return the hashed password
	 * @throws RuntimeException if there is an error while hashing the password
	 */
	public String hashPassword( String password , String salt ) {
		
		String saltedPassword = salt + password;
		
		try {
			
			MessageDigest messageDigest = MessageDigest.getInstance( "SHA-256" );
			byte[] hashedBytes = messageDigest.digest( saltedPassword.getBytes() );
			return salt + bytesToHex( hashedBytes );
			
		} catch ( NoSuchAlgorithmException e ) {
			
			throw new RuntimeException( "Error hashing password: " + e.getMessage() );
			
		}
		
	}
	
	
	/**
	 * 
	 * Generates a random salt value for password hashing.
	 * 
	 * @return the generated salt value
	 */
	@SuppressWarnings( "unused" )
	private static String generateSalt() {
		
		SecureRandom random = new SecureRandom();
		byte[] saltBytes = new byte[16];
		random.nextBytes( saltBytes );
		return bytesToHex( saltBytes );
		
	}
	
	
	/**
	 * 
	 * Converts an array of bytes into a hexadecimal string.
	 * 
	 * @param bytes the byte array to convert
	 * @return the hexadecimal string representation of the byte array
	 */
	private static String bytesToHex( byte[] bytes ) {
		
		StringBuilder sb = new StringBuilder();
		
		for ( byte b : bytes ) {
			
			sb.append( String.format( "%02x" , b ) );
			
		}
		
		return sb.toString();
		
	}
	
}
