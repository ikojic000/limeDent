package view.panel.menuBar;


import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;


public class MenuItem extends JPanel {
	
	/**
	 *
	 */
	private static final long serialVersionUID = 5935144180086751839L;
	
	public int getIndex() {
		
		return index;
		
	}
	
	
	public void setIndex( int index ) {
		
		this.index = index;
		
	}
	
	
	public boolean isSelected() {
		
		return selected;
		
	}
	
	
	public void setSelected( boolean selected ) {
		
		this.selected = selected;
		repaint();
		
	}
	
	private final List<EventMenuSelected> events = new ArrayList<>();
	private int index;
	private boolean selected;
	private boolean mouseOver;
	
	public MenuItem( Icon icon , String name , int index ) {
		
		setBackground( new Color( 255 , 0 , 0 ) );
		
		setOpaque( false );
		setCursor( Cursor.getPredefinedCursor( Cursor.HAND_CURSOR ) );
		this.index = index;
		
		lbIcon = new JLabel();
		lbIcon.setIcon( icon );
		lbIcon.setHorizontalAlignment( SwingConstants.CENTER );
		
		lbName = new JLabel();
		lbName.setText( name );
		lbName.setFont( new Font( "Century Gothic" , Font.BOLD , 15 ) );
		lbName.setForeground( new Color( 244 , 244 , 249 ) );
		initComponents();
		addMouseListener( new MouseAdapter() {
			
			@Override
			public void mouseEntered( MouseEvent me ) {
				
				mouseOver = true;
				
			}
			
			
			@Override
			public void mouseExited( MouseEvent me ) {
				
				mouseOver = false;
				
			}
			
			
			@Override
			public void mouseReleased( MouseEvent me ) {
				
				if ( SwingUtilities.isLeftMouseButton( me ) ) {
					
					if ( mouseOver ) {
						
						setSelected( true );
						repaint();
						runEvent();
						
					}
					
				}
				
			}
			
		} );
		
	}
	
	
	@Override
	protected void paintComponent( Graphics grphcs ) {
		
		if ( selected ) {
			
			Graphics2D g2 = (Graphics2D) grphcs;
			g2.setRenderingHint( RenderingHints.KEY_ANTIALIASING , RenderingHints.VALUE_ANTIALIAS_ON );
			g2.setColor( new Color( 46 , 191 , 165 ) );
			g2.setComposite( AlphaComposite.getInstance( AlphaComposite.SRC_OVER , 0.2f ) );
			g2.fillRect( 0 , 0 , getWidth() , getHeight() );
			g2.setComposite( AlphaComposite.SrcOver );
			g2.setColor( new Color( 244 , 244 , 249 ) );
			g2.fillRect( 0 , 0 , 8 , getHeight() );
			
		}
		
		super.paintComponent( grphcs );
		
	}
	
	
	private void runEvent() {
		
		for ( EventMenuSelected event : events ) {
			
			event.selected( index );
			
		}
		
	}
	
	
	public void addEvent( EventMenuSelected event ) {
		
		events.add( event );
		
	}
	
	
	private void initComponents() {
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 40 , 350 , 0 };
		gridBagLayout.rowHeights = new int[] { 50 , 0 };
		gridBagLayout.columnWeights = new double[] { 0.0 , 1.0 , Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 1.0 , Double.MIN_VALUE };
		setLayout( gridBagLayout );
		
		GridBagConstraints gbc_lbName = new GridBagConstraints();
		gbc_lbName.anchor = GridBagConstraints.WEST;
		gbc_lbName.insets = new Insets( 0 , 20 , 0 , 0 );
		gbc_lbName.fill = GridBagConstraints.VERTICAL;
		gbc_lbName.gridx = 1;
		gbc_lbName.gridy = 0;
		add( lbName , gbc_lbName );
		
		GridBagConstraints gbc_lbIcon = new GridBagConstraints();
		gbc_lbIcon.fill = GridBagConstraints.BOTH;
		gbc_lbIcon.insets = new Insets( 0 , 20 , 0 , 5 );
		gbc_lbIcon.gridx = 0;
		gbc_lbIcon.gridy = 0;
		add( lbIcon , gbc_lbIcon );
		
	}
	
	private JLabel lbIcon;
	private JLabel lbName;
	
}
