package view.viewPanel;


import java.awt.CardLayout;
import java.awt.Color;

import javax.swing.border.MatteBorder;

import controller.LoginController;
import design.panel.RoundedPanel;
import view.View;


public class CardPanel extends RoundedPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1680729189874995548L;
	private View view;
	private CardLayout cl;
	private HomePanel homePanel;
	private UslugePanel uslugePanel;
	private PonudePanel ponudePanel;
	private NovaPonudaPanel novaPonudaPanel;
	private UserSettingsPanel userSettingsPanel;
	private NoviPregledPanel noviPregledPanel;
	private DetaljiPanel detaljiPanel;
	private NoviPacijentPanel noviPacijentPanel;
	@SuppressWarnings( "unused" )
	private LoginController loginController;
	
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
	
	
	private void initComponents() {
		
		homePanel = new HomePanel( this , view );
		uslugePanel = new UslugePanel( this , view );
		ponudePanel = new PonudePanel( this , view );
		
		novaPonudaPanel = new NovaPonudaPanel( this , view );
		userSettingsPanel = new UserSettingsPanel( this , view );
		userSettingsPanel.setBackground( new Color( 255 , 255 , 255 ) );
		userSettingsPanel.setShadowColor( new Color( 255 , 255 , 255 ) );
		noviPregledPanel = new NoviPregledPanel( this , view );
		homePanel.setNoviPregledPanel( noviPregledPanel );
		detaljiPanel = new DetaljiPanel( this , view );
		homePanel.setDetaljiPanel( detaljiPanel );
		noviPacijentPanel = new NoviPacijentPanel( this , view );
		
	}
	
	
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
	
	
	public void showCard( String card ) {
		
		cl.show( this , card );
		clearCard( card );
		
	}
	
	
	/*
	 * Clear all methods.. It's used for clearing all input from the panel when it
	 * shows again.
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
	
	
	public void clearHomePanel() {
		
		homePanel.clearAll();
		
	}
	
	
	public void clearUslugePanel() {
		
		uslugePanel.clearAll();
		
	}
	
	
	public void clearPonudePanel() {
		
		ponudePanel.clearAll();
		
	}
	
	
	public void clearNovaPonudaPanel() {
		
		novaPonudaPanel.clearAll();
		
	}
	
	
	public CardLayout getCardLayout() {
		
		return cl;
		
	}
	
	
	/**
	 * @param loginController the loginController to set
	 */
	public void setLoginController( LoginController loginController ) {
		
		this.loginController = loginController;
		userSettingsPanel.setLoginController( loginController );
		noviPregledPanel.setLoginController( loginController );
		novaPonudaPanel.setLoginController( loginController );
		
	}
	
	
	/**
	 * @return the userSettingsPanel
	 */
	public UserSettingsPanel getUserSettingsPanel() {
		
		return userSettingsPanel;
		
	}
	
}
