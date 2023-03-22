package dao;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import model.Database;
import model.User;


/**
 *
 * @author ikojic000
 *
 *         The UserDAO class is responsible for accessing user data in the
 *         database. It contains methods for retrieving hashed passwords from
 *         the database, retrieving user data from the database and updating
 *         user information such as their password, personal information, and
 *         profile photo. The class maintains a connection to the database and
 *         uses prepared statements to execute SQL queries.
 *		
 */
public class UserDAO {
	
	/**
	 * Creates a new UserDAO object .
	 */
	public UserDAO() {
		
	}
	
	
	/**
	 *
	 * Retrieves the hashed password for the given username from the database.
	 *
	 * @param username - the username to search for
	 * @return the hashed password for the given username, or null if no matching
	 *         user is found
	 * @throws RuntimeException if an error occurs while retrieving the hashed
	 *                          password from the database
	 */
	public String getHashedPasswordFromDatabase( String username ) {
		
		String sql = "select password from users where username = ?";
		
		Connection connection = Database.getDatabase().getConnection();
		
		try {
			
			PreparedStatement pst = connection.prepareStatement( sql );
			pst.setString( 1 , username );
			ResultSet rs = pst.executeQuery();
			
			if ( rs.next() ) {
				
				return rs.getString( "password" );
				
			} else {
				
				return null;
				
			}
			
		} catch ( SQLException e ) {
			
			throw new RuntimeException( "Error getting hashed password from database: " + e.getMessage() );
			
		}
		
	}
	
	
	/**
	 *
	 * Retrieves the user data for the given user from the database.
	 *
	 * @param user a User object containing the username and password to search for
	 * @return a User object containing all the user's data if the username and
	 *         password match a user in the database, or null if no matching user is
	 *         found
	 */
	public User getUser( User user ) {
		
		User loggedInUser = null;
		
		String sql = "select * from users where username=? and password=?  ";
		
		Connection connection = Database.getDatabase().getConnection();
		
		try {
			
			ImageIcon profilePhoto = null;
			
			PreparedStatement pst = connection.prepareStatement( sql );
			pst.setString( 1 , user.getUsername() );
			pst.setString( 2 , user.getPassword() );
			
			ResultSet rs = pst.executeQuery();
			
			if ( rs.next() ) {
				
				if ( rs.getBinaryStream( "profilePhoto" ) != null ) {
					
					BufferedImage im = ImageIO.read( rs.getBinaryStream( "profilePhoto" ) );
					profilePhoto = new ImageIcon( im );
					
				} else {
					
					profilePhoto = new ImageIcon( this.getClass().getResource( "/MPDLogo_Transparent.png" ) );
					
				}
				
				loggedInUser = new User( rs.getInt( "id" ) , rs.getString( "username" ) , rs.getString( "password" ) ,
						rs.getString( "ime" ) , rs.getString( "prezime" ) , rs.getString( "mail" ) ,
						rs.getString( "phone" ) , profilePhoto );
				
			} else {
				
				return null;
				
			}
			
		} catch ( SQLException e ) {
			
			e.printStackTrace();
			
		} catch ( IOException e ) {
			
			e.printStackTrace();
			
		}
		
		return loggedInUser;
		
	}
	
	
	/**
	 *
	 * Updates the password for the given user in the database.
	 *
	 * @param newPassword - the new password to set for the user
	 * @param user        - a User object containing the username of the user to
	 *                    update
	 */
	public void updateUserPassword( String newPassword , User user ) {
		
		String sql = "UPDATE users SET password =? WHERE username = ?;";
		
		Connection connection = Database.getDatabase().getConnection();
		
		try {
			
			PreparedStatement pst = connection.prepareStatement( sql );
			pst.setString( 1 , newPassword );
			pst.setString( 2 , user.getUsername() );
			pst.executeUpdate( sql );
			
		} catch ( SQLException e ) {
			
			e.printStackTrace();
			
		}
		
	}
	
	
	/**
	 *
	 * Updates the user data for the given user in the database.
	 *
	 * @param loggedInUser - a User object containing the updated user data
	 */
	public void updateUser( User loggedInUser ) {
		
		String sql = "update users set ime = ?, prezime = ?, phone = ?, mail = ?, password = ? where id = ?";
		
		Connection connection = Database.getDatabase().getConnection();
		
		try {
			
			PreparedStatement pst = connection.prepareStatement( sql );
			pst.setString( 1 , loggedInUser.getName() );
			pst.setString( 2 , loggedInUser.getLastName() );
			pst.setString( 3 , loggedInUser.getPhone() );
			pst.setString( 4 , loggedInUser.getMail() );
			pst.setString( 5 , loggedInUser.getPassword() );
			pst.setInt( 6 , loggedInUser.getId() );
			pst.executeUpdate();
			
		} catch ( SQLException e ) {
			
			e.printStackTrace();
			
		}
		
	}
	
	
	/**
	 *
	 * Updates the profile photo for the given user in the database.
	 *
	 * @param loggedInUser - a User object containing the user to update
	 * @param photo        - a File object containing the new profile photo
	 */
	public void updateUserPhoto( User loggedInUser , File photo ) {
		
		String sql = "update users set profilePhoto = ? where id = ?";
		
		Connection connection = Database.getDatabase().getConnection();
		
		try {
			
			InputStream in = new FileInputStream( photo.getAbsolutePath() );
			PreparedStatement pst = connection.prepareStatement( sql );
			pst.setBlob( 1 , in );
			pst.setInt( 2 , loggedInUser.getId() );
			pst.executeUpdate();
			
		} catch ( SQLException e ) {
			
			e.printStackTrace();
			
		} catch ( FileNotFoundException e ) {
			
			e.printStackTrace();
			
		}
		
	}
	
}
