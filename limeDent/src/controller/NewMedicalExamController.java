package controller;


import java.util.ArrayList;

import dao.MedicalExamDAO;
import model.MedicalExam;
import model.Patient;
import model.tableModels.MedicalExamTableModel;
import view.viewPanel.NewMedicalExamPanel;


/**
 * 
 * @author ikojic000
 *
 * 
 *         The NoviPregledController class represents a controller for
 *         NoviPregledPanel, responsible for handling user interactions with the
 *         panel and for retrieving and modifying medical exams related to a
 *         specific patient.
 */
public class NewMedicalExamController {
	
	private ArrayList<MedicalExam> medicalExamList = new ArrayList<MedicalExam>();
	private NewMedicalExamPanel noviPregledPanel;
	private MedicalExamDAO medicalExamDAO;
	private Patient patient;
	
	/**
	 * 
	 * Constructs a NoviPregledController object with the specified
	 * NoviPregledPanel. Initializes the medical exam DAO for retrieving and
	 * modifying medical exams.
	 * 
	 * @param noviPregledPanel The NoviPregledPanel associated with this controller.
	 */
	public NewMedicalExamController( NewMedicalExamPanel noviPregledPanel ) {
		
		this.medicalExamDAO = new MedicalExamDAO();
		this.noviPregledPanel = noviPregledPanel;
		
	}
	
	
	/**
	 * 
	 * Returns the MedicalExam object selected by the user in the table.
	 * 
	 * @param row The row index of the selected MedicalExam object.
	 * @return The selected MedicalExam object.
	 */
	public MedicalExam getSelectedExam( int row ) {
		
		return medicalExamList.get( row );
		
	}
	
	
	/**
	 * 
	 * Retrieves all medical exams related to the current patient from the medical
	 * exam DAO. Updates the list of medical exams stored in the controller with the
	 * retrieved exams.
	 * 
	 * @return An ArrayList containing all medical exams related to the current
	 *         patient.
	 */
	public ArrayList<MedicalExam> getMedicalExamList() {
		
		medicalExamList = medicalExamDAO.getAllExamsByPatientID( patient );
		return medicalExamList;
		
	}
	
	
	/**
	 * 
	 * Updates the table displayed in the NoviPregledPanel with the list of medical
	 * exams.
	 */
	public void setTableData() {
		
		medicalExamList = getMedicalExamList();
		MedicalExamTableModel tableModel = new MedicalExamTableModel( medicalExamList );
		noviPregledPanel.getTable().setModel( tableModel );
		
	}
	
	
	/**
	 * 
	 * Deletes a selected medical exam from the list of medical exams stored in the
	 * controller and from the database through the medical exam DAO. Updates the
	 * patient's last exam date. Clears all fields in the NoviPregledPanel.
	 * 
	 * @param row The row index of the selected MedicalExam object to be deleted.
	 */
	public void deleteExam( int row ) {
		
		MedicalExam exam = getSelectedExam( row );
		medicalExamList.remove( exam );
		medicalExamDAO.deleteExam( exam );
		patient.setLastExam( medicalExamList.size() > 0 ? medicalExamList.get( 0 ).getDate() : null );
		
		noviPregledPanel.clearAll();
		
	}
	
	
	/**
	 * 
	 * Adds a new medical exam to the list of medical exams stored in the controller
	 * and to the database through the medical exam DAO. Updates the table displayed
	 * in the NoviPregledPanel with the updated list of medical exams. Updates the
	 * patient's last exam date.
	 * 
	 * @param exam The MedicalExam object to be added to the list of medical exams.
	 */
	public void addExam( MedicalExam exam ) {
		
		medicalExamList = medicalExamDAO.addExam( exam , patient );
		MedicalExamTableModel tableModel = new MedicalExamTableModel( medicalExamList );
		noviPregledPanel.getTable().setModel( tableModel );
		patient.setLastExam( medicalExamList.get( 0 ).getDate() );
		
	}
	
	
	/**
	 * 
	 * Sets the Patient object associated with this NoviPregledController.
	 * 
	 * @param patient the Patient object to be associated with this
	 *                NoviPregledController
	 */
	public void setPatient( Patient patient ) {
		
		this.patient = patient;
		
	}
	
}
