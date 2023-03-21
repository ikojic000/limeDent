package controller;


import java.util.ArrayList;
import java.util.stream.Collectors;

import controller.observer.PatientListObservable;
import controller.observer.PatientListObserver;
import dao.PatientDAO;
import model.Patient;
import model.tableModels.PatientTableModel;
import view.viewPanel.HomePanel;


public class HomeController implements PatientListObserver {
	
	private ArrayList<Patient> patientList = new ArrayList<Patient>();
	private PatientTableModel tableModel;
	private HomePanel homePanel;
	private PatientDAO patientDAO;
	private PatientCheck patientCheck;
	
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
	
	
	public Patient getSelectedPatient( int row ) {
		
		return tableModel.getPatientList().get( row );
		
	}
	
	
	public ArrayList<Patient> getPatients() {
		
		patientList = patientDAO.getAllPatients();
		return patientList;
		
	}
	
	
	public Patient getPatientByID( Integer id ) {
		
		Patient patient = patientDAO.getPatientByID( id );
		return patient;
		
	}
	
	
	public void setTableData() {
		
		tableModel = new PatientTableModel( patientList );
		homePanel.getTable().setModel( tableModel );
		
	}
	
	
	public void deletePatient( int row ) {
		
		Patient patient = getSelectedPatient( row );
		
		tableModel.getPatientList().remove( patient );
		patientList.remove( patient );
		tableModel.setPatientList( patientList );
		tableModel.fireTableDataChanged();
		patientDAO.deletePatient( patient );
		
		homePanel.clearAll();
		
	}
	
	
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
