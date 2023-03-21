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
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.border.MatteBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import controller.UslugeController;
import design.button.ButtonShadow;
import design.button.ButtonTable;
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
import model.Product;
import raven.glasspanepopup.GlassPanePopup;
import view.View;


public class UslugePanel extends RoundedShadowPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4380414859779004657L;
	private TextField txtNazivArtikla;
	private TextField txtCijenaArtikla;
	private ButtonShadow btnDodaj;
	private ButtonShadow btnUpdate;
	private ButtonShadow btnDelete;
	private TextField txtSearch;
	private FontAwesomeIcon searchIcon;
	private JLabel lblSearch;
	private JScrollPane tableScrollPane;
	private ScrollBarCustom tableScrollBar;
	private CustomTable table;
	private Notification notification;
	protected static final DecimalFormat dfKoristenje = new DecimalFormat( "###.00" ,
			DecimalFormatSymbols.getInstance( Locale.US ) );
	protected static final DecimalFormat dfPrikaz = new DecimalFormat( "###.00" ,
			DecimalFormatSymbols.getInstance( Locale.GERMANY ) );
	private JLabel lblFormTitle;
//	*** VIEW + PANEL ***
	@SuppressWarnings( "unused" )
	private View view;
	@SuppressWarnings( "unused" )
	private CardPanel cardParent;
	private UslugeController uslugeController;
	private ButtonTable btnUndo;
	
	public UslugePanel( CardPanel cardParent , View view ) {
		
		super( 20 );
		
		this.view = view;
		this.cardParent = cardParent;
		this.uslugeController = new UslugeController( this );
		
		setOpaque( false );
		setFont( new Font( "Century Gothic" , Font.BOLD , 16 ) );
		setBackground( new Color( 244 , 244 , 249 ) );
		
		notification = new Notification( view , NotificationType.INFO , Location.TOP_CENTER , "" , "" );
		
		txtNazivArtikla = new TextField();
		txtNazivArtikla.setPreferredSize( new Dimension( 300 , 70 ) );
		txtNazivArtikla.setMinimumSize( new Dimension( 300 , 70 ) );
		txtNazivArtikla.setMaximumSize( new Dimension( 300 , 70 ) );
		txtNazivArtikla.setLineColor( new Color( 46 , 191 , 165 ) );
		txtNazivArtikla.setLabelText( "Naziv usluge" );
		txtNazivArtikla.setHintTextColor( new Color( 121 , 118 , 118 ) );
		txtNazivArtikla.setForeground( new Color( 44 , 51 , 51 ) );
		txtNazivArtikla.setFont( new Font( "Century Gothic" , Font.PLAIN , 15 ) );
		txtNazivArtikla.setBackground( new Color( 244 , 244 , 249 ) );
		
		txtCijenaArtikla = new TextField();
		txtCijenaArtikla.setLineColor( new Color( 46 , 191 , 165 ) );
		txtCijenaArtikla.setLabelText( "Cijena usluge" );
		txtCijenaArtikla.setHintTextColor( new Color( 121 , 118 , 118 ) );
		txtCijenaArtikla.setForeground( new Color( 44 , 51 , 51 ) );
		txtCijenaArtikla.setFont( new Font( "Century Gothic" , Font.PLAIN , 15 ) );
		txtCijenaArtikla.setBackground( new Color( 244 , 244 , 249 ) );
		
		btnDodaj = new ButtonShadow();
		btnDodaj.setPreferredSize( new Dimension( 150 , 45 ) );
		btnDodaj.setText( "Dodaj" );
		btnDodaj.setForeground( new Color( 121 , 118 , 118 ) );
		btnDodaj.setFont( new Font( "Century Gothic" , Font.PLAIN , 15 ) );
		btnDodaj.setFocusPainted( false );
		
		btnUpdate = new ButtonShadow();
		btnUpdate.setPreferredSize( new Dimension( 170 , 45 ) );
		btnUpdate.setMinimumSize( new Dimension( 170 , 45 ) );
		btnUpdate.setMaximumSize( new Dimension( 170 , 45 ) );
		btnUpdate.setText( "Ažuriraj" );
		btnUpdate.setForeground( new Color( 121 , 118 , 118 ) );
		btnUpdate.setFont( new Font( "Century Gothic" , Font.PLAIN , 15 ) );
		btnUpdate.setFocusPainted( false );
		btnUpdate.setEnabled( false );
		
		searchIcon = new FontAwesomeIcon();
		searchIcon.setIcon( FontAwesome.SEARCH );
		searchIcon.setSize( 20 );
		searchIcon.setColor1( new Color( 121 , 118 , 118 ) );
		searchIcon.setColor2( new Color( 121 , 118 , 118 ) );
		
		lblSearch = new JLabel( "" );
		lblSearch.setIcon( searchIcon.toIcon() );
		
		txtSearch = new TextField();
		txtSearch.setMinimumSize( new Dimension( 350 , 65 ) );
		txtSearch.setMaximumSize( new Dimension( 350 , 65 ) );
		txtSearch.setPreferredSize( new Dimension( 350 , 65 ) );
		txtSearch.setLineColor( new Color( 46 , 191 , 165 ) );
		txtSearch.setLabelText( "Pretraži.." );
		txtSearch.setHintTextColor( new Color( 121 , 118 , 118 ) );
		txtSearch.setForeground( new Color( 44 , 51 , 51 ) );
		txtSearch.setFont( new Font( "Century Gothic" , Font.PLAIN , 15 ) );
		txtSearch.setBackground( new Color( 244 , 244 , 249 ) );
		
		btnUndo = new ButtonTable();
		btnUndo.setIconTextGap( 20 );
		btnUndo.setFont( new Font( "Century Gothic" , Font.PLAIN , 15 ) );
		btnUndo.setText( "Poništi brisanje" );
		btnUndo.setFocusPainted( false );
		btnUndo.setIcon( new ImageIcon( UslugePanel.class.getResource( "/undo.png" ) ) );
		
		btnDelete = new ButtonShadow();
		btnDelete.setIconTextGap( 2 );
		btnDelete.setIcon( null );
		btnDelete.setPreferredSize( new Dimension( 150 , 45 ) );
		btnDelete.setText( "Izbri\u0161i" );
		btnDelete.setForegroundColorOUT( new Color( 146 , 20 , 12 ) );
		btnDelete.setForegroundColorIN( new Color( 146 , 20 , 12 ) );
		btnDelete.setForeground( new Color( 146 , 20 , 12 ) );
		btnDelete.setFont( new Font( "Century Gothic" , Font.PLAIN , 15 ) );
		btnDelete.setFocusPainted( false );
		btnDelete.setEnabled( false );
		
		tableScrollPane = new JScrollPane();
		tableScrollPane.setForeground( Color.BLACK );
		tableScrollPane.setBorder( new MatteBorder( 1 , 1 , 1 , 1 , (Color) getBackground() ) );
		tableScrollPane.setBackground( new Color( 244 , 244 , 249 ) );
		tableScrollPane.getViewport().setBackground( new Color( 244 , 244 , 249 ) );
		
		tableScrollBar = new ScrollBarCustom( 80 );
		tableScrollBar.setUnitIncrement( 2 );
		tableScrollBar.setThumbSize( 80 );
		tableScrollBar.setForeground( new Color( 46 , 191 , 165 ) );
		tableScrollPane.setVerticalScrollBar( tableScrollBar );
		
		table = new CustomTable();
		table.setFirstBold( false );
		table.setForeground( new Color( 0 , 0 , 0 ) );
		table.setFont( new Font( "Century Gothic" , Font.PLAIN , 15 ) );
		table.setCursor( Cursor.getPredefinedCursor( Cursor.HAND_CURSOR ) );
		table.setBackground( getBackground() );
		table.setBorder( null );
		table.setShowVerticalLines( false );
		table.setShowHorizontalLines( false );
		table.setShowGrid( false );
		
		table.setModel(
				new DefaultTableModel( new Object[][] {} , new String[] { "ID" , "Šifra" , "Naziv" , "Cijena" , } ) );
		
		table.getTableHeader().setPreferredSize( new Dimension( 785 , 40 ) );
		table.getTableHeader().setFont( new Font( "Century Gothic" , Font.BOLD , 15 ) );
		
		table.getColumnModel().getColumn( 0 ).setMaxWidth( 100 );
		
		tableScrollPane.setViewportView( table );
		insertTableData();
		
		lblFormTitle = new JLabel( "Dodaj novu uslugu" );
		lblFormTitle.setForeground( new Color( 121 , 118 , 118 ) );
		lblFormTitle.setFont( new Font( "Century Gothic" , Font.BOLD , 18 ) );
		
		initLayout();
		activatePanel();
		
	}
	
	
	private void activatePanel() {
		
//		PANEL LISTENER - CLEAR ALL
		addMouseListener( new MouseAdapter() {
			
			@Override
			public void mouseClicked( MouseEvent e ) {
				
				if ( table.getSelectionModel().isSelectionEmpty() ) {
					
					table.clearSelection();
					
				} else {
					
					clearAll();
					
				}
				
			}
			
		} );
		
//		TABLE LISTENERI
		table.addMouseListener( new MouseAdapter() {
			
			@Override
			public void mouseClicked( MouseEvent e ) {
				
				Product product = uslugeController.getSelectedProduct( table.getSelectedRow() );
				txtNazivArtikla.setText( product.getName() );
				txtCijenaArtikla.setText( dfPrikaz.format( product.getPrice() ) );
				
			}
			
		} );
		
		table.getSelectionModel().addListSelectionListener( new ListSelectionListener() {
			
			@Override
			public void valueChanged( ListSelectionEvent e ) {
				
				if ( table.getSelectionModel().isSelectionEmpty() ) {
					
					btnDodaj.setEnabled( true );
					btnUpdate.setEnabled( false );
					btnDelete.setEnabled( false );
					lblFormTitle.setText( "Dodaj novu uslugu" );
					
				} else {
					
					btnDodaj.setEnabled( false );
					btnUpdate.setEnabled( true );
					btnDelete.setEnabled( true );
					lblFormTitle.setText( "Ažuriraj / izbriši odabran uslugu" );
					
				}
				
			}
			
		} );
		
//		BTN LISTENERI
		btnDodaj.addMouseListener( new MouseAdapter() {
			
			@Override
			public void mouseClicked( MouseEvent e ) {
				
				if ( !btnDodaj.isEnabled() ) {
					
					notification.setType( NotificationType.WARNING );
					notification.setLblTitle( "Upozorenje" );
					notification.setLbMessageText( "Odznačite odabranog uslugu ..." );
					notification.showNotification();
					
				}
				
			}
			
		} );
		
		btnDodaj.addActionListener( new ActionListener() {
			
			public void actionPerformed( ActionEvent e ) {
				
				System.out.println( "CIJENA: " + txtCijenaArtikla.getText() );
				
				BigDecimal cijena = BigDecimal
						.valueOf( Double.parseDouble( txtCijenaArtikla.getText().replace( "," , "." ) ) );
				System.out.println( "Cijena nakon formata: " + cijena );
				Product addedProduct = new Product( txtNazivArtikla.getText() , cijena );
				uslugeController.addProduct( addedProduct );
				
				notification.setType( NotificationType.SUCCESS );
				notification.setLblTitle( "Dodana nova usluga" );
				notification.setLbMessageText( "Dodali ste " + addedProduct.getName() + " u bazu..." );
				notification.showNotification();
				
			}
			
		} );
		
		btnUpdate.addMouseListener( new MouseAdapter() {
			
			@Override
			public void mouseClicked( MouseEvent e ) {
				
				if ( !btnUpdate.isEnabled() ) {
					
					notification.setType( NotificationType.WARNING );
					notification.setLblTitle( "Upozorenje" );
					notification.setLbMessageText( "Označite uslugu ..." );
					notification.showNotification();
					
				}
				
			}
			
		} );
		
		btnUpdate.addActionListener( new ActionListener() {
			
			public void actionPerformed( ActionEvent e ) {
				
				notification.setType( NotificationType.SUCCESS );
				notification.setLblTitle( "Ažurirana usluga" );
				notification
						.setLbMessageText( "Ažurirali ste " + table.getValueAt( table.getSelectedRow() , 2 ) + " ..." );
				
				Message msg = new Message();
				msg.setMessageTitle( "Jeste li sigurni da želite ažurirati uslugu: "
						+ table.getValueAt( table.getSelectedRow() , 2 ) );
				msg.setMessageText( "Pritiskom gumba OK usluga će se ažurirati: \n" + "Stara vrijednosti: "
						+ table.getValueAt( table.getSelectedRow() , 2 ) + " - €"
						+ table.getValueAt( table.getSelectedRow() , 3 ) + "\n" + "Nove vrijednosti: "
						+ txtNazivArtikla.getText() + " - €" + txtCijenaArtikla.getText() );
				msg.eventOK( new ActionListener() {
					
					@Override
					public void actionPerformed( ActionEvent e ) {
						
						uslugeController.updateProduct( table.getSelectedRow() );
						notification.showNotification();
						GlassPanePopup.closePopupLast();
						
					}
					
				} );
				
				GlassPanePopup.showPopup( msg );
				msg = null;
				
			}
			
		} );
		
		btnDelete.addMouseListener( new MouseAdapter() {
			
			@Override
			public void mouseClicked( MouseEvent e ) {
				
				if ( !btnDelete.isEnabled() ) {
					
					notification.setType( NotificationType.WARNING );
					notification.setLblTitle( "Upozorenje" );
					notification.setLbMessageText( "Označite uslugu ..." );
					notification.showNotification();
					
				}
				
			}
			
		} );
		
		btnDelete.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed( ActionEvent e ) {
				
				notification.setType( NotificationType.SUCCESS );
				notification.setLblTitle( "Izbrisana usluga" );
				notification.setLbMessageText(
						"Izbrisali ste " + table.getValueAt( table.getSelectedRow() , 2 ) + " iz baze ..." );
				
				Message msg = new Message();
				msg.setMessageTitle( "Jeste li sigurni da želite izbrisati uslugu: "
						+ table.getValueAt( table.getSelectedRow() , 2 ) );
				msg.setMessageText( "Pritiskom gumba OK usluga se briše nepovratno." );
				msg.eventOK( new ActionListener() {
					
					@Override
					public void actionPerformed( ActionEvent e ) {
						
						try {
							
							uslugeController.deleteProduct( table.getSelectedRow() );
							notification.showNotification();
							GlassPanePopup.closePopupLast();
							
						} catch ( NumberFormatException e1 ) {
							
							e1.printStackTrace();
							
						}
						
					}
					
				} );
				
				GlassPanePopup.showPopup( msg );
				msg = null;
				
			}
			
		} );
		
		btnUndo.addActionListener( new ActionListener() {
			
			public void actionPerformed( ActionEvent e ) {
				
				String msg = uslugeController.undoCommandAction();
				
				notification.setType( NotificationType.INFO );
				notification.setLblTitle( "Info" );
				notification.setLbMessageText( msg );
				notification.showNotification();
				
			}
			
		} );
		
