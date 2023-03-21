package view.viewPanel;


import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import controller.LoginController;
import controller.observer.UserObserver;
import design.button.ButtonShadow;
import design.messageDialog.Message;
import design.notification.Notification;
import design.notification.Notification.Location;
import design.notification.Notification.NotificationType;
import design.panel.ImageAvatar;
import design.panel.RoundedShadowPanel;
import design.txtInput.TextField;
import jnafilechooser.api.JnaFileChooser;
import model.User;
import raven.glasspanepopup.GlassPanePopup;
import view.View;


/**
 * 
 * @author ikojic000
 * 
 *         UserSettingsPanel is a custom JPanel used to display and edit user
 *         information such as name, contact information and login credentials.
 *         It implements the UserObserver interface to receive notifications
 *         when the user's data is updated.
 * 		
 */
public class UserSettingsPanel extends RoundedShadowPanel implements UserObserver {
	
	private static final long serialVersionUID = 240251659066632166L;
	// *** VIEW + PANEL ***
	@SuppressWarnings( "unused" )
	private View view;
	@SuppressWarnings( "unused" )
	private CardPanel cardParent;
	private RoundedShadowPanel panelOsobPodaci;
	private RoundedShadowPanel panelSigurnost;
	private JLabel lblOsobPodaci;
	private JLabel lblSigurnost;
	private TextField txtImePrezime;
	private TextField txtMobitel;
	private TextField txtMail;
	private TextField txtPassword;
	private TextField txtConfirmPassword;
	private TextField txtOldPassword;
	private ImageAvatar imgUser;
	private ButtonShadow btnChooseImg;
	private ButtonShadow btnSaveOP;
	private ButtonShadow btnSaveSP;
	private LoginController loginController;
	private Notification notification;
	
