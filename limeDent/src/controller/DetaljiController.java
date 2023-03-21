package controller;


import dao.PatientDAO;
import model.Patient;
import view.viewPanel.DetaljiPanel;


public class DetaljiController {
	
	private DetaljiPanel detaljiPanel;
	private PatientDAO patientDAO;
	
	public DetaljiController() {
		
		this.patientDAO = new PatientDAO();
		
	}
	
	
	public void setPatientData() {
		
	}
	
	
	public void deletePatient( Patient patient ) {
		
		patientDAO.deletePatient( patient );
		
	}
	
	
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
	
	
	public void updatePatientPhoto( Patient patient ) {
		
		patientDAO.updatePatientPhoto( patient );
		
	}
	
	
	/**
	 * @return the detaljiPanel
	 */
	public DetaljiPanel getDetaljiPanel() {
		
		return detaljiPanel;
		
	}
	
	
	/**
	 * @param detaljiPanel the detaljiPanel to set
	 */
	public void setDetaljiPanel( DetaljiPanel detaljiPanel ) {
		
		this.detaljiPanel = detaljiPanel;
		
	}
	
}
