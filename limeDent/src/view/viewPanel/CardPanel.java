package view.viewPanel;


import java.awt.CardLayout;
import java.awt.Color;

import javax.swing.border.MatteBorder;

import controller.LoginController;
import design.panel.RoundedPanel;
import view.View;


/**
 * 
 * @author ikojic000
 * 
 *         This class represents a custom JPanel that extends the RoundedPanel
 *         class. It contains several other JPanels, each corresponding to a
 *         specific "card" or view in the CardLayout of the panel. The class
 *         provides methods for showing a specific card, clearing the input data
 *         from a specific card, and getting the CardLayout object of the panel.
 *         Additionally, it allows setting a LoginController object for the user
 *         settings, new examination, and new offer panels.
 * 		
 */
public class CardPanel extends RoundedPanel {
	
	private static final long serialVersionUID = -1680729189874995548L;
	private View view;
	private CardLayout cl;
	private HomePanel homePanel;
	private ProductsPanel uslugePanel;
	private OffersPanel ponudePanel;
	private NewOfferPanel novaPonudaPanel;
	private UserSettingsPanel userSettingsPanel;
	private NewMedicalExamPanel noviPregledPanel;
	private PatientDetailsPanel detaljiPanel;
	private NewPatientPanel noviPacijentPanel;
	@SuppressWarnings( "unused" )
	private LoginController loginController;
	
	/**
	 * 
	 * Constructs a new CardPanel object with the specified View / JFrame object.
	 * Initializes the CardLayout object and sets the background, foreground,
	 * opacity, and border of the panel.
	 * 
	 * @param view the View object to use
	 */
	public CardPanel( View view ) {
		
		super( 20 );
		
		this.view = view;
		this.cl = new CardLayout();
		setLayout( cl );
		
		setBackground( Color.white );
		setForeground( Color.white );
		setOpaque( false );
		setBorder( new MatteBorder( 1 , 1 , 1 , 1 , Color.white ) );
		initComponents();
		initLayout();
		
	}
	
	
	/**
	 * 
	 * Initializes the HomePanel, UslugePanel, PonudePanel, NovaPonudaPanel,
	 * UserSettingsPanel, NoviPregledPanel, DetaljiPanel, and NoviPacijentPanel
	 * objects and sets them to their corresponding variables.
	 */
	private void initComponents() {
		
		homePanel = new HomePanel( this , view );
		uslugePanel = new ProductsPanel( this , view );
		ponudePanel = new OffersPanel( this , view );
		novaPonudaPanel = new NewOfferPanel( this , view );
		userSettingsPanel = new UserSettingsPanel( this , view );
		userSettingsPanel.setBackground( new Color( 255 , 255 , 255 ) );
		userSettingsPanel.setShadowColor( new Color( 255 , 255 , 255 ) );
		noviPregledPanel = new NewMedicalExamPanel( this , view );
		homePanel.setNoviPregledPanel( noviPregledPanel );
		detaljiPanel = new PatientDetailsPanel( this , view );
		homePanel.setDetaljiPanel( detaljiPanel );
		noviPacijentPanel = new NewPatientPanel( this , view );
		
	}
	
	
	/**
	 * 
	 * Adds the HomePanel, UslugePanel, PonudePanel, NovaPonudaPanel,
	 * UserSettingsPanel, NoviPregledPanel, DetaljiPanel, and NoviPacijentPanel
	 * objects to the CardLayout object with their corresponding names.
	 */
	private void initLayout() {
		
		add( homePanel , "homePanel" );
		add( uslugePanel , "uslugePanel" );
		add( ponudePanel , "ponudePanel" );
		add( novaPonudaPanel , "novaPonudaPanel" );
		add( userSettingsPanel , "userSettingsPanel" );
		add( noviPregledPanel , "noviPregledPanel" );
		add( detaljiPanel , "detaljiPanel" );
		add( noviPacijentPanel , "noviPacijentPanel" );
		
	}
	
	
	/**
	 * 
	 * Show the specified card by using CardLayout.
	 * 
	 * @param card the card to be shown
	 */
	public void showCard( String card ) {
		
		cl.show( this , card );
		clearCard( card );
		
	}
	
	
	/**
	 * 
	 * Clear all input fields and reset the state of the specified panel to its
	 * initial state.
	 * 
	 * @param card the card whose panel is to be cleared
	 */
	public void clearCard( String card ) {
		
		switch (card) {
			
			case "homePanel":
				homePanel.clearAll();
				break;
			
			case "uslugePanel":
				uslugePanel.clearAll();
				break;
			
			case "ponudePanel":
				ponudePanel.clearAll();
				break;
			
			case "novaPonudaPanel":
				novaPonudaPanel.clearAll();
				break;
			
			case "noviPacijentPanel":
				noviPacijentPanel.clearAll();
				break;
			
		}
		
	}
	
	
	/**
	 * 
	 * Clear all input fields and reset the state of the homePanel to its initial
	 * state.
	 */
	public void clearHomePanel() {
		
		homePanel.clearAll();
		
	}
	
	
	/**
	 * 
	 * Clear all input fields and reset the state of the uslugePanel to its initial
	 * state.
	 */
	public void clearUslugePanel() {
		
		uslugePanel.clearAll();
		
	}
	
	
	/**
	 * 
	 * Clear all input fields and reset the state of the ponudePanel to its initial
	 * state.
	 */
	public void clearPonudePanel() {
		
		ponudePanel.clearAll();
		
	}
	
	
	/**
	 * 
	 * Clear all input fields and reset the state of the novaPonudaPanel to its
	 * initial state.
	 */
	public void clearNovaPonudaPanel() {
		
		novaPonudaPanel.clearAll();
		
	}
	
	
	/**
	 * 
	 * Get the CardLayout object used by this panel.
	 * 
	 * @return the CardLayout object used by this panel
	 */
	public CardLayout getCardLayout() {
		
		return cl;
		
	}
	
	
	/**
	 * 
	 * Set the LoginController object to be used by this panel and its child
	 * components.
	 * 
	 * @param loginController the LoginController object to be set
	 */
	public void setLoginController( LoginController loginController ) {
		
		this.loginController = loginController;
		userSettingsPanel.setLoginController( loginController );
		noviPregledPanel.setLoginController( loginController );
		novaPonudaPanel.setLoginController( loginController );
		
	}
	
	
	/**
	 * 
	 * Get the UserSettingsPanel object used by this panel.
	 * 
	 * @return the UserSettingsPanel object used by this panel
	 */
	public UserSettingsPanel getUserSettingsPanel() {
		
		return userSettingsPanel;
		
	}
	
}
