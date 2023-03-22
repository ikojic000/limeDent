package view.viewPanel;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import design.txtInput.TextField;


/**
 *
 * The InputControls class provides static methods for input validation of
 * various types of input fields such as OIB, JMBG, phone number, and email.
 */
public class InputControls {
	
	/**
	 *
	 * Validates the provided OIB (11 numeric characters) and sets helper text on
	 * the given text field based on the validation result.
	 *
	 * @param txtOIB The text field to validate.
	 *
	 * @return true if the OIB is valid, false otherwise.
	 */
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
	
	
	/**
	 *
	 * Validates the provided JMBG (13 numeric characters) and sets helper text on
	 * the given text field based on the validation result.
	 *
	 * @param txtJMBG The text field to validate.
	 *
	 * @return true if the JMBG is valid, false otherwise.
	 */
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
	
	
	/**
	 *
	 * Validates the provided phone number using a regular expression and sets
	 * helper text on the given text field based on the validation result.
	 *
	 * @param txtBrojMobitela The text field to validate.
	 *
	 * @return true if the phone number is valid, false otherwise.
	 */
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
	
	
	/**
	 *
	 * Validates the provided email address using a regular expression and sets
	 * helper text on the given text field based on the validation result.
	 *
	 * @param txtMail The text field to validate.
	 *
	 * @return true if the email address is valid, false otherwise.
	 */
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