	/**
	 * 
	 * Constructs a UserSettingsPanel and initializes its components with the
	 * specified CardPanel and View / JFrame.
	 * 
	 * @param cardParent The CardPanel that the UserSettingsPanel belongs to.
	 * @param view       The View instance that contains the UserSettingsPanel.
	 */
	public UserSettingsPanel( CardPanel cardParent , View view ) {
		
		super( 20 );
		this.view = view;
		this.cardParent = cardParent;
		
		setOpaque( false );
		setFont( new Font( "Century Gothic" , Font.BOLD , 16 ) );
		setBackground( new Color( 255 , 255 , 255 ) );
		setShadowColor( new Color( 255 , 255 , 255 ) );
		
		panelOsobPodaci = new RoundedShadowPanel( 20 );
		panelOsobPodaci.setShadowOpacity( 0.8f );
		panelOsobPodaci.setShadowColor( new Color( 170 , 170 , 170 ) );
		panelOsobPodaci.setBackground( new Color( 244 , 244 , 249 ) );
		panelOsobPodaci.setCornerRadius( 20 );
		
		txtImePrezime = new TextField();
		txtImePrezime.setLabelText( "Ime i prezime" );
		txtImePrezime.setFont( new Font( "Century Gothic" , Font.PLAIN , 15 ) );
		txtImePrezime.setBackground( new Color( 244 , 244 , 249 ) );
		txtImePrezime.setBounds( 460 , 85 , 300 , 65 );
		
		txtMobitel = new TextField();
		txtMobitel.setLabelText( "Broj mobitela" );
		txtMobitel.setFont( new Font( "Century Gothic" , Font.PLAIN , 15 ) );
		txtMobitel.setBackground( new Color( 244 , 244 , 249 ) );
		txtMobitel.setBounds( 460 , 161 , 300 , 65 );
		
		txtMail = new TextField();
		txtMail.setLabelText( "Mail" );
		txtMail.setFont( new Font( "Century Gothic" , Font.PLAIN , 15 ) );
		txtMail.setBackground( new Color( 244 , 244 , 249 ) );
		txtMail.setBounds( 460 , 237 , 300 , 65 );
		
		imgUser = new ImageAvatar();
		imgUser.setForeground( new Color( 44 , 51 , 51 ) );
		imgUser.setBorderSize( 2 );
		imgUser.setBounds( 30 , 80 , 300 , 300 );
		imgUser.setIcon( new ImageIcon( this.getClass().getResource( "/MPDLogo_Transparent.png" ) ) );
		
		notification = new Notification( view , NotificationType.SUCCESS , Location.TOP_CENTER , "" , "" );
		
		btnChooseImg = new ButtonShadow();
		btnChooseImg.setFocusPainted( false );
		btnChooseImg.setText( "Odaberi fotografiju" );
		btnChooseImg.setFont( new Font( "Century Gothic" , Font.PLAIN , 12 ) );
		btnChooseImg.setBounds( 105 , 393 , 150 , 35 );
		
		lblOsobPodaci = new JLabel( "Osobni podaci" );
		lblOsobPodaci.setFont( new Font( "Century Gothic" , Font.BOLD , 15 ) );
		lblOsobPodaci.setForeground( new Color( 121 , 118 , 118 ) );
		lblOsobPodaci.setBounds( 30 , 15 , 200 , 35 );
		
		btnSaveOP = new ButtonShadow();
		btnSaveOP.setFocusPainted( false );
		btnSaveOP.setText( "Spremi promjene" );
		btnSaveOP.setFont( new Font( "Century Gothic" , Font.PLAIN , 15 ) );
		btnSaveOP.setBounds( 560 , 383 , 200 , 45 );
		
		panelSigurnost = new RoundedShadowPanel( 20 );
		panelSigurnost.setShadowOpacity( 0.8f );
		panelSigurnost.setShadowColor( new Color( 170 , 170 , 170 ) );
		panelSigurnost.setLayout( null );
		panelSigurnost.setCornerRadius( 20 );
		panelSigurnost.setBackground( new Color( 244 , 244 , 249 ) );
		
		txtConfirmPassword = new TextField();
		txtConfirmPassword.setLabelText( "Ponovi novu lozinku" );
		txtConfirmPassword.setFont( new Font( "Century Gothic" , Font.PLAIN , 15 ) );
		txtConfirmPassword.setBackground( new Color( 244 , 244 , 249 ) );
		txtConfirmPassword.setBounds( 460 , 137 , 300 , 65 );
		
		txtPassword = new TextField();
		txtPassword.setLabelText( "Nova lozinka" );
		txtPassword.setFont( new Font( "Century Gothic" , Font.PLAIN , 15 ) );
		txtPassword.setBackground( new Color( 244 , 244 , 249 ) );
		txtPassword.setBounds( 460 , 61 , 300 , 65 );
		
		lblSigurnost = new JLabel( "Sigurnost" );
		lblSigurnost.setForeground( new Color( 121 , 118 , 118 ) );
		lblSigurnost.setFont( new Font( "Century Gothic" , Font.BOLD , 15 ) );
		lblSigurnost.setBounds( 30 , 15 , 200 , 35 );
		
		btnSaveSP = new ButtonShadow();
		btnSaveSP.setFocusPainted( false );
		btnSaveSP.setText( "Promijeni lozinku" );
		btnSaveSP.setFont( new Font( "Century Gothic" , Font.PLAIN , 15 ) );
		btnSaveSP.setBounds( 560 , 261 , 200 , 45 );
		
		txtOldPassword = new TextField();
		txtOldPassword.setLabelText( "Stara lozinka" );
		txtOldPassword.setFont( new Font( "Century Gothic" , Font.PLAIN , 15 ) );
		txtOldPassword.setBackground( new Color( 244 , 244 , 249 ) );
		txtOldPassword.setBounds( 30 , 61 , 300 , 65 );
		
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
		
		btnChooseImg.addActionListener( new ActionListener() {
			
			public void actionPerformed( ActionEvent e ) {
				
				notification.setLbMessageText( "Fotografija promijenjena" );
				JnaFileChooser imgChooser = new JnaFileChooser();
				imgChooser.addFilter( "Pictures" , "jpg" , "jpeg" , "png" );
				boolean returnValue = imgChooser.showOpenDialog( null );
				
				if ( returnValue ) {
					
					File selectedFile = imgChooser.getSelectedFile();
					
					loginController.getLoggedInUser()
							.setProfilePhoto( new ImageIcon( selectedFile.getAbsolutePath() ) );
					loginController.getLoggedInUser().notifyObservers();
					loginController.updateUserPhoto( selectedFile );
					
					notification.setType( NotificationType.SUCCESS );
					notification.setLblTitle( "Fotografija promijenjena" );
					notification.setLbMessageText( "Profilna fotografija uspješno promijenjena" );
					notification.showNotification();
					
				}
				
			}
			
		} );
		
		btnSaveOP.addActionListener( new ActionListener() {
			
			public void actionPerformed( ActionEvent e ) {
				
				loginController.getLoggedInUser().setName( separateName( txtImePrezime.getText() )[0] );
				loginController.getLoggedInUser().setLastName( separateName( txtImePrezime.getText() )[1] );
				loginController.getLoggedInUser().setPhone( txtMobitel.getText() );
				loginController.getLoggedInUser().setMail( txtMail.getText() );
				loginController.getLoggedInUser().notifyObservers();
				loginController.updateUser();
				setUserData( loginController.getLoggedInUser() );
				
				notification.setType( NotificationType.SUCCESS );
				notification.setLblTitle( "Podaci promijenjeni" );
				notification.setLbMessageText( "Uspiješno ste promijenili svoje podatke ..." );
				notification.showNotification();
				
			}
			
		} );
		
		btnSaveSP.addActionListener( new ActionListener() {
			
			public void actionPerformed( ActionEvent e ) {
				
				if ( checkPassword() ) {
					
					if ( txtPassword.getText().equals( txtConfirmPassword.getText() ) ) {
						
						Message msg = new Message();
						msg.setMessageTitle( "Promjena lozinke" );
						msg.setMessageText( "Jeste li sigurni da želite promijeniti lozinku?" );
						msg.eventOK( new ActionListener() {
							
							@Override
							public void actionPerformed( ActionEvent e ) {
								
								String newPassword = loginController.getLoggedInUser().hashPassword(
										txtConfirmPassword.getText() ,
										loginController.getLoggedInUser().getPassword().substring( 0 , 32 ) );
								loginController.getLoggedInUser().setPassword( newPassword );
								loginController.updateUser();
								
								clearAll();
								
								notification.setType( NotificationType.SUCCESS );
								notification.setLblTitle( "Lozinka promijenjena" );
								notification.setLbMessageText( "Uspiješno ste promijenili svoju lozinku..." );
								notification.showNotification();
								
								GlassPanePopup.closePopupLast();
								
							}
							
						} );
						GlassPanePopup.showPopup( msg );
						msg = null;
						
					} else {
						
						txtConfirmPassword.setText( "" );
						txtConfirmPassword.setHelperText( "Lozinke se ne podudaraju" );
						txtConfirmPassword.requestFocus();
						
					}
					
				} else {
					
					txtOldPassword.setText( "" );
					txtOldPassword.setHelperText( "Unijeli ste krivu lozinku" );
					txtPassword.setText( "" );
					txtConfirmPassword.setText( "" );
					txtOldPassword.requestFocus();
					
				}
				
			}
			
		} );
		
	}
	
	
	/**
	 * 
	 * Checks whether the entered password matches the current user's password.
	 * 
	 * @return true if the entered password matches the current user's password,
	 *         false otherwise
	 */
	private boolean checkPassword() {
		
		String userPassword = loginController.getLoggedInUser().getPassword();
		String enteredPassword = loginController.getLoggedInUser().hashPassword( txtOldPassword.getText() ,
				userPassword.substring( 0 , 32 ) );
		
		if ( enteredPassword.equals( userPassword ) ) {
			
			return true;
			
		}
		
		return false;
		
	}
	
	
	/**
	 * 
	 * Clears all input fields and selections.
	 */
	private void clearAll() {
		
		txtPassword.setText( "" );
		txtPassword.setHelperText( "" );
		txtConfirmPassword.setText( "" );
		txtConfirmPassword.setHelperText( "" );
		txtOldPassword.setText( "" );
		txtOldPassword.setHelperText( "" );
		
	}
	
	
	/**
	 * 
	 * Sets the text and image data of the user settings panel with the data from
	 * the logged in user.
	 * 
	 * @param loggedInUser the user data to display in the panel
	 */
	public void setUserData( User loggedInUser ) {
		
		txtImePrezime.setText( loggedInUser.getName() + " " + loggedInUser.getLastName() );
		txtMobitel.setText( loggedInUser.getPhone() );
		txtMail.setText( loggedInUser.getMail() );
		imgUser.setIcon( loggedInUser.getProfilePhoto() );
		imgUser.repaint();
		
	}
	
	
	/**
	 * 
	 * Separates the full name into first name and last name, and returns them as an
	 * array of Strings.
	 * 
	 * @param fullName the full name to separate
	 * @return an array of Strings containing the first name and last name,
	 *         respectively
	 */
	private String[] separateName( String fullName ) {
		
		int spaceIndex = fullName.indexOf( " " );
		String name = fullName.substring( 0 , spaceIndex );
		String lastName = fullName.substring( spaceIndex + 1 );
		return new String[] { name , lastName };
		
	}
	
	
	/**
	 * 
	 * Initiates the layout of the panel.
	 */
	public void initLayout() {
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0 , 800 , 0 , 0 };
		gridBagLayout.rowHeights = new int[] { 0 , 450 , 35 , 322 , 0 , 0 };
		gridBagLayout.columnWeights = new double[] { 1.0 , 0.0 , 1.0 , Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 1.0 , 0.0 , 1.0 , 0.0 , 1.0 , Double.MIN_VALUE };
		setLayout( gridBagLayout );
		
