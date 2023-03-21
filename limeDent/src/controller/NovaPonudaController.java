package controller;


import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

import javax.swing.filechooser.FileSystemView;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import controller.observer.OfferListObservable;
import dao.OfferDAO;
import dao.PatientDAO;
import dao.ProductDAO;
import model.Offer;
import model.OfferTblPreviewData;
import model.Patient;
import model.Product;
import model.tableModels.OfferTblPreviewModel;
import view.viewPanel.NovaPonudaPanel;


public class NovaPonudaController {
	
	private NovaPonudaPanel novaPonudaPanel;
	private OfferDAO offerDAO;
	private ProductDAO productDAO;
	private ArrayList<OfferTblPreviewData> tblPreviewData = new ArrayList<OfferTblPreviewData>();
	private OfferTblPreviewModel tblPreviewModel;
	private ArrayList<Patient> patientList;
	private PatientDAO patientDAO;
	
	public NovaPonudaController( NovaPonudaPanel novaPonudaPanel ) {
		
		this.novaPonudaPanel = novaPonudaPanel;
		this.offerDAO = new OfferDAO();
		this.productDAO = new ProductDAO();
		this.patientDAO = new PatientDAO();
		this.patientList = patientDAO.getAllPatients();
		
	}
	
	
	public void setComboBoxData() {
		
		ArrayList<Product> productList = productDAO.getAllProducts();
		
		for ( Product product : productList ) {
			
			novaPonudaPanel.getCbArtikli().addItem( product );
			
		}
		
	}
	
	
	public void setTableData() {
		
		tblPreviewModel = new OfferTblPreviewModel( tblPreviewData );
		novaPonudaPanel.getTable().setModel( tblPreviewModel );
		
	}
	
	
	public void addProductToTable( Object objProd , Integer pieces , Integer discount ) {
		
		Product product = (Product) objProd;
		
		tblPreviewData.add( new OfferTblPreviewData( product , pieces , discount ) );
		tblPreviewModel.fireTableDataChanged();
		calculateTotal();
		novaPonudaPanel.getTxtKolicina().setText( "1" );
		novaPonudaPanel.getTxtPopust().setText( "0" );
		
	}
	
	
	public void removeProductFromTable() {
		
		tblPreviewData.remove( tblPreviewData.get( novaPonudaPanel.getTable().getSelectedRow() ) );
		tblPreviewModel.fireTableDataChanged();
		calculateTotal();
		
	}
	
	
	public void clearTable() {
		
		tblPreviewData.removeAll( tblPreviewData );
		tblPreviewModel.fireTableDataChanged();
		calculateTotal();
		
	}
	
	
	public void calculateTotal() {
		
		BigDecimal total = BigDecimal.ZERO;
		BigDecimal totalWithDiscount = BigDecimal.ZERO;
		
		for ( OfferTblPreviewData data : tblPreviewData ) {
			
			total = total.add( data.getTotal() );
			totalWithDiscount = totalWithDiscount.add( data.getTotalWithDiscount() );
			
		}
		
		novaPonudaPanel.getLblUkupno().setText( total.setScale( 2 , RoundingMode.HALF_UP ).toString() );
		
	}
	
	
	public void autocopletePatient() {
		
		ArrayList<String> patientNames = new ArrayList<String>();
		
		for ( Patient patient : patientList ) {
			
			patientNames.add( patient.getName() );
			
		}
		
		AutoCompleteDecorator.decorate( novaPonudaPanel.getTxtImePrezime() , patientNames , false );
		
	}
	
	
	public void setPriceTxt() {
		
		novaPonudaPanel.getTxtCijena()
				.setText( ((Product) novaPonudaPanel.getCbArtikli().getSelectedItem()).getPrice().toString() );
		
	}
	
	
	@SuppressWarnings( "unused" )
	public void generatePDF( Offer offer ) {
		
		String path = createFolder() + offer.getTitle() + "_" + offer.getPatientName() + ".pdf";
		offer.setUrl( path );
		offerDAO.addOffer( offer );
		PDFGenerator pdf = new PDFGenerator( createFolder() , offer , this.tblPreviewModel );
		OfferListObservable.getInstance().notifyObservers();
		
	}
	
	
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
	
}
