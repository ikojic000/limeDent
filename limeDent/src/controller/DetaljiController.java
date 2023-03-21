package controller;


import dao.PatientDAO;
import model.Patient;
import view.viewPanel.DetaljiPanel;


/**
 * 
 * @author ikojic000
 * 
 *         DetaljiController is a class that represents a controller for
 *         DetaljiPanel. It handles patient data and updates the information in
 *         the database.
 * 
 */
public class DetaljiController {
	
	private DetaljiPanel detaljiPanel;
	private PatientDAO patientDAO;
	
	/**
	 * 
	 * Constructs a new DetaljiController object and initializes the DAO object.
	 */
	public DetaljiController() {
		
		this.patientDAO = new PatientDAO();
		
	}
	
	
	/**
	 * 
	 * Sets the patient data.
	 */
	public void setPatientData() {
		
		// TODO: implement method
	}
	
	
	/**
	 * 
	 * Deletes the specified patient from the database.
	 * 
	 * @param patient the patient to be deleted
	 */
	public void deletePatient( Patient patient ) {
		
		patientDAO.deletePatient( patient );
		
	}
	
	
	/**
	 * 
	 * Updates the specified patient's information in the database.
	 * 
	 * @param patient the patient to be updated
	 */
	public void updatePatient( Patient patient ) {
		
		patient.setName( detaljiPanel.getTxtimePrezime().getText() );
		patient.setOib( Long.valueOf( detaljiPanel.getTxtOIB().getText() ) );
		patient.setJmbg( Long.valueOf(
				detaljiPanel.getTxtJMBG().getText() == null ? "0000000000000" : detaljiPanel.getTxtJMBG().getText() ) );
		patient.setAddress( detaljiPanel.getTxtAdresa().getText() );
		patient.setCity( detaljiPanel.getTxtGrad().getText() );
		patient.setPhone( detaljiPanel.getTxtBrojMobitela().getText() );
		patient.setMail( detaljiPanel.getTxtMail().getText() );
		patient.setMedicalHistory( detaljiPanel.getTxtPovijestBolesti().getText() );
		patient.setAlergies( detaljiPanel.getTxtAlergije().getText() );
		patientDAO.updatePatient( patient );
		
	}
	
	
	/**
	 * 
	 * Updates the specified patient's photo in the database.
	 * 
	 * @param patient the patient whose photo is to be updated
	 */
	public void updatePatientPhoto( Patient patient ) {
		
		patientDAO.updatePatientPhoto( patient );
		
	}
	
	
	/**
	 * 
	 * Returns the DetaljiPanel object associated with this controller.
	 * 
	 * @return the DetaljiPanel object
	 */
	public DetaljiPanel getDetaljiPanel() {
		
		return detaljiPanel;
		
	}
	
	
	/**
	 * 
	 * Sets the DetaljiPanel object associated with this controller.
	 * 
	 * @param detaljiPanel the DetaljiPanel object to set
	 */
	public void setDetaljiPanel( DetaljiPanel detaljiPanel ) {
		
		this.detaljiPanel = detaljiPanel;
		
	}
	
}