		GridBagConstraints gbc_panelOsobPodaci = new GridBagConstraints();
		gbc_panelOsobPodaci.fill = GridBagConstraints.BOTH;
		gbc_panelOsobPodaci.insets = new Insets( 0 , 0 , 5 , 5 );
		gbc_panelOsobPodaci.gridx = 1;
		gbc_panelOsobPodaci.gridy = 1;
		add( panelOsobPodaci , gbc_panelOsobPodaci );
		panelOsobPodaci.setLayout( null );
		
		GridBagConstraints gbc_panelSigurnost = new GridBagConstraints();
		gbc_panelSigurnost.insets = new Insets( 0 , 0 , 5 , 5 );
		gbc_panelSigurnost.fill = GridBagConstraints.BOTH;
		gbc_panelSigurnost.gridx = 1;
		gbc_panelSigurnost.gridy = 3;
		add( panelSigurnost , gbc_panelSigurnost );
		
		panelOsobPodaci.add( txtImePrezime );
		panelOsobPodaci.add( txtMobitel );
		panelOsobPodaci.add( txtMail );
		panelOsobPodaci.add( imgUser );
		panelOsobPodaci.add( btnChooseImg );
		panelOsobPodaci.add( lblOsobPodaci );
		panelOsobPodaci.add( btnSaveOP );
		panelSigurnost.add( txtConfirmPassword );
		panelSigurnost.add( txtPassword );
		panelSigurnost.add( lblSigurnost );
		panelSigurnost.add( btnSaveSP );
		panelSigurnost.add( txtOldPassword );
		
	}
	
	
	/**
	 * 
	 * Returns the LoginController object used by this user settings panel.
	 * 
	 * @return the LoginController object used by this user settings panel
	 */
	public LoginController getLoginController() {
		
		return loginController;
		
	}
	
	
	/**
	 * 
	 * Sets the LoginController object used by this user settings panel, and adds
	 * this panel as an observer of the logged in user data.
	 * 
	 * @param loginController the LoginController object to set for this user
	 *                        settings panel
	 */
	public void setLoginController( LoginController loginController ) {
		
		this.loginController = loginController;
		loginController.getLoggedInUser().addObserver( this );
		setUserData( loginController.getLoggedInUser() );
		
	}
	
	
	/**
	 * 
	 * Updates the user profile photo data in the user settings panel with the data
	 * from the logged in user.
	 * 
	 * @param loggedInUser the user data containing the updated profile photo
	 */
	@Override
	public void updateProfilePhoto( User loggedInUser ) {
		
		setUserData( loggedInUser );
		System.out.println( "Update profile photo" );
		
	}
	
	
	/**
	 * 
	 * Updates the user information data in the user settings panel with the data
	 * from the logged in user.
	 * 
	 * @param loggedInUser the user data containing the updated user information
	 */
	@Override
	public void updateUserInfo( User loggedInUser ) {
		
		setUserData( loggedInUser );
		System.out.println( "Update User Info" );
		
	}
	
}
