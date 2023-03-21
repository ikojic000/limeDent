package app;


import javax.swing.SwingUtilities;

import model.DatabaseConnectionThread;
import view.ViewLogin;


/**
 * 
 * @author ikojic000
 * 
 * 
 *         The Start class is the entry point of the application. It initializes
 *         and starts the database connection thread and opens the login view.
 */
public class Start {
	
	/**
	 * 
	 * The main method of the Start class is the entry point of the application. It
	 * initializes the login view and starts the database connection thread.
	 * 
	 * @param args command line arguments
	 */
	public static void main( String[] args ) {
		
		/**
		 * Open the login view using SwingUtilities.invokeLater method to ensure that it
		 * runs on the event dispatch thread.
		 */
		SwingUtilities.invokeLater( () -> {
			
			ViewLogin viewLogin = new ViewLogin();
			viewLogin.setVisible( true );
			
		} );
		
		/**
		 * Start the database connection thread
		 */
		DatabaseConnectionThread databaseConnectionThread = new DatabaseConnectionThread();
		databaseConnectionThread.start();
		
	}
	
}
