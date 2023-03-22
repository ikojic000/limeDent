package view.viewPanel;


import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;

import controller.LoginController;
import controller.NewMedicalExamController;
import design.button.ButtonShadow;
import design.messageDialog.Message;
import design.messageDialog.MessageInfo;
import design.notification.Notification;
import design.notification.Notification.Location;
import design.notification.Notification.NotificationType;
import design.panel.RoundedShadowPanel;
import design.scroll.ScrollBarCustom;
import design.table.CustomTable;
import design.table.actionTable.TableActionEvent;
import design.txtInput.TextArea;
import design.txtInput.TextAreaScroll;
import model.MedicalExam;
import model.Patient;
import raven.glasspanepopup.GlassPanePopup;
import view.View;


/**
 * 
 * @author ikojic000
 * 
 *         The NoviPregledPanel class extends RoundedShadowPanel and represents
 *         a panel for managing patient's medical examinations. It contains
 *         components for adding, updating, and deleting medical examinations,
 *         and displaying medical examinations in a table.
 * 		
 */
public class NewMedicalExamPanel extends RoundedShadowPanel {
	
	private static final long serialVersionUID = 4380414859779004657L;
	protected static final DecimalFormat dfKoristenje = new DecimalFormat( "0.00" ,
			DecimalFormatSymbols.getInstance( Locale.US ) );
	protected static final DecimalFormat dfPrikaz = new DecimalFormat( "###.00" ,
			DecimalFormatSymbols.getInstance( Locale.GERMANY ) );
	private JLabel lblFormTitle;
	private TextAreaScroll txtNoviPregledScroll;
	private TextArea txtNoviPregled;
	private ButtonShadow btnNoviPregled;
	private JScrollPane allPregledScrollPane;
	private JLabel lblProsliPregledi;
	private Notification notification;
	private CustomTable table;
//	*** VIEW + PANEL ***
	@SuppressWarnings( "unused" )
	private View view;
	@SuppressWarnings( "unused" )
	private CardPanel cardParent;
	private NewMedicalExamController noviPregledController;
	private LoginController loginController;
	private Patient patient;
	
	/**
	 * 
	 * Constructs a new NoviPregledPanel with the given CardPanel and View / JFrame
	 * objects. Initializes all UI components and sets up the panel's layout.
	 * 
	 * @param cardParent the CardPanel parent for this panel
	 * @param view       the View parent for this panel
	 */
	@SuppressWarnings( "serial" )
	public NewMedicalExamPanel( CardPanel cardParent , View view ) {
		
		super( 20 );
		this.view = view;
		this.cardParent = cardParent;
		this.noviPregledController = new NewMedicalExamController( this );
		
		setOpaque( false );
		setFont( new Font( "Century Gothic" , Font.BOLD , 16 ) );
		setBackground( new Color( 244 , 244 , 249 ) );
		
		notification = new Notification( view , NotificationType.SUCCESS , Location.TOP_CENTER , "" , "" );
		
		lblFormTitle = new JLabel( "Dodaj novi pregled - " );
		lblFormTitle.setForeground( new Color( 121 , 118 , 118 ) );
		lblFormTitle.setFont( new Font( "Century Gothic" , Font.BOLD , 18 ) );
		
		txtNoviPregledScroll = new TextAreaScroll();
		txtNoviPregledScroll.setPreferredSize( new Dimension( 250 , 250 ) );
		txtNoviPregledScroll.setMinimumSize( new Dimension( 250 , 250 ) );
		txtNoviPregledScroll.setLineColor( new Color( 46 , 191 , 165 ) );
		txtNoviPregledScroll.setLabelText( "Novi pregled" );
		txtNoviPregledScroll.setForeground( new Color( 44 , 51 , 51 ) );
		txtNoviPregledScroll.setFont( new Font( "Century Gothic" , Font.PLAIN , 15 ) );
		txtNoviPregledScroll.setBackground( new Color( 244 , 244 , 249 ) );
		
		txtNoviPregled = new TextArea();
		txtNoviPregled.setForeground( new Color( 44 , 51 , 51 ) );
		txtNoviPregled.setFont( new Font( "Century Gothic" , Font.PLAIN , 15 ) );
		txtNoviPregledScroll.setViewportView( txtNoviPregled );
		
		btnNoviPregled = new ButtonShadow();
		btnNoviPregled.setPreferredSize( new Dimension( 170 , 45 ) );
		btnNoviPregled.setMinimumSize( new Dimension( 170 , 45 ) );
		btnNoviPregled.setMaximumSize( new Dimension( 170 , 45 ) );
		btnNoviPregled.setText( "Dodaj novi pregled" );
		btnNoviPregled.setForeground( new Color( 121 , 118 , 118 ) );
		btnNoviPregled.setFont( new Font( "Century Gothic" , Font.PLAIN , 15 ) );
		btnNoviPregled.setFocusPainted( false );
		
		ScrollBarCustom sc = new ScrollBarCustom( 80 );
		sc.setUnitIncrement( 5 );
		
		lblProsliPregledi = new JLabel( "Pro\u0161li pregledi" );
		lblProsliPregledi.setForeground( new Color( 121 , 118 , 118 ) );
		lblProsliPregledi.setFont( new Font( "Century Gothic" , Font.BOLD , 16 ) );
		
		allPregledScrollPane = new JScrollPane();
		allPregledScrollPane.setBackground( new Color( 244 , 244 , 249 ) );
		allPregledScrollPane.getViewport().setBackground( this.getBackground() );
		allPregledScrollPane.setBorder( BorderFactory.createEmptyBorder() );
		allPregledScrollPane.setVerticalScrollBar( sc );
		allPregledScrollPane.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_ALWAYS );
		allPregledScrollPane.setHorizontalScrollBarPolicy( JScrollPane.HORIZONTAL_SCROLLBAR_NEVER );
		
