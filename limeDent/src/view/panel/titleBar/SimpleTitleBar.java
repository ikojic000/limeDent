package view.panel.titleBar;


import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;


public class SimpleTitleBar extends JPanel {
	
	private static final long serialVersionUID = -978416423870696735L;
	private ComponentResizer resize;
	private int x;
	private int y;
	private SimpleButtonBar simpleButtonBar1;
	private JPanel panelMove;
	private JLabel lblTitle;
	
	public SimpleTitleBar( String title ) {
		
		initComponents( title );
		
	}
	
	
	public void init( JFrame fram ) {
		
		resize = new ComponentResizer();
		resize.setSnapSize( new Dimension( 10 , 10 ) );
		resize.setMinimumSize( new Dimension( 300 , 200 ) );
		resize.setMaximumSize( Toolkit.getDefaultToolkit().getScreenSize() );
		resize.registerComponent( fram );
		initMoving( fram );
		simpleButtonBar1.initEvent( fram );
		
	}
	
	
	private void initMoving( JFrame fram ) {
		
		panelMove.addMouseListener( new MouseAdapter() {
			
			@Override
			public void mousePressed( MouseEvent me ) {
				
				if ( fram.getExtendedState() != Frame.MAXIMIZED_BOTH && SwingUtilities.isLeftMouseButton( me ) ) {
					
					x = me.getX();
					y = me.getY();
		
//					x = me.getX() + 3;
//					y = me.getY() + 3;
				}
				
			}
			
			
			@Override
			public void mouseClicked( MouseEvent me ) {
				
				if ( SwingUtilities.isLeftMouseButton( me ) && me.getClickCount() == 2 ) {
					
					if ( fram.getExtendedState() == Frame.MAXIMIZED_BOTH ) {
						
						fram.setExtendedState( Frame.NORMAL );
						
					} else {
						
						fram.setExtendedState( Frame.MAXIMIZED_BOTH );
						
					}
					
				}
				
			}
			
		} );
		panelMove.addMouseMotionListener( new MouseMotionAdapter() {
			
			@Override
			public void mouseDragged( MouseEvent me ) {
				
				if ( SwingUtilities.isLeftMouseButton( me ) ) {
					
					if ( fram.getExtendedState() == Frame.MAXIMIZED_BOTH ) {
						
						fram.setExtendedState( Frame.NORMAL );
						
					}
					
					fram.setLocation( me.getXOnScreen() - x , me.getYOnScreen() - y );
					
				}
				
			}
			
		} );
		
	}
	
	
	private void initComponents( String title ) {
		
		panelMove = new JPanel();
		lblTitle = new JLabel();
		Image appIcon = new ImageIcon( this.getClass().getResource( "/lime_icon.png" ) ).getImage();
		lblTitle.setIcon( new ImageIcon( appIcon ) );
		lblTitle.setIconTextGap( 10 );
		
		simpleButtonBar1 = new SimpleButtonBar();
		
		setBackground( new Color( 50 , 50 , 50 ) );
		
		panelMove.setOpaque( false );
		panelMove.setCursor( Cursor.getPredefinedCursor( Cursor.HAND_CURSOR ) );
		
		lblTitle.setFont( new Font( "Century Gothic" , 1 , 18 ) );
		lblTitle.setForeground( new Color( 244 , 244 , 249 ) );
		lblTitle.setText( title );
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWeights = new double[] { 0.0 , 1.0 , 0.0 };
		gridBagLayout.columnWidths = new int[] { 100 , 78 , 0 };
		setLayout( gridBagLayout );
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets( 0 , 15 , 0 , 0 );
		gbc.gridx = 0;
		gbc.gridy = 0;
		add( lblTitle , gbc );
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets( 0 , 0 , 0 , 0 );
		add( panelMove , gbc );
		
		gbc.gridx = 2;
		gbc.gridy = 0;
		gbc.insets = new Insets( 0 , 5 , 0 , 5 );
		add( simpleButtonBar1 , gbc );
		
	}
	
}
