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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.border.MatteBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import controller.HomeController;
import design.button.ButtonShadow;
import design.messageDialog.Message;
import design.notification.Notification;
import design.notification.Notification.Location;
import design.notification.Notification.NotificationType;
import design.panel.RoundedShadowPanel;
import design.scroll.ScrollBarCustom;
import design.table.CustomTable;
import design.txtInput.TextField;
import javaswingdev.FontAwesome;
import javaswingdev.FontAwesomeIcon;
import model.Patient;
import raven.glasspanepopup.GlassPanePopup;
import view.View;


public class HomePanel extends RoundedShadowPanel {
	
	private static final long serialVersionUID = -3312651701806916282L;
	private FontAwesomeIcon searchIcon;
	private JLabel lblSearch;
	private TextField txtSearch;
	private ButtonShadow btnDodaj;
	private ButtonShadow btnIzbrisi;
	private ButtonShadow btnPregled;
	private ButtonShadow btnDetalji;
	private CustomTable table;
	private JScrollPane tableScrollPane;
	private ScrollBarCustom tableScrollBar;
	private Notification notification;
	
//	*** VIEW + PANEL ***
	@SuppressWarnings( "unused" )
	private View view;
	private CardPanel cardParent;
	private NoviPregledPanel noviPregledPanel;
	private DetaljiPanel detaljiPanel;
	private HomeController homeController;
	
