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
import java.util.ArrayList;

import javax.swing.JLabel;

import controller.NoviPacijentController;
import design.button.ButtonShadow;
import design.messageDialog.Message;
import design.notification.Notification;
import design.notification.Notification.Location;
import design.notification.Notification.NotificationType;
import design.panel.RoundedShadowPanel;
import design.txtInput.TextArea;
import design.txtInput.TextAreaScroll;
import design.txtInput.TextField;
import raven.glasspanepopup.GlassPanePopup;
import view.View;


/**
 * 
 * @author ikojic000
 * 
 *         The NoviPacijentPanel class extends RoundedShadowPanel and represents
 *         a panel for adding new patient. It contains components for adding
 *         patients data
 * 
 */
public class NoviPacijentPanel extends RoundedShadowPanel {
	
	private static final long serialVersionUID = 4464610519421738643L;
	private TextField txtAdresa;
	private TextField txtJMBG;
	private ButtonShadow btnDodaj;
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
	private Notification notification;
//	*** VIEW + PANEL ***
	@SuppressWarnings( "unused" )
	private View view;
	private CardPanel cardParent;
	private NoviPacijentController noviPacijentController;
	
//	checkers
	private boolean isOIBValid = true;
	private boolean isJMBGValid = true;
	private boolean isPhoneValid = true;
	private boolean isMailValid = true;
	private ArrayList<Boolean> checkers = new ArrayList<>();
	
