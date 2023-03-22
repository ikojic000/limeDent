package view.panel.footerBar;


import java.awt.Color;
import java.awt.Font;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import net.miginfocom.swing.MigLayout;


public class FooterBar extends JPanel {
	
	/**
	 *
	 */
	private static final long serialVersionUID = -973683967357077782L;
	private JLabel lblFooter;
	private JLabel lblTime;
	
	public FooterBar() {
		
		setBackground( new Color( 44 , 51 , 51 ) );
		
		initComponents();
		layoutComponents();
		initTime();
		
	}
	
	
	private void initComponents() {
		
		lblFooter = new JLabel( "Powered by MPD Tech. " );
		lblFooter.setHorizontalAlignment( SwingConstants.CENTER );
		lblFooter.setFont( new Font( "Century Gothic" , Font.PLAIN , 15 ) );
		lblFooter.setForeground( new Color( 244 , 244 , 249 ) );
		
		lblTime = new JLabel( " 20:00:00 " );
		lblTime.setFont( new Font( "Century Gothic" , Font.BOLD , 15 ) );
		lblTime.setForeground( new Color( 244 , 244 , 249 ) );
		
	}
	
	
	private void layoutComponents() {
		
		setLayout( new MigLayout( "" , "[grow,fill]" , "[]" ) );
		
		add( lblFooter , "cell 0 0,grow" );
		
		add( lblTime , "pos 1al 0.5al" );
		
	}
	
	
	private void initTime() {
		
		TimerTask timerTask = new TimerTask() {
			
			@Override
			public void run() {
				
				LocalDateTime myDateObj = LocalDateTime.now();
				DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern( "dd-MM-yyyy HH:mm" );
				String formattedDate = myDateObj.format( myFormatObj );
				lblTime.setText( formattedDate );
				
			}
			
		};
		
		Timer timer = new Timer();
		
		timer.scheduleAtFixedRate( timerTask , 0 , 100 );
		
	}
	
}
