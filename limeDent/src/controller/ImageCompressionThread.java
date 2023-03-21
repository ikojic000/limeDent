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


public class ImageCompressionThread extends Thread {
	
	private File selectedImage;
	private Patient patient;
	private Notification notification;
	private static final int MAX_SIZE = 512 * 1024; // maximum size in bytes
	
	public ImageCompressionThread( File selectedImage , Patient patient , Notification notification ) {
		
		this.selectedImage = selectedImage;
		this.patient = patient;
		this.notification = notification;
		
	}
	
	
	@Override
	public void run() {
		
		Thread.currentThread().setName( "Image Compression Thread" );
		
		try {
			
			System.out.println( "Compressing image from: " + Thread.currentThread().getName() );
			BufferedImage image = ImageIO.read( selectedImage );
			byte[] compressedImage = null;
			
			if ( selectedImage.length() <= MAX_SIZE ) {
				
				// image is already smaller than the maximum size
				System.out.println( "Setting Patient ProfilePhoto from: " + Thread.currentThread().getName() );
				patient.setProfilePhoto( new ImageIcon( selectedImage.getAbsolutePath() ) );
				compressedImage = Files.readAllBytes( selectedImage.toPath() );
				
			} else {
				
				// compress the image
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
							// we cannot compress the image anymore, so we just exit the loop
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
