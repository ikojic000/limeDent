package view;


import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controller.LoginController;
import design.button.ButtonShadow;
import design.notification.Notification;
import design.notification.Notification.Location;
import design.notification.Notification.NotificationType;
import design.txtInput.PasswordField;
import design.txtInput.TextField;
import javaswingdev.GoogleMaterialDesignIcon;
import javaswingdev.GoogleMaterialIcon;
import model.User;
import view.panel.titleBar.Buttont;


@SuppressWarnings( "serial" )
public class ViewLogin extends JFrame {
	
	private JPanel contentPane;
	private JPanel panelLeft;
	private TextField txtUsername;
	private PasswordField txtPassword;
	private ButtonShadow btnLogin;
	private Buttont btnMinimize;
	private Buttont btnClose;
	private GoogleMaterialIcon iconClose;
	private GoogleMaterialIcon iconMinimize;
	private ImageIcon imgLogo;
	private JLabel lblLogo;
	private JPanel panelRight;
	private JFrame view;
	private Notification notification;
	private LoginController loginController;
	
	public ViewLogin() {
		
//		TASKBAR APP ICON
		Image appIcon = new ImageIcon( this.getClass().getResource( "/lime_icon_large.png" ) ).getImage();
		this.setIconImage( appIcon );
		
		this.loginController = new LoginController();
		this.view = this;
		
		contentPane = new JPanel();
		contentPane.setBackground( new Color( 244 , 244 , 249 ) );
		contentPane.setBorder( new EmptyBorder( 5 , 5 , 5 , 5 ) );
		contentPane.setLayout( null );
		setContentPane( contentPane );
		
		notification = new Notification( view , NotificationType.WARNING , Location.TOP_CENTER , "" , "" );
		
		btnMinimize = new Buttont();
		btnMinimize.setHoverColor( new Color( 210 , 214 , 214 ) );
		btnMinimize.setBounds( 920 , 0 , 30 , 30 );
		contentPane.add( btnMinimize );
		
		btnClose = new Buttont();
		btnClose.setHoverColor( new Color( 255 , 48 , 48 ) );
		btnClose.setBounds( 960 , 0 , 30 , 30 );
		contentPane.add( btnClose );
		
		iconClose = new GoogleMaterialIcon();
		iconMinimize = new GoogleMaterialIcon();
		
		iconClose.setColor1( new Color( 0 , 0 , 0 ) );
		iconClose.setColor2( new Color( 111 , 111 , 111 ) );
		iconClose.setIcon( GoogleMaterialDesignIcon.CLOSE );
		iconClose.setSize( 20 );
		
		iconMinimize.setColor1( new Color( 0 , 0 , 0 ) );
		iconMinimize.setColor2( new Color( 111 , 111 , 111 ) );
		iconMinimize.setIcon( GoogleMaterialDesignIcon.REMOVE );
		iconMinimize.setSize( 20 );
		
		btnClose.setIcon( iconClose.toIcon() );
		btnMinimize.setIcon( iconMinimize.toIcon() );
		
		panelLeft = new JPanel();
		panelLeft.setBackground( new Color( 44 , 51 , 51 ) );
		panelLeft.setBounds( 0 , 0 , 500 , 800 );
		contentPane.add( panelLeft );
		panelLeft.setLayout( null );
		
		imgLogo = new ImageIcon( "img/lime_icon_large.png" );
		
		lblLogo = new JLabel( "limeDent" );
		lblLogo.setForeground( new Color( 244 , 249 , 249 ) );
		lblLogo.setVerticalTextPosition( SwingConstants.BOTTOM );
		lblLogo.setIconTextGap( 20 );
		lblLogo.setFont( new Font( "Century Gothic" , Font.BOLD , 25 ) );
		lblLogo.setHorizontalAlignment( SwingConstants.CENTER );
		lblLogo.setHorizontalTextPosition( SwingConstants.CENTER );
		lblLogo.setBounds( 10 , 11 , 480 , 778 );
		panelLeft.add( lblLogo );
		
		lblLogo.setIcon( imgLogo );
		
		panelRight = new JPanel();
		panelRight.setBackground( new Color( 244 , 244 , 249 ) );
		panelRight.setBounds( 500 , 30 , 500 , 770 );
		contentPane.add( panelRight );
		panelRight.setLayout( null );
		
		txtUsername = new TextField();
		txtUsername.setText( "ikojic" );
		txtUsername.setForeground( new Color( 44 , 51 , 51 ) );
		txtUsername.setHintTextColor( new Color( 121 , 118 , 118 ) );
		txtUsername.setLabelText( "Korisničko ime" );
		txtUsername.setFont( new Font( "Century Gothic" , Font.PLAIN , 15 ) );
		txtUsername.setHorizontalAlignment( SwingConstants.LEFT );
		txtUsername.setBounds( 100 , 220 , 300 , 70 );
		txtUsername.setColumns( 10 );
		txtUsername.setBackground( new Color( 244 , 244 , 249 ) );
		panelRight.add( txtUsername );
		
		txtPassword = new PasswordField();
		txtPassword.setText( "ikojic" );
		txtPassword.setForeground( new Color( 44 , 51 , 51 ) );
		txtPassword.setLabelText( "Lozinka" );
		txtPassword.setFont( new Font( "Century Gothic" , Font.PLAIN , 15 ) );
		txtPassword.setBackground( new Color( 244 , 244 , 249 ) );
		txtPassword.setHorizontalAlignment( SwingConstants.LEFT );
		txtPassword.setShowAndHide( true );
		txtPassword.setBounds( 100 , 340 , 300 , 70 );
		panelRight.add( txtPassword );
		
		btnLogin = new ButtonShadow();
		btnLogin.setForeground( new Color( 44 , 51 , 51 ) );
		btnLogin.setText( "Login" );
		btnLogin.setFocusPainted( false );
		btnLogin.setForeground( new Color( 121 , 118 , 118 ) );
		btnLogin.setFont( new Font( "Century Gothic" , Font.PLAIN , 15 ) );
		btnLogin.setBounds( 100 , 460 , 300 , 50 );
		panelRight.add( btnLogin );
		
		activatePanel();
		
		this.getRootPane().setDefaultButton( btnLogin );
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		setBackground( new Color( 244 , 249 , 249 ) );
		setResizable( false );
		setSize( 1000 , 800 );
		setUndecorated( true );
		setLocationRelativeTo( null );
		setVisible( true );
		
	}
	
	
	private void activatePanel() {
		
//		MINIMIZE AND CLOSE LISTENERS
		btnMinimize.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed( ActionEvent ae ) {
				
				setState( JFrame.ICONIFIED );
				
			}
			
		} );
		
		btnClose.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed( ActionEvent ae ) {
				
				System.exit( 0 );
				
			}
			
		} );
		
