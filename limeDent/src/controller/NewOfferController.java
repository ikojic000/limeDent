package controller;


import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

import javax.swing.filechooser.FileSystemView;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import controller.observer.OfferListObservable;
import controller.observer.OfferTblPreviewObserver;
import controller.observer.ProductListObservable;
import controller.observer.ProductListObserver;
import dao.OfferDAO;
import dao.PatientDAO;
import dao.ProductDAO;
import model.Offer;
import model.OfferTblPreviewData;
import model.Patient;
import model.Product;
import model.tableModels.OfferTblPreviewModel;
import view.viewPanel.NewOfferPanel;


/**
 *
 * @author ikojic000
 *
 *         The NovaPonudaController class represents a controller that manages
 *         the creation of new offers. It handles the functionality of
 *         populating combo boxes and tables, adding and removing products from
 *         the table, calculating the total price, generating PDF files, and
 *         more.
 *
 */
public class NewOfferController implements OfferTblPreviewObserver , ProductListObserver {
	
	private NewOfferPanel novaPonudaPanel;
	private OfferDAO offerDAO;
	private ProductDAO productDAO;
	private ArrayList<OfferTblPreviewData> tblPreviewData = new ArrayList<>();
	private OfferTblPreviewModel tblPreviewModel;
	private ArrayList<Patient> patientList;
	private PatientDAO patientDAO;
	
