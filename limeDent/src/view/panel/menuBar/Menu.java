package view.panel.menuBar;


import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.LoginController;
import design.panel.HalfRoundShadowPanel;
import javaswingdev.GoogleMaterialDesignIcon;
import javaswingdev.GoogleMaterialIcon;
import model.User;
import net.miginfocom.swing.MigLayout;


public class Menu extends HalfRoundShadowPanel {
	
	/**
	 *
	 */
	private static final long serialVersionUID = -2333006692423787699L;
	private MigLayout layout;
	private JPanel panelMenu;
	private JButton btnHamburger;
	private EventMenuSelected event;
	private Header header;
	private Bottom bottom;
	private User loggedInUser;
	
	public Menu( LoginController loginController ) {
		
		super( 20 );
		
		this.loggedInUser = loginController.getLoggedInUser();
		setShadowOpacity( 0.65f );
		setBackground( new Color( 44 , 51 , 51 ) );
		initComponents();
		setOpaque( false );
		init();
		
	}
	
	
	private void init() {
		
		setLayout( new MigLayout( "wrap, fillx, insets 0" , "[fill]" , "35[]10[]push[60]5" ) );
		panelMenu = new JPanel();
		header = new Header( loggedInUser );
		bottom = new Bottom();
		createButtonMenu();
		panelMenu.setOpaque( false );
		layout = new MigLayout( "fillx, wrap" , "0[fill]0" , "0[]10[]0" );
		panelMenu.setLayout( layout );
		add( btnHamburger , "pos 1al 0al 100% 50" );
		add( header );
		add( panelMenu );
		add( bottom );
		
	}
	
	
	public void addMenu( ModelMenu menu ) {
		
		MenuItem item = new MenuItem( menu.getIcon() , menu.getMenuName() , panelMenu.getComponentCount() );
		item.addEvent( new EventMenuSelected() {
			
			@Override
			public void selected( int index ) {
				
				clearMenu( index );
				
			}
			
		} );
		item.addEvent( event );
		panelMenu.add( item );
		
	}
	
	
	private void createButtonMenu() {
		
		GoogleMaterialIcon menuIcon = new GoogleMaterialIcon();
		menuIcon.setIcon( GoogleMaterialDesignIcon.MENU );
		menuIcon.setSize( 30 );
		menuIcon.setColor1( new Color( 244 , 244 , 249 ) );
		menuIcon.setColor2( new Color( 244 , 244 , 249 ) );
		
		btnHamburger = new JButton();
		btnHamburger.setContentAreaFilled( false );
		btnHamburger.setCursor( new Cursor( Cursor.HAND_CURSOR ) );
		btnHamburger.setIcon( menuIcon.toIcon() );
		btnHamburger.setFocusPainted( false );
		btnHamburger.setBorder( new EmptyBorder( 5 , 12 , 5 , 12 ) );
		
	}
	
	
	private void initComponents() {
		
		GroupLayout layout = new GroupLayout( this );
		this.setLayout( layout );
		layout.setHorizontalGroup(
				layout.createParallelGroup( GroupLayout.Alignment.LEADING ).addGap( 0 , 197 , Short.MAX_VALUE ) );
		layout.setVerticalGroup(
				layout.createParallelGroup( GroupLayout.Alignment.LEADING ).addGap( 0 , 512 , Short.MAX_VALUE ) );
		
	}
	
	
	private void clearMenu( int exceptIndex ) {
		
		for ( Component com : panelMenu.getComponents() ) {
			
			MenuItem item = (MenuItem) com;
			
			if ( item.getIndex() != exceptIndex ) {
				
				item.setSelected( false );
				
			}
			
		}
		
	}
	
	
	public void addEventMenu( ActionListener event ) {
		
		btnHamburger.addActionListener( event );
		
	}
	
	
	public void setAlpha( float alpha ) {
		
		header.setAlpha( alpha );
		bottom.setAlpha( alpha );
		
	}
	
	
	public void setEvent( EventMenuSelected event ) {
		
		this.event = event;
		
	}
	
	
	public JButton getBtnHamburger() {
		
		return btnHamburger;
		
	}
	
	
	public void setUserSettingsEvent( ActionListener userSettingsEvent ) {
		
		bottom.addUserSettingsEvent( userSettingsEvent );
		
	}
	
	
	public void setLogoutEvent( ActionListener logoutEvent ) {
		
		bottom.addLogoutEvent( logoutEvent );
		
	}
	
}
