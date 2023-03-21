package controller;


import java.util.ArrayList;
import java.util.stream.Collectors;

import controller.observer.PatientListObservable;
import controller.observer.PatientListObserver;
import dao.PatientDAO;
import model.Patient;
import model.tableModels.PatientTableModel;
import view.viewPanel.HomePanel;


/**
 * 
 * @author ikojic000
 *
 *         The HomeController class implements PatientListObserver and manages
 *         the interaction between the patient data, the database, and the
 *         HomePanel UI component. It handles adding, removing, and updating
 *         patients, as well as filtering the list of patients by name search.
 */
public class HomeController implements PatientListObserver {
	
	private ArrayList<Patient> patientList = new ArrayList<Patient>();
	private PatientTableModel tableModel;
	private HomePanel homePanel;
	private PatientDAO patientDAO;
	private PatientCheck patientCheck;
	
	/**
	 * 
	 * Constructs a new HomeController object with a HomePanel UI component and
	 * initializes it with the patient data.
	 * 
	 * @param homePanel the HomePanel UI component
	 */
	public HomeController( HomePanel homePanel ) {
		
		this.homePanel = homePanel;
		this.patientDAO = new PatientDAO();
		this.patientList = getPatients();
		PatientListObservable.getInstance().addObserver( this );
		this.patientCheck = new PatientCheck( this );
		
	}
	
	
	public void startPatientCheckThread() {
		
		patientCheck.start();
		
	}
	
	
	/**
	 * 
	 * Gets the patient object at the specified row in the patient table.
	 * 
	 * @param row the row index of the patient object to retrieve
	 * @return the patient object at the specified row
	 */
	public Patient getSelectedPatient( int row ) {
		
		return tableModel.getPatientList().get( row );
		
	}
	
	
	/**
	 * 
	 * Retrieves all patients from the database and returns them as an ArrayList.
	 * 
	 * @return the ArrayList of all patients
	 */
	public ArrayList<Patient> getPatients() {
		
		patientList = patientDAO.getAllPatients();
		return patientList;
		
	}
	
	
	/**
	 * 
	 * Retrieves the patient object with the specified ID from the database.
	 * 
	 * @param id the ID of the patient to retrieve
	 * @return the patient object with the specified ID
	 */
	public Patient getPatientByID( Integer id ) {
		
		Patient patient = patientDAO.getPatientByID( id );
		return patient;
		
	}
	
	
	/**
	 * 
	 * Sets the data for the patient table UI component with the current patient
	 * data.
	 */
	public void setTableData() {
		
		tableModel = new PatientTableModel( patientList );
		homePanel.getTable().setModel( tableModel );
		
	}
	
	
	/**
	 * 
	 * Deletes the patient object at the specified row in the patient table from the
	 * database and updates the UI component accordingly.
	 * 
	 * @param row the row index of the patient object to delete
	 */
	public void deletePatient( int row ) {
		
		Patient patient = getSelectedPatient( row );
		
		tableModel.getPatientList().remove( patient );
		patientList.remove( patient );
		tableModel.setPatientList( patientList );
		tableModel.fireTableDataChanged();
		patientDAO.deletePatient( patient );
		
		homePanel.clearAll();
		
	}
	
	
	/**
	 * 
	 * Filters the list of patients by name search and updates the UI component
	 * accordingly.
	 */
	public void setSearchData() {
		
		String search = homePanel.getTxtSearch().getText();
		
		if ( search == null || search.isEmpty() ) {
			
			tableModel.setPatientList( patientList );
			
		} else {
			
			ArrayList<Patient> searchList = patientList.stream()
					.filter( p -> p.getName().toLowerCase().contains( search.toLowerCase() ) )
					.collect( Collectors.toCollection( ArrayList::new ) );
			
			tableModel.setPatientList( searchList );
			
		}
		
	}
	
	
	/**
	 * Updates the patient list data when changes are made to the database
	 */
	@Override
	public void updatePatientList() {
		
		this.patientList = getPatients();
		tableModel.setPatientList( patientList );
		
	}
	
	
	/**
	 * @return the patientList
	 */
	public ArrayList<Patient> getPatientList() {
		
		return patientList;
		
	}
	
}
