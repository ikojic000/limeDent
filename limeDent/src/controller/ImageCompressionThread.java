package controller;


import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import javax.swing.ImageIcon;

import design.notification.Notification;
import design.notification.Notification.NotificationType;
import model.Patient;


/**
 * 
 * @author ikojic000
 * 
 *         The ImageCompressionThread class is responsible for compressing
 *         images that are larger than 512KB. If the image is already smaller
 *         than 512KB, it sets the patient's profile photo and no compression is
 *         performed. The compressed image is set as the patient's profile photo
 *         and saved to the user's desktop. A notification is also displayed
 *         indicating whether the compression was successful or not. This class
 *         extends Thread class to enable running the compression process in a
 *         separate thread.
 * 
 */
public class ImageCompressionThread extends Thread {
	
	/** The selected image to be compressed */
	private File selectedImage;
	/** The patient whose image is being compressed */
	private Patient patient;
	/** The notification to display to the user after compression is complete */
	private Notification notification;
	/** The maximum size that an image can have in bytes */
	private static final int MAX_SIZE = 512 * 1024;
	
	/**
	 * 
	 * Constructs an ImageCompressionThread with the specified selected image,
	 * patient and notification.
	 * 
	 * @param selectedImage the image to be compressed
	 * @param patient       the patient whose image is being compressed
	 * @param notification  the notification to display after compression is
	 *                      complete
	 */
	public ImageCompressionThread( File selectedImage , Patient patient , Notification notification ) {
		
		this.selectedImage = selectedImage;
		this.patient = patient;
		this.notification = notification;
		
	}
	
	
	/**
	 * 
	 * This method is called when the thread is started. It compresses the image and
	 * sets the compressed image as the patient's profile photo. If the image cannot
	 * be compressed any further, a notification is displayed indicating that the
	 * image is too large. The compressed image is also saved to the user's desktop.
	 */
	@Override
	public void run() {
		
		Thread.currentThread().setName( "Image Compression Thread" );
		
		try {
			
			System.out.println( "Compressing image from: " + Thread.currentThread().getName() );
			BufferedImage image = ImageIO.read( selectedImage );
			byte[] compressedImage = null;
			
			if ( selectedImage.length() <= MAX_SIZE ) {
				
//				image is already smaller than the maximum size
				System.out.println( "Setting Patient ProfilePhoto from: " + Thread.currentThread().getName() );
				patient.setProfilePhoto( new ImageIcon( selectedImage.getAbsolutePath() ) );
				compressedImage = Files.readAllBytes( selectedImage.toPath() );
				
			} else {
				
//				compress the image
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				float quality = 0.5f; // initial compression quality
				boolean done = false;
				
				while ( !done ) {
					
					System.out.println( "Compressing image - " + quality );
					baos.reset(); // clear the ByteArrayOutputStream
					ImageWriter writer = ImageIO.getImageWritersByFormatName( "jpg" ).next();
					ImageWriteParam param = writer.getDefaultWriteParam();
					param.setCompressionMode( ImageWriteParam.MODE_EXPLICIT );
					param.setCompressionQuality( quality );
					ImageOutputStream ios = ImageIO.createImageOutputStream( baos );
					writer.setOutput( ios );
					writer.write( null , new IIOImage( image , null , null ) , param );
					ios.close();
					writer.dispose();
					compressedImage = baos.toByteArray();
					int size = compressedImage.length;
					
					if ( size <= MAX_SIZE ) {
						
						System.out.println( "Compressing image less than 64kb" );
						System.out.println( "Setting Patient ProfilePhoto from: " + Thread.currentThread().getName() );
						patient.setProfilePhoto( new ImageIcon( compressedImage ) );
						
						notification.setType( NotificationType.SUCCESS );
						notification.setLblTitle( "Uspiješna kompresija" );
						notification.setLbMessageText( "Fotografiji je uspješno smanjena veličina" );
						notification.showNotification();
						
						try {
							
							System.out.println( "Saving Compressing image " );
							File desktop = new File( System.getProperty( "user.home" ) + "/Desktop" );
							File compressedFile = new File( desktop , "compressed_image.jpg" );
							FileOutputStream fos = new FileOutputStream( compressedFile );
							fos.write( compressedImage );
							fos.close();
							System.out.println(
									"Compressed image saved to desktop: " + compressedFile.getAbsolutePath() );
							
						} catch ( IOException e ) {
							
							e.printStackTrace();
							
						}
						
						done = true;
						
					} else {
						
						quality -= 0.05f; // decrease the compression quality by 0.05
						
						if ( quality < 0.05f ) {
							
							System.out.println( "Cant be compressed anymore..." );
//							we cannot compress the image anymore, so we just exit the loop
							notification.setType( NotificationType.WARNING );
							notification.setLblTitle( "Fotografija prevelika" );
							notification.setLbMessageText( "Odaberite fotografiju manju od 2MB..." );
							notification.showNotification();
							done = true;
							
						}
						
					}
					
				}
				
			}
			
		} catch ( IOException e ) {
			
			e.printStackTrace();
			
		}
		
	}
	
}
