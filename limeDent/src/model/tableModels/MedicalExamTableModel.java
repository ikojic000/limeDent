package model.tableModels;


import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import model.MedicalExam;


/**
 *
 * @author ikojic000
 *
 *         The MedicalExamTableModel class is a table model for displaying and
 *         editing medical exam information. It extends the AbstractTableModel
 *         class. The table model contains a list of MedicalExam objects and
 *         defines the column names for the table.
 *		
 */
public class MedicalExamTableModel extends AbstractTableModel {
	
	private static final long serialVersionUID = -8388688554313142598L;
	private ArrayList<MedicalExam> examList;
	private String[] columnNames = { "Datum" , "Doktor" , "Pregled" , "" };
	boolean[] canEdit = new boolean[] { false , false , false , true };
	
	/**
	 *
	 * Constructs an MedicalExamTableModel object with the given list of Offer
	 * objects.
	 *
	 * @param examList - the list of MedicalExam objects to be displayed in the
	 *                 table
	 */
	public MedicalExamTableModel( ArrayList<MedicalExam> examList ) {
		
		this.examList = examList;
		
	}
	
	
	/**
	 *
	 * Returns the number of rows in the table, which is equal to the size of the
	 * examList.
	 *
	 * @return the number of rows in the table
	 */
	@Override
	public int getRowCount() {
		
		return examList.size();
		
	}
	
	
	/**
	 *
	 * Returns the number of columns in the table, which is equal to the length of
	 * the columnNames array.
	 *
	 * @return the number of columns in the table
	 */
	@Override
	public int getColumnCount() {
		
		return columnNames.length;
		
	}
	
	
	/**
	 *
	 * Returns the name of the column at the given index.
	 *
	 * @param column the index of the column
	 * @return the name of the column at the given index
	 */
	@Override
	public String getColumnName( int column ) {
		
		return columnNames[column];
		
	}
	
	
	/**
	 *
	 * Returns whether or not the cell at the given row and column can be edited.
	 *
	 * @param rowIndex    the index of the row
	 * @param columnIndex the index of the column
	 * @return true if the cell is editable, false otherwise
	 */
	@Override
	public boolean isCellEditable( int rowIndex , int columnIndex ) {
		
		return canEdit[columnIndex];
		
	}
	
	
	/**
	 *
	 * Returns the value at the given row and column in the table.
	 *
	 * @param rowIndex    - the index of the row
	 * @param columnIndex - the index of the column
	 * @return the value at the given row and column
	 */
	@Override
	public Object getValueAt( int rowIndex , int columnIndex ) {
		
		MedicalExam exam = examList.get( rowIndex );
		
		switch (columnIndex) {
			
			case 0:
				return exam.getDateFormatted();
			
			case 1:
				return exam.getDoctorName();
			
			case 2:
				return exam.getInfo();
			
			default:
				return null;
			
		}
		
	}
	
	
	/**
	 *
	 * Returns the list of MedicalExam objects displayed in the table.
	 *
	 * @return the list of MedicalExam objects
	 */
	public ArrayList<MedicalExam> getExamList() {
		
		return examList;
		
	}
	
}
