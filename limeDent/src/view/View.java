package view;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

import controller.LoginController;
import javaswingdev.FontAwesome;
import javaswingdev.FontAwesomeIcon;
import javaswingdev.GoogleMaterialDesignIcon;
import javaswingdev.GoogleMaterialIcon;
import raven.glasspanepopup.GlassPanePopup;
import view.panel.footerBar.FooterBar;
import view.panel.menuBar.EventMenuSelected;
import view.panel.menuBar.Menu;
import view.panel.menuBar.ModelMenu;
import view.panel.titleBar.SimpleTitleBar;
import view.viewPanel.CardPanel;


/**
 * @author ikojic000
 *
 */
@SuppressWarnings( "serial" )
public class View extends JFrame {
	
	private LoginController loginController;
	private JPanel contentPane;
	private SimpleTitleBar simpleTitleBar;
	private FooterBar footer;
	private CardPanel cardPanel;
	private Menu menu;
	private Animator animator;
	private boolean menuShow;
	
	public View( LoginController loginController ) {
		
		this.loginController = loginController;
		
//		TASKBAR APP ICON
		setName( "LimeDent" );
		Image appIcon = new ImageIcon( this.getClass().getResource( "/lime_icon_large.png" ) ).getImage();
		this.setIconImage( appIcon );
		
		GlassPanePopup.install( this );
		
		contentPane = new JPanel();
		contentPane.setBackground( Color.white );
		
		contentPane.setBorder( new EmptyBorder( 0 , -6 , 0 , 0 ) );
		
		setContentPane( contentPane );
		BorderLayout bl = new BorderLayout();
		bl.setHgap( 10 );
		bl.setVgap( 10 );
		contentPane.setLayout( bl );
		
		simpleTitleBar = new SimpleTitleBar( "limeDent" );
		simpleTitleBar.setBackground( new Color( 44 , 51 , 51 ) );
		Dimension titleBarDims = new Dimension();
		titleBarDims.height = 35;
		simpleTitleBar.setPreferredSize( titleBarDims );
		contentPane.add( simpleTitleBar , BorderLayout.NORTH );
		simpleTitleBar.init( this );
		
		footer = new FooterBar();
		Dimension footerDims = new Dimension();
		footerDims.height = 30;
		footer.setPreferredSize( footerDims );
		bl.setVgap( 5 );
		contentPane.add( footer , BorderLayout.SOUTH );
		
		cardPanel = new CardPanel( this );
		cardPanel.setLoginController( loginController );
		contentPane.add( cardPanel , BorderLayout.CENTER );
		
		menu = new Menu( loginController );
		contentPane.add( menu , BorderLayout.WEST );
		
		Dimension menuDims = new Dimension();
		menuDims.width = 65;
		menu.setPreferredSize( menuDims );
		
		menu.setUserSettingsEvent( new ActionListener() {
			
			@Override
			public void actionPerformed( ActionEvent e ) {
				
				cardPanel.showCard( "userSettingsPanel" );
				
			}
			
		} );
		
		menu.setLogoutEvent( new ActionListener() {
			
			@Override
			public void actionPerformed( ActionEvent e ) {
				
				dispose();
				ViewLogin viewLogin = new ViewLogin();
				viewLogin.setVisible( true );
				
			}
			
		} );
		
		menu.addEventMenu( new ActionListener() {
			
			@Override
			public void actionPerformed( ActionEvent ae ) {
				
				if ( !animator.isRunning() ) {
					
					animator.start();
					
				}
				
			}
			
		} );
		menu.setEvent( new EventMenuSelected() {
			
			@Override
			public void selected( int index ) {
				
				selectedMenu( index );
				
			}
			
		} );
		
		GoogleMaterialIcon menuIcon = new GoogleMaterialIcon();
		menuIcon.setIcon( GoogleMaterialDesignIcon.MENU );
		menuIcon.setSize( 30 );
		menuIcon.setColor1( new Color( 244 , 244 , 249 ) );
		menuIcon.setColor2( new Color( 244 , 244 , 249 ) );
		
		GoogleMaterialIcon menuIcon2 = new GoogleMaterialIcon();
		menuIcon2.setIcon( GoogleMaterialDesignIcon.KEYBOARD_ARROW_LEFT );
		menuIcon2.setSize( 30 );
		menuIcon2.setColor1( new Color( 244 , 244 , 249 ) );
		menuIcon2.setColor2( new Color( 244 , 244 , 249 ) );
		
		FontAwesomeIcon lblUserIcon = new FontAwesomeIcon();
		lblUserIcon.setIcon( FontAwesome.USER_MD );
		lblUserIcon.setSize( 25 );
		lblUserIcon.setColor1( new Color( 244 , 244 , 249 ) );
		lblUserIcon.setColor2( new Color( 244 , 244 , 249 ) );
		
		FontAwesomeIcon btnHomeIcon = new FontAwesomeIcon();
		btnHomeIcon.setIcon( FontAwesome.HOME );
		btnHomeIcon.setSize( 25 );
		btnHomeIcon.setColor1( new Color( 244 , 244 , 249 ) );
		btnHomeIcon.setColor2( new Color( 244 , 244 , 249 ) );
		
		FontAwesomeIcon btnArtikliIcon = new FontAwesomeIcon();
		btnArtikliIcon.setIcon( FontAwesome.BARCODE );
		btnArtikliIcon.setSize( 25 );
		btnArtikliIcon.setColor1( new Color( 244 , 244 , 249 ) );
		btnArtikliIcon.setColor2( new Color( 244 , 244 , 249 ) );
		
		FontAwesomeIcon btnPonudeIcon = new FontAwesomeIcon();
		btnPonudeIcon.setIcon( FontAwesome.FILES_O );
		btnPonudeIcon.setSize( 25 );
		btnPonudeIcon.setColor1( new Color( 244 , 244 , 249 ) );
		btnPonudeIcon.setColor2( new Color( 244 , 244 , 249 ) );
		
		FontAwesomeIcon btnNovaPonudaIcon = new FontAwesomeIcon();
		btnNovaPonudaIcon.setIcon( FontAwesome.FILE_O );
		btnNovaPonudaIcon.setSize( 25 );
		btnNovaPonudaIcon.setColor1( new Color( 244 , 244 , 249 ) );
		btnNovaPonudaIcon.setColor2( new Color( 244 , 244 , 249 ) );
		
		FontAwesomeIcon btnKalendarIcon = new FontAwesomeIcon();
		btnKalendarIcon.setIcon( FontAwesome.CALENDAR_O );
		btnKalendarIcon.setSize( 25 );
		btnKalendarIcon.setColor1( new Color( 244 , 244 , 249 ) );
		btnKalendarIcon.setColor2( new Color( 244 , 244 , 249 ) );
		
		menu.addMenu( new ModelMenu( "Početna" , btnHomeIcon.toIcon() ) );
		menu.addMenu( new ModelMenu( "Usluge" , btnArtikliIcon.toIcon() ) );
		menu.addMenu( new ModelMenu( "Ponude" , btnPonudeIcon.toIcon() ) );
		menu.addMenu( new ModelMenu( "Nova Ponuda" , btnNovaPonudaIcon.toIcon() ) );
		menu.addMenu( new ModelMenu( "Kalendar" , btnKalendarIcon.toIcon() ) );
		
		TimingTarget target = new TimingTargetAdapter() {
			
			@Override
			public void timingEvent( float fraction ) {
				
				double width;
				
				if ( menuShow ) {
					
					width = 65 + (150 * (1f - fraction));
					menu.setAlpha( 1f - fraction );
//					***
					menu.getBtnHamburger().setIcon( menuIcon.toIcon() );
					
				} else {
					
					width = 65 + (150 * fraction);
					menu.setAlpha( fraction );
//					***
					
					menu.getBtnHamburger().setIcon( menuIcon2.toIcon() );
					
				}
				
				menuDims.width = (int) width;
				contentPane.revalidate();
				
			}
			
			
			@Override
			public void end() {
				
				menuShow = !menuShow;
				
			}
			
		};
		animator = new Animator( 300 , target );
		animator.setResolution( 0 );
		animator.setAcceleration( 0.5f );
		animator.setDeceleration( 0.5f );
		
		setUndecorated( true );
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		setMinimumSize( new Dimension( 1500 , 1020 ) );
		setLocationRelativeTo( null );
		
	}
	
	
/*
 * selectedMenu() and showCard() methods are used in MenuEvent. When MenuItem is
 * selected it show the card accordingly.
 */
	private void selectedMenu( int index ) {
		
		if ( index == 0 ) {
			
			cardPanel.showCard( "homePanel" );
			
		} else if ( index == 1 ) {
			
			cardPanel.showCard( "uslugePanel" );
			
		} else if ( index == 2 ) {
			
			cardPanel.showCard( "ponudePanel" );
			
		} else if ( index == 3 ) {
			
			cardPanel.showCard( "novaPonudaPanel" );
			
		} else if ( index == 4 ) {
			
			cardPanel.showCard( "calendarPanel" );
			
		}
		
	}
	
	
	public CardPanel getCardPanel() {
		
		return cardPanel;
		
	}
	
	
	public LoginController getLoginController() {
		
		return loginController;
		
	}
	
	
	public void setLoginController( LoginController loginController ) {
		
		this.loginController = loginController;
		
	}
	
}
