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
import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;

import controller.LoginController;
import controller.NovaPonudaController;
import design.button.ButtonShadow;
import design.button.RadioButton;
import design.comboBox.ComboBox;
import design.panel.RoundedShadowPanel;
import design.scroll.ScrollBarCustom;
import design.table.CustomTable;
import design.txtInput.TextArea;
import design.txtInput.TextAreaScroll;
import design.txtInput.TextField;
import model.Offer;
import model.Product;
import model.User;
import view.View;


/**
 * 
 * @author ikojic000
 * 
 *         A UI panel for creating new offer. This panel extends the
 *         RoundedShadowPanel class. It includes various text fields, buttons,
 *         labels, table, combobox .... Fields that user fills in will generate
 *         in a PDF offer. This panel also contains a NovaPonudaController
 *         object to handle user actions.
 * 		
 */
public class NovaPonudaPanel extends RoundedShadowPanel {
	
	private static final long serialVersionUID = 4647942436198867355L;
	private TextField txtNazivPonude;
	private TextField txtImePrezime;
	private ComboBox<Product> cbArtikli;
	private TextField txtCijena;
	private TextField txtKolicina;
	private TextField txtPopust;
	private JScrollPane tableScrollPane;
	private ScrollBarCustom tableScrollBar;
	private CustomTable table;
	private ButtonShadow btnDodaj;
	private ButtonShadow btnIzbrisi;
	private ButtonShadow btnClear;
	private RadioButton rbBtnGotovina;
	private ButtonGroup bgNacinPlacanja;
	private ComboBox<Object> cbKartica;
	private RadioButton rbBtnKartice;
	private ButtonShadow btnPDF;
	private TextAreaScroll textAreaScroll;
	private JLabel lblUkupno;
	private TextArea txtBiljeske;
	private JLabel lblTotal;
	private JLabel lblPercentage;
	
//	*** VIEW + PANEL ***
	@SuppressWarnings( "unused" )
	private View view;
	@SuppressWarnings( "unused" )
	private CardPanel cardParent;
	private NovaPonudaController novaPonudaController;
	private LoginController loginController;
	
