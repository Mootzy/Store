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

/*******************************************************************************************************************
 * 								   		~~~~~~~~~ README ~~~~~~~~~~`
 *
 *
 *
 * 1)Locating document to read in
 * ****************************************
 * Text document named Item.txt is located in src/main folder
 * open this text file from the file chooser in order to read the file into main.
 * Correctly prints to console full list however has not yet properly printed to swift gui
 *
 * 2)Credentials for accessing different functions of Appliciation
 * *********************************************************************
 * username and password to log into store portal is admin admin, used to see uploading and reading of text file
 * username and password to log into customer portal is user user, used to select items from cart and see updated qty
 *
 * GUI isnt properly displaying list from .txt document anymore, however it is creating an arrayList of the elements.
 *
 * 3) Periodic updates and Concerns still to be adressed with current Part of Project
 * **********************************************************************************************
 *                    Tyler Wallace Store Pt.4 Wed/Nov 18 2020
 *!! In order to see menu populate from array !!
 * LOG INTO:
 * username: user
 * password: user
 *
 *
 *
 *                    Tyler Wallace Store pt.5 Thur/nov 19 2020
 * !! The 'add to cart' button demonstrates updating qty of object.
 * Currently this only works for NIKE-SHIRT as it is hardcoded to update userInventory[0]
 * Reguardless of which option added to cart.
 * need to find way to pass value of selected radio button to the actionEvent "AddToCartAction"
 * So that it knows which item to 1( multiplied by however many user adds to cart) from qty.
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 * */