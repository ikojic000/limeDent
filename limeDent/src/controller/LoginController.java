package controller;


import java.io.File;

import dao.UserDAO;
import model.User;


/**
 * 
 * @author ikojic000
 * 
 *         The LoginController class is responsible for handling user login
 *         functionality. It interacts with the UserDAO class to retrieve user
 *         credentials and update user information.
 * 		
 */
public class LoginController {
	
	private User loggedInUser;
	private UserDAO userDAO;
	
	/**
	 * Constructs a new LoginController object and initializes the UserDAO instance.
	 */
	public LoginController() {
		
		this.userDAO = new UserDAO();
		
	}
	
	
	/**
	 * 
	 * Verifies if the user's credentials are valid and returns the corresponding
	 * user if they are. If the password is incorrect, returns a string with an
	 * error message.
	 * 
	 * @param user the User object containing the username and password to be
	 *             verified
	 * @return the corresponding User object if the credentials are valid, or a
	 *         string with an error message otherwise
	 */
	public Object checkCredentials( User user ) {
		
		String hashedDBPassword = userDAO.getHashedPasswordFromDatabase( user.getUsername() );
		
		if ( hashedDBPassword != null ) {
			
			String hashedPassword = user.hashPassword( user.getPassword() , hashedDBPassword.substring( 0 , 32 ) );
			
			if ( hashedPassword.equals( hashedDBPassword ) ) {
				
				user.setPassword( hashedPassword );
				return getUser( user );
				
			} else {
				
				return "Unijeli ste krivu lozinku!";
				
			}
			
		} else {
			
			return false;
			
		}
		
	}
	
	
	/**
	 * 
	 * Returns the User object corresponding to the given User object.
	 * 
	 * @param user the User object to retrieve from the database
	 * @return the corresponding User object if found, or null otherwise
	 */
	public User getUser( User user ) {
		
		return userDAO.getUser( user );
		
	}
	
	
	/**
	 * 
	 * Returns the currently logged in User object.
	 * 
	 * @return the currently logged in User object
	 */
	public User getLoggedInUser() {
		
		return loggedInUser;
		
	}
	
	
	/**
	 * 
	 * Sets the currently logged in User object.
	 * 
	 * @param user the User object to set as the currently logged in user
	 */
	public void setLoggedInUser( User user ) {
		
		this.loggedInUser = user;
		
	}
	
	
	/**
	 * 
	 * Updates the information of the currently logged in user.
	 */
	public void updateUser() {
		
		userDAO.updateUser( loggedInUser );
		
	}
	
	
	/**
	 * 
	 * Updates the photo of the currently logged in user.
	 * 
	 * @param photo the File object representing the new user photo
	 */
	public void updateUserPhoto( File photo ) {
		
		userDAO.updateUserPhoto( loggedInUser , photo );
		
	}
	
}