	public HomePanel( CardPanel cardParent , View view ) {
		
		super( 20 );
		this.view = view;
		this.cardParent = cardParent;
		this.homeController = new HomeController( this );
		homeController.startPatientCheckThread();
		
		setToolTipText( "Klikni za odzna�?avanje pacijenta ..." );
		setOpaque( false );
		setFont( new Font( "Century Gothic" , Font.BOLD , 16 ) );
		setBackground( new Color( 244 , 244 , 249 ) );
		
		notification = new Notification( view , NotificationType.INFO , Location.TOP_CENTER , "" , "" );
		
		searchIcon = new FontAwesomeIcon();
		searchIcon.setIcon( FontAwesome.SEARCH );
		searchIcon.setSize( 15 );
		searchIcon.setColor1( new Color( 121 , 118 , 118 ) );
		searchIcon.setColor2( new Color( 121 , 118 , 118 ) );
		
		lblSearch = new JLabel( "" );
		lblSearch.setIcon( searchIcon.toIcon() );
		lblSearch.setMaximumSize( new Dimension( 340 , 65 ) );
		
		txtSearch = new TextField();
		txtSearch.setPreferredSize( new Dimension( 340 , 65 ) );
		txtSearch.setMinimumSize( new Dimension( 340 , 65 ) );
		txtSearch.setMaximumSize( new Dimension( 340 , 65 ) );
		txtSearch.setLineColor( new Color( 46 , 191 , 165 ) );
		txtSearch.setLabelText( "Pretra\u017Ei" );
		txtSearch.setHintTextColor( new Color( 121 , 118 , 118 ) );
		txtSearch.setForeground( new Color( 44 , 51 , 51 ) );
		txtSearch.setFont( new Font( "Century Gothic" , Font.PLAIN , 15 ) );
		txtSearch.setBackground( new Color( 244 , 244 , 249 ) );
		
		btnIzbrisi = new ButtonShadow();
		btnIzbrisi.setEnabled( false );
		btnIzbrisi.setMaximumSize( new Dimension( 170 , 45 ) );
		btnIzbrisi.setMinimumSize( new Dimension( 170 , 45 ) );
		btnIzbrisi.setPreferredSize( new Dimension( 170 , 45 ) );
		btnIzbrisi.setText( "Izbri\u0161i" );
		btnIzbrisi.setForegroundColorOUT( new Color( 146 , 20 , 12 ) );
		btnIzbrisi.setForegroundColorIN( new Color( 146 , 20 , 12 ) );
		btnIzbrisi.setForeground( new Color( 146 , 20 , 12 ) );
		btnIzbrisi.setFont( new Font( "Century Gothic" , Font.PLAIN , 15 ) );
		btnIzbrisi.setFocusPainted( false );
		
		btnDodaj = new ButtonShadow();
		btnDodaj.setMaximumSize( new Dimension( 200 , 45 ) );
		btnDodaj.setMinimumSize( new Dimension( 200 , 45 ) );
		btnDodaj.setPreferredSize( new Dimension( 200 , 45 ) );
		btnDodaj.setText( "Dodaj novog pacijenta" );
		btnDodaj.setForeground( new Color( 121 , 118 , 118 ) );
		btnDodaj.setFont( new Font( "Century Gothic" , Font.PLAIN , 15 ) );
		btnDodaj.setFocusPainted( false );
		
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
		table.setForeground( new Color( 0 , 0 , 0 ) );
		table.setFont( new Font( "Century Gothic" , Font.PLAIN , 15 ) );
		table.setCursor( Cursor.getPredefinedCursor( Cursor.HAND_CURSOR ) );
		table.setBackground( getBackground() );
		table.setBorder( null );
		table.setShowVerticalLines( false );
		table.setShowHorizontalLines( false );
		table.setShowGrid( false );
		table.getTableHeader().setPreferredSize( new Dimension( 785 , 40 ) );
		table.getTableHeader().setFont( new Font( "Century Gothic" , Font.BOLD , 15 ) );
		tableScrollPane.setViewportView( table );
		table.setModel( new DefaultTableModel( new Object[][] {} ,
				new String[] { "ID" , "Ime i prezime" , "OIB" , "Mobitel" , "Mail" , "Zadnji pregled" } ) );
		table.getColumnModel().getColumn( 0 ).setMaxWidth( 80 );
		
		insertTableData();
		
		btnDetalji = new ButtonShadow();
		btnDetalji.setEnabled( false );
		btnDetalji.setText( "Detalji" );
		btnDetalji.setPreferredSize( new Dimension( 170 , 45 ) );
		btnDetalji.setMinimumSize( new Dimension( 170 , 45 ) );
		btnDetalji.setMaximumSize( new Dimension( 170 , 45 ) );
		btnDetalji.setForeground( new Color( 121 , 118 , 118 ) );
		btnDetalji.setFont( new Font( "Century Gothic" , Font.PLAIN , 15 ) );
		btnDetalji.setFocusPainted( false );
		
		btnPregled = new ButtonShadow();
		btnPregled.setEnabled( false );
		btnPregled.setMaximumSize( new Dimension( 170 , 45 ) );
		btnPregled.setMinimumSize( new Dimension( 170 , 45 ) );
		btnPregled.setPreferredSize( new Dimension( 170 , 45 ) );
		btnPregled.setText( "Novi pregled" );
		btnPregled.setForeground( new Color( 121 , 118 , 118 ) );
		btnPregled.setFont( new Font( "Century Gothic" , Font.PLAIN , 15 ) );
		btnPregled.setFocusPainted( false );
		
		initLayout();
		activatePanel();
		
	}
	
	
	private void initLayout() {
		
		GridBagLayout gbl_mainPanel = new GridBagLayout();
		gbl_mainPanel.columnWidths = new int[] { 35 , 170 , 170 , 240 , 240 , 35 , 0 };
		gbl_mainPanel.rowHeights = new int[] { 75 , 470 , 45 , 65 , 65 , 65 , 65 , 60 , 30 , 0 };
		gbl_mainPanel.columnWeights = new double[] { 1.0 , 0.0 , 1.0 , 1.0 , 1.0 , 1.0 , Double.MIN_VALUE };
		gbl_mainPanel.rowWeights = new double[] { 0.0 , 0.0 , 0.0 , 0.0 , 0.0 , 0.0 , 0.0 , 0.0 , 1.0 ,
												  Double.MIN_VALUE };
		
		setLayout( gbl_mainPanel );
		
		GridBagConstraints gbc_lblSearch = new GridBagConstraints();
		gbc_lblSearch.gridx = 0;
		gbc_lblSearch.gridy = 0;
		gbc_lblSearch.anchor = GridBagConstraints.EAST;
		gbc_lblSearch.insets = new Insets( 20 , 0 , 5 , 5 );
		add( lblSearch , gbc_lblSearch );
		
		GridBagConstraints gbc_txtSearch = new GridBagConstraints();
		gbc_txtSearch.gridwidth = 2;
		gbc_txtSearch.anchor = GridBagConstraints.SOUTHWEST;
		gbc_txtSearch.fill = GridBagConstraints.VERTICAL;
		gbc_txtSearch.insets = new Insets( 10 , 0 , 5 , 5 );
		gbc_txtSearch.gridx = 1;
		gbc_txtSearch.gridy = 0;
		add( txtSearch , gbc_txtSearch );
		
		GridBagConstraints gbc_btnIzbrisi = new GridBagConstraints();
		gbc_btnIzbrisi.anchor = GridBagConstraints.SOUTHEAST;
		gbc_btnIzbrisi.insets = new Insets( 0 , 0 , 5 , 5 );
		gbc_btnIzbrisi.gridx = 3;
		gbc_btnIzbrisi.gridy = 0;
		add( btnIzbrisi , gbc_btnIzbrisi );
		
		GridBagConstraints gbc_btnDodaj = new GridBagConstraints();
		gbc_btnDodaj.anchor = GridBagConstraints.SOUTHEAST;
		gbc_btnDodaj.insets = new Insets( 0 , 0 , 5 , 5 );
		gbc_btnDodaj.gridx = 4;
		gbc_btnDodaj.gridy = 0;
		add( btnDodaj , gbc_btnDodaj );
		
		GridBagConstraints gbc_tableScrollPane = new GridBagConstraints();
		gbc_tableScrollPane.gridheight = 6;
		gbc_tableScrollPane.fill = GridBagConstraints.BOTH;
		gbc_tableScrollPane.insets = new Insets( 10 , 10 , 10 , 10 );
		gbc_tableScrollPane.gridwidth = 4;
		gbc_tableScrollPane.gridx = 1;
		gbc_tableScrollPane.gridy = 1;
		add( tableScrollPane , gbc_tableScrollPane );
		
		GridBagConstraints gbc_btnDetalji = new GridBagConstraints();
		gbc_btnDetalji.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnDetalji.insets = new Insets( 0 , 0 , 5 , 5 );
		gbc_btnDetalji.gridx = 1;
		gbc_btnDetalji.gridy = 7;
		add( btnDetalji , gbc_btnDetalji );
		
		GridBagConstraints gbc_btnPregled = new GridBagConstraints();
		gbc_btnPregled.anchor = GridBagConstraints.NORTHEAST;
		gbc_btnPregled.insets = new Insets( 0 , 0 , 5 , 5 );
		gbc_btnPregled.gridx = 4;
		gbc_btnPregled.gridy = 7;
		add( btnPregled , gbc_btnPregled );
		
	}
	
	
	private void activatePanel() {
		
//		PANEL LISTENER - CLEAR ALL
		addMouseListener( new MouseAdapter() {
			
			@Override
			public void mouseClicked( MouseEvent e ) {
				
				clearAll();
				
			}
			
		} );
		
//		TEXTFIELD LISTENER - SEARCH		
		txtSearch.addKeyListener( new KeyAdapter() {
			
			@Override
			public void keyReleased( KeyEvent e ) {
				
				homeController.setSearchData();
				
			}
			
		} );
		
//		BTN LISTENERI
		btnPregled.addActionListener( new ActionListener() {
			
			public void actionPerformed( ActionEvent e ) {
				
				Patient patient = homeController.getSelectedPatient( table.getSelectedRow() );
				noviPregledPanel.setPatient( patient );
				cardParent.showCard( "noviPregledPanel" );
				
			}
			
		} );
		
		btnPregled.addMouseListener( new MouseAdapter() {
			
			@Override
			public void mouseClicked( MouseEvent e ) {
				
				if ( !btnPregled.isEnabled() ) {
					
					notification.setType( NotificationType.WARNING );
					notification.setLblTitle( "Upozorenje" );
					notification.setLbMessageText( "Odaberite pacijenta" );
					notification.showNotification();
					
				}
				
			}
			
		} );
		
		btnDetalji.addMouseListener( new MouseAdapter() {
			
			@Override
			public void mouseClicked( MouseEvent e ) {
				
				if ( !btnDetalji.isEnabled() ) {
					
					notification.setType( NotificationType.WARNING );
					notification.setLblTitle( "Upozorenje" );
					notification.setLbMessageText( "Odaberite pacijenta" );
					notification.showNotification();
					
				}
				
			}
			
		} );
		
		btnDetalji.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed( ActionEvent e ) {
				
				Patient patient = homeController.getSelectedPatient( table.getSelectedRow() );
				detaljiPanel.setPatient( patient );
				
				cardParent.showCard( "detaljiPanel" );
				
			}
			
		} );
		
		btnDodaj.addMouseListener( new MouseAdapter() {
			
			@Override
			public void mouseClicked( MouseEvent e ) {
				
				if ( !btnDodaj.isEnabled() ) {
					
					notification.setType( NotificationType.WARNING );
					notification.setLblTitle( "Upozorenje" );
					notification.setLbMessageText( "Odznačite odabranog pacijenta" );
					notification.showNotification();
					
				}
				
			}
			
		} );
		
		btnDodaj.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed( ActionEvent e ) {
				
				cardParent.showCard( "noviPacijentPanel" );
				
			}
			
		} );
		
		btnIzbrisi.addMouseListener( new MouseAdapter() {
			
			@Override
			public void mouseClicked( MouseEvent e ) {
				
				if ( !btnIzbrisi.isEnabled() ) {
					
					notification.setType( NotificationType.WARNING );
					notification.setLblTitle( "Upozorenje" );
					notification.setLbMessageText( "Odaberite pacijenta" );
					notification.showNotification();
					
				}
				
			}
			
		} );
		
		btnIzbrisi.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed( ActionEvent e ) {
				
				notification.setType( NotificationType.WARNING );
				notification.setLblTitle( "Pacijent izbrisan" );
				notification.setLbMessageText(
						"Pacijent " + table.getValueAt( table.getSelectedRow() , 1 ) + " izbrisan ..." );
				
				Message msg = new Message();
				msg.setMessageTitle( "Jeste li sigurni da želite izbrisati pacijenta: "
						+ table.getValueAt( table.getSelectedRow() , 1 ) );
				msg.setMessageText( "Pritiskom gumba OK pacijent se briše nepovratno." );
				msg.eventOK( new ActionListener() {
					
					@Override
					public void actionPerformed( ActionEvent ae ) {
						
						homeController.deletePatient( table.getSelectedRow() );
						
						notification.showNotification();
						GlassPanePopup.closePopupLast();
						
					}
					
				} );
				
				GlassPanePopup.showPopup( msg );
				msg = null;
				
			}
			
		} );
		
