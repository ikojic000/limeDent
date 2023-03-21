package model;


import java.sql.SQLException;


/**
 * 
 * @author ikojic000
 * 
 *         The DatabaseConnectionThread class is a thread that runs in the
 *         background and periodically checks the status of the database
 *         connection. If the connection is closed, it attempts to reconnect to
 *         the database. This class extends the Thread class and overrides the
 *         run() method to implement the connection checking functionality.
 * 		
 */
public class DatabaseConnectionThread extends Thread {
	
//	The amount of time to sleep between connection checks
	private final static int SLEEP_TIME = 15 * 1000;
	
//	Flag indicating whether the thread should keep running or not
	private boolean running;
	
//	The singleton instance of the database
	private Database database;
	
	/**
	 * 
	 * Constructor that initializes the instance variables
	 */
	public DatabaseConnectionThread() {
		
		running = true;
		database = Database.getDatabase();
		
	}
	
	
	/**
	 * 
	 * Sets the running flag to false, which will cause the thread to stop running
	 */
	public void stopThread() {
		
		running = false;
		
	}
	
	
	/**
	 * 
	 * The run() method is called when the thread is started, and it contains the
	 * logic for checking the database connection status and attempting to reconnect
	 * if necessary.
	 */
	@SuppressWarnings( "static-access" )
	@Override
	public void run() {
		
		Thread.currentThread().setName( "Database Connection Thread" );
		
		while ( running ) {
			
			try {
				
//				If the database connection is closed, attempt to reconnect
				if ( database.getConnection().isClosed() ) {
					
					System.out.println( "Database disconnected...Trying to connect again" );
					database.connect();
					
				} else {
					
//					If the database connection is open, print a message indicating that
					System.out.println( "Database connected...Running on thread " + Thread.currentThread().getName() );
					
				}
				
//				Sleep for the specified amount of time before checking the connection again
				Thread.sleep( SLEEP_TIME );
				
			} catch ( InterruptedException | SQLException e ) {
				
//				Print any exceptions that occur during the connection check
				e.printStackTrace();
				
			}
			
		}
		
	}
	
}
