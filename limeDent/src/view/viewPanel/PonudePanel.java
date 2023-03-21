package view.viewPanel;


import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;

import controller.PonudeController;
import design.messageDialog.Message;
import design.notification.Notification;
import design.notification.Notification.Location;
import design.notification.Notification.NotificationType;
import design.panel.RoundedShadowPanel;
import design.scroll.ScrollBarCustom;
import design.table.CustomTable;
import design.table.actionTable.TableActionEvent;
import design.txtInput.TextField;
import javaswingdev.FontAwesome;
import javaswingdev.FontAwesomeIcon;
import raven.glasspanepopup.GlassPanePopup;
import view.View;


public class PonudePanel extends RoundedShadowPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2670464673455355768L;
	private TextField txtSearch;
	private JScrollPane tableScrollPane;
	private ScrollBarCustom tableScrollBar;
	private CustomTable table;
	private FontAwesomeIcon searchIcon;
	private JLabel lblSearch;
	private Notification notification;
	
//	*** VIEW + PANEL ***
	@SuppressWarnings( "unused" )
	private View view;
	@SuppressWarnings( "unused" )
	private CardPanel cardParent;
	private PonudeController ponudeController;
	
	public PonudePanel( CardPanel cardParent , View view ) {
		
		super( 20 );
		this.view = view;
		this.cardParent = cardParent;
		this.ponudeController = new PonudeController( this );
		
		setOpaque( false );
		setFont( new Font( "Century Gothic" , Font.BOLD , 16 ) );
		setBackground( new Color( 244 , 244 , 249 ) );
		
		notification = new Notification( view , NotificationType.WARNING , Location.TOP_CENTER , "" , "" );
		
		searchIcon = new FontAwesomeIcon();
		searchIcon.setIcon( FontAwesome.SEARCH );
		searchIcon.setSize( 20 );
		searchIcon.setColor1( new Color( 121 , 118 , 118 ) );
		searchIcon.setColor2( new Color( 121 , 118 , 118 ) );
		
		lblSearch = new JLabel( "" );
		lblSearch.setIcon( searchIcon.toIcon() );
		
		txtSearch = new TextField();
		txtSearch.setMaximumSize( new Dimension( 350 , 65 ) );
		txtSearch.setMinimumSize( new Dimension( 350 , 65 ) );
		txtSearch.setPreferredSize( new Dimension( 350 , 65 ) );
		txtSearch.setLineColor( new Color( 46 , 191 , 165 ) );
		txtSearch.setLabelText( "Pretra\u017Ei.." );
		txtSearch.setHintTextColor( new Color( 121 , 118 , 118 ) );
		txtSearch.setForeground( new Color( 44 , 51 , 51 ) );
		txtSearch.setFont( new Font( "Century Gothic" , Font.PLAIN , 15 ) );
		txtSearch.setBackground( new Color( 244 , 244 , 249 ) );
		
		tableScrollPane = new JScrollPane();
		tableScrollPane.setForeground( new Color( 0 , 0 , 0 ) );
		tableScrollPane.setBackground( getBackground() );
		tableScrollPane.setBorder( new MatteBorder( 1 , 1 , 1 , 1 , (Color) getBackground() ) );
		tableScrollPane.getViewport().setBackground( getBackground() );
		
		tableScrollBar = new ScrollBarCustom( 80 );
		tableScrollBar.setUnitIncrement( 2 );
		tableScrollBar.setThumbSize( 80 );
		tableScrollBar.setForeground( new Color( 46 , 191 , 165 ) );
		tableScrollPane.setVerticalScrollBar( tableScrollBar );
		
		table = new CustomTable();
		table.setLastAction( true );
		table.setForeground( new Color( 0 , 0 , 0 ) );
		table.setFont( new Font( "Century Gothic" , Font.PLAIN , 15 ) );
		table.setCursor( Cursor.getPredefinedCursor( Cursor.HAND_CURSOR ) );
		table.setBackground( getBackground() );
		table.setBorder( null );
		table.setShowVerticalLines( false );
		table.setShowHorizontalLines( false );
		table.setShowGrid( false );
		table.setRowHeight( 45 );
		
		table.setModel( new DefaultTableModel( new Object[][] {} ,
				new String[] { "Šifra" , "Naziv" , "Autor" , "Pacijent" , "Datum" , "" } ) );
		
		table.getTableHeader().setPreferredSize( new Dimension( 785 , 40 ) );
		table.getTableHeader().setFont( new Font( "Century Gothic" , Font.BOLD , 15 ) );
		
		table.getColumnModel().getColumn( 0 ).setMaxWidth( 200 );
		
		tableScrollPane.setViewportView( table );
		
		insertTableData();
		
		initLayout();
		activatePanel();
		
	}
	
	
	private void activatePanel() {
		
//		TABLE LISTENERI
		TableActionEvent event = new TableActionEvent() {
			
			@Override
			public void onEdit( int row ) {
				
				System.out.println( "ID: " + ponudeController.getSelectedOffer( row ).getId() + " Title: "
						+ ponudeController.getSelectedOffer( row ).getTitle() );
				
			}
			
			
			@Override
			public void onDelete( int row ) {
				
				if ( table.isEditing() ) {
					
					table.getCellEditor().stopCellEditing();
					
				}
				
				notification.setLbMessageText(
						"Ponuda " + table.getValueAt( table.getSelectedRow() , 1 ) + " izbrisana ..." );
				Message msg = new Message();
				msg.setMessageTitle( "Brisanje pregleda" );
				msg.setMessageText( "Jeste li sigurni da želite izbrisati ponudu "
						+ table.getValueAt( table.getSelectedRow() , 1 ) + " ?" );
				
				msg.eventOK( new ActionListener() {
					
					@Override
					public void actionPerformed( ActionEvent e ) {
						
						ponudeController.deleteOffer( row );
						notification.showNotification();
						GlassPanePopup.closePopupLast();
						
					}
					
				} );
				GlassPanePopup.showPopup( msg );
				msg = null;
				
			}
			
			
			@Override
			public void onView( int row ) {
				
				System.out.println( "View row : " + row );
				ponudeController.viewPDF( row );
				
			}
			
		};
		
		table.setActionEvent( event );
		
//		TEXTFIELD LISTENERS
		txtSearch.addKeyListener( new KeyAdapter() {
			
			@Override
			public void keyReleased( KeyEvent e ) {
				
				ponudeController.setSearchData();
				
			}
			
		} );
		
	}
	
	
	private void initLayout() {
		
		GridBagLayout gbl_mainPanel = new GridBagLayout();
		gbl_mainPanel.columnWidths = new int[] { 0 , 200 , 420 , 200 , 0 , 0 };
		gbl_mainPanel.rowHeights = new int[] { 50 , 65 , 35 , 800 , 20 , 0 };
		gbl_mainPanel.columnWeights = new double[] { 1.0 , 1.0 , 0.0 , 1.0 , 1.0 , Double.MIN_VALUE };
		gbl_mainPanel.rowWeights = new double[] { 0.0 , 0.0 , 0.0 , 1.0 , 1.0 , Double.MIN_VALUE };
		setLayout( gbl_mainPanel );
		
		GridBagConstraints gbc_lblSearch = new GridBagConstraints();
		gbc_lblSearch.gridx = 0;
		gbc_lblSearch.gridy = 1;
		gbc_lblSearch.anchor = GridBagConstraints.EAST;
		gbc_lblSearch.insets = new Insets( 0 , 0 , 5 , 5 );
		
		add( lblSearch , gbc_lblSearch );
		
		GridBagConstraints gbc_txtSearch = new GridBagConstraints();
		gbc_txtSearch.gridwidth = 2;
		gbc_txtSearch.anchor = GridBagConstraints.WEST;
		gbc_txtSearch.insets = new Insets( 0 , 0 , 5 , 5 );
		gbc_txtSearch.fill = GridBagConstraints.VERTICAL;
		gbc_txtSearch.gridx = 1;
		gbc_txtSearch.gridy = 1;
		add( txtSearch , gbc_txtSearch );
		
		GridBagConstraints gbc_tableScrollPane = new GridBagConstraints();
		gbc_tableScrollPane.gridwidth = 3;
		gbc_tableScrollPane.insets = new Insets( 0 , 10 , 20 , 10 );
		gbc_tableScrollPane.fill = GridBagConstraints.BOTH;
		gbc_tableScrollPane.gridx = 1;
		gbc_tableScrollPane.gridy = 3;
		add( tableScrollPane , gbc_tableScrollPane );
		
	}
	
	
	public void insertTableData() {
		
		ponudeController.setTableData();
		
	}
	
	
	public void clearAll() {
		
		txtSearch.setText( "" );
		table.clearSelection();
		
	}
	
	
	/**
	 * @return the table
	 */
	public CustomTable getTable() {
		
		return table;
		
	}
	
	
	/**
	 * @return the txtSearch
	 */
	public TextField getTxtSearch() {
		
		return txtSearch;
		
	}
	
}
