package controller.observer;


import model.User;


/**
 * @author ikojic000
 * 
 *         UserObserver is an interface that represents an observer in the
 *         observer design pattern. It contains two methods for updating the
 *         observer when the user's profile photo or user information changes.
 * 		
 */
public interface UserObserver {
	
	/**
	 * Updates the observer with the latest profile photo of the logged-in user.
	 * 
	 * @param loggedInUser the user whose profile photo has changed
	 */
	void updateProfilePhoto( User loggedInUser );
	
	/**
	 * Updates the observer with the latest information of the logged-in user.
	 * 
	 * @param loggedInUser the user whose information has changed
	 */
	void updateUserInfo( User loggedInUser );
	
}
