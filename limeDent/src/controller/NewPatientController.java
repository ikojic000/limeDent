package controller;


import javax.swing.ImageIcon;

import controller.observer.PatientListObservable;
import dao.PatientDAO;
import model.Patient;
import view.viewPanel.NewPatientPanel;


/**
 *
 * @author ikojic000
 *
 *         The NoviPacijentController class is responsible for managing and
 *         controlling the functionality related to adding a new patient to the
 *         system. It contains methods for adding a new patient to the database,
 *         using the PatientDAO class. The class also has an instance variable
 *         of type NoviPacijentPanel, which is used to access and manipulate the
 *         graphical user interface elements related to adding a new patient.
 */
public class NewPatientController {
	
	private PatientDAO patientDAO;
	private NewPatientPanel noviPacijentPanel;
	
	/**
	 *
	 * Constructs a new NoviPacijentController object, initializing the patientDAO
	 * and noviPacijentPanel instance variables.
	 *
	 * @param noviPacijentPanel - the panel used to add a new patient
	 */
	public NewPatientController( NewPatientPanel noviPacijentPanel ) {
		
		this.noviPacijentPanel = noviPacijentPanel;
		this.patientDAO = new PatientDAO();
		
	}
	
	
	/**
	 *
	 * Adds a new patient to the system by creating a new Patient object and adding
	 * it to the database using the PatientDAO class. Notifies observers of the
	 * PatientListObservable singleton object of the change. Also switches the
	 * current visible panel to the home panel.
	 */
	public void addPatient() {
		
		Integer id = null;
		String name = noviPacijentPanel.getTxtimePrezime().getText();
		
		String oibText = noviPacijentPanel.getTxtOIB().getText();
		
		Long oib = (oibText != null && !oibText.isEmpty()) ? Long.valueOf( oibText ) : 0L;
		String phone = noviPacijentPanel.getTxtBrojMobitela().getText();
		String mail = noviPacijentPanel.getTxtMail().getText();
		
		String jmbgText = noviPacijentPanel.getTxtJMBG().getText();
		
		Long jmbg = (jmbgText != null && !jmbgText.isEmpty()) ? Long.valueOf( jmbgText ) : 0L;
		
		String address = noviPacijentPanel.getTxtAdresa().getText();
		String city = noviPacijentPanel.getTxtGrad().getText();
		String medicalHistory = noviPacijentPanel.getTxtPovijestBolesti().getText();
		String alergies = noviPacijentPanel.getTxtAlergije().getText();
		ImageIcon profile = new ImageIcon( this.getClass().getResource( "/MPDLogo_Transparent.png" ) );
		
		Patient patient = new Patient( id , name , oib , jmbg , mail , phone , address , city , medicalHistory ,
				alergies , profile );
		
		patientDAO.addPatient( patient );
		PatientListObservable.getInstance().notifyObservers();
		noviPacijentPanel.getCardParent().showCard( "homePanel" );
		
	}
	
}