		table = new CustomTable();
		table.setFirstBold( true );
		table.setLastAction( true );
		table.setForeground( new Color( 0 , 0 , 0 ) );
		table.setFont( new Font( "Century Gothic" , Font.BOLD , 15 ) );
		table.setCursor( Cursor.getPredefinedCursor( Cursor.HAND_CURSOR ) );
		table.setBackground( getBackground() );
		table.setBorder( null );
		table.setShowVerticalLines( false );
		table.setShowHorizontalLines( false );
		table.setShowGrid( false );
		table.setRowHeight( 75 );
		
		table.setModel(
				new DefaultTableModel( new Object[][] { { "13-01-2023" , "Ivan Kojić" , "Pregled Test" , null } } ,
						
						new String[] { "Datum" , "Doktor" , "Pregled" , "" } ) {
				
				} );
		
		table.getTableHeader().setFont( new Font( "Century Gothic" , Font.BOLD , 15 ) );
		table.getColumnModel().getColumn( 0 ).setPreferredWidth( 100 );
		table.getColumnModel().getColumn( 0 ).setMinWidth( 100 );
		table.getColumnModel().getColumn( 0 ).setMaxWidth( 100 );
		table.getColumnModel().getColumn( 1 ).setPreferredWidth( 155 );
		table.getColumnModel().getColumn( 1 ).setMinWidth( 155 );
		table.getColumnModel().getColumn( 1 ).setMaxWidth( 155 );
		table.getColumnModel().getColumn( 3 ).setPreferredWidth( 120 );
		table.getColumnModel().getColumn( 3 ).setMinWidth( 120 );
		table.getColumnModel().getColumn( 3 ).setMaxWidth( 120 );
		
		table.getTableHeader().setPreferredSize( new Dimension( 785 , 40 ) );
		
		allPregledScrollPane.setViewportView( table );
		
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
		
//		TABLE LISTENERS
		TableActionEvent event = new TableActionEvent() {
			
			@Override
			public void onEdit( int row ) {
				
				txtNoviPregled.setText( noviPregledController.getSelectedExam( row ).getInfo() );
				
			}
			
			
			@Override
			public void onDelete( int row ) {
				
				if ( table.isEditing() ) {
					
					table.getCellEditor().stopCellEditing();
					
				}
				
				notification.setType( NotificationType.WARNING );
				notification.setLblTitle( "Pregled izbrisan" );
				notification.setLbMessageText( "Pregled dana "
						+ noviPregledController.getSelectedExam( row ).getDateFormatted() + " izbrisan ..." );
				
				Message msg = new Message();
				msg.setMessageTitle( "Brisanje pregleda" );
				msg.setMessageText( "Jeste li sigurni da \u017eelite izbrisati pregled napravljen dana:  \n"
						+ noviPregledController.getSelectedExam( row ).getDateFormatted() + " ?"
						+ "\nPregled napravio/la: " + noviPregledController.getSelectedExam( row ).getDoctorName() );
				
				msg.eventOK( new ActionListener() {
					
					@Override
					public void actionPerformed( ActionEvent e ) {
						
						noviPregledController.deleteExam( row );
						notification.showNotification();
						GlassPanePopup.closePopupLast();
						
					}
					
				} );
				GlassPanePopup.showPopup( msg );
				msg = null;
				
			}
			
			
			@Override
			public void onView( int row ) {
				
				MessageInfo msgInfo = new MessageInfo();
				msgInfo.setMessageTitle( "Datum: " + noviPregledController.getSelectedExam( row ).getDateFormatted() );
				msgInfo.setMessageText(
						"Pregled napravio/la: " + noviPregledController.getSelectedExam( row ).getDoctorName()
								+ "\nPregled:\n " + noviPregledController.getSelectedExam( row ).getInfo() );
				System.out.println( "View row : " + noviPregledController.getSelectedExam( row ).getId() );
				GlassPanePopup.showPopup( msgInfo );
				
			}
			
		};
		
