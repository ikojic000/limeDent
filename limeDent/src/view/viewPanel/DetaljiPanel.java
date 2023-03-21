package view.viewPanel;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingWorker;

import controller.DetaljiController;
import controller.ImageCompressionThread;
import design.button.ButtonShadow;
import design.messageDialog.Message;
import design.notification.Notification;
import design.notification.Notification.Location;
import design.notification.Notification.NotificationType;
import design.panel.ImageAvatar;
import design.panel.RoundedShadowPanel;
import design.txtInput.TextArea;
import design.txtInput.TextAreaScroll;
import design.txtInput.TextField;
import jnafilechooser.api.JnaFileChooser;
import model.Patient;
import raven.glasspanepopup.GlassPanePopup;
import view.View;


/**
 * 
 * @author ikojic000
 * 
 *         A UI panel for displaying and editing patient details. This panel
 *         extends the RoundedShadowPanel class. It includes various text
 *         fields, buttons, labels, and an image component. The user can view,
 *         edit and delete patient details through this panel. This panel also
 *         contains a DetaljiController object to handle user actions.
 * 		
 */
public class DetaljiPanel extends RoundedShadowPanel {
	
	private static final long serialVersionUID = 4922166281676916917L;
	private TextField txtAdresa;
	private TextField txtJMBG;
	private ButtonShadow btnIzbrisi;
	private ButtonShadow btnUredi;
	private TextField txtMail;
	private TextField txtBrojMobitela;
	private TextField txtOIB;
	private TextField txtimePrezime;
	private JLabel lblFormTitle;
	private TextField txtGrad;
	private TextAreaScroll txtPovijestBolestiScroll;
	private TextArea txtPovijestBolesti;
	private TextAreaScroll txtAlergijeScroll;
	private TextArea txtAlergije;
	private ImageAvatar imgPacijent;
	private ButtonShadow btnChooseImg;
	private Notification notification;
//	*** VIEW + PANEL ***
	@SuppressWarnings( "unused" )
	private View view;
	private CardPanel cardParent;
	private Patient patient;
	private DetaljiController detaljiController;
	
