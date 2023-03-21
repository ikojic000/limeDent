package controller;


import java.io.File;

import dao.UserDAO;
import model.User;


public class LoginController {
	
	private User loggedInUser;
	private UserDAO userDAO;
	
	public LoginController() {
		
		this.userDAO = new UserDAO();
		
	}
	
	
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
	
	
	public User getUser( User user ) {
		
		return userDAO.getUser( user );
		
	}
	
	
	public User getLoggedInUser() {
		
		return loggedInUser;
		
	}
	
	
	public void setLoggedInUser( User user ) {
		
		this.loggedInUser = user;
		
	}
	
	
	public void updateUser() {
		
		userDAO.updateUser( loggedInUser );
		
	}
	
	
	public void updateUserPhoto( File photo ) {
		
		userDAO.updateUserPhoto( loggedInUser , photo );
		
	}
	
}
