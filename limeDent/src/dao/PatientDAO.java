package dao;


import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import model.Database;
import model.Patient;


/**
 * @author ikojic000
 * 
 *         The PatientDAO class represents the data access object for patients.
 *         It provides methods to retrieve and modify patient data in the
 *         database.
 *
 */
public class PatientDAO {
	
	/**
	 * Constructs a new PatientDAO object.
	 */
	public PatientDAO() {
		
	}
	
	
	/**
	 * 
	 * Retrieves all patients from the database.
	 * 
	 * @return an ArrayList containing all patients.
	 */
	public ArrayList<Patient> getAllPatients() {
		
		Connection connection = Database.getDatabase().getConnection();
		ArrayList<Patient> allPatients = new ArrayList<Patient>();
		
		String sql = "SELECT p.*, m.date as lastExam " + "FROM patients p "
				+ "LEFT JOIN (SELECT idPatient, MAX(date) AS date FROM medicalexam GROUP BY idPatient) m ON p.id = m.idPatient";
		
		try {
			
			PreparedStatement pst = connection.prepareStatement( sql );
			ResultSet rs = pst.executeQuery();
			ImageIcon profilePhoto = null;
			
			while ( rs.next() ) {
				
				Integer id = rs.getInt( "id" );
				String name = rs.getString( "imePrezime" );
				Long oib = rs.getLong( "oib" );
				Long jmbg = rs.getLong( "jmbg" );
				String mail = rs.getString( "mail" );
				String phone = rs.getString( "phone" );
				String address = rs.getString( "patientAddress" );
				String city = rs.getString( "patientCity" );
				LocalDateTime lastExam = rs.getTimestamp( "lastExam" ) != null
						? rs.getTimestamp( "lastExam" ).toLocalDateTime()
						: null;
				String medicalHistory = rs.getString( "patientMedicalHistory" );
				String alergies = rs.getString( "patientAlergies" );
				
				if ( rs.getBinaryStream( "profilePhoto" ) != null ) {
					
					BufferedImage im = ImageIO.read( rs.getBinaryStream( "profilePhoto" ) );
					profilePhoto = new ImageIcon( im );
					
				} else {
					
					profilePhoto = new ImageIcon( this.getClass().getResource( "/MPDLogo_Transparent.png" ) );
					
				}
				
				Patient patient = new Patient( id , name , oib , jmbg , mail , phone , address , city , lastExam ,
						medicalHistory , alergies , profilePhoto );
				allPatients.add( patient );
				
			}
			
		} catch ( SQLException | IOException e ) {
			
			e.printStackTrace();
			
		}
		
		return allPatients;
		
	}
	
	
	/**
	 * 
	 * Retrieves a patient from the database by ID.
	 * 
	 * @param id - the ID of the patient to retrieve.
	 * 
	 * @return the patient with the specified ID, or null if not found.
	 */
	public Patient getPatientByID( Integer id ) {
		
		Patient patient = null;
		String sql = "SELECT p.*, m.date AS lastExam\r\n" + "FROM patients p \r\n" + "LEFT JOIN (\r\n"
				+ "  SELECT idPatient, MAX(date) AS date \r\n" + "  FROM medicalexam \r\n" + "  GROUP BY idPatient\r\n"
				+ ") m ON p.id = m.idPatient \r\n" + "WHERE p.id = ?;";
		
		Connection connection = Database.getDatabase().getConnection();
		
		try {
			
			PreparedStatement pst = connection.prepareStatement( sql );
			pst.setString( 1 , id.toString() );
			ResultSet rs = pst.executeQuery();
			ImageIcon profilePhoto = null;
			
			while ( rs.next() ) {
				
				LocalDateTime lastExam = rs.getTimestamp( "lastExam" ) != null
						? rs.getTimestamp( "lastExam" ).toLocalDateTime()
						: null;
				
				if ( rs.getBinaryStream( "profilePhoto" ) != null ) {
					
					BufferedImage im = ImageIO.read( rs.getBinaryStream( "profilePhoto" ) );
					profilePhoto = new ImageIcon( im );
					
				} else {
					
					profilePhoto = new ImageIcon( this.getClass().getResource( "/MPDLogo_Transparent.png" ) );
					
				}
				
				patient = new Patient( rs.getInt( "id" ) , rs.getString( "imePrezime" ) , rs.getLong( "oib" ) ,
						rs.getLong( "jmbg" ) , rs.getString( "mail" ) , rs.getString( "phone" ) ,
						rs.getString( "patientAddress" ) , rs.getString( "patientCity" ) , lastExam ,
						rs.getString( "patientMedicalHistory" ) , rs.getString( "patientAlergies" ) , profilePhoto );
				
			}
			
		} catch ( SQLException | IOException e ) {
			
			e.printStackTrace();
			
		}
		
		return patient;
		
	}
	
	
	/**
	 * 
	 * Adds a new patient to the database.
	 * 
	 * @param patient - the patient to add to the database
	 */
	public void addPatient( Patient patient ) {
		
		String name = patient.getName();
		Long oib = patient.getOib();
		Long jmbg = patient.getJmbg();
		String phone = patient.getPhone();
		String mail = patient.getMail();
		String address = patient.getAddress();
		String city = patient.getCity();
		String medicalHistory = patient.getMedicalHistory();
		String alergies = patient.getAlergies();
		
		Connection connection = Database.getDatabase().getConnection();
		
		try {
			
			String sql = "insert into patients(imePrezime, mail, phone, oib, jmbg, patientAddress, patientCity, patientMedicalHistory, patientAlergies) values (?, ?, ?, ?, ?, ? ,?, ? ,?)";
			PreparedStatement pst = connection.prepareStatement( sql );
			pst.setString( 1 , name );
			pst.setString( 2 , mail );
			pst.setString( 3 , phone );
			pst.setString( 4 , oib.toString() );
			pst.setString( 5 , jmbg.toString() );
			pst.setString( 6 , address );
			pst.setString( 7 , city );
			pst.setString( 8 , medicalHistory );
			pst.setString( 9 , alergies );
			
			pst.executeUpdate();
			
		} catch ( SQLException e ) {
			
			e.printStackTrace();
			
		}
		
	}
	
	
	/**
	 * 
	 * Updates the information of a patient in the database.
	 * 
	 * @param patient - the patient whose information will be updated
	 */
	public void updatePatient( Patient patient ) {
		
		Integer id = patient.getId();
		String name = patient.getName();
		Long oib = patient.getOib();
		Long jmbg = patient.getJmbg();
		String phone = patient.getPhone();
		String mail = patient.getMail();
		String address = patient.getAddress();
		String city = patient.getCity();
		String medicalHistory = patient.getMedicalHistory();
		String alergies = patient.getAlergies();
		
		Connection connection = Database.getDatabase().getConnection();
		
		try {
			
			String sql = "UPDATE patients SET imePrezime=?, mail=?, phone=?, patientAddress=?, patientCity=?, patientMedicalHistory=?, patientAlergies=?, oib=? ,jmbg=? where id = ?";
			PreparedStatement pst = connection.prepareStatement( sql );
			pst.setString( 1 , name );
			pst.setString( 2 , mail );
			pst.setString( 3 , phone );
			pst.setString( 4 , address );
			pst.setString( 5 , city );
			pst.setString( 6 , medicalHistory );
			pst.setString( 7 , alergies );
			pst.setString( 8 , oib.toString() );
			pst.setString( 9 , jmbg.toString() );
			pst.setString( 10 , id.toString() );
			pst.executeUpdate();
			
		} catch ( SQLException e ) {
			
			e.printStackTrace();
			
		}
		
	}
	
	
	/**
	 * 
	 * Updates the profile photo of a patient in the database.
	 * 
	 * @param patient - the patient whose photo will be updated
	 */
	public void updatePatientPhoto( Patient patient ) {
		
		Integer id = patient.getId();
		byte[] profilePhotoBytes = getProfilePhotoBytes( patient.getProfilePhoto() );
		
		Connection connection = Database.getDatabase().getConnection();
		
		try {
			
			String sql = "UPDATE patients SET profilePhoto=? where id = ?";
			PreparedStatement pst = connection.prepareStatement( sql );
			pst.setBytes( 1 , profilePhotoBytes );
			pst.setString( 2 , id.toString() );
			pst.executeUpdate();
			
		} catch ( SQLException e ) {
			
			e.printStackTrace();
			
		}
		
	}
	
	
	/**
	 * 
	 * Converts a profile photo to a byte array.
	 * 
	 * @param profile - the profile photo to convert
	 * 
	 * @return a byte array containing the profile photo data
	 */
	private byte[] getProfilePhotoBytes( ImageIcon profile ) {
		
		if ( profile == null ) {
			
			return null;
			
		}
		
		try {
			
			BufferedImage image = new BufferedImage( profile.getIconWidth() , profile.getIconHeight() ,
					BufferedImage.TYPE_INT_RGB );
			Graphics g = image.createGraphics();
			profile.paintIcon( null , g , 0 , 0 );
			g.dispose();
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write( image , "jpg" , baos );
			baos.flush();
			byte[] profilePhotoBytes = baos.toByteArray();
			baos.close();
			return profilePhotoBytes;
			
		} catch ( IOException e ) {
			
			e.printStackTrace();
			
		}
		
		return null;
		
	}
	
	
	/**
	 * 
	 * Deletes a patient from the database.
	 * 
	 * @param patient - the patient to be deleted
	 */
	public void deletePatient( Patient patient ) {
		
		String sql = "delete from patients where id=? and imePrezime=?;";
		
		Connection connection = Database.getDatabase().getConnection();
		
		try {
			
			PreparedStatement pst = connection.prepareStatement( sql );
			pst.setInt( 1 , patient.getId() );
			pst.setString( 2 , patient.getName() );
			pst.executeUpdate();
			
		} catch ( SQLException e ) {
			
			e.printStackTrace();
			
		}
		
	}
	
	
	/**
	 * 
	 * Searches for patients based on the provided search string. Uses SQL query to
	 * retrieve the data from the database.
	 * 
	 * @param search the search string to filter patients
	 * @return an ArrayList of Patient objects that match the search criteria
	 */
	public ArrayList<Patient> searchPatient( String search ) {
		
		ArrayList<Patient> searchedPatients = new ArrayList<Patient>();
		String sql = "SELECT p.*, m.date AS lastExam\r\n" + "FROM patients p \r\n" + "LEFT JOIN (\r\n"
				+ "  SELECT idPatient, MAX(date) AS date \r\n" + "  FROM medicalexam \r\n" + "  GROUP BY idPatient\r\n"
				+ ") m ON p.id = m.idPatient \r\n" + "WHERE p.imePrezime like ?;";
		
		Connection connection = Database.getDatabase().getConnection();
		
		try {
			
			PreparedStatement pst = connection.prepareStatement( sql );
			pst.setString( 1 , "%" + search + "%" );
			ResultSet rs = pst.executeQuery();
			
			while ( rs.next() ) {
				
				LocalDateTime lastExam = rs.getTimestamp( "lastExam" ) != null
						? rs.getTimestamp( "lastExam" ).toLocalDateTime()
						: null;
				
				Patient patient = new Patient( rs.getInt( "id" ) , rs.getString( "imePrezime" ) , rs.getLong( "oib" ) ,
						rs.getString( "phone" ) , rs.getString( "mail" ) , lastExam );
				
				searchedPatients.add( patient );
				
			}
			
		} catch ( SQLException e ) {
			
			e.printStackTrace();
			
		}
		
		return searchedPatients;
		
	}
	
}