	/**
	 *
	 * Constructs a new NovaPonudaController object and initializes its fields.
	 *
	 * @param novaPonudaPanel the NovaPonudaPanel object that represents the GUI
	 *                        panel for creating new offers
	 */
	public NewOfferController( NewOfferPanel novaPonudaPanel ) {
		
		this.novaPonudaPanel = novaPonudaPanel;
		this.offerDAO = new OfferDAO();
		this.productDAO = new ProductDAO();
		this.patientDAO = new PatientDAO();
		this.patientList = patientDAO.getAllPatients();
		ProductListObservable.getInstance().addObserver( this );
		
	}
	
	
	/**
	 *
	 * Populates the combo box in the NovaPonudaPanel object with data from the
	 * ProductDAO object.
	 */
	public void setComboBoxData() {
		
		ArrayList<Product> productList = productDAO.getAllProducts();
		
		for ( Product product : productList ) {
			
			novaPonudaPanel.getCbArtikli().addItem( product );
			
		}
		
	}
	
	
	/**
	 *
	 * Sets the OfferTblPreviewModel as Table Model to the table on the panel panel.
	 * OfferTblPreviewModel accepts list of OfferTblPreviewData objects. Adds
	 * NovaPonudaController as a OfferTblPreviewObserver observer.
	 */
	public void setTableData() {
		
		tblPreviewModel = new OfferTblPreviewModel( tblPreviewData );
		tblPreviewModel.addObserver( this );
		novaPonudaPanel.getTable().setModel( tblPreviewModel );
		
	}
	
	
	/**
	 *
	 * Adds a new product to the tblPreviewData field and updates the table and
	 * total price accordingly.
	 *
	 * @param objProd  the product to be added
	 * @param pieces   the number of pieces of the product to be added
	 * @param discount the discount to be applied to the product
	 */
	public void addProductToTable( Object objProd , Integer pieces , Integer discount ) {
		
		Product product = (Product) objProd;
		
		tblPreviewData.add( new OfferTblPreviewData( product , pieces , discount ) );
		tblPreviewModel.fireTableDataChanged();
		calculateTotal();
		novaPonudaPanel.getTxtKolicina().setText( "1" );
		novaPonudaPanel.getTxtPopust().setText( "0" );
		
	}
	
	
	/**
	 *
	 * Removes the selected product from the tblPreviewData field and updates the
	 * table and total price accordingly.
	 */
	public void removeProductFromTable() {
		
		tblPreviewData.remove( tblPreviewData.get( novaPonudaPanel.getTable().getSelectedRow() ) );
		tblPreviewModel.fireTableDataChanged();
		calculateTotal();
		
	}
	
	
	/**
	 *
	 * Removes all products from the tblPreviewData field and updates the table and
	 * total price accordingly.
	 */
	public void clearTable() {
		
		tblPreviewData.removeAll( tblPreviewData );
		tblPreviewModel.fireTableDataChanged();
		calculateTotal();
		
	}
	
	
	/**
	 *
	 * Calculates the total price of all items in the offer and displays it in the
	 * appropriate UI component. Also calculates the total price with discounts
	 * included.
	 */
	public void calculateTotal() {
		
		BigDecimal total = BigDecimal.ZERO;
		BigDecimal totalWithDiscount = BigDecimal.ZERO;
		
		for ( OfferTblPreviewData data : tblPreviewData ) {
			
			total = total.add( data.getTotal() );
			totalWithDiscount = totalWithDiscount.add( data.getTotalWithDiscount() );
			
		}
		
		novaPonudaPanel.getLblUkupno().setText( total.setScale( 2 , RoundingMode.HALF_UP ).toString() );
		
	}
	
	
	/**
	 *
	 * Autocompletes the patient name field based on existing patient names.
	 */
	public void autocopletePatient() {
		
		ArrayList<String> patientNames = new ArrayList<>();
		
		for ( Patient patient : patientList ) {
			
			patientNames.add( patient.getName() );
			
		}
		
		AutoCompleteDecorator.decorate( novaPonudaPanel.getTxtImePrezime() , patientNames , false );
		
	}
	
	
	/**
	 *
	 * Sets the price text of the currently selected product in the appropriate UI
	 * component.
	 */
	public void setPriceTxt() {
		
		if ( novaPonudaPanel.getCbArtikli().getSelectedItem() != null ) {
			
			novaPonudaPanel.getTxtCijena()
					.setText( ((Product) novaPonudaPanel.getCbArtikli().getSelectedItem()).getPrice().toString() );
			
		} else {
			
			novaPonudaPanel.getTxtCijena().setText( "" );
			
		}
		
	}
	
	
	/**
	 *
	 * Generates a PDF file for the specified offer, saves it to disk, and updates
	 * the offer's URL. Also updates the offer in the database and notifies
	 * observers of the offer list.
	 *
	 * @param offer the offer to generate a PDF for
	 */
	@SuppressWarnings( "unused" )
	public void generatePDF( Offer offer ) {
		
		String path = createFolder() + offer.getTitle() + "_" + offer.getPatientName() + ".pdf";
		offer.setUrl( path );
		offerDAO.addOffer( offer );
		PDFGenerator pdf = new PDFGenerator( createFolder() , offer , this.tblPreviewModel );
		OfferListObservable.getInstance().notifyObservers();
		
	}
	
	
	/**
	 *
	 * Creates a folder for storing generated PDF files, if it does not already
	 * exist.
	 *
	 * @return the path to the created folder
	 */
	private String createFolder() {
		
		FileSystemView filesys = FileSystemView.getFileSystemView();
		File file = filesys.getHomeDirectory();
		String path = file.getAbsolutePath();
		path = path + "\\LimeDentPonude\\";
		
		String folderPath = path;
		
		File ponudeFolder = new File( folderPath );
		
		if ( !ponudeFolder.exists() ) {
			
			ponudeFolder.mkdir();
			
		} else {
			
			System.out.println( "folder vec postoji" );
			
		}
		
		return folderPath;
		
	}
	
	
	/**
	 *
	 * Updates the UI component that shows total price after the table values have
	 * been changed.
	 */
	@Override
	public void tableModelDataChanged() {
		
		calculateTotal();
		
	}
	
	
	/**
	 *
	 * Updates the UI component that shows list of all products after that list was
	 * changed.
	 */
	@Override
	public void updateProductList() {
		
		novaPonudaPanel.getCbArtikli().removeAllItems();
		setComboBoxData();
		novaPonudaPanel.getCbArtikli().repaint();
		
	}
	
}
