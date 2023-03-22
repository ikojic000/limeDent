package view.panel.menuBar;


import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.SwingConstants;

import design.button.Button;
import design.panel.HalfRoundShadowPanel;
import design.panel.shadow.ShadowType;
import javaswingdev.FontAwesome;
import javaswingdev.FontAwesomeIcon;
import net.miginfocom.swing.MigLayout;


public class Bottom extends HalfRoundShadowPanel {
	
	/**
	 *
	 */
	private static final long serialVersionUID = -8491093143484311389L;
	private float alpha;
	private Button btnLogout;
	private FontAwesomeIcon btnLogoutIcon;
	private Button btnUserSettings;
	private FontAwesomeIcon btnUserSettingsIcon;
	
	public Bottom() {
		
		super( 20 );
		setShadowSize( 2 );
		setShadowOpacity( 0.65f );
		setShadowType( ShadowType.TOP );
		setMaximumSize( new Dimension( 211 , 150 ) );
		setOpaque( false );
		setBackground( new Color( 46 , 191 , 165 ) );
		initComponents();
		initLayout();
		
	}
	
	
	private void initComponents() {
		
		setBorder( BorderFactory.createEmptyBorder( 5 , 5 , 5 , 5 ) );
		
		btnLogout = new Button();
		btnLogout.setRound( 20 );
		btnLogout.setHorizontalAlignment( SwingConstants.LEFT );
		btnLogout.setHorizontalTextPosition( SwingConstants.RIGHT );
		btnLogout.setText( "Logout" );
		btnLogout.setIconTextGap( 30 );
		btnLogout.setForeground( new Color( 244 , 244 , 249 ) );
		btnLogout.setFont( new Font( "Century Gothic" , Font.BOLD , 15 ) );
		btnLogout.setFocusPainted( false );
		btnLogout.setEffectColor( new Color( 244 , 244 , 249 ) );
		btnLogout.setBorderColor( new Color( 46 , 191 , 165 ) );
		btnLogout.setBackground( new Color( 46 , 191 , 165 ) );
		
		btnLogoutIcon = new FontAwesomeIcon();
		btnLogoutIcon.setIcon( FontAwesome.SIGN_OUT );
		btnLogoutIcon.setSize( 19 );
		btnLogoutIcon.setColor1( new Color( 244 , 244 , 249 ) );
		btnLogoutIcon.setColor2( new Color( 244 , 244 , 249 ) );
		btnLogout.setIcon( btnLogoutIcon.toIcon() );
		
		btnUserSettingsIcon = new FontAwesomeIcon();
		btnUserSettingsIcon.setIcon( FontAwesome.WRENCH );
		btnUserSettingsIcon.setSize( 19 );
		btnUserSettingsIcon.setColor1( new Color( 244 , 244 , 249 ) );
		btnUserSettingsIcon.setColor2( new Color( 244 , 244 , 249 ) );
		
		btnUserSettings = new Button();
		btnUserSettings.setRound( 20 );
		btnUserSettings.setHorizontalTextPosition( SwingConstants.RIGHT );
		btnUserSettings.setHorizontalAlignment( SwingConstants.LEFT );
		btnUserSettings.setText( "Postavke" );
		btnUserSettings.setIconTextGap( 30 );
		btnUserSettings.setForeground( new Color( 244 , 244 , 249 ) );
		btnUserSettings.setFont( new Font( "Century Gothic" , Font.BOLD , 15 ) );
		btnUserSettings.setFocusPainted( false );
		btnUserSettings.setEffectColor( new Color( 244 , 244 , 249 ) );
		btnUserSettings.setBorderColor( new Color( 46 , 191 , 165 ) );
		btnUserSettings.setBackground( new Color( 46 , 191 , 165 ) );
		btnUserSettings.setIcon( btnUserSettingsIcon.toIcon() );
		
	}
	
	
	public void initLayout() {
		
		setLayout( new MigLayout( "" , "[grow]" , "15[25px:n,top]15[25px:n,top]15" ) );
		
		add( btnUserSettings , "cell 0 0,grow" );
		add( btnLogout , "cell 0 1,grow" );
		
	}
	
	
	@Override
	public void paint( Graphics grphcs ) {
		
		Graphics2D g2 = (Graphics2D) grphcs;
		g2.setRenderingHint( RenderingHints.KEY_ANTIALIASING , RenderingHints.VALUE_ANTIALIAS_ON );
		g2.setComposite( AlphaComposite.getInstance( AlphaComposite.SRC_OVER , alpha ) );
		g2.setColor( getBackground() );
		g2.fillRoundRect( 5 , 5 , getWidth() - 10 , getHeight() - 10 , 20 , 20 );
		super.paint( grphcs );
		
	}
	
	
	public void setAlpha( float alpha ) {
		
		this.alpha = alpha;
		
	}
	
	
	public void addUserSettingsEvent( ActionListener event ) {
		
		btnUserSettings.addActionListener( event );
		
	}
	
	
	public void addLogoutEvent( ActionListener event ) {
		
		btnLogout.addActionListener( event );
		
	}
	
}
