package model;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 * 
 * @author ikojic000
 * 
 *         The MedicalExam class represents a medical examination that can be
 *         carried out on a patient. It contains various attributes such as the
 *         examination ID, examination details, doctor ID and name, patient ID
 *         and name, and examination date. The class provides constructors to
 *         create MedicalExam objects with different combinations of attributes,
 *         as well as getter and setter methods for each attribute. The
 *         getDateFormatted() method returns the examination date in the format
 *         "dd-MM-yyyy HH:mm".
 * 
 */
public class MedicalExam {
	
	private Integer id;
	private String info;
	private Integer idDoctor;
	private String doctorName;
	private Integer idPatient;
	private String patientName;
	private LocalDateTime date;
	
	/**
	 * 
	 * Creates a MedicalExam object with the specified ID.
	 * 
	 * @param id the examination ID
	 */
	public MedicalExam( Integer id ) {
		
		this.id = id;
		
	}
	
	
	/**
	 * 
	 * Creates a MedicalExam object with the specified ID, examination details, and
	 * doctor name.
	 * 
	 * @param id         the examination ID
	 * @param info       the examination details
	 * @param doctorName the name of the doctor who carried out the examination
	 */
	public MedicalExam( Integer id , String info , String doctorName ) {
		
		this.id = id;
		this.info = info;
		this.doctorName = doctorName;
		
	}
	
	
	/**
	 * 
	 * Creates a MedicalExam object with the specified examination details, doctor
	 * ID, and patient ID.
	 * 
	 * @param info      the examination details
	 * @param idDoctor  the ID of the doctor who carried out the examination
	 * @param idPatient the ID of the patient who underwent the examination
	 */
	public MedicalExam( String info , Integer idDoctor , Integer idPatient ) {
		
		this.info = info;
		this.idDoctor = idDoctor;
		this.idPatient = idPatient;
		
	}
	
	
	/**
	 * 
	 * Creates a MedicalExam object with all the specified attributes.
	 * 
	 * @param id          the examination ID
	 * @param info        the examination details
	 * @param idDoctor    the ID of the doctor who carried out the examination
	 * @param doctorName  the name of the doctor who carried out the examination
	 * @param idPatient   the ID of the patient who underwent the examination
	 * @param patientName the name of the patient who underwent the examination
	 * @param date        the date and time when the examination was carried out
	 */
	public MedicalExam( Integer id , String info , Integer idDoctor , String doctorName , Integer idPatient ,
						String patientName , LocalDateTime date ) {
		
		this.id = id;
		this.info = info;
		this.idDoctor = idDoctor;
		this.doctorName = doctorName;
		this.idPatient = idPatient;
		this.patientName = patientName;
		this.date = date;
		
	}
	
	
	/**
	 * 
	 * Returns the examination ID.
	 * 
	 * @return the examination ID
	 */
	public Integer getId() {
		
		return id;
		
	}
	
	
	/**
	 * 
	 * Sets the examination ID.
	 * 
	 * @param id the examination ID to set
	 */
	public void setId( Integer id ) {
		
		this.id = id;
		
	}
	
	
	/**
	 * 
	 * Returns the examination details.
	 * 
	 * @return the examination details
	 */
	public String getInfo() {
		
		return info;
		
	}
	
	
	/**
	 * 
	 * Sets the examination details.
	 * 
	 * @param info the examination details to set
	 */
	public void setInfo( String info ) {
		
		this.info = info;
		
	}
	
	
	/**
	 * 
	 * Returns the ID of the doctor associated with this appointment.
	 * 
	 * @return the ID of the doctor
	 */
	public Integer getIdDoctor() {
		
		return idDoctor;
		
	}
	
	
	/**
	 * 
	 * Sets the ID of the doctor associated with this appointment.
	 * 
	 * @param idDoctor the ID of the doctor
	 */
	public void setIdDoctor( Integer idDoctor ) {
		
		this.idDoctor = idDoctor;
		
	}
	
	
	/**
	 * 
	 * Returns the name of the doctor associated with this appointment.
	 * 
	 * @return the name of the doctor
	 */
	public String getDoctorName() {
		
		return doctorName;
		
	}
	
	
	/**
	 * 
	 * Sets the name of the doctor associated with this appointment.
	 * 
	 * @param doctorName the name of the doctor
	 */
	public void setDoctorName( String doctorName ) {
		
		this.doctorName = doctorName;
		
	}
	
	
	/**
	 * 
	 * Returns the ID of the patient associated with this appointment.
	 * 
	 * @return the ID of the patient
	 */
	public Integer getIdPatient() {
		
		return idPatient;
		
	}
	
	
	/**
	 * 
	 * Sets the ID of the patient associated with this appointment.
	 * 
	 * @param idPatient the ID of the patient
	 */
	public void setIdPatient( Integer idPatient ) {
		
		this.idPatient = idPatient;
		
	}
	
	
	/**
	 * 
	 * Returns the name of the patient associated with this appointment.
	 * 
	 * @return the name of the patient
	 */
	public String getPatientName() {
		
		return patientName;
		
	}
	
	
	/**
	 * 
	 * Sets the name of the patient associated with this appointment.
	 * 
	 * @param patientName the name of the patient
	 */
	public void setPatientName( String patientName ) {
		
		this.patientName = patientName;
		
	}
	
	
	/**
	 * 
	 * Returns the date and time of this appointment.
	 * 
	 * @return the date and time of this appointment
	 */
	public LocalDateTime getDate() {
		
		return date;
		
	}
	
	
	/**
	 * 
	 * Returns a formatted string representation of the date and time of this
	 * appointment.
	 * 
	 * @return a formatted string representation of the date and time of this
	 *         appointment
	 */
	public String getDateFormatted() {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern( "dd-MM-yyyy HH:mm" );
		return getDate().format( formatter );
		
	}
	
	
	/**
	 * 
	 * Sets the date and time of this appointment.
	 * 
	 * @param date the date and time of this appointment
	 */
	public void setDate( LocalDateTime date ) {
		
		this.date = date;
		
	}
	
}
