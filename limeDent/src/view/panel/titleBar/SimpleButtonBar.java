package view.panel.titleBar;


import java.awt.Color;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import javaswingdev.GoogleMaterialDesignIcon;
import javaswingdev.GoogleMaterialIcon;


public class SimpleButtonBar extends JPanel {
	
	private static final long serialVersionUID = -3402370653318260862L;
	
	public SimpleButtonBar() {
		
		initComponents();
		setOpaque( false );
		cmdClose.setIcon( iconClose.toIcon() );
		cmdResize.setIcon( iconRestore.toIcon() );
		cmdMinimize.setIcon( iconMinimize.toIcon() );
		cmdMinimize.setFont( cmdMinimize.getFont().deriveFont( 0 , 3 ) );
		
	}
	
	
	public void initEvent( JFrame fram ) {
		
		fram.addWindowStateListener( new WindowStateListener() {
			
			@Override
			public void windowStateChanged( WindowEvent we ) {
				
				if ( we.getNewState() == Frame.MAXIMIZED_BOTH ) {
					
					cmdResize.setIcon( iconMax.toIcon() );
					
				} else if ( we.getNewState() == Frame.NORMAL ) {
					
					cmdResize.setIcon( iconRestore.toIcon() );
					
				}
				
			}
			
		} );
		cmdClose.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed( ActionEvent ae ) {
				
				System.exit( 0 );
				
			}
			
		} );
		cmdMinimize.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed( ActionEvent ae ) {
				
				fram.setState( Frame.ICONIFIED );
				
			}
			
		} );
		cmdResize.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed( ActionEvent ae ) {
				
				if ( fram.getExtendedState() == Frame.MAXIMIZED_BOTH ) {
					
					fram.setExtendedState( Frame.NORMAL );
					
				} else {
					
					fram.setExtendedState( Frame.MAXIMIZED_BOTH );
					
				}
				
			}
			
		} );
		
	}
	
	
	private void initComponents() {
		
		iconClose = new GoogleMaterialIcon();
		iconMax = new GoogleMaterialIcon();
		iconMinimize = new GoogleMaterialIcon();
		iconRestore = new GoogleMaterialIcon();
		cmdClose = new Buttont();
		cmdResize = new Buttont();
		cmdMinimize = new Buttont();
		
		iconClose.setColor1( new Color( 111 , 111 , 111 ) );
		iconClose.setColor2( new Color( 215 , 215 , 215 ) );
		iconClose.setIcon( GoogleMaterialDesignIcon.CLOSE );
		iconClose.setSize( 18 );
		
		iconMax.setColor1( new Color( 111 , 111 , 111 ) );
		iconMax.setColor2( new Color( 215 , 215 , 215 ) );
		iconMax.setIcon( GoogleMaterialDesignIcon.CONTENT_COPY );
		iconMax.setSize( 16 );
		
		iconMinimize.setColor1( new Color( 111 , 111 , 111 ) );
		iconMinimize.setColor2( new Color( 215 , 215 , 215 ) );
		iconMinimize.setIcon( GoogleMaterialDesignIcon.REMOVE );
		iconMinimize.setSize( 18 );
		
		iconRestore.setColor1( new Color( 111 , 111 , 111 ) );
		iconRestore.setColor2( new Color( 215 , 215 , 215 ) );
		iconRestore.setIcon( GoogleMaterialDesignIcon.CROP_DIN );
		
		cmdClose.setHoverColor( new Color( 255 , 48 , 48 ) );
		
		cmdMinimize.setBorder( BorderFactory.createEmptyBorder( 8 , 1 , 1 , 1 ) );
		
		GroupLayout layout = new GroupLayout( this );
		this.setLayout( layout );
		layout.setHorizontalGroup( layout.createParallelGroup( GroupLayout.Alignment.LEADING )
				.addGroup( GroupLayout.Alignment.TRAILING , layout.createSequentialGroup().addGap( 0 , 0 , 0 )
						.addComponent( cmdMinimize , GroupLayout.PREFERRED_SIZE , 25 , GroupLayout.PREFERRED_SIZE )
						.addGap( 0 , 0 , 0 )
						.addComponent( cmdResize , GroupLayout.PREFERRED_SIZE , 25 , GroupLayout.PREFERRED_SIZE )
						.addGap( 0 , 0 , 0 )
						.addComponent( cmdClose , GroupLayout.PREFERRED_SIZE , 25 , GroupLayout.PREFERRED_SIZE ) ) );
		layout.setVerticalGroup( layout.createParallelGroup( GroupLayout.Alignment.LEADING )
				.addComponent( cmdClose , GroupLayout.DEFAULT_SIZE , 25 , Short.MAX_VALUE )
				.addComponent( cmdResize , GroupLayout.DEFAULT_SIZE , 25 , Short.MAX_VALUE )
				.addComponent( cmdMinimize , GroupLayout.DEFAULT_SIZE , 25 , Short.MAX_VALUE ) );
		
	}
	
	private Buttont cmdClose;
	private Buttont cmdMinimize;
	private Buttont cmdResize;
	private GoogleMaterialIcon iconClose;
	private GoogleMaterialIcon iconMax;
	private GoogleMaterialIcon iconMinimize;
	private GoogleMaterialIcon iconRestore;
	
}
