package controller.observer;


/**
 * @author ikojic000
 * 
 *         PatientListObserver is an interface that represents an observer in
 *         the observer design pattern. It contains a method for updating the
 *         observer when the patient list changes.
 * 		
 */
public interface PatientListObserver {
	
	/**
	 * Updates the observer with the latest changes to the patient list.
	 */
	void updatePatientList();
	
}
