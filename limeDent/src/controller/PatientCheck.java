package controller;


import java.util.ArrayList;

import model.Patient;


public class PatientCheck extends Thread {
	
	private boolean running;
	private final static int SLEEP_TIME = 30 * 1000;
	private HomeController homeController;
	private ArrayList<Patient> databasePatients;
	
	public PatientCheck( HomeController homeController ) {
		
		running = true;
		this.homeController = homeController;
		
	}
	
	
	@Override
	public void run() {
		
		Thread.currentThread().setName( "PatientCheckThread" );
		System.out.println( "*** PatientCheckThread started on " + Thread.currentThread().getName() + "***" );
		
		while ( running ) {
			
			try {
				
				System.out.println( "Thread " + Thread.currentThread().getName() + " checking the Patient list..." );
				
				isSameData( homeController.getPatientList() );
				
				Thread.sleep( SLEEP_TIME );
				
			} catch ( Exception e ) {
				
				e.printStackTrace();
				
			}
			
		}
		
	}
	
	
	private void isSameData( ArrayList<Patient> controllerPatients ) {
		
		databasePatients = homeController.getPatients();
		
		if ( controllerPatients.size() != databasePatients.size() ) {
			
			System.out.println( "Patients - Not same size" );
			homeController.updatePatientList();
			
		} else {
			
			System.out.println( "Patients - Same size" );
			System.out.println( "Checking individual patients" );
			
			for ( int i = 0; i < controllerPatients.size(); i++ ) {
				
				if ( controllerPatients.get( i ).equals( databasePatients.get( i ) ) ) {
					
				} else {
					
					System.out.println( "Patient to update - " + controllerPatients.get( i ).getName() );
					setNewPatientData( controllerPatients.get( i ) , databasePatients.get( i ) );
					
					homeController.setTableData();
					
				}
				
			}
			
		}
		
	}
	
	
	private void setNewPatientData( Patient controllerPatient , Patient databasePatient ) {
		
		controllerPatient.setName( databasePatient.getName() );
		controllerPatient.setOib( databasePatient.getOib() );
		controllerPatient.setJmbg( databasePatient.getJmbg() );
		controllerPatient.setAddress( databasePatient.getAddress() );
		controllerPatient.setCity( databasePatient.getCity() );
		controllerPatient.setPhone( databasePatient.getPhone() );
		controllerPatient.setMail( databasePatient.getMail() );
		controllerPatient.setLastExam( databasePatient.getLastExam() );
		controllerPatient.setProfilePhoto( databasePatient.getProfilePhoto() );
		System.out.println( "Patient updated" );
		
	}
	
}