//		PANEL LISTENER
		panelRight.addMouseListener( new MouseAdapter() {
			
			@Override
			public void mouseClicked( MouseEvent e ) {
				
				getContentPane().requestFocusInWindow();
				
			}
			
		} );
		
//		BTN LISTENERS
		btnLogin.addActionListener( new ActionListener() {
			
			@SuppressWarnings( "deprecation" )
			public void actionPerformed( ActionEvent e ) {
				
				String username = txtUsername.getText();
				String password = txtPassword.getText();
				
				Object result = loginController.checkCredentials( new User( username , password ) );
				
				if ( result instanceof User ) {
					
					User loggedInUser = (User) result;
					loginController.setLoggedInUser( loggedInUser );
					System.out.println( "Logged IN - " + loggedInUser.getName() + " " + loggedInUser.getLastName() );
					
					dispose();
					long start = System.currentTimeMillis();
					System.out.println( "Start - " + start );
					View view = new View( loginController );
					view.setVisible( true );
					long end = System.currentTimeMillis();
					System.out.println( "End - " + end );
					System.out.println( "Time to show frame: " + (end - start) + " milliseconds" );
		
//					View view = new View( loginController );
//					view.setVisible( true );
					
				} else {
					
					resultMessage( result );
					
				}
				
			}
			
		} );
		
	}
	
	
	private void resultMessage( Object result ) {
		
		if ( result instanceof String ) {
			
			String errorMessage = (String) result;
			System.out.println( errorMessage );
			
			notification.setLbMessageText( errorMessage );
			notification.showNotification();
			txtPassword.setText( "" );
			txtPassword.requestFocus();
			
		} else {
			
			notification.setLbMessageText( "Ne postoji korisnik s tim korisničkim imenom." );
			notification.showNotification();
			
		}
		
	}
	
}
