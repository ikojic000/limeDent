package controller;


import java.util.ArrayList;

import dao.MedicalExamDAO;
import model.MedicalExam;
import model.Patient;
import model.tableModels.MedicalExamTableModel;
import view.viewPanel.NoviPregledPanel;


public class NoviPregledController {
	
	private ArrayList<MedicalExam> medicalExamList = new ArrayList<MedicalExam>();
	private NoviPregledPanel noviPregledPanel;
	private MedicalExamDAO medicalExamDAO;
	private Patient patient;
	
	public NoviPregledController( NoviPregledPanel noviPregledPanel ) {
		
		this.medicalExamDAO = new MedicalExamDAO();
		this.noviPregledPanel = noviPregledPanel;
		
	}
	
	
	public MedicalExam getSelectedExam( int row ) {
		
		return medicalExamList.get( row );
		
	}
	
	
	public ArrayList<MedicalExam> getMedicalExamList() {
		
		medicalExamList = medicalExamDAO.getAllExamsByPatientID( patient );
		return medicalExamList;
		
	}
	
	
	public void setTableData() {
		
		medicalExamList = getMedicalExamList();
		MedicalExamTableModel tableModel = new MedicalExamTableModel( medicalExamList );
		noviPregledPanel.getTable().setModel( tableModel );
		
	}
	
	
	public void deleteExam( int row ) {
		
		MedicalExam exam = getSelectedExam( row );
		medicalExamList.remove( exam );
		medicalExamDAO.deleteExam( exam );
		patient.setLastExam( medicalExamList.size() > 0 ? medicalExamList.get( 0 ).getDate() : null );
		
		noviPregledPanel.clearAll();
		
	}
	
	
	public void addExam( MedicalExam exam ) {
		
		medicalExamList = medicalExamDAO.addExam( exam , patient );
		MedicalExamTableModel tableModel = new MedicalExamTableModel( medicalExamList );
		noviPregledPanel.getTable().setModel( tableModel );
		patient.setLastExam( medicalExamList.get( 0 ).getDate() );
		
	}
	
	
	public void setPatient( Patient patient ) {
		
		this.patient = patient;
		
	}
	
}
