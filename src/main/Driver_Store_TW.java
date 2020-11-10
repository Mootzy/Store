package main;
import javax.swing.*;


public class Driver_Store_TW
{

	public static void main( String[ ] args )
	{
		IDandPasswords idandPasswords = new IDandPasswords ( );

		LoginPage loginPage = new LoginPage ( idandPasswords.getLoginInfo ( ) );

		
		
		
	}

	
	
	
	
	
	

}

/**
 * Text document named Item.txt is located in src/main folder open this text file from the file chooser in order to read
 * the file into main. Correctly prints to console full list however has not yet properly printed to swift gui username
 * and password to log into store portal is admin admin
 **/