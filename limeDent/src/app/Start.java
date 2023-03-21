package app;


import javax.swing.SwingUtilities;

import model.DatabaseConnectionThread;
import view.ViewLogin;


public class Start {
	
	public static void main( String[] args ) {
		
		SwingUtilities.invokeLater( () -> {
			
			System.out.println( "Running GUI" );
			ViewLogin viewLogin = new ViewLogin();
			viewLogin.setVisible( true );
			
		} );
		
		DatabaseConnectionThread databaseConnectionThread = new DatabaseConnectionThread();
		databaseConnectionThread.start();
		
	}
	
}
