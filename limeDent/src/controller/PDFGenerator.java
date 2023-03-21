package controller;


import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.util.Objects;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import model.Offer;
import model.tableModels.OfferTblPreviewModel;


/**
 * 
 * @author ikojic000
 * 
 *         The PDFGenerator class generates and opens a PDF file. It takes in
 *         the folder path, the offer object, and the offer table preview model
 *         object as parameters to create the PDF file. This class generates a
 *         header with the MPDTech logo and contact information, patient's name,
 *         and the offer title. It also generates a table with the offer details
 *         such as item name, price, and quantity. This class has a single
 *         constructor that initializes the instance variables and calls the
 *         generatePDF() method to create the PDF file and the openPDF() method
 *         to open the file.
 */
public class PDFGenerator {
	
	/**
	 * The path to the folder where the PDF file will be saved.
	 */
	private String folderPath;
	/**
	 * An instance of the Offer class containing the offer details.
	 */
	private Offer offer;
	/**
	 * An instance of the OfferTblPreviewModel class containing the offer table
	 * details.
	 */
	private OfferTblPreviewModel tblModel;
	
	/**
	 * 
	 * The constructor for the PDFGenerator class initializes the instance variables
	 * and calls the generatePDF() method to create the PDF file and the openPDF()
	 * method to open the file.
	 * 
	 * @param folderPath The path to the folder where the PDF file will be saved.
	 * @param offer      An instance of the Offer class containing the offer
	 *                   details.
	 * @param tblModel   An instance of the OfferTblPreviewModel class containing
	 *                   the offer table details.
	 */
	public PDFGenerator( String folderPath , Offer offer , OfferTblPreviewModel tblModel ) {
		
		this.folderPath = folderPath;
		this.offer = offer;
		this.tblModel = tblModel;
		
		String fileName = folderPath + "/" + offer.getTitle() + "_" + offer.getPatientName() + ".pdf";
		
		generatePDF( fileName , offer , tblModel );
		openPDF( fileName );
		
	}
	
	
	/**
	 * 
	 * This method generates a PDF file with the offer details and saves it in the
	 * specified folder path. It takes in the file name, offer object, and offer
	 * table preview model object as parameters.
	 * 
	 * @param fileName The name of the PDF file.
	 * @param offer    An instance of the Offer class containing the offer details.
	 * @param tblModel An instance of the OfferTblPreviewModel class containing the
	 *                 offer table details.
	 * @throws FileNotFoundException If the specified file is not found.
	 * @throws DocumentException     If an error occurs while creating the PDF
	 *                               document.
	 * @throws IOException           If an error occurs while writing the PDF
	 *                               document to the output stream.
	 */
	private void generatePDF( String fileName , Offer offer , OfferTblPreviewModel tblModel ) {
		
		try {
			
			Document document = new Document();
			
			OutputStream os = new FileOutputStream( fileName );
			
			PdfWriter.getInstance( document , os );
			
			document.open();
			
			document.setPageSize( PageSize.A4 );
			document.setMargins( 5f , 5f , 5f , 5f );
			
			Font font1 = new Font( FontFamily.HELVETICA , 12 , Font.BOLD );
			Font font2 = new Font( FontFamily.HELVETICA , 10 , Font.ITALIC );
			Font font3 = new Font( FontFamily.HELVETICA , 12 , Font.NORMAL );
			Font font4 = new Font( FontFamily.HELVETICA , 10 , Font.NORMAL );
			Font font5 = new Font( FontFamily.HELVETICA , 5 , Font.ITALIC );
			
			URL resource = PDFGenerator.class.getResource( "/pdfLogo.png" );
			
			Image image = Image.getInstance( Objects.requireNonNull( resource ) );
			
//			HEADER 
			
			float columnWidthTest[] = { 22f , 50f , 28f };
			PdfPTable tableTest = new PdfPTable( columnWidthTest );
			tableTest.setWidthPercentage( 100f );
			
			PdfPCell cell13 = new PdfPCell( image , false );
			cell13.setRowspan( 3 );
			cell13.setPadding( 0 );
			cell13.setHorizontalAlignment( PdfPCell.ALIGN_CENTER );
			cell13.setVerticalAlignment( PdfPCell.ALIGN_CENTER );
			cell13.setBorder( com.itextpdf.text.Rectangle.NO_BORDER );
			tableTest.addCell( cell13 );
			
			PdfPCell cell = new PdfPCell( new Phrase( " MPDTech " , font1 ) );
			cell.setBorder( com.itextpdf.text.Rectangle.NO_BORDER );
			tableTest.addCell( cell );
			
			cell = new PdfPCell( new Phrase( " Tel: +385 1 2345 678 " , font2 ) );
			cell.setHorizontalAlignment( PdfPCell.ALIGN_RIGHT );
			cell.setBorder( com.itextpdf.text.Rectangle.NO_BORDER );
			tableTest.addCell( cell );
			
			cell = new PdfPCell( new Phrase( " 23000 Zadar " , font2 ) );
			cell.setBorder( com.itextpdf.text.Rectangle.NO_BORDER );
			tableTest.addCell( cell );
			
			cell = new PdfPCell( new Phrase( "  " , font2 ) );
			cell.setHorizontalAlignment( PdfPCell.ALIGN_RIGHT );
			cell.setBorder( com.itextpdf.text.Rectangle.NO_BORDER );
			tableTest.addCell( cell );
			
			cell = new PdfPCell( new Phrase( " www.mpdtech.com.hr " , font2 ) );
			cell.setBorder( com.itextpdf.text.Rectangle.NO_BORDER );
			tableTest.addCell( cell );
			
			cell = new PdfPCell( new Phrase( " E: ivan.kojic@mail.com " , font2 ) );
			cell.setHorizontalAlignment( PdfPCell.ALIGN_RIGHT );
			cell.setBorder( com.itextpdf.text.Rectangle.NO_BORDER );
			tableTest.addCell( cell );
			
			document.add( tableTest );
			
			document.add( new Paragraph( "\n" ) );
			document.add( new Paragraph( "\n" ) );
			document.add( new Paragraph( "\n" ) );
			
			Paragraph paraNazivPonude = new Paragraph( "Ponuda: " + offer.getTitle() , font3 );
			Paragraph paraImePrezime = new Paragraph( "Ime i prezime: " + offer.getPatientName() , font3 );
			Paragraph formattedDatePara = new Paragraph( offer.getDateFormatted() , font2 );
			
			document.add( new Paragraph( "\n" ) );
			document.add( paraImePrezime );
			document.add( new Paragraph( "\n" ) );
			document.add( new Paragraph( "\n" ) );
			document.add( paraNazivPonude );
			document.add( new Paragraph( "\n" , font2 ) );
			document.add( formattedDatePara );
			document.add( new Paragraph( "\n" , font5 ) );
			
//			TABLE
			
			var headerFont = FontFactory.getFont( "Arial" , 12 );
			headerFont.setColor( BaseColor.BLACK );
			
			PdfPTable artikliTable = new PdfPTable( tblModel.getColumnCount() );
			artikliTable.setWidthPercentage( 95f );
			PdfPCell artTblHead = new PdfPCell( new Phrase( " Naziv " , headerFont ) );
			headerCellDesign( artTblHead );
			artikliTable.addCell( artTblHead );
			
			artTblHead = new PdfPCell( new Phrase( " Cijena " , headerFont ) );
			headerCellDesign( artTblHead );
			artikliTable.addCell( artTblHead );
			
			artTblHead = new PdfPCell( new Phrase( " Komada " , headerFont ) );
			headerCellDesign( artTblHead );
			artikliTable.addCell( artTblHead );
			
			artTblHead = new PdfPCell( new Phrase( " Ukupno " , headerFont ) );
			headerCellDesign( artTblHead );
			artikliTable.addCell( artTblHead );
			
			artTblHead = new PdfPCell( new Phrase( " Popust " , headerFont ) );
			headerCellDesign( artTblHead );
			artikliTable.addCell( artTblHead );
			
			artTblHead = new PdfPCell( new Phrase( " Ukupno s popustom " , headerFont ) );
			headerCellDesign( artTblHead );
			artikliTable.addCell( artTblHead );
			
			for ( int i = 0; i < tblModel.getRowCount(); i++ ) {
				
				for ( int j = 0; j < tblModel.getColumnCount(); j++ ) {
					
					Object val = tblModel.getValueAt( i , j );
					
					if ( val != null ) {
						
						PdfPCell cellArtikli = new PdfPCell( new Phrase( val.toString() , font4 ) );
						cellArtikliDesign( cellArtikli );
						artikliTable.addCell( cellArtikli );
						
					} else {
						
						PdfPCell cellArtikli = new PdfPCell( new Phrase( "" ) );
						cellArtikliDesign( cellArtikli );
						artikliTable.addCell( cellArtikli );
						
					}
					
				}
				
			}
			
			PdfPCell zadnjiRedCell = new PdfPCell( new Phrase( "Ukupno:" , headerFont ) );
			prazniRedDesign( zadnjiRedCell );
			artikliTable.addCell( zadnjiRedCell );
			
			for ( int i = 0; i < 4; i++ ) {
				
				zadnjiRedCell = new PdfPCell( new Phrase( "" ) );
				prazniRedDesign( zadnjiRedCell );
				artikliTable.addCell( zadnjiRedCell );
				
			}
			
			zadnjiRedCell = new PdfPCell( new Phrase( offer.getTotal() + " HRK" , headerFont ) );
			prazniRedDesign( zadnjiRedCell );
			artikliTable.addCell( zadnjiRedCell );
			
			document.add( artikliTable );
			
			Paragraph adminPdf = new Paragraph( "Ponudu napravio/la: " + offer.getAuthorName() , font2 );
			
			document.add( adminPdf );
			
			document.add( new Paragraph( "\n" ) );
			document.add( new Paragraph( "\n" ) );
			
			String crta = "_______________________________________";
			
			Paragraph crtaPdf = new Paragraph( crta , font3 );
			document.add( crtaPdf );
			document.add( new Paragraph( "\n" ) );
			
			Paragraph biljeskeView = new Paragraph( offer.getNotes() , font2 );
			document.add( biljeskeView );
			
			document.close();
			os.close();
			
		} catch ( DocumentException | IOException e ) {
			
			e.printStackTrace();
			
		}
		
	}
	
	
	/**
	 * 
	 * This method opens the PDF file with the default system application for PDF
	 * files. It takes in the file name as a parameter.
	 * 
	 * @param fileName The name of the PDF file.
	 * @throws IOException If an error occurs while opening the PDF file.
	 */
	private void openPDF( String file_name ) {
		
		try {
			
			Desktop.getDesktop().open( new File( file_name ) );
			
		} catch ( IOException e ) {
			
			e.printStackTrace();
			
		}
		
	}
	
	
	private static void headerCellDesign( PdfPCell artTblHead ) {
		
		artTblHead.setHorizontalAlignment( PdfPCell.ALIGN_CENTER );
		artTblHead.setVerticalAlignment( PdfPCell.ALIGN_MIDDLE );
		artTblHead.setPaddingTop( 10 );
		artTblHead.setPaddingBottom( 10 );
		artTblHead.setPaddingLeft( 0 );
		artTblHead.setPaddingRight( 0 );
		artTblHead.setBorder( com.itextpdf.text.Rectangle.TOP | com.itextpdf.text.Rectangle.BOTTOM );
		
	}
	
	
	private static void cellArtikliDesign( PdfPCell cellArtikli ) {
		
		cellArtikli.setHorizontalAlignment( PdfPCell.ALIGN_CENTER );
		cellArtikli.setBorder( 0 );
		cellArtikli.setPaddingTop( 5 );
		cellArtikli.setPaddingBottom( 5 );
		
	}
	
	
	private static void prazniRedDesign( PdfPCell cellArtikli ) {
		
		cellArtikli.setHorizontalAlignment( PdfPCell.ALIGN_CENTER );
		cellArtikli.setBorder( com.itextpdf.text.Rectangle.TOP | com.itextpdf.text.Rectangle.BOTTOM );
		cellArtikli.setPaddingTop( 5 );
		cellArtikli.setPaddingBottom( 10 );
		
	}
	
	
	/**
	 * 
	 * Returns the current folder path.
	 *
	 * @return the folder path as a String.
	 */
	public String getFolderPath() {
		
		return folderPath;
		
	}
	
	
	/**
	 * 
	 * Sets the folder path to the specified String.
	 *
	 * @param folderPath the new folder path to be set.
	 */
	public void setFolderPath( String folderPath ) {
		
		this.folderPath = folderPath;
		
	}
	
	
	/**
	 * 
	 * Returns the current Offer object.
	 *
	 * @return the Offer object.
	 */
	public Offer getOffer() {
		
		return offer;
		
	}
	
	
	/**
	 * 
	 * Sets the Offer object to the specified Offer.
	 *
	 * @param offer the new Offer object to be set.
	 */
	public void setOffer( Offer offer ) {
		
		this.offer = offer;
		
	}
	
	
	/**
	 * 
	 * Returns the current OfferTblPreviewModel object.
	 *
	 * @return the OfferTblPreviewModel object.
	 */
	public OfferTblPreviewModel getTblModel() {
		
		return tblModel;
		
	}
	
	
	/**
	 * 
	 * Sets the OfferTblPreviewModel object to the specified OfferTblPreviewModel.
	 *
	 * @param tblModel the new OfferTblPreviewModel object to be set.
	 */
	public void setTblModel( OfferTblPreviewModel tblModel ) {
		
		this.tblModel = tblModel;
		
	}
	
}