	/**
	 * 
	 * Constructs a new NoviPacijentPanel with the given CardPanel and View / JFrame
	 * objects. Initializes all UI components and sets up the panel's layout.
	 * 
	 * @param cardParent the CardPanel parent for this panel
	 * @param view       the View parent for this panel
	 */
	public NoviPacijentPanel( CardPanel cardParent , View view ) {
		
		super( 20 );
		this.view = view;
		this.cardParent = cardParent;
		this.noviPacijentController = new NoviPacijentController( this );
		
		setOpaque( false );
		setFont( new Font( "Century Gothic" , Font.BOLD , 16 ) );
		setBackground( new Color( 244 , 244 , 249 ) );
		
		notification = new Notification( view , NotificationType.SUCCESS , Location.TOP_CENTER , "" , "" );
		
		lblFormTitle = new JLabel( "Dodaj novog pacijenta" );
		lblFormTitle.setForeground( new Color( 121 , 118 , 118 ) );
		lblFormTitle.setFont( new Font( "Century Gothic" , Font.BOLD , 18 ) );
		
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
		
		btnDodaj = new ButtonShadow();
		btnDodaj.setText( "Dodaj" );
		btnDodaj.setPreferredSize( new Dimension( 170 , 45 ) );
		btnDodaj.setMinimumSize( new Dimension( 170 , 45 ) );
		btnDodaj.setMaximumSize( new Dimension( 170 , 45 ) );
		btnDodaj.setForeground( new Color( 121 , 118 , 118 ) );
		btnDodaj.setFont( new Font( "Century Gothic" , Font.PLAIN , 15 ) );
		btnDodaj.setFocusPainted( false );
		
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
		
		txtOIB.addKeyListener( new KeyAdapter() {
			
			@Override
			public void keyReleased( KeyEvent e ) {
				
				isOIBValid = InputControls.checkOIB( txtOIB );
				
			}
			
		} );
		
		txtJMBG.addKeyListener( new KeyAdapter() {
			
			@Override
			public void keyReleased( KeyEvent e ) {
				
				isJMBGValid = InputControls.checkJMBG( txtJMBG );
				
			}
			
		} );
		
		txtBrojMobitela.addKeyListener( new KeyAdapter() {
			
			@Override
			public void keyReleased( KeyEvent e ) {
				
				isPhoneValid = InputControls.checkPhone( txtBrojMobitela );
				
			}
			
		} );
		
		txtMail.addKeyListener( new KeyAdapter() {
			
			@Override
			public void keyReleased( KeyEvent e ) {
				
				isMailValid = InputControls.checkMail( txtMail );
				
			}
			
		} );
		
		btnDodaj.addActionListener( new ActionListener() {
			
			public void actionPerformed( ActionEvent e ) {
				
				if ( !isAllValid() ) {
					
					notification.setType( NotificationType.WARNING );
					notification.setLblTitle( "Krivi unos" );
					notification.setLbMessageText( "Unijeli ste neispravne podatke" );
					notification.showNotification();
					
				} else {
					
					notification.setType( NotificationType.SUCCESS );
					notification.setLblTitle( "Pacijent dodan" );
					notification.setLbMessageText( "Dodali ste novog pacijenta: " + txtimePrezime.getText() );
					
					Message msg = new Message();
					msg.setMessageTitle(
							"Jeste li sigurni da \u017eelite dodati pacijenta: " + txtimePrezime.getText() );
					msg.setMessageText( "Pritiskom gumba OK pacijent će biti dodan u sustav." );
					
					msg.eventOK( new ActionListener() {
						
						@Override
						public void actionPerformed( ActionEvent e ) {
							
							noviPacijentController.addPatient();
							notification.showNotification();
							GlassPanePopup.closePopupLast();
							
						}
						
					} );
					
					GlassPanePopup.showPopup( msg );
					
				}
				
			}
			
		} );
		
	}
	
	
	/**
	 * 
	 * Adds the input checkers to the list of checkers to be validated. If the list
	 * is not empty, it clears the list before adding the checkers.
	 */
	private void addCheckers() {
		
		if ( !checkers.isEmpty() ) {
			
			checkers.clear();
			
		}
		
		checkers.add( isOIBValid );
		checkers.add( isJMBGValid );
		checkers.add( isPhoneValid );
		checkers.add( isMailValid );
		
	}
	
	
	/**
	 * 
	 * Checks if all the input fields are valid by iterating through the list of
	 * checkers and returning false if any of them returns false.
	 * 
	 * @return boolean indicating if all the input fields are valid
	 */
	private boolean isAllValid() {
		
		addCheckers();
		
		for ( boolean check : checkers ) {
			
			if ( !check )
				return false;
			
		}
		
		return true;
		
	}
	
	
	/**
	 * @param isOIBValid the isOIBValid to set
	 */
	public void setOIBValid( boolean isOIBValid ) {
		
		this.isOIBValid = isOIBValid;
		
	}
	
	
	/**
	 * @param isJMBGValid the isJMBGValid to set
	 */
	public void setJMBGValid( boolean isJMBGValid ) {
		
		this.isJMBGValid = isJMBGValid;
		
	}
	
	
	/**
	 * @param isPhoneValid the isPhoneValid to set
	 */
	public void setPhoneValid( boolean isPhoneValid ) {
		
		this.isPhoneValid = isPhoneValid;
		
	}
	
	
	/**
	 * @param isMailValid the isMailValid to set
	 */
	public void setMailValid( boolean isMailValid ) {
		
		this.isMailValid = isMailValid;
		
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
		
		GridBagConstraints gbc_txtimePrezime = new GridBagConstraints();
		gbc_txtimePrezime.gridwidth = 2;
		gbc_txtimePrezime.anchor = GridBagConstraints.SOUTHWEST;
		gbc_txtimePrezime.insets = new Insets( 0 , 0 , 5 , 5 );
		gbc_txtimePrezime.gridx = 1;
		gbc_txtimePrezime.gridy = 3;
		add( txtimePrezime , gbc_txtimePrezime );
		
		GridBagConstraints gbc_txtOIB = new GridBagConstraints();
		gbc_txtOIB.gridwidth = 2;
		gbc_txtOIB.anchor = GridBagConstraints.NORTHWEST;
		gbc_txtOIB.insets = new Insets( 0 , 0 , 5 , 5 );
		gbc_txtOIB.gridx = 1;
		gbc_txtOIB.gridy = 5;
		add( txtOIB , gbc_txtOIB );
		
		GridBagConstraints gbc_txtJMBG = new GridBagConstraints();
		gbc_txtJMBG.gridwidth = 2;
		gbc_txtJMBG.anchor = GridBagConstraints.NORTHWEST;
		gbc_txtJMBG.insets = new Insets( 0 , 0 , 5 , 5 );
		gbc_txtJMBG.gridx = 1;
		gbc_txtJMBG.gridy = 6;
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
		
		GridBagConstraints gbc_btnDodaj = new GridBagConstraints();
		gbc_btnDodaj.anchor = GridBagConstraints.NORTHEAST;
		gbc_btnDodaj.insets = new Insets( 0 , 0 , 5 , 5 );
		gbc_btnDodaj.gridx = 4;
		gbc_btnDodaj.gridy = 12;
		add( btnDodaj , gbc_btnDodaj );
		
	}
	
	
	/**
	 * 
	 * Returns the label object that displays the title of the form.
	 *
	 * @return The `JLabel` object representing the title of the form.
	 */
	public JLabel getLblFormTitle() {
		
		return lblFormTitle;
		
	}
	
	
	/**
	 * 
	 * Returns the text field object that allows entering the patient's first and
	 * last name.
	 *
	 * @return The `TextField` object representing the patient's first and last
	 *         name.
	 */
	public TextField getTxtimePrezime() {
		
		return txtimePrezime;
		
	}
	
	
	/**
	 * 
	 * Returns the text field object that allows entering the patient's address.
	 *
	 * @return The `TextField` object representing the patient's address.
	 */
	public TextField getTxtAdresa() {
		
		return txtAdresa;
		
	}
	
	
	/**
	 * 
	 * Returns the text field object that allows entering the patient's JMBG number.
	 *
	 * @return The `TextField` object representing the patient's JMBG number.
	 */
	public TextField getTxtJMBG() {
		
		return txtJMBG;
		
	}
	
	
	/**
	 * 
	 * Returns the text field object that allows entering the patient's email
	 * address.
	 *
	 * @return The `TextField` object representing the patient's email address.
	 */
	public TextField getTxtMail() {
		
		return txtMail;
		
	}
	
	
	/**
	 * 
	 * Returns the text field object that allows entering the patient's mobile phone
	 * number.
	 *
	 * @return The `TextField` object representing the patient's mobile phone
	 *         number.
	 */
	public TextField getTxtBrojMobitela() {
		
		return txtBrojMobitela;
		
	}
	
	
	/**
	 * 
	 * Returns the text field object that allows entering the patient's OIB number.
	 *
	 * @return The `TextField` object representing the patient's OIB number.
	 */
	public TextField getTxtOIB() {
		
		return txtOIB;
		
	}
	
	
	/**
	 * 
	 * Returns the text field object that allows entering the patient's city name.
	 *
	 * @return The `TextField` object representing the patient's city name.
	 */
	public TextField getTxtGrad() {
		
		return txtGrad;
		
	}
	
	
	/**
	 * 
	 * Returns the text area object that displays the patient's medical history.
	 *
	 * @return The `TextArea` object representing the patient's medical history.
	 */
	public TextArea getTxtPovijestBolesti() {
		
		return txtPovijestBolesti;
		
	}
	
	
	/**
	 * 
	 * Returns the text area object that displays the patient's allergies.
	 *
	 * @return The `TextArea` object representing the patient's allergies.
	 */
	public TextArea getTxtAlergije() {
		
		return txtAlergije;
		
	}
	
	
	/**
	 * 
	 * Returns the `CardPanel` object that this panel belongs to.
	 *
	 * @return The `CardPanel` object representing the parent panel.
	 */
	public CardPanel getCardParent() {
		
		return cardParent;
		
	}
	
}
