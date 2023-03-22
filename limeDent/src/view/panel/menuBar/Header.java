package view.panel.menuBar;


import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import controller.observer.UserObserver;
import design.panel.ImageAvatar;
import javaswingdev.FontAwesome;
import javaswingdev.FontAwesomeIcon;
import model.User;
import net.miginfocom.swing.MigLayout;


public class Header extends JPanel implements UserObserver {
	
	/**
	 *
	 */
	private static final long serialVersionUID = 4018943012787520053L;
	private float alpha;
	private ImageAvatar imgUser;
	private FontAwesomeIcon lblUserIcon;
	private JLabel lblUser;
	private JSeparator separator;
	private User loggedInUser;
	
	public Header( User loggedInUser ) {
		
		this.loggedInUser = loggedInUser;
		this.loggedInUser.addObserver( this );
		
		setMaximumSize( new Dimension( 180 , 150 ) );
		
		initComponents();
		setOpaque( false );
		
		setLayout( new MigLayout( "" , "35[250px,grow,fill]" , "[150.00px,center]10[20px,top][15.00]" ) );
		
		imgUser = new ImageAvatar();
		imgUser.setBorderSize( 2 );
		imgUser.setForeground( Color.WHITE );
//		imgUser.setIcon( new ImageIcon( this.getClass().getResource( "/janica.jpg" ) ) );
		imgUser.setIcon( loggedInUser.getProfilePhoto() );
		add( imgUser , "cell 0 0,grow" );
		
		lblUserIcon = new FontAwesomeIcon();
		lblUserIcon.setIcon( FontAwesome.USER_MD );
		lblUserIcon.setSize( 20 );
		lblUserIcon.setColor1( new Color( 244 , 244 , 249 ) );
		lblUserIcon.setColor2( new Color( 244 , 244 , 249 ) );
		
		lblUser = new JLabel( loggedInUser.getName() + " " + loggedInUser.getLastName() );
		lblUser.setHorizontalTextPosition( SwingConstants.RIGHT );
		lblUser.setIconTextGap( 20 );
		lblUser.setHorizontalAlignment( SwingConstants.CENTER );
		lblUser.setFont( new Font( "Century Gothic" , Font.BOLD , 15 ) );
		lblUser.setForeground( new Color( 244 , 244 , 249 ) );
		lblUser.setIcon( lblUserIcon.toIcon() );
		add( lblUser , "flowx,cell 0 1,growx,aligny top" );
		
		separator = new JSeparator();
		separator.setBackground( new Color( 46 , 191 , 165 , 122 ) );
		separator.setForeground( separator.getBackground() );
		add( separator , "cell 0 2, growx" );
		
	}
	
	
	@Override
	public void paint( Graphics grphcs ) {
		
		Graphics2D g2 = (Graphics2D) grphcs;
		g2.setComposite( AlphaComposite.getInstance( AlphaComposite.SRC_OVER , alpha ) );
		super.paint( grphcs );
		
	}
	
	
	private void initComponents() {
		
		lblUserIcon = new FontAwesomeIcon();
		lblUserIcon.setIcon( FontAwesome.USER_MD );
		lblUserIcon.setSize( 20 );
		lblUserIcon.setColor1( new Color( 244 , 244 , 249 ) );
		lblUserIcon.setColor2( new Color( 244 , 244 , 249 ) );
		
	}
	
	
	public void setAlpha( float alpha ) {
		
		this.alpha = alpha;
		
	}
	
	
	@Override
	public void updateProfilePhoto( User loggedInUser ) {
		
		imgUser.setIcon( loggedInUser.getProfilePhoto() );
		System.out.println( "Update menu profile photo" );
		imgUser.repaint();
		
	}
	
	
	@Override
	public void updateUserInfo( User loggedInUser ) {
		
		lblUser.setText( loggedInUser.getName() + " " + loggedInUser.getLastName() );
		System.out.println( "Update menu user info" );
		
	}
	
}