//		INPUT LISTENERI
		txtCijenaArtikla.addFocusListener( new FocusAdapter() {
			
			@Override
			public void focusLost( FocusEvent e ) {
				
				if ( txtCijenaArtikla.getText() == "" || txtCijenaArtikla.getText() == null ) {
					
					txtCijenaArtikla.setHelperText( "" );
					getParent().requestFocus();
					
				}
				
			}
			
		} );
		
		txtCijenaArtikla.addKeyListener( new KeyAdapter() {
			
			@Override
			public void keyReleased( KeyEvent e ) {
				
				checkCijena( txtCijenaArtikla.getText() );
				
			}
			
		} );
		
		txtSearch.addKeyListener( new KeyAdapter() {
			
			@Override
			public void keyReleased( KeyEvent e ) {
				
				txtSearch.addKeyListener( new KeyAdapter() {
					
					@Override
					public void keyReleased( KeyEvent e ) {
						
						uslugeController.setSearchData();
						
					}
					
				} );
				
			}
			
		} );
		
	}
	
	
	private void initLayout() {
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 50 , 50 , 50 , 50 , 50 , 50 , 50 , 50 , 50 , 50 , 50 , 50 , 50 , 50 ,
												 50 , 50 , 50 , 0 };
		gridBagLayout.rowHeights = new int[] { 65 , 70 , 0 , 35 , 45 , 35 , 70 , 35 , 389 , 0 };
		gridBagLayout.columnWeights = new double[] { 1.0 , 1.0 , 0.0 , 0.0 , 0.0 , 0.0 , 0.0 , 0.0 , 0.0 , 0.0 , 0.0 ,
													 0.0 , 0.0 , 0.0 , 0.0 , 1.0 , 1.0 , Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0 , 0.0 , 0.0 , 0.0 , 0.0 , 0.0 , 0.0 , 0.0 , 1.0 ,
												  Double.MIN_VALUE };
		setLayout( gridBagLayout );
		
		GridBagConstraints gbc_lblFormTitle = new GridBagConstraints();
		gbc_lblFormTitle.anchor = GridBagConstraints.SOUTHWEST;
		gbc_lblFormTitle.gridwidth = 6;
		gbc_lblFormTitle.insets = new Insets( 0 , 0 , 5 , 5 );
		gbc_lblFormTitle.gridx = 1;
		gbc_lblFormTitle.gridy = 0;
		add( lblFormTitle , gbc_lblFormTitle );
		
		GridBagConstraints gbc_txtNazivArtikla = new GridBagConstraints();
		gbc_txtNazivArtikla.anchor = GridBagConstraints.WEST;
		gbc_txtNazivArtikla.fill = GridBagConstraints.VERTICAL;
		gbc_txtNazivArtikla.insets = new Insets( 0 , 0 , 5 , 5 );
		gbc_txtNazivArtikla.gridwidth = 5;
		gbc_txtNazivArtikla.gridx = 1;
		gbc_txtNazivArtikla.gridy = 1;
		add( txtNazivArtikla , gbc_txtNazivArtikla );
		
		GridBagConstraints gbc_txtCijenaArtikla = new GridBagConstraints();
		gbc_txtCijenaArtikla.fill = GridBagConstraints.BOTH;
		gbc_txtCijenaArtikla.insets = new Insets( 0 , 0 , 5 , 5 );
		gbc_txtCijenaArtikla.gridwidth = 6;
		gbc_txtCijenaArtikla.gridx = 6;
		gbc_txtCijenaArtikla.gridy = 1;
		add( txtCijenaArtikla , gbc_txtCijenaArtikla );
		
		GridBagConstraints gbc_btnDodaj = new GridBagConstraints();
		gbc_btnDodaj.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnDodaj.insets = new Insets( 0 , 0 , 5 , 5 );
		gbc_btnDodaj.gridx = 1;
		gbc_btnDodaj.gridy = 4;
		add( btnDodaj , gbc_btnDodaj );
		
		GridBagConstraints gbc_btnUpdate = new GridBagConstraints();
		gbc_btnUpdate.anchor = GridBagConstraints.NORTH;
		gbc_btnUpdate.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnUpdate.insets = new Insets( 0 , 0 , 5 , 5 );
		gbc_btnUpdate.gridwidth = 3;
		gbc_btnUpdate.gridx = 2;
		gbc_btnUpdate.gridy = 4;
		add( btnUpdate , gbc_btnUpdate );
		
		GridBagConstraints gbc_btnUndo = new GridBagConstraints();
		gbc_btnUndo.anchor = GridBagConstraints.EAST;
		gbc_btnUndo.gridwidth = 6;
		gbc_btnUndo.fill = GridBagConstraints.VERTICAL;
		gbc_btnUndo.insets = new Insets( 0 , 0 , 5 , 0 );
		gbc_btnUndo.gridx = 10;
		gbc_btnUndo.gridy = 5;
		add( btnUndo , gbc_btnUndo );
		
		GridBagConstraints gbc_lblSearch = new GridBagConstraints();
		gbc_lblSearch.anchor = GridBagConstraints.EAST;
		gbc_lblSearch.insets = new Insets( 0 , 0 , 5 , 5 );
		gbc_lblSearch.gridx = 0;
		gbc_lblSearch.gridy = 6;
		add( lblSearch , gbc_lblSearch );
		
		GridBagConstraints gbc_txtSearch = new GridBagConstraints();
		gbc_txtSearch.fill = GridBagConstraints.BOTH;
		gbc_txtSearch.insets = new Insets( 0 , 0 , 5 , 5 );
		gbc_txtSearch.gridwidth = 5;
		gbc_txtSearch.gridx = 1;
		gbc_txtSearch.gridy = 6;
		add( txtSearch , gbc_txtSearch );
		
		GridBagConstraints gbc_btnDelete = new GridBagConstraints();
		gbc_btnDelete.gridwidth = 6;
		gbc_btnDelete.anchor = GridBagConstraints.SOUTHEAST;
		gbc_btnDelete.insets = new Insets( 0 , 0 , 5 , 5 );
		gbc_btnDelete.gridx = 10;
		gbc_btnDelete.gridy = 6;
		add( btnDelete , gbc_btnDelete );
		
		GridBagConstraints gbc_tableScrollPane = new GridBagConstraints();
		gbc_tableScrollPane.gridheight = 2;
		gbc_tableScrollPane.insets = new Insets( 0 , 10 , 50 , 10 );
		gbc_tableScrollPane.fill = GridBagConstraints.BOTH;
		gbc_tableScrollPane.gridwidth = 15;
		gbc_tableScrollPane.gridx = 1;
		gbc_tableScrollPane.gridy = 7;
		add( tableScrollPane , gbc_tableScrollPane );
		
	}
	
	
	private void insertTableData() {
		
		uslugeController.setTableData();
		
	}
	
	
	private void checkCijena( String cijena ) {
		
		boolean isCijenaValid = true;
		
		String regex = "^(\\d|-)?(\\d|,)*\\.?\\d*$";
		Pattern pattern = Pattern.compile( regex );
		Matcher matcher = pattern.matcher( cijena );
		
		if ( !matcher.matches() ) {
			
			isCijenaValid = false;
			
		}
		
		if ( !isCijenaValid ) {
			
			txtCijenaArtikla.setHelperText( "Unesite valjan oblik iznosa .." );
			
		} else {
			
			txtCijenaArtikla.setHelperText( "" );
			
		}
		
	}
	
	
	public void clearAll() {
		
		txtCijenaArtikla.setText( "" );
		txtNazivArtikla.setText( "" );
		txtSearch.setText( "" );
		table.clearSelection();
		this.requestFocus();
		
	}
	
	
	public CustomTable getTable() {
		
		return table;
		
	}
	
	
	/**
	 * @return the txtNazivArtikla
	 */
	public TextField getTxtNazivArtikla() {
		
		return txtNazivArtikla;
		
	}
	
	
	/**
	 * @return the txtCijenaArtikla
	 */
	public TextField getTxtCijenaArtikla() {
		
		return txtCijenaArtikla;
		
	}
	
	
	/**
	 * @return the txtSearch
	 */
	public TextField getTxtSearch() {
		
		return txtSearch;
		
	}
	
}