	/**
	 * 
	 * Constructs a new DetaljiPanel with the given CardPanel and View / JFrame
	 * objects. Initializes all UI components and sets up the panel's layout.
	 * 
	 * @param cardParent the CardPanel parent for this panel
	 * @param view       the View parent for this panel
	 */
	public DetaljiPanel( CardPanel cardParent , View view ) {
		
		super( 20 );
		this.view = view;
		this.cardParent = cardParent;
		this.patient = new Patient( null );
		this.detaljiController = new DetaljiController();
		
		setOpaque( false );
		setFont( new Font( "Century Gothic" , Font.BOLD , 16 ) );
		setBackground( new Color( 244 , 244 , 249 ) );
		
		notification = new Notification( view , NotificationType.SUCCESS , Location.TOP_CENTER , "" , "" );
		
		lblFormTitle = new JLabel( "Pacijent: " + patient.getName() );
		lblFormTitle.setForeground( new Color( 121 , 118 , 118 ) );
		lblFormTitle.setFont( new Font( "Century Gothic" , Font.BOLD , 18 ) );
		
		imgPacijent = new ImageAvatar();
		imgPacijent.setBorderSize( 2 );
		imgPacijent.setForeground( new Color( 44 , 51 , 51 ) );
		imgPacijent.setIcon( new ImageIcon( this.getClass().getResource( "/MPDLogo_Transparent.png" ) ) );
		
		btnChooseImg = new ButtonShadow();
		btnChooseImg.setPreferredSize( new Dimension( 170 , 45 ) );
		btnChooseImg.setMinimumSize( new Dimension( 170 , 45 ) );
		btnChooseImg.setMaximumSize( new Dimension( 170 , 45 ) );
		btnChooseImg.setText( "Odaberi fotografiju" );
		btnChooseImg.setFont( new Font( "Century Gothic" , Font.PLAIN , 15 ) );
		btnChooseImg.setFocusPainted( false );
		
		txtimePrezime = new TextField();
		txtimePrezime.setPreferredSize( new Dimension( 340 , 65 ) );
		txtimePrezime.setMinimumSize( new Dimension( 340 , 65 ) );
		txtimePrezime.setMaximumSize( new Dimension( 340 , 65 ) );
		txtimePrezime.setLineColor( new Color( 46 , 191 , 165 ) );
		txtimePrezime.setLabelText( "Ime i prezime" );
		txtimePrezime.setHintTextColor( new Color( 121 , 118 , 118 ) );
		txtimePrezime.setForeground( new Color( 44 , 51 , 51 ) );
		txtimePrezime.setFont( new Font( "Century Gothic" , Font.PLAIN , 15 ) );
		txtimePrezime.setBackground( new Color( 244 , 244 , 249 ) );
		
		txtOIB = new TextField();
		txtOIB.setPreferredSize( new Dimension( 340 , 65 ) );
		txtOIB.setMinimumSize( new Dimension( 340 , 65 ) );
		txtOIB.setMaximumSize( new Dimension( 340 , 65 ) );
		txtOIB.setLineColor( new Color( 46 , 191 , 165 ) );
		txtOIB.setLabelText( "OIB" );
		txtOIB.setHintTextColor( new Color( 121 , 118 , 118 ) );
		txtOIB.setForeground( new Color( 44 , 51 , 51 ) );
		txtOIB.setFont( new Font( "Century Gothic" , Font.PLAIN , 15 ) );
		txtOIB.setBackground( new Color( 244 , 244 , 249 ) );
		
		txtJMBG = new TextField();
		txtJMBG.setPreferredSize( new Dimension( 340 , 65 ) );
		txtJMBG.setMinimumSize( new Dimension( 340 , 65 ) );
		txtJMBG.setMaximumSize( new Dimension( 340 , 65 ) );
		txtJMBG.setLineColor( new Color( 46 , 191 , 165 ) );
		txtJMBG.setLabelText( "JMBG" );
		txtJMBG.setHintTextColor( new Color( 121 , 118 , 118 ) );
		txtJMBG.setForeground( new Color( 44 , 51 , 51 ) );
		txtJMBG.setFont( new Font( "Century Gothic" , Font.PLAIN , 15 ) );
		txtJMBG.setBackground( new Color( 244 , 244 , 249 ) );
		
		txtAdresa = new TextField();
		txtAdresa.setPreferredSize( new Dimension( 340 , 65 ) );
		txtAdresa.setMinimumSize( new Dimension( 340 , 65 ) );
		txtAdresa.setMaximumSize( new Dimension( 340 , 65 ) );
		txtAdresa.setLineColor( new Color( 46 , 191 , 165 ) );
		txtAdresa.setLabelText( "Adresa" );
		txtAdresa.setHintTextColor( new Color( 121 , 118 , 118 ) );
		txtAdresa.setForeground( new Color( 44 , 51 , 51 ) );
		txtAdresa.setFont( new Font( "Century Gothic" , Font.PLAIN , 15 ) );
		txtAdresa.setBackground( new Color( 244 , 244 , 249 ) );
		
		txtGrad = new TextField();
		txtGrad.setPreferredSize( new Dimension( 340 , 65 ) );
		txtGrad.setMinimumSize( new Dimension( 340 , 65 ) );
		txtGrad.setMaximumSize( new Dimension( 340 , 65 ) );
		txtGrad.setLineColor( new Color( 46 , 191 , 165 ) );
		txtGrad.setLabelText( "Grad" );
		txtGrad.setHintTextColor( new Color( 121 , 118 , 118 ) );
		txtGrad.setForeground( new Color( 44 , 51 , 51 ) );
		txtGrad.setFont( new Font( "Century Gothic" , Font.PLAIN , 15 ) );
		txtGrad.setBackground( new Color( 244 , 244 , 249 ) );
		
		txtBrojMobitela = new TextField();
		txtBrojMobitela.setPreferredSize( new Dimension( 340 , 65 ) );
		txtBrojMobitela.setMinimumSize( new Dimension( 340 , 65 ) );
		txtBrojMobitela.setMaximumSize( new Dimension( 340 , 65 ) );
		txtBrojMobitela.setLineColor( new Color( 46 , 191 , 165 ) );
		txtBrojMobitela.setLabelText( "Broj mobitela" );
		txtBrojMobitela.setHintTextColor( new Color( 121 , 118 , 118 ) );
		txtBrojMobitela.setForeground( new Color( 44 , 51 , 51 ) );
		txtBrojMobitela.setFont( new Font( "Century Gothic" , Font.PLAIN , 15 ) );
		txtBrojMobitela.setBackground( new Color( 244 , 244 , 249 ) );
		
		txtMail = new TextField();
		txtMail.setPreferredSize( new Dimension( 340 , 65 ) );
		txtMail.setMinimumSize( new Dimension( 340 , 65 ) );
		txtMail.setMaximumSize( new Dimension( 340 , 65 ) );
		txtMail.setLineColor( new Color( 46 , 191 , 165 ) );
		txtMail.setLabelText( "Mail" );
		txtMail.setHintTextColor( new Color( 121 , 118 , 118 ) );
		txtMail.setForeground( new Color( 44 , 51 , 51 ) );
		txtMail.setFont( new Font( "Century Gothic" , Font.PLAIN , 15 ) );
		txtMail.setBackground( new Color( 244 , 244 , 249 ) );
		
		txtPovijestBolestiScroll = new TextAreaScroll();
		txtPovijestBolestiScroll.setPreferredSize( new Dimension( 250 , 250 ) );
		txtPovijestBolestiScroll.setMinimumSize( new Dimension( 250 , 250 ) );
		txtPovijestBolestiScroll.setLineColor( new Color( 46 , 191 , 165 ) );
		txtPovijestBolestiScroll.setLabelText( "Povijest bolesti" );
		txtPovijestBolestiScroll.setForeground( new Color( 44 , 51 , 51 ) );
		txtPovijestBolestiScroll.setFont( new Font( "Century Gothic" , Font.PLAIN , 15 ) );
		txtPovijestBolestiScroll.setBackground( new Color( 244 , 244 , 249 ) );
		
		txtPovijestBolesti = new TextArea();
		txtPovijestBolesti.setForeground( new Color( 44 , 51 , 51 ) );
		txtPovijestBolesti.setFont( new Font( "Century Gothic" , Font.PLAIN , 15 ) );
		txtPovijestBolestiScroll.setViewportView( txtPovijestBolesti );
		
		txtAlergijeScroll = new TextAreaScroll();
		txtAlergijeScroll.setPreferredSize( new Dimension( 250 , 250 ) );
		txtAlergijeScroll.setMinimumSize( new Dimension( 250 , 250 ) );
		txtAlergijeScroll.setLineColor( new Color( 46 , 191 , 165 ) );
		txtAlergijeScroll.setLabelText( "Alergije" );
		txtAlergijeScroll.setForeground( new Color( 44 , 51 , 51 ) );
		txtAlergijeScroll.setFont( new Font( "Century Gothic" , Font.PLAIN , 15 ) );
		txtAlergijeScroll.setBackground( new Color( 244 , 244 , 249 ) );
		
		txtAlergije = new TextArea();
		txtAlergije.setForeground( new Color( 44 , 51 , 51 ) );
		txtAlergije.setFont( new Font( "Century Gothic" , Font.PLAIN , 15 ) );
		txtAlergijeScroll.setViewportView( txtAlergije );
		
		btnUredi = new ButtonShadow();
		btnUredi.setText( "Ažuriraj" );
		btnUredi.setPreferredSize( new Dimension( 170 , 45 ) );
		btnUredi.setMinimumSize( new Dimension( 170 , 45 ) );
		btnUredi.setMaximumSize( new Dimension( 170 , 45 ) );
		btnUredi.setForeground( new Color( 121 , 118 , 118 ) );
		btnUredi.setFont( new Font( "Century Gothic" , Font.PLAIN , 15 ) );
		btnUredi.setFocusPainted( false );
		
		btnIzbrisi = new ButtonShadow();
		btnIzbrisi.setText( "Izbriši" );
		btnIzbrisi.setPreferredSize( new Dimension( 170 , 45 ) );
		btnIzbrisi.setMinimumSize( new Dimension( 170 , 45 ) );
		btnIzbrisi.setMaximumSize( new Dimension( 170 , 45 ) );
		btnIzbrisi.setForegroundColorOUT( new Color( 146 , 20 , 12 ) );
		btnIzbrisi.setForegroundColorIN( new Color( 146 , 20 , 12 ) );
		btnIzbrisi.setForeground( new Color( 146 , 20 , 12 ) );
		btnIzbrisi.setFont( new Font( "Century Gothic" , Font.PLAIN , 15 ) );
		btnIzbrisi.setFocusPainted( false );
		
		initLayout();
		activatePanel();
		
	}
	
	
	/**
	 * 
	 * Activates the panel by setting up various listeners for its components. These
	 * listeners are used to handle events like button clicks, textfield input,
	 * table selection, etc. Once activated, this panel becomes fully functional and
	 * can be interacted with by the user.
	 */
	private void activatePanel() {
		
		btnIzbrisi.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed( ActionEvent e ) {
				
				notification.setType( NotificationType.WARNING );
				notification.setLblTitle( "Pacijent izbrisan" );
				notification.setLbMessageText( "Pacijent " + patient.getName() + " izbrisan" );
				
				Message msg = new Message();
				msg.setMessageTitle( "Jeste li sigurni da želite izbrisati pacijenta: " + patient.getName() );
				msg.setMessageText( "Pritiskom gumba OK pacijent se briše nepovratno." );
				msg.eventOK( new ActionListener() {
					
					@Override
					public void actionPerformed( ActionEvent ae ) {
						
						detaljiController.deletePatient( patient );
						cardParent.showCard( "homePanel" );
						notification.showNotification();
						GlassPanePopup.closePopupLast();
						
					}
					
				} );
				
				GlassPanePopup.showPopup( msg );
				
			}
			
		} );
		
		btnUredi.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed( ActionEvent e ) {
				
				notification.setType( NotificationType.INFO );
				notification.setLblTitle( "Pacijent ažuriran" );
				notification.setLbMessageText( "Pacijent " + patient.getName() + " ažuriran" );
				
				Message msg = new Message();
				msg.setMessageTitle( "Jeste li sigurni da želite ažurirati pacijenta: " + patient.getName() );
				msg.setMessageText( "Pritiskom gumba OK pacijent će se ažurirati." );
				msg.eventOK( new ActionListener() {
					
					@Override
					public void actionPerformed( ActionEvent ae ) {
						
						detaljiController.updatePatient( patient );
						setData();
						
						GlassPanePopup.closePopupLast();
						notification.showNotification();
						
					}
					
				} );
				
				GlassPanePopup.showPopup( msg );
				
			}
			
		} );
		
		txtOIB.addKeyListener( new KeyAdapter() {
			
			@Override
			public void keyReleased( KeyEvent e ) {
				
				checkOIB( txtOIB.getText() );
				
			}
			
		} );
		
		txtJMBG.addKeyListener( new KeyAdapter() {
			
			@Override
			public void keyReleased( KeyEvent e ) {
				
				checkJMBG( txtJMBG.getText() );
				
			}
			
		} );
		
		txtBrojMobitela.addKeyListener( new KeyAdapter() {
			
			@Override
			public void keyReleased( KeyEvent e ) {
				
				checkPhone( txtBrojMobitela.getText() );
				
			}
			
		} );
		
		txtMail.addKeyListener( new KeyAdapter() {
			
			@Override
			public void keyReleased( KeyEvent e ) {
				
				checkMail( txtMail.getText() );
				
			}
			
		} );
		
		btnChooseImg.addActionListener( new ActionListener() {
			
			public void actionPerformed( ActionEvent e ) {
				
				JnaFileChooser imgChooser = new JnaFileChooser();
				imgChooser.addFilter( "Pictures" , "jpg" , "jpeg" , "png" );
				boolean returnValue = imgChooser.showOpenDialog( null );
				
				if ( returnValue ) {
					
					File selectedFile = imgChooser.getSelectedFile();
					imgPacijent.setIcon( new ImageIcon( selectedFile.getAbsolutePath() ) );
					imgPacijent.repaint();
					
					SwingWorker<Void , Void> worker = new SwingWorker<Void , Void>() {
						
						@Override
						protected Void doInBackground() throws Exception {
							
							ImageCompressionThread imgThread = new ImageCompressionThread( selectedFile , patient ,
									notification );
							imgThread.start();
							imgThread.join();
							return null;
							
						}
						
						
						@Override
						protected void done() {
							
							detaljiController.updatePatientPhoto( patient );
							notification.setType( NotificationType.SUCCESS );
							notification.setLblTitle( "Fotografija promijenjena" );
							notification.setLbMessageText( "Uspiješno ste promijenili fotografiju" );
							notification.showNotification();
							
						}
						
					};
					
					worker.execute();
					System.out.println( "Worker done - " + worker.isDone() );
					
				}
				
			}
			
		} );
		
	}
	
	
	/**
	 * 
	 * Checks if the provided OIB is valid. If the OIB is invalid, displays a helper
	 * text under the OIB TextField indicating the invalid format.
	 * 
	 * @param oib The OIB to check.
	 */
	public void checkOIB( String oib ) {
		
		boolean isOIBValid = true;
		
		if ( oib.length() != 11 ) {
			
			isOIBValid = false;
			
		} else {
			
			char[] chars = oib.toCharArray();
			
			for ( char c : chars ) {
				
				if ( c < '0' || c > '9' ) {
					
					isOIBValid = false;
					
				}
				
			}
			
		}
		
		if ( !isOIBValid ) {
			
			txtOIB.setHelperText( "Neispravan format OIB-a .." );
			
		} else {
			
			txtOIB.setHelperText( "" );
			
		}
		
	}
	
	
	/**
	 * 
	 * Checks if the provided JMBG is valid. If the JMBG is invalid, displays a
	 * helper text under the JMBG TextField indicating the invalid format.
	 * 
	 * @param jmbg The JMBG to check.
	 */
	public void checkJMBG( String jmbg ) {
		
		boolean isJMBGValid = true;
		
		if ( jmbg.length() != 13 ) {
			
			isJMBGValid = false;
			
		} else {
			
			char[] chars = jmbg.toCharArray();
			
			for ( char c : chars ) {
				
				if ( c < '0' || c > '9' ) {
					
					isJMBGValid = false;
					
				}
				
			}
			
		}
		
		if ( !isJMBGValid ) {
			
			txtJMBG.setHelperText( "Neispravan format JMBG-a .." );
			
		} else {
			
			txtJMBG.setHelperText( "" );
			
		}
		
	}
	
	
	/**
	 * 
	 * Checks if the provided phone number is valid. If the phone number is invalid,
	 * displays a helper text under the phone number TextField indicating the
	 * invalid format.
	 * 
	 * @param phone The phone number to check.
	 */
	public void checkPhone( String phone ) {
		
		boolean isPhoneValid = true;
		String regex = "^\\+?\\d{1,4}?[-.\\s]?\\(?\\d{1,3}?\\)?[-.\\s]?\\d{1,4}[-.\\s]?\\d{1,4}[-.\\s]?\\d{1,9}$";
		Pattern pattern = Pattern.compile( regex );
		Matcher matcher = pattern.matcher( phone );
		
		if ( !matcher.matches() ) {
			
			isPhoneValid = false;
			
		}
		
		if ( !isPhoneValid ) {
			
			txtBrojMobitela.setHelperText( "Neispravan format broja mobitela .." );
			
		} else {
			
			txtBrojMobitela.setHelperText( "" );
			
		}
		
	}
	
	
	/**
	 * 
	 * Checks if the provided email is valid. If the email is invalid, displays a
	 * helper text under the email TextField indicating the invalid format.
	 * 
	 * @param mail The email to check.
	 */
	public void checkMail( String mail ) {
		
		boolean isMailValid = true;
		String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}";
		Pattern pattern = Pattern.compile( regex );
		Matcher matcher = pattern.matcher( mail );
		
		if ( !matcher.matches() ) {
			
			isMailValid = false;
			
		}
		
		if ( !isMailValid ) {
			
			txtMail.setHelperText( "Neispravan format mail-a .. " );
			
		} else {
			
			txtMail.setHelperText( null );
			
		}
		
	}
	
	
	/**
	 * 
	 * This method sets the data for the patient into the UI fields. It updates the
	 * label for form title, patient name field, OIB field, JMBG field, address
	 * field, city field, phone field, mail field, medical history field, allergies
	 * field and profile photo field.
	 * 
	 * @param patient An object of Patient class representing the patient whose data
	 *                is to be set in the UI fields.
	 */
	public void setData() {
		
		lblFormTitle.setText( "Pacijent: " + patient.getName() );
		txtimePrezime.setText( patient.getName() );
		txtOIB.setText( patient.getOib().toString() );
		txtJMBG.setText( patient.getJmbg() == null ? "" : patient.getJmbg().toString() );
		txtAdresa.setText( patient.getAddress() );
		txtGrad.setText( patient.getCity() );
		txtBrojMobitela.setText( patient.getPhone() );
		txtMail.setText( patient.getMail() );
		txtPovijestBolesti.setText( patient.getMedicalHistory() );
		txtAlergije.setText( patient.getAlergies() );
		imgPacijent.setIcon( patient.getProfilePhoto() != null ? patient.getProfilePhoto()
				: new ImageIcon( getClass().getResource( "/MPDLogo_Transparent.png" ) ) );
		
	}
	
	
	/**
	 * 
	 * This method clears all the UI fields that are related to the patient data.
	 */
	public void clearAll() {
		
		txtimePrezime.setText( "" );
		txtOIB.setText( "" );
		txtJMBG.setText( "" );
		txtAdresa.setText( "" );
		txtGrad.setText( "" );
		txtBrojMobitela.setText( "" );
		txtMail.setText( "" );
		txtPovijestBolesti.setText( "" );
		txtAlergije.setText( "" );
		
	}
	
	
	/**
	 * 
	 * Initiates the layout of the panel.
	 */
	private void initLayout() {
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 35 , 340 , 100 , 97 , 575 , 35 , 0 };
		gridBagLayout.rowHeights = new int[] { 30 , 23 , 20 , 220 , 35 , 65 , 65 , 65 , 65 , 65 , 65 , 65 , 45 , 20 ,
											   45 , 30 , 0 };
		gridBagLayout.columnWeights = new double[] { 1.0 , 0.0 , 0.0 , 0.0 , 0.0 , 1.0 , Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0 , 0.0 , 0.0 , 0.0 , 0.0 , 0.0 , 0.0 , 0.0 , 0.0 , 0.0 , 0.0 ,
												  0.0 , 0.0 , 0.0 , 0.0 , 0.0 , Double.MIN_VALUE };
		
		setLayout( gridBagLayout );
		
		GridBagConstraints gbc_lblFormTitle = new GridBagConstraints();
		gbc_lblFormTitle.anchor = GridBagConstraints.NORTH;
		gbc_lblFormTitle.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblFormTitle.insets = new Insets( 0 , 0 , 5 , 5 );
		gbc_lblFormTitle.gridx = 1;
		gbc_lblFormTitle.gridy = 1;
		add( lblFormTitle , gbc_lblFormTitle );
		
		GridBagConstraints gbc_imgPacijent = new GridBagConstraints();
		gbc_imgPacijent.fill = GridBagConstraints.BOTH;
		gbc_imgPacijent.insets = new Insets( 0 , 0 , 5 , 5 );
		gbc_imgPacijent.gridx = 1;
		gbc_imgPacijent.gridy = 3;
		add( imgPacijent , gbc_imgPacijent );
		
		GridBagConstraints gbc_btnChooseImg = new GridBagConstraints();
		gbc_btnChooseImg.fill = GridBagConstraints.VERTICAL;
		gbc_btnChooseImg.insets = new Insets( 0 , 0 , 5 , 5 );
		gbc_btnChooseImg.gridx = 1;
		gbc_btnChooseImg.gridy = 4;
		add( btnChooseImg , gbc_btnChooseImg );
		
		GridBagConstraints gbc_txtimePrezime = new GridBagConstraints();
		gbc_txtimePrezime.gridwidth = 2;
		gbc_txtimePrezime.anchor = GridBagConstraints.NORTHWEST;
		gbc_txtimePrezime.insets = new Insets( 0 , 0 , 5 , 5 );
		gbc_txtimePrezime.gridx = 1;
		gbc_txtimePrezime.gridy = 5;
		add( txtimePrezime , gbc_txtimePrezime );
		
		GridBagConstraints gbc_txtOIB = new GridBagConstraints();
		gbc_txtOIB.gridwidth = 2;
		gbc_txtOIB.anchor = GridBagConstraints.NORTHWEST;
		gbc_txtOIB.insets = new Insets( 0 , 0 , 5 , 5 );
		gbc_txtOIB.gridx = 1;
		gbc_txtOIB.gridy = 6;
		add( txtOIB , gbc_txtOIB );
		
		GridBagConstraints gbc_txtJMBG = new GridBagConstraints();
		gbc_txtJMBG.gridwidth = 2;
		gbc_txtJMBG.anchor = GridBagConstraints.NORTHWEST;
		gbc_txtJMBG.insets = new Insets( 0 , 0 , 5 , 5 );
		gbc_txtJMBG.gridx = 1;
		gbc_txtJMBG.gridy = 7;
		add( txtJMBG , gbc_txtJMBG );
		
		GridBagConstraints gbc_txtAdresa = new GridBagConstraints();
		gbc_txtAdresa.gridwidth = 2;
		gbc_txtAdresa.anchor = GridBagConstraints.NORTHWEST;
		gbc_txtAdresa.insets = new Insets( 0 , 0 , 5 , 5 );
		gbc_txtAdresa.gridx = 1;
		gbc_txtAdresa.gridy = 8;
		add( txtAdresa , gbc_txtAdresa );
		
		GridBagConstraints gbc_txtGrad = new GridBagConstraints();
		gbc_txtGrad.anchor = GridBagConstraints.NORTHWEST;
		gbc_txtGrad.insets = new Insets( 0 , 0 , 5 , 5 );
		gbc_txtGrad.gridx = 1;
		gbc_txtGrad.gridy = 9;
		add( txtGrad , gbc_txtGrad );
		
		GridBagConstraints gbc_txtBrojMobitela = new GridBagConstraints();
		gbc_txtBrojMobitela.anchor = GridBagConstraints.NORTHWEST;
		gbc_txtBrojMobitela.insets = new Insets( 0 , 0 , 5 , 5 );
		gbc_txtBrojMobitela.gridx = 1;
		gbc_txtBrojMobitela.gridy = 10;
		add( txtBrojMobitela , gbc_txtBrojMobitela );
		
		GridBagConstraints gbc_txtMail = new GridBagConstraints();
		gbc_txtMail.anchor = GridBagConstraints.NORTHWEST;
		gbc_txtMail.insets = new Insets( 0 , 0 , 5 , 5 );
		gbc_txtMail.gridx = 1;
		gbc_txtMail.gridy = 11;
		add( txtMail , gbc_txtMail );
		
		GridBagConstraints gbc_txtPovijestBolestiScroll = new GridBagConstraints();
		gbc_txtPovijestBolestiScroll.gridwidth = 2;
		gbc_txtPovijestBolestiScroll.anchor = GridBagConstraints.SOUTH;
		gbc_txtPovijestBolestiScroll.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtPovijestBolestiScroll.insets = new Insets( 0 , 0 , 5 , 5 );
		gbc_txtPovijestBolestiScroll.gridheight = 4;
		gbc_txtPovijestBolestiScroll.gridx = 3;
		gbc_txtPovijestBolestiScroll.gridy = 3;
		add( txtPovijestBolestiScroll , gbc_txtPovijestBolestiScroll );
		
		GridBagConstraints gbc_txtAlergijeScroll = new GridBagConstraints();
		gbc_txtAlergijeScroll.gridwidth = 2;
		gbc_txtAlergijeScroll.fill = GridBagConstraints.BOTH;
		gbc_txtAlergijeScroll.insets = new Insets( 0 , 0 , 5 , 5 );
		gbc_txtAlergijeScroll.gridheight = 4;
		gbc_txtAlergijeScroll.gridx = 3;
		gbc_txtAlergijeScroll.gridy = 8;
		add( txtAlergijeScroll , gbc_txtAlergijeScroll );
		
		GridBagConstraints gbc_btnUredi = new GridBagConstraints();
		gbc_btnUredi.anchor = GridBagConstraints.NORTHEAST;
		gbc_btnUredi.insets = new Insets( 0 , 0 , 5 , 5 );
		gbc_btnUredi.gridx = 4;
		gbc_btnUredi.gridy = 12;
		add( btnUredi , gbc_btnUredi );
		
		GridBagConstraints gbc_btnIzbrisi = new GridBagConstraints();
		gbc_btnIzbrisi.insets = new Insets( 0 , 0 , 5 , 5 );
		gbc_btnIzbrisi.anchor = GridBagConstraints.NORTHEAST;
		gbc_btnIzbrisi.gridx = 4;
		gbc_btnIzbrisi.gridy = 14;
		add( btnIzbrisi , gbc_btnIzbrisi );
		
	}
	
	
	/**
	 * 
	 * Returns the JLabel object that represents the title of the patient details
	 * form.
	 * 
	 * @return the JLabel object that represents the title of the patient details
	 *         form
	 */
	public JLabel getLblFormTitle() {
		
		return lblFormTitle;
		
	}
	
	
	/**
	 * 
	 * Returns the TextField object that represents the text field for patient name
	 * and surname.
	 * 
	 * @return the TextField object that represents the text field for patient name
	 *         and surname
	 */
	public TextField getTxtimePrezime() {
		
		return txtimePrezime;
		
	}
	
	
	/**
	 * 
	 * Returns the TextField object that represents the text field for patient
	 * address.
	 * 
	 * @return the TextField object that represents the text field for patient
	 *         address
	 */
	public TextField getTxtAdresa() {
		
		return txtAdresa;
		
	}
	
	
	/**
	 * 
	 * Returns the TextField object that represents the text field for patient JMBG.
	 * 
	 * @return the TextField object that represents the text field for patient JMBG
	 */
	public TextField getTxtJMBG() {
		
		return txtJMBG;
		
	}
	
	
	/**
	 * 
	 * Returns the TextField object that represents the text field for patient
	 * email.
	 * 
	 * @return the TextField object that represents the text field for patient email
	 */
	public TextField getTxtMail() {
		
		return txtMail;
		
	}
	
	
	/**
	 * 
	 * Returns the TextField object that represents the text field for patient phone
	 * number.
	 * 
	 * @return the TextField object that represents the text field for patient phone
	 *         number
	 */
	public TextField getTxtBrojMobitela() {
		
		return txtBrojMobitela;
		
	}
	
	
	/**
	 * 
	 * Returns the TextField object that represents the text field for patient OIB.
	 * 
	 * @return the TextField object that represents the text field for patient OIB
	 */
	public TextField getTxtOIB() {
		
		return txtOIB;
		
	}
	
	
	/**
	 * 
	 * Returns the TextField object that represents the text field for patient city.
	 * 
	 * @return the TextField object that represents the text field for patient city
	 */
	public TextField getTxtGrad() {
		
		return txtGrad;
		
	}
	
	
	/**
	 * 
	 * Returns the TextArea object that represents the text area for patient medical
	 * history.
	 * 
	 * @return the TextArea object that represents the text area for patient medical
	 *         history
	 */
	public TextArea getTxtPovijestBolesti() {
		
		return txtPovijestBolesti;
		
	}
	
	
	/**
	 * 
	 * Returns the TextArea object that represents the text area for patient
	 * allergies.
	 * 
	 * @return the TextArea object that represents the text area for patient
	 *         allergies
	 */
	public TextArea getTxtAlergije() {
		
		return txtAlergije;
		
	}
	
	
	/**
	 * 
	 * Returns the Patient object that represents the patient whose details are
	 * displayed in the panel.
	 * 
	 * @return the Patient object that represents the patient whose details are
	 *         displayed in the panel
	 */
	public Patient getPatient() {
		
		return patient;
		
	}
	
	
	/**
	 * 
	 * Sets the Patient object that represents the patient whose details are to be
	 * displayed in the panel.
	 * 
	 * @param patient the Patient object that represents the patient whose details
	 *                are to be displayed in the panel
	 */
	public void setPatient( Patient patient ) {
		
		this.patient = patient;
		detaljiController.setDetaljiPanel( this );
		setData();
		
	}
	
}