	/**
	 * 
	 * Creates a new instance of the NovaPonudaPanel class with the specified
	 * CardPanel and View / JFrame objects.
	 * 
	 * @param cardParent the parent CardPanel object of the panel
	 * @param view       the View object representing the UI view
	 */
	public NovaPonudaPanel( CardPanel cardParent , View view ) {
		
		super( 20 );
		this.view = view;
		this.cardParent = cardParent;
		this.novaPonudaController = new NovaPonudaController( this );
		
		setOpaque( false );
		setFont( new Font( "Century Gothic" , Font.BOLD , 16 ) );
		setBackground( new Color( 244 , 244 , 249 ) );
		
		txtNazivPonude = new TextField();
		txtNazivPonude.setLineColor( new Color( 46 , 191 , 165 ) );
		txtNazivPonude.setLabelText( "Naziv Ponude" );
		txtNazivPonude.setHintTextColor( new Color( 121 , 118 , 118 ) );
		txtNazivPonude.setForeground( new Color( 44 , 51 , 51 ) );
		txtNazivPonude.setFont( new Font( "Century Gothic" , Font.PLAIN , 15 ) );
		txtNazivPonude.setBackground( new Color( 244 , 244 , 249 ) );
		
		txtImePrezime = new TextField();
		txtImePrezime.setLineColor( new Color( 46 , 191 , 165 ) );
		txtImePrezime.setLabelText( "Ime i prezime" );
		txtImePrezime.setHintTextColor( new Color( 121 , 118 , 118 ) );
		txtImePrezime.setForeground( new Color( 44 , 51 , 51 ) );
		txtImePrezime.setFont( new Font( "Century Gothic" , Font.PLAIN , 15 ) );
		txtImePrezime.setBackground( new Color( 244 , 244 , 249 ) );
		
		cbArtikli = new ComboBox<Product>();
		cbArtikli.setPreferredSize( new Dimension( 300 , 45 ) );
		cbArtikli.setMinimumSize( new Dimension( 300 , 45 ) );
		cbArtikli.setFont( new Font( "Century Gothic" , Font.PLAIN , 15 ) );
		cbArtikli.setBackground( new Color( 244 , 244 , 249 ) );
		
		cbArtikli.setBackground( getBackground() );
		JTextField cbText = ((JTextField) cbArtikli.getEditor().getEditorComponent());
		cbText.setBackground( getBackground() );
		
		txtCijena = new TextField();
		txtCijena.setText( "Test Cijena" );
		txtCijena.setLineColor( new Color( 46 , 191 , 165 ) );
		txtCijena.setLabelText( "Cijena" );
		txtCijena.setHintTextColor( new Color( 121 , 118 , 118 ) );
		txtCijena.setHelperText( "Cijena odabranog artikla" );
		txtCijena.setForeground( new Color( 44 , 51 , 51 ) );
		txtCijena.setFont( new Font( "Century Gothic" , Font.PLAIN , 15 ) );
		txtCijena.setEnabled( false );
		txtCijena.setDisabledTextColor( new Color( 44 , 44 , 49 ) );
		txtCijena.setBackground( new Color( 244 , 244 , 249 ) );
		
		txtKolicina = new TextField();
		txtKolicina.setText( "1" );
		txtKolicina.setLineColor( new Color( 46 , 191 , 165 ) );
		txtKolicina.setLabelText( "Količina" );
		txtKolicina.setHintTextColor( new Color( 121 , 118 , 118 ) );
		txtKolicina.setForeground( new Color( 44 , 51 , 51 ) );
		txtKolicina.setFont( new Font( "Century Gothic" , Font.PLAIN , 15 ) );
		txtKolicina.setBackground( new Color( 244 , 244 , 249 ) );
		
		txtPopust = new TextField();
		txtPopust.setText( "0" );
		txtPopust.setLineColor( new Color( 46 , 191 , 165 ) );
		txtPopust.setLabelText( "Popust" );
		txtPopust.setHintTextColor( new Color( 121 , 118 , 118 ) );
		txtPopust.setForeground( new Color( 44 , 51 , 51 ) );
		txtPopust.setFont( new Font( "Century Gothic" , Font.PLAIN , 15 ) );
		txtPopust.setBackground( new Color( 244 , 244 , 249 ) );
		
		lblPercentage = new JLabel( "%" );
		lblPercentage.setFont( new Font( "Century Gothic" , Font.PLAIN , 15 ) );
		lblPercentage.setForeground( new Color( 121 , 118 , 118 ) );
		lblPercentage.setHorizontalAlignment( SwingConstants.CENTER );
		
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
		table.setFirstBold( false );
		table.setForeground( new Color( 0 , 0 , 0 ) );
		table.setFont( new Font( "Century Gothic" , Font.PLAIN , 15 ) );
		table.setCursor( Cursor.getPredefinedCursor( Cursor.HAND_CURSOR ) );
		table.setBackground( getBackground() );
		table.setBorder( null );
		table.setShowVerticalLines( false );
		table.setShowHorizontalLines( false );
		table.setShowGrid( false );
		
		table.setModel( new DefaultTableModel( new Object[][] {} ,
				new String[] { "Naziv" , "Cijena" , "Količina" , "Ukupno" , "Popust" , "Ukupno s popustom" ,
							   "Ukupno s PDV-om" } ) );
		table.getColumnModel().getColumn( 0 ).setMinWidth( 325 );
		table.getColumnModel().getColumn( 0 ).setMaxWidth( 325 );
		table.getColumnModel().getColumn( 0 ).setPreferredWidth( 325 );
		table.getColumnModel().getColumn( 2 ).setMinWidth( 100 );
		table.getColumnModel().getColumn( 2 ).setMaxWidth( 100 );
		table.getColumnModel().getColumn( 2 ).setPreferredWidth( 100 );
		table.getColumnModel().getColumn( 4 ).setMinWidth( 100 );
		table.getColumnModel().getColumn( 4 ).setMaxWidth( 100 );
		table.getColumnModel().getColumn( 4 ).setPreferredWidth( 100 );
		
		table.getTableHeader().setPreferredSize( new Dimension( 785 , 40 ) );
		table.getTableHeader().setFont( new Font( "Century Gothic" , Font.BOLD , 15 ) );
		
		tableScrollPane.setViewportView( table );
		
		btnDodaj = new ButtonShadow();
		btnDodaj.setMaximumSize( new Dimension( 150 , 45 ) );
		btnDodaj.setPreferredSize( new Dimension( 150 , 45 ) );
		btnDodaj.setText( "Dodaj" );
		btnDodaj.setForeground( new Color( 121 , 118 , 118 ) );
		btnDodaj.setFont( new Font( "Century Gothic" , Font.PLAIN , 15 ) );
		btnDodaj.setFocusPainted( false );
		
		btnIzbrisi = new ButtonShadow();
		btnIzbrisi.setMaximumSize( new Dimension( 150 , 45 ) );
		btnIzbrisi.setText( "Izbriši" );
		btnIzbrisi.setForegroundColorOUT( new Color( 146 , 20 , 12 ) );
		btnIzbrisi.setForegroundColorIN( new Color( 146 , 20 , 12 ) );
		btnIzbrisi.setForeground( new Color( 146 , 20 , 12 ) );
		btnIzbrisi.setFont( new Font( "Century Gothic" , Font.PLAIN , 15 ) );
		btnIzbrisi.setFocusPainted( false );
		
		btnClear = new ButtonShadow();
		btnClear.setMaximumSize( new Dimension( 150 , 45 ) );
		btnClear.setText( "Očisti tablicu" );
		btnClear.setForegroundColorOUT( new Color( 81 , 62 , 0 ) );
		btnClear.setForegroundColorIN( new Color( 81 , 62 , 0 ) );
		btnClear.setForeground( new Color( 81 , 62 , 0 ) );
		btnClear.setFont( new Font( "Century Gothic" , Font.PLAIN , 15 ) );
		btnClear.setFocusPainted( false );
		
		rbBtnGotovina = new RadioButton();
		rbBtnGotovina.setForeground( new Color( 44 , 51 , 51 ) );
		rbBtnGotovina.setMinimumSize( new Dimension( 150 , 25 ) );
		rbBtnGotovina.setPreferredSize( new Dimension( 150 , 25 ) );
		rbBtnGotovina.setText( "Gotovina" );
		rbBtnGotovina.setIconTextGap( 10 );
		
		rbBtnKartice = new RadioButton();
		rbBtnKartice.setForeground( new Color( 44 , 51 , 51 ) );
		rbBtnKartice.setPreferredSize( new Dimension( 150 , 25 ) );
		rbBtnKartice.setMinimumSize( new Dimension( 150 , 25 ) );
		rbBtnKartice.setText( "Kartice" );
		rbBtnKartice.setIconTextGap( 10 );
		
		bgNacinPlacanja = new ButtonGroup();
		bgNacinPlacanja.add( rbBtnGotovina );
		bgNacinPlacanja.add( rbBtnKartice );
		
		cbKartica = new ComboBox<>();
		cbKartica.setMaximumRowCount( 5 );
		
		cbKartica.setModel( new DefaultComboBoxModel<Object>(
				new String[] { "" , "Jednokratno" , "2" , "3" , "4" , "5" , "6" , "7" , "8" , "9" , "10" , "11" , "12" ,
							   "13" , "14" , "15" , "16" , "17" , "18" , "19" , "20" , "21" , "22" , "23" , "24" } ) );
		
		cbKartica.setFont( new Font( "Century Gothic" , Font.PLAIN , 15 ) );
		cbKartica.setBackground( new Color( 244 , 244 , 249 ) );
		JTextField cbKarticaTxt = ((JTextField) cbKartica.getEditor().getEditorComponent());
		cbKarticaTxt.setBackground( new Color( 244 , 244 , 249 ) );
		cbKartica.setEnabled( false );
		
		lblTotal = new JLabel( "Ukupno:" );
		lblTotal.setMinimumSize( new Dimension( 150 , 20 ) );
		lblTotal.setMaximumSize( new Dimension( 150 , 20 ) );
		lblTotal.setPreferredSize( new Dimension( 150 , 20 ) );
		lblTotal.setHorizontalAlignment( SwingConstants.RIGHT );
		lblTotal.setForeground( new Color( 121 , 118 , 118 ) );
		lblTotal.setFont( new Font( "Century Gothic" , Font.BOLD , 15 ) );
		
		lblUkupno = new JLabel( "0.00" );
		lblUkupno.setHorizontalAlignment( SwingConstants.RIGHT );
		lblUkupno.setForeground( new Color( 44 , 51 , 51 ) );
		lblUkupno.setFont( new Font( "Century Gothic" , Font.BOLD , 15 ) );
		
		textAreaScroll = new TextAreaScroll();
		textAreaScroll.setPreferredSize( new Dimension( 250 , 250 ) );
		textAreaScroll.setMinimumSize( new Dimension( 250 , 250 ) );
		textAreaScroll.setForeground( new Color( 44 , 51 , 51 ) );
		textAreaScroll.setLineColor( new Color( 46 , 191 , 165 ) );
		textAreaScroll.setLabelText( "Bilješke" );
		textAreaScroll.setFont( new Font( "Century Gothic" , Font.PLAIN , 15 ) );
		textAreaScroll.setBackground( new Color( 244 , 244 , 249 ) );
		
		txtBiljeske = new TextArea();
		txtBiljeske.setForeground( new Color( 44 , 51 , 51 ) );
		txtBiljeske.setFont( new Font( "Century Gothic" , Font.PLAIN , 15 ) );
		textAreaScroll.setViewportView( txtBiljeske );
		
		btnPDF = new ButtonShadow();
		btnPDF.setMaximumSize( new Dimension( 125 , 125 ) );
		btnPDF.setMinimumSize( new Dimension( 125 , 125 ) );
		btnPDF.setPreferredSize( new Dimension( 125 , 125 ) );
		btnPDF.setFocusPainted( false );
		btnPDF.setBackground( new Color( 44 , 51 , 51 ) );
		btnPDF.setForeground( new Color( 244 , 244 , 249 ) );
		btnPDF.setShadowColor( new Color( 0 , 0 , 0 ) );
		btnPDF.setForegroundColorOUT( new Color( 244 , 244 , 249 ) );
		btnPDF.setForegroundColorIN( new Color( 244 , 244 , 249 ) );
		btnPDF.setRippleColor( new Color( 220 , 220 , 220 ) );
		btnPDF.setRound( 25 );
		btnPDF.setText( "PDF" );
		btnPDF.setFont( new Font( "Century Gothic" , Font.BOLD , 20 ) );
		
		initLayout();
		activatePanel();
		insertData();
		
	}
	
	
	/**
	 * 
	 * Activates the panel by setting up various listeners for its components. These
	 * listeners are used to handle events like button clicks, textfield input,
	 * table selection, etc. Once activated, this panel becomes fully functional and
	 * can be interacted with by the user.
	 */
	private void activatePanel() {
		
		txtKolicina.addFocusListener( new FocusAdapter() {
			
			@Override
			public void focusLost( FocusEvent e ) {
				
				if ( !txtKolicina.getText().equals( "" ) ) {
					
					if ( !checkKolicina() ) {
						
						txtKolicina.requestFocus();
						txtKolicina.selectAll();
						txtKolicina.setHelperText( "Unesite drugi broj" );
						
					} else {
						
						txtKolicina.setHelperText( "" );
						
					}
					
				} else {
					
					txtKolicina.setText( "1" );
					
				}
				
			}
			
		} );
		
		txtPopust.addFocusListener( new FocusAdapter() {
			
			@Override
			public void focusLost( FocusEvent e ) {
				
				if ( !txtPopust.getText().equals( "" ) ) {
					
					if ( !checkPopust() ) {
						
						txtPopust.requestFocus();
						txtPopust.selectAll();
						txtPopust.setHelperText( "Unesite drugi broj" );
						
					} else {
						
						txtPopust.setHelperText( "" );
						
					}
					
				} else {
					
					txtPopust.setText( "0" );
					
				}
				
			}
			
		} );
		
		rbBtnGotovina.addActionListener( new ActionListener() {
			
			public void actionPerformed( ActionEvent e ) {
				
				cbKartica.setEnabled( false );
				
			}
			
		} );
		rbBtnKartice.addActionListener( new ActionListener() {
			
			public void actionPerformed( ActionEvent e ) {
				
				cbKartica.setEnabled( true );
				cbKartica.setSelectedIndex( 1 );
				
			}
			
		} );
		
		btnDodaj.addActionListener( new ActionListener() {
			
			public void actionPerformed( ActionEvent e ) {
				
				novaPonudaController.addProductToTable( cbArtikli.getSelectedItem() ,
						Integer.valueOf( txtKolicina.getText() ) , Integer.valueOf( txtPopust.getText() ) );
				
			}
			
		} );
		
		btnIzbrisi.addActionListener( new ActionListener() {
			
			public void actionPerformed( ActionEvent e ) {
				
				novaPonudaController.removeProductFromTable();
				
			}
			
		} );
		
		btnClear.addActionListener( new ActionListener() {
			
			public void actionPerformed( ActionEvent e ) {
				
				novaPonudaController.clearTable();
				
			}
			
		} );
		
		cbArtikli.addActionListener( new ActionListener() {
			
			public void actionPerformed( ActionEvent e ) {
				
				novaPonudaController.setPriceTxt();
				
			}
			
		} );
		
		btnPDF.addActionListener( new ActionListener() {
			
			public void actionPerformed( ActionEvent e ) {
				
				User loggedInUser = loginController.getLoggedInUser();
				Offer offer = new Offer( txtNazivPonude.getText() , loggedInUser , txtImePrezime.getText() ,
						LocalDateTime.now() , BigDecimal.valueOf( Double.parseDouble( lblUkupno.getText() ) ) ,
						txtBiljeske.getText() );
				
				novaPonudaController.generatePDF( offer );
				clearAll();
				
			}
			
		} );
		
	}
	
	
	/**
	 * 
	 * Inserts test data into the table, sets data in the combo box, and enables
	 * autocomplete for patient name.
	 */
	public void insertData() {
		
		novaPonudaController.setTableData();
		novaPonudaController.setComboBoxData();
		novaPonudaController.autocopletePatient();
		
	}
	
	
	/**
	 * 
	 * Check if the entered quantity is valid.
	 * 
	 * @return true if the quantity is valid, false otherwise.
	 */
	private boolean checkKolicina() {
		
		boolean isKolicinaValid = true;
		int kolicina = 0;
		
		try {
			
			kolicina = Integer.parseInt( txtKolicina.getText() );
			
		} catch ( NumberFormatException e ) {
			
			isKolicinaValid = false;
			
		}
		
		if ( kolicina < 0 ) {
			
			isKolicinaValid = false;
			
		}
		
		return isKolicinaValid;
		
	}
	
	
	/**
	 * 
	 * Check if the entered discount percentage is valid.
	 * 
	 * @return true if the discount percentage is valid, false otherwise.
	 */
	private boolean checkPopust() {
		
		boolean isPopustValid = true;
		int popust = 0;
		
		try {
			
			popust = Integer.parseInt( txtPopust.getText() );
			
		} catch ( NumberFormatException e ) {
			
			isPopustValid = false;
			
		}
		
		if ( popust < 0 || popust > 99 ) {
			
			isPopustValid = false;
			
		}
		
		return isPopustValid;
		
	}
	
	
	/**
	 * 
	 * This method clears all the UI fields that are related to the patient data.
	 */
	public void clearAll() {
		
		txtNazivPonude.setText( "" );
		txtImePrezime.setText( "" );
		txtKolicina.setText( "1" );
		txtPopust.setText( "0" );
		txtBiljeske.setText( "" );
		rbBtnGotovina.setSelected( false );
		rbBtnKartice.setSelected( false );
		novaPonudaController.clearTable();
		
		if ( cbArtikli.getItemCount() >= 0 ) {
			
			cbArtikli.setSelectedIndex( 0 );
			
		}
		
	}
	
	
	/**
	 * 
	 * Initiates the layout of the panel.
	 */
	private void initLayout() {
		
		GridBagLayout gbl_mainPanel = new GridBagLayout();
		gbl_mainPanel.columnWidths = new int[] { 25 , 25 , 25 , 25 , 25 , 25 , 25 , 25 , 25 , 25 , 25 , 25 , 25 , 25 ,
												 25 , 25 , 25 , 25 , 25 , 25 , 25 , 25 , 25 , 25 , 25 , 25 , 25 , 25 ,
												 25 , 25 , 25 , 25 , 25 , 25 , 25 , 25 , 25 , 0 };
		gbl_mainPanel.rowHeights = new int[] { 15 , 65 , 10 , 65 , 25 , 45 , 10 , 380 , 40 , 20 , 250 , 0 , 0 };
		gbl_mainPanel.columnWeights = new double[] { 1.0 , 0.0 , 0.0 , 0.0 , 0.0 , 0.0 , 0.0 , 0.0 , 0.0 , 0.0 , 0.0 ,
													 0.0 , 0.0 , 0.0 , 0.0 , 0.0 , 0.0 , 0.0 , 0.0 , 0.0 , 0.0 , 0.0 ,
													 0.0 , 0.0 , 0.0 , 0.0 , 0.0 , 0.0 , 0.0 , 0.0 , 0.0 , 0.0 , 0.0 ,
													 0.0 , 0.0 , 0.0 , 1.0 , Double.MIN_VALUE };
		gbl_mainPanel.rowWeights = new double[] { 1.0 , 0.0 , 0.0 , 0.0 , 0.0 , 0.0 , 0.0 , 0.0 , 0.0 , 0.0 , 0.0 ,
												  1.0 , Double.MIN_VALUE };
		setLayout( gbl_mainPanel );
		
		GridBagConstraints gbc_txtNazivPonude = new GridBagConstraints();
		gbc_txtNazivPonude.gridwidth = 12;
		gbc_txtNazivPonude.insets = new Insets( 0 , 0 , 5 , 5 );
		gbc_txtNazivPonude.fill = GridBagConstraints.BOTH;
		gbc_txtNazivPonude.gridx = 1;
		gbc_txtNazivPonude.gridy = 1;
		add( txtNazivPonude , gbc_txtNazivPonude );
		
		GridBagConstraints gbc_txtImePrezime = new GridBagConstraints();
		gbc_txtImePrezime.gridwidth = 13;
		gbc_txtImePrezime.insets = new Insets( 0 , 0 , 5 , 5 );
		gbc_txtImePrezime.fill = GridBagConstraints.BOTH;
		gbc_txtImePrezime.gridx = 23;
		gbc_txtImePrezime.gridy = 1;
		add( txtImePrezime , gbc_txtImePrezime );
		
		GridBagConstraints gbc_cbArtikli = new GridBagConstraints();
		gbc_cbArtikli.gridwidth = 12;
		gbc_cbArtikli.insets = new Insets( 0 , 0 , 5 , 5 );
		gbc_cbArtikli.fill = GridBagConstraints.HORIZONTAL;
		gbc_cbArtikli.gridx = 1;
		gbc_cbArtikli.gridy = 3;
		add( cbArtikli , gbc_cbArtikli );
		
		GridBagConstraints gbc_txtCijena = new GridBagConstraints();
		gbc_txtCijena.gridwidth = 7;
		gbc_txtCijena.insets = new Insets( 0 , 0 , 5 , 5 );
		gbc_txtCijena.fill = GridBagConstraints.BOTH;
		gbc_txtCijena.gridx = 14;
		gbc_txtCijena.gridy = 3;
		add( txtCijena , gbc_txtCijena );
		
		GridBagConstraints gbc_txtKolicina = new GridBagConstraints();
		gbc_txtKolicina.gridwidth = 7;
		gbc_txtKolicina.insets = new Insets( 0 , 0 , 5 , 5 );
		gbc_txtKolicina.fill = GridBagConstraints.BOTH;
		gbc_txtKolicina.gridx = 22;
		gbc_txtKolicina.gridy = 3;
		add( txtKolicina , gbc_txtKolicina );
		
		GridBagConstraints gbc_txtPopust = new GridBagConstraints();
		gbc_txtPopust.gridwidth = 5;
		gbc_txtPopust.insets = new Insets( 0 , 0 , 5 , 5 );
		gbc_txtPopust.fill = GridBagConstraints.BOTH;
		gbc_txtPopust.gridx = 30;
		gbc_txtPopust.gridy = 3;
		add( txtPopust , gbc_txtPopust );
		
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel.insets = new Insets( 0 , 0 , 5 , 5 );
		gbc_lblNewLabel.gridx = 35;
		gbc_lblNewLabel.gridy = 3;
		add( lblPercentage , gbc_lblNewLabel );
		
		GridBagConstraints gbc_btnDodaj = new GridBagConstraints();
		gbc_btnDodaj.fill = GridBagConstraints.BOTH;
		gbc_btnDodaj.gridwidth = 6;
		gbc_btnDodaj.insets = new Insets( 0 , 0 , 5 , 5 );
		gbc_btnDodaj.gridx = 1;
		gbc_btnDodaj.gridy = 5;
		add( btnDodaj , gbc_btnDodaj );
		
		GridBagConstraints gbc_btnIzbrisi = new GridBagConstraints();
		gbc_btnIzbrisi.fill = GridBagConstraints.BOTH;
		gbc_btnIzbrisi.gridwidth = 6;
		gbc_btnIzbrisi.insets = new Insets( 0 , 0 , 5 , 5 );
		gbc_btnIzbrisi.gridx = 8;
		gbc_btnIzbrisi.gridy = 5;
		add( btnIzbrisi , gbc_btnIzbrisi );
		
		GridBagConstraints gbc_btnClear = new GridBagConstraints();
		gbc_btnClear.fill = GridBagConstraints.BOTH;
		gbc_btnClear.gridwidth = 6;
		gbc_btnClear.insets = new Insets( 0 , 0 , 5 , 5 );
		gbc_btnClear.gridx = 30;
		gbc_btnClear.gridy = 5;
		add( btnClear , gbc_btnClear );
		
		GridBagConstraints gbc_tableScrollPane = new GridBagConstraints();
		gbc_tableScrollPane.fill = GridBagConstraints.BOTH;
		gbc_tableScrollPane.insets = new Insets( 5 , 50 , 5 , 50 );
		gbc_tableScrollPane.gridwidth = 37;
		gbc_tableScrollPane.gridx = 0;
		gbc_tableScrollPane.gridy = 7;
		add( tableScrollPane , gbc_tableScrollPane );
		
		GridBagConstraints gbc_rbBtnGotovina = new GridBagConstraints();
		gbc_rbBtnGotovina.fill = GridBagConstraints.HORIZONTAL;
		gbc_rbBtnGotovina.gridwidth = 6;
		gbc_rbBtnGotovina.insets = new Insets( 0 , 0 , 5 , 5 );
		gbc_rbBtnGotovina.gridx = 1;
		gbc_rbBtnGotovina.gridy = 8;
		add( rbBtnGotovina , gbc_rbBtnGotovina );
		
		GridBagConstraints gbc_rbBtnKartice = new GridBagConstraints();
		gbc_rbBtnKartice.fill = GridBagConstraints.HORIZONTAL;
		gbc_rbBtnKartice.gridwidth = 6;
		gbc_rbBtnKartice.insets = new Insets( 0 , 0 , 5 , 5 );
		gbc_rbBtnKartice.gridx = 7;
		gbc_rbBtnKartice.gridy = 8;
		add( rbBtnKartice , gbc_rbBtnKartice );
		
		GridBagConstraints gbc_cbKartica = new GridBagConstraints();
		gbc_cbKartica.gridwidth = 7;
		gbc_cbKartica.insets = new Insets( 0 , 0 , 5 , 5 );
		gbc_cbKartica.fill = GridBagConstraints.BOTH;
		gbc_cbKartica.gridx = 13;
		gbc_cbKartica.gridy = 8;
		add( cbKartica , gbc_cbKartica );
		
		GridBagConstraints gbc_lblTotal = new GridBagConstraints();
		gbc_lblTotal.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblTotal.gridwidth = 6;
		gbc_lblTotal.insets = new Insets( 0 , 0 , 5 , 5 );
		gbc_lblTotal.gridx = 25;
		gbc_lblTotal.gridy = 8;
		add( lblTotal , gbc_lblTotal );
		
		GridBagConstraints gbc_lblUkupno = new GridBagConstraints();
		gbc_lblUkupno.anchor = GridBagConstraints.EAST;
		gbc_lblUkupno.gridwidth = 5;
		gbc_lblUkupno.insets = new Insets( 0 , 0 , 5 , 5 );
		gbc_lblUkupno.gridx = 31;
		gbc_lblUkupno.gridy = 8;
		add( lblUkupno , gbc_lblUkupno );
		
		GridBagConstraints gbc_textAreaScroll = new GridBagConstraints();
		gbc_textAreaScroll.anchor = GridBagConstraints.NORTH;
		gbc_textAreaScroll.gridheight = 2;
		gbc_textAreaScroll.gridwidth = 22;
		gbc_textAreaScroll.insets = new Insets( 0 , 0 , 5 , 5 );
		gbc_textAreaScroll.fill = GridBagConstraints.HORIZONTAL;
		gbc_textAreaScroll.gridx = 1;
		gbc_textAreaScroll.gridy = 9;
		add( textAreaScroll , gbc_textAreaScroll );
		
		GridBagConstraints gbc_btnPDF = new GridBagConstraints();
		gbc_btnPDF.gridwidth = 6;
		gbc_btnPDF.insets = new Insets( 0 , 0 , 5 , 5 );
		gbc_btnPDF.gridx = 30;
		gbc_btnPDF.gridy = 10;
		add( btnPDF , gbc_btnPDF );
		
	}
	
	
	/**
	 * 
	 * Returns the text field for the offer name.
	 * 
	 * @return the txtNazivPonude
	 */
	public TextField getTxtNazivPonude() {
		
		return txtNazivPonude;
		
	}
	
	
	/**
	 * 
	 * Returns the text field for the customer name.
	 * 
	 * @return the txtImePrezime
	 */
	public TextField getTxtImePrezime() {
		
		return txtImePrezime;
		
	}
	
	
	/**
	 * 
	 * Returns the text field for the quantity of products.
	 * 
	 * @return the txtKolicina
	 */
	public TextField getTxtKolicina() {
		
		return txtKolicina;
		
	}
	
	
	/**
	 * 
	 * Returns the text field for the discount percentage.
	 * 
	 * @return the txtPopust
	 */
	public TextField getTxtPopust() {
		
		return txtPopust;
		
	}
	
	
	/**
	 * 
	 * Returns the table that displays the list of products.
	 * 
	 * @return the table
	 */
	public CustomTable getTable() {
		
		return table;
		
	}
	
	
	/**
	 * 
	 * Returns the text area for notes.
	 * 
	 * @return the txtBiljeske
	 */
	public TextArea getTxtBiljeske() {
		
		return txtBiljeske;
		
	}
	
	
	/**
	 * 
	 * Returns the combo box for selecting products.
	 * 
	 * @return the cbArtikli
	 */
	public ComboBox<Product> getCbArtikli() {
		
		return cbArtikli;
		
	}
	
	
	/**
	 * 
	 * Returns the label for the total price.
	 * 
	 * @return the lblUkupno
	 */
	public JLabel getLblUkupno() {
		
		return lblUkupno;
		
	}
	
	
	/**
	 * 
	 * Returns the text field for the price of a product.
	 * 
	 * @return the txtCijena
	 */
	public TextField getTxtCijena() {
		
		return txtCijena;
		
	}
	
	
	/**
	 * 
	 * Sets the login controller.
	 * 
	 * @param loginController the loginController to set
	 */
	public void setLoginController( LoginController loginController ) {
		
		this.loginController = loginController;
		
	}
	
}
