//package view.viewPanel;
//
//
//import java.awt.BorderLayout;
//import java.awt.Color;
//import java.awt.Font;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.util.Properties;
//
//import design.panel.RoundedShadowPanel;
//import javafx.application.Platform;
//import javafx.concurrent.Worker;
//import javafx.embed.swing.JFXPanel;
//import javafx.scene.Scene;
//import javafx.scene.web.WebView;
//
//
//public class CalendarPanel extends RoundedShadowPanel {
//
//	/**
//	 *
//	 */
//	private static final long serialVersionUID = 6986159853822015237L;
//	private JFXPanel jfxPanel;
//
//	public CalendarPanel( int radius ) {
//
//		super( radius );
//
//		jfxPanel = new JFXPanel();
//		Platform.runLater( this::createJFXContent );
//
//		setShadowOpacity( 0.65f );
//		setOpaque( false );
//		setFont( new Font( "Century Gothic" , Font.BOLD , 16 ) );
//		setBackground( new Color( 244 , 244 , 249 ) );
//
//		initLayout();
//
//	}
//
//
//	private void initLayout() {
//
//		BorderLayout bl = new BorderLayout( 25 , 50 );
//
//		setLayout( bl );
//
//		add( jfxPanel , BorderLayout.CENTER );
//
//	}
//
////	private void createJFXContent() {
////
////		System.out.println( "Create JFX" );
////		WebView webView = new WebView();
////		webView.getEngine().load( "https://calendar.google.com/calendar/" );
//////        webView.getEngine().load("https://calendar.google.com/calendar/u/0/r?tab=rc&pli=1");
//////        webView.getEngine().getDocument().getElementById("identifierId").setAttribute("data-initial-value", "ikojic000@gmail.com");
////
////		Scene scene = new Scene( webView );
////		jfxPanel.setScene( scene );
////
////	}
//
//
////	*** TEST ***
//	private void createJFXContent() {
//
//		System.out.println( "Create JFX" );
//		WebView webView = new WebView();
//		webView.getEngine().load( "https://calendar.google.com/calendar/" );
//
//		// Wait for the page to load before running the JavaScript code
//		webView.getEngine().getLoadWorker().stateProperty().addListener( ( observable , oldState , newState ) -> {
//
//			if ( newState == Worker.State.SUCCEEDED ) {
//
//				// Fill in the email field with your email address
//				webView.getEngine()
//						.executeScript( "document.getElementById('identifierId').value = 'ikojic000@gmail.com';" );
//				webView.getEngine().executeScript( "document.getElementById('identifierNext').click();" );
//
//			}
//
//		} );
//
//		// Wait for the page to load before running the JavaScript code
//		webView.getEngine().getLoadWorker().stateProperty().addListener( ( observable , oldState , newState ) -> {
//
//			if ( newState == Worker.State.SUCCEEDED ) {
//
//				// Fill in the password field with your password
//				webView.getEngine().executeScript(
//						"setTimeout(function() { document.querySelector('input[type=password]').value = '"
//								+ getPassword() + "'; }, 2000);" );
//				// Click the "Next" button to log in
//				webView.getEngine().executeScript( "document.getElementById('identifierNext').click();" );
//
//			}
//
//		} );
//
//		Scene scene = new Scene( webView );
//		jfxPanel.setScene( scene );
//
//	}
//
//
//	private String getPassword() {
//
//		String password = null;
//		Properties prop = new Properties();
//
//		try {
//
//			prop.load( new FileInputStream( this.getClass().getResource( "/config.properties" ).getPath() ) );
//			password = prop.getProperty( "googleCalendarPW" );
//
//		} catch ( IOException ex ) {
//
//			ex.printStackTrace();
//
//		}
//
//		return password;
//
//	}
//
//}
