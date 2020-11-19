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
 * Tyler Wallace
 * Text document named Item.txt is located in src/main folder open this text file from the file chooser in order to read
 * the file into main. Correctly prints to console full list however has not yet properly printed to swift gui username
 * and password to log into store portal is admin admin
 *
 * GUI isnt properly displaying list from .txt document anymore, however it is creating an arrayList of the elements.
 *
 **** Tyler Wallace Store Pt.4 Wed/Nov 18 2020****
 *!! In order to see menu populate from array !!
 * LOG INTO:
 * username: user
 * password: user
 *
 *
 *
 *
 *
 **/