//		TABLE LISTENERI
		table.getSelectionModel().addListSelectionListener( new ListSelectionListener() {
			
			@Override
			public void valueChanged( ListSelectionEvent e ) {
				
				btnEnableDisable();
				
			}
			
		} );
		
		table.addMouseListener( new MouseAdapter() {
			
			@Override
			public void mouseClicked( MouseEvent e ) {
				
				if ( e.getClickCount() == 2 && !e.isConsumed() ) {
					
					e.consume();
					Patient patient = homeController.getSelectedPatient( table.getSelectedRow() );
					noviPregledPanel.setPatient( patient );
					cardParent.showCard( "noviPregledPanel" );
					
				}
				
			}
			
		} );
		
	}
	
	
	private void insertTableData() {
		
		homeController.setTableData();
		
	}
	
	
	public void clearAll() {
		
		txtSearch.setText( "" );
		homeController.setTableData();
		table.clearSelection();
		
		this.requestFocus();
		
	}
	
	
	private void btnEnableDisable() {
		
		if ( table.getSelectionModel().isSelectionEmpty() ) {
			
			btnDodaj.setEnabled( true );
			btnIzbrisi.setEnabled( false );
			btnPregled.setEnabled( false );
			btnDetalji.setEnabled( false );
			
		} else {
			
			btnDodaj.setEnabled( false );
			btnIzbrisi.setEnabled( true );
			btnPregled.setEnabled( true );
			btnDetalji.setEnabled( true );
			
		}
		
	}
	
	
	public void setNoviPregledPanel( NoviPregledPanel noviPregledPanel ) {
		
		this.noviPregledPanel = noviPregledPanel;
		
	}
	
	
	public void setDetaljiPanel( DetaljiPanel detaljiPanel ) {
		
		this.detaljiPanel = detaljiPanel;
		
	}
	
	/*
	 * 
	 * Getters and setters for Panel components
	 * 
	 */
	
	
	/**
	 * @return the txtSearch
	 */
	public TextField getTxtSearch() {
		
		return txtSearch;
		
	}
	
	
	/**
	 * @return the btnDodaj
	 */
	public ButtonShadow getBtnDodaj() {
		
		return btnDodaj;
		
	}
	
	
	/**
	 * @return the btnIzbrisi
	 */
	public ButtonShadow getBtnIzbrisi() {
		
		return btnIzbrisi;
		
	}
	
	
	/**
	 * @return the btnPregled
	 */
	public ButtonShadow getBtnPregled() {
		
		return btnPregled;
		
	}
	
	
	/**
	 * @return the btnDetalji
	 */
	public ButtonShadow getBtnDetalji() {
		
		return btnDetalji;
		
	}
	
	
	/**
	 * @return the table
	 */
	public CustomTable getTable() {
		
		return table;
		
	}
	
	
	/**
	 * @return the cardParent
	 */
	public CardPanel getCardParent() {
		
		return cardParent;
		
	}
	
}