		table.setActionEvent( event );
		
//		BUTTON LISTENER
		btnNoviPregled.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed( ActionEvent e ) {
				
				Integer idPatient = patient.getId();
				Integer idDoctor = loginController.getLoggedInUser().getId();
				MedicalExam exam = new MedicalExam( txtNoviPregled.getText() , idDoctor , idPatient );
				noviPregledController.addExam( exam );
				clearAll();
				
			}
			
		} );
		
	}
	
	
	/**
	 * 
	 * Initiates the layout of the panel.
	 */
	private void initLayout() {
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 45 , 400 , 568 , 45 , 0 };
		gridBagLayout.rowHeights = new int[] { 35 , 23 , 275 , 45 , 35 , 619 , 35 , 0 };
		gridBagLayout.columnWeights = new double[] { 1.0 , 1.0 , 1.0 , 1.0 , Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0 , 0.0 , 0.0 , 0.0 , 0.0 , 1.0 , 0.0 , Double.MIN_VALUE };
		setLayout( gridBagLayout );
		
		GridBagConstraints gbc_lblFormTitle = new GridBagConstraints();
		gbc_lblFormTitle.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblFormTitle.insets = new Insets( 0 , 0 , 5 , 5 );
		gbc_lblFormTitle.gridx = 1;
		gbc_lblFormTitle.gridy = 1;
		add( lblFormTitle , gbc_lblFormTitle );
		
		GridBagConstraints gbc_txtNoviPregledScroll = new GridBagConstraints();
		gbc_txtNoviPregledScroll.anchor = GridBagConstraints.SOUTH;
		gbc_txtNoviPregledScroll.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtNoviPregledScroll.insets = new Insets( 0 , 0 , 5 , 5 );
		gbc_txtNoviPregledScroll.gridx = 1;
		gbc_txtNoviPregledScroll.gridy = 2;
		add( txtNoviPregledScroll , gbc_txtNoviPregledScroll );
		
		GridBagConstraints gbc_lblProliPregledi = new GridBagConstraints();
		gbc_lblProliPregledi.anchor = GridBagConstraints.WEST;
		gbc_lblProliPregledi.insets = new Insets( 5 , 25 , 5 , 5 );
		gbc_lblProliPregledi.gridx = 1;
		gbc_lblProliPregledi.gridy = 4;
		add( lblProsliPregledi , gbc_lblProliPregledi );
		
		GridBagConstraints gbc_tblUslugeScrollPane = new GridBagConstraints();
		gbc_tblUslugeScrollPane.gridwidth = 2;
		gbc_tblUslugeScrollPane.insets = new Insets( 0 , 30 , 5 , 5 );
		gbc_tblUslugeScrollPane.fill = GridBagConstraints.BOTH;
		gbc_tblUslugeScrollPane.gridx = 1;
		gbc_tblUslugeScrollPane.gridy = 5;
		add( allPregledScrollPane , gbc_tblUslugeScrollPane );
		
		GridBagConstraints gbc_btnNoviPregled = new GridBagConstraints();
		gbc_btnNoviPregled.anchor = GridBagConstraints.EAST;
		gbc_btnNoviPregled.insets = new Insets( 0 , 0 , 5 , 5 );
		gbc_btnNoviPregled.gridx = 1;
		gbc_btnNoviPregled.gridy = 3;
		add( btnNoviPregled , gbc_btnNoviPregled );
		
	}
	
	
	/**
	 * 
	 * Sets the form title and table data for a given patient
	 * 
	 * @param patient The patient to set data for
	 */
	public void setData( Patient patient ) {
		
		lblFormTitle.setText( "Dodaj novi pregled - " + patient.getName() );
		noviPregledController.setTableData();
		
	}
	
	
	/**
	 * 
	 * Clears all the input fields and selections on the panel
	 */
	public void clearAll() {
		
		txtNoviPregled.setText( "" );
		allPregledScrollPane.getVerticalScrollBar().setValue( 0 );
		this.requestFocus();
		
	}
	
	
	/**
	 * 
	 * @return The label that displays the form title
	 */
	public JLabel getLblFormTitle() {
		
		return lblFormTitle;
		
	}
	
	
	/**
	 * 
	 * @return The table on the panel
	 */
	public CustomTable getTable() {
		
		return table;
		
	}
	
	
	/**
	 * 
	 * @return The patient associated with the panel
	 */
	public Patient getPatient() {
		
		return patient;
		
	}
	
	
	/**
	 * 
	 * Sets the patient for the panel and updates the data displayed on it
	 * 
	 * @param patient The patient to set
	 */
	public void setPatient( Patient patient ) {
		
		this.patient = patient;
		noviPregledController.setPatient( patient );
		setData( patient );
		
	}
	
	
	/**
	 * 
	 * Sets the login controller for the panel
	 * 
	 * @param loginController The login controller to set
	 */
	public void setLoginController( LoginController loginController ) {
		
		this.loginController = loginController;
		
	}
	
}
