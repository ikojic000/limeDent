package view.viewPanel;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import design.txtInput.TextField;


public class InputControls {
	
	public static boolean checkOIB( TextField txtOIB ) {
		
		String oib = txtOIB.getText();
		
		boolean isOIBValid = true;
		
//		Checking if input has 11 numeric characters
		if ( oib.length() != 11 ) {
			
			isOIBValid = false;
			
		} else {
			
			char[] chars = oib.toCharArray();
			
			for ( char c : chars ) {
				
				if ( c < '0' || c > '9' ) {
					
					isOIBValid = false;
					
				}
				
			}
			
		}
		
//		Checking if input String is empty
		if ( oib.equals( "" ) ) {
			
			isOIBValid = true;
			
		}
		
//		Setting HelperText depending on phone validation
		if ( !isOIBValid ) {
			
			txtOIB.setHelperText( "Neispravan format OIB-a .." );
			
		} else {
			
			txtOIB.setHelperText( "" );
			
		}
		
		return isOIBValid;
		
	}
	
	
	public static boolean checkJMBG( TextField txtJMBG ) {
		
		String jmbg = txtJMBG.getText();
		
		boolean isJMBGValid = true;
		
//		Checking if input has 13 numeric characters
		if ( jmbg.length() != 13 ) {
			
			isJMBGValid = false;
			
		} else {
			
			char[] chars = jmbg.toCharArray();
			
			for ( char c : chars ) {
				
				if ( c < '0' || c > '9' ) {
					
					isJMBGValid = false;
					
				}
				
			}
			
		}
		
//		Checking if input String is empty
		if ( jmbg.equals( "" ) ) {
			
			isJMBGValid = true;
			
		}
		
//		Setting HelperText depending on phone validation
		if ( !isJMBGValid ) {
			
			txtJMBG.setHelperText( "Neispravan format JMBG-a .." );
			
		} else {
			
			txtJMBG.setHelperText( "" );
			
		}
		
		return isJMBGValid;
		
	}
	
	
	public static boolean checkPhone( TextField txtBrojMobitela ) {
		
		boolean isPhoneValid = true;
		String phone = txtBrojMobitela.getText();
		
		String regex = "^\\+?\\d{1,4}?[-.\\s]?\\(?\\d{1,3}?\\)?[-.\\s]?\\d{1,4}[-.\\s]?\\d{1,4}[-.\\s]?\\d{1,9}$";
		Pattern pattern = Pattern.compile( regex );
		Matcher matcher = pattern.matcher( phone );
		
//		Checking if input String matches regex
		if ( !matcher.matches() ) {
			
			isPhoneValid = false;
			
		}
		
//		Checking if input String is empty
		if ( phone.equals( "" ) ) {
			
			isPhoneValid = true;
			
		}
		
//		Setting HelperText depending on phone validation
		if ( !isPhoneValid ) {
			
			txtBrojMobitela.setHelperText( "Neispravan format broja mobitela .." );
			
		} else {
			
			txtBrojMobitela.setHelperText( "" );
			
		}
		
		return isPhoneValid;
		
	}
	
	
	public static boolean checkMail( TextField txtMail ) {
		
		boolean isMailValid = true;
		String mail = txtMail.getText();
		
		String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}";
		Pattern pattern = Pattern.compile( regex );
		Matcher matcher = pattern.matcher( mail );
		
//		Checking if input String matches regex
		if ( !matcher.matches() ) {
			
			isMailValid = false;
			
		}
		
//		Checking if input String is empty
		if ( mail.equals( "" ) ) {
			
			isMailValid = true;
			
		}
		
//		Setting HelperText depending on phone validation
		if ( !isMailValid ) {
			
			txtMail.setHelperText( "Neispravan format mail-a .. " );
			
		} else {
			
			txtMail.setHelperText( "" );
			
		}
		
		return isMailValid;
		
	}
	
}
