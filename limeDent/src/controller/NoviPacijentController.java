package controller;


import javax.swing.ImageIcon;

import controller.observer.PatientListObservable;
import dao.PatientDAO;
import model.Patient;
import view.viewPanel.NoviPacijentPanel;


public class NoviPacijentController {
	
	private PatientDAO patientDAO;
	private NoviPacijentPanel noviPacijentPanel;
	
	public NoviPacijentController( NoviPacijentPanel noviPacijentPanel ) {
		
		this.noviPacijentPanel = noviPacijentPanel;
		this.patientDAO = new PatientDAO();
		
	}
	
	
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
