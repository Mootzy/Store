package main;

import java.awt.event.*;

import javax.swing.*;
import java.io.*;
import java.nio.file.attribute.UserDefinedFileAttributeView;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.awt.*;

public class WelcomePage implements ActionListener
{
	File item = new File("Item.txt");

	JFrame frame = new JFrame ( );

	FileDialog fd = new FileDialog ( frame, "Choose a file to load the list", FileDialog.LOAD );

	String userDir = System.getProperty ( "user.home" );
	JFileChooser fc = new JFileChooser ( userDir );

	JLabel welcomeLabel = new JLabel ( "Store Inventory" );

	JTextArea itemList = new JTextArea ( "Please Select a file to open to import new inventory" );
	JTextArea shopMenu = new JTextArea();
	JTextArea custWelcomeTxt = new JTextArea("Welcome Valued Customer!");
	JTextArea userPortalBanner = new JTextArea("Our Current Selection: " + "\n");

	JButton selectButton = new JButton ( "Select" );

	JButton viewCart = new JButton(("view cart/checkout"));
	JButton addToCart = new JButton("add to cart");
	JButton viewMenu = new JButton(("view menu"));



	ArrayList<Item> userInventory = InventoryPage.inventory;

	WelcomePage(String userID)
	{


		welcomeLabel.setBounds ( 175, 0, 200, 35 );
		welcomeLabel.setFont ( new Font ( null, Font.PLAIN, 25 ) );
		welcomeLabel.setText ( "Hello, " + userID );

		itemList.setBounds ( 50, 200, 500, 50 );
		itemList.setFont ( new Font ( null, Font.BOLD, 15 ) );
		itemList.setEditable ( false );
		itemList.setWrapStyleWord ( true );
		itemList.setLineWrap ( true );
		itemList.setBackground ( new Color ( frame.getBackground ( ).getRGB ( ), true ) );
		/* itemList.setText ( "test" ); */

		String userDir = System.getProperty ( "user.home" );



		//code for if on ADMIN Welcome-Page
		if(userID.equalsIgnoreCase("admin")) {


			selectButton.setBounds(200, 275, 75, 25);
			selectButton.setVisible(true);
			selectButton.setFocusable(false);
			// this took 2 hours to figure out you have to also add actionListener...
			selectButton.addActionListener(this);


			frame.add(selectButton);
			frame.add(itemList);
			frame.setTitle("Store Inventory Portal");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.add(welcomeLabel);
			frame.setLocation(600, 250);
			frame.setSize(500, 620);
			frame.setLayout(null);
			frame.setVisible(true);
			frame.setIconImage(
					Toolkit.getDefaultToolkit().getImage("C:\\Users\\tyler\\Desktop\\Photos-new-icon.png"));
		}

		//code for if on USERS Welcome-Page
		else{






			//Text area for Customer's Menu
			shopMenu.setBounds(50, 175, 500, 700);
			shopMenu.setFont(new Font(null, Font.BOLD, 15));
			shopMenu.setEditable(false);
			shopMenu.setWrapStyleWord(true);
			shopMenu.setLineWrap(true);
			shopMenu.setBackground(new Color(frame.getBackground().getRGB(), true));






			//This needs to correctly populate the text area with the contents of the imported _inventory_ arrayList, which it does but the List has not
			//yet been populated unless the Admin user logins in first... how to fix...


			//InventoryPage.inventory = readFile(File "/Users/admin/Desktop/Java Projects/Store/src/Item.txt");


			//ArrayList<Item>testInv = new ArrayList<>();



			//File item = new File("Item.txt");
			readFile(item);


			//String userView = null;




			shopMenu.setText(userInventory.toString());
			shopMenu.setText(InventoryPage.inventory.toString());
			shopMenu.setText(readFile(item));

			//TEST CODE TO SEE IF ARRAY IS PROPERPLY POPULATING
			System.out.println(userInventory + " Test code to see if array is properly pupulating for menu");









//			viewMenu.setBounds(50, 500, 175, 25);
//			viewMenu.setVisible(true);
//			viewMenu.setFocusable(false);
//			viewMenu.addActionListener(this);




			//Add to cart button
			addToCart.setBounds(50, 500, 175, 25);
			addToCart.setVisible(true);
			addToCart.setFocusable(false);
			addToCart.addActionListener(this);

			viewCart.setBounds(225, 500, 175, 25);
			viewCart.setVisible(true);
			viewCart.setFocusable(false);
			viewCart.addActionListener(this);

			//Create Welcome banner " hi, user"
			custWelcomeTxt.setBounds(50, 50, 500, 50);
			custWelcomeTxt.setFont ( new Font ( null, Font.BOLD, 15 ) );
			custWelcomeTxt.setEditable ( false );
			custWelcomeTxt.setWrapStyleWord ( true );
			custWelcomeTxt.setLineWrap ( true );
			custWelcomeTxt.setBackground ( new Color ( frame.getBackground ( ).getRGB ( ), true ) );

			//Create Banner " Our Current Selection:"
			userPortalBanner.setBounds(50, 115, 500, 50);
			userPortalBanner.setFont ( new Font ( null, Font.BOLD, 25 ) );
			userPortalBanner.setEditable ( false );
			userPortalBanner.setWrapStyleWord ( true );
			userPortalBanner.setLineWrap ( true );
			userPortalBanner.setBackground ( new Color ( frame.getBackground ( ).getRGB ( ), true ) );


			//Create JFrame and add all components
			frame.add(addToCart);
			frame.add(userPortalBanner);
			frame.add(viewCart);
			frame.add(custWelcomeTxt);
			frame.add(shopMenu);
			frame.setTitle("Customer Portal");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.add(welcomeLabel);
			frame.setLocation(600, 250);
			frame.setSize(500, 620);
			frame.setLayout(null);
			frame.setVisible(true);
			frame.setIconImage(
					Toolkit.getDefaultToolkit().getImage("C:\\Users\\tyler\\Desktop\\Photos-new-icon.png"));
		}
	}


	/**
	 * not yet completed for addToCart button. ****USERS WELCOME-PAGE****
	 */


	/**
	 * not yet completed for addToCart button. ****USERS WELCOME-PAGE****
	 * @param a
	 *
	 **/


	public void actionPerform(ActionEvent a){
		if(a.getSource() == addToCart )
		{

			int returnVal =
					JOptionPane.showOptionDialog(null,"Select an item",
												"Add to Cart",
												JOptionPane.YES_NO_OPTION,
												JOptionPane.INFORMATION_MESSAGE, null,
							new String[]{ "1", "2", "3"}, null);



		}
	}

	/**
	 * Action listener for selectButton, opens fileChooser dialog inorder to import txt file. ****ADMINS WELCOMEPAGE****
	 * @param e
	 */
	@Override
	public void actionPerformed(ActionEvent e )
	{
		if ( e.getSource ( ) == selectButton )
		{
			int returnVal = fc.showOpenDialog ( fc );

			if ( returnVal == JFileChooser.APPROVE_OPTION )
			{
				fc.setCurrentDirectory ( new File ( "C:\\" ) );
				File file = fc.getSelectedFile ( );
				//readFile(file);
				frame.dispose ( );
				new InventoryPage(file);

			}

		}
	}

	
	
	//****bad code that didnt work right the first time****** ...what did you learn?//
	/*
	 * public void ReadFileChooserOpenedFiled( ActionEvent e ) throws IOException { File file = fc.getSelectedFile ( );
	 * String Data = null;
	 * 
	 * Scanner reader = new Scanner ( file ); while ( reader.hasNextLine ( ) ) { Data = reader.nextLine ( );
	 * 
	 * System.out.println ( Data ); } reader.close ( ); }
	 */
	
	
	
	
	//Method only presents Cost, Color and Item Name for User
	public  String readFile( File file )
	{
		String itemData = null;
		JTextArea ta = new JTextArea();
		String customerItem = "";

		try
		{
			// File itemList = new File ( "Item.txt" );

			Scanner reader = new Scanner ( file );

			while ( reader.hasNextLine ( ) )
			{

				//updating readFile Method to-DO!!!!
				String name = reader.next() + " ";
				String color = reader.next() + " ";
				double cost = Double.valueOf(reader.next().substring(1));
				int qty = reader.nextInt();
				int sales = reader.nextInt();

				Item newItem = new Item(name, color, cost, qty, sales);
				String itemInfo = newItem.getName() + " ";
				itemInfo += newItem.getColor() + " ";
				itemInfo+= newItem.getCost() + " ";

				userInventory.add(newItem);

				//code to debug only printing cost color and name... working in console
				System.out.println(newItem.itemInfo(newItem) + " here i am");
				customerItem += newItem.itemInfo(newItem);



				//itemData = userInventory.toString() + "\n";
				itemData = newItem.itemInfo(newItem);
				itemData += reader.nextLine() + " \n";


				System.out.println ( itemData );
				ta.setText ( itemData + "\n" );
				

			}
			reader.close ( );

		} catch ( FileNotFoundException e )
		{
			System.out.println ( " ____________________\r\n" + "/                    \\\r\n" + "!  file not found :( !\r\n"
					+ "!                    !\r\n" + "\\____________________/\r\n" + "         !  !\r\n"
					+ "         !  !\r\n" + "         L_ !\r\n" + "        / _)!\r\n" + "       / /__L\r\n"
					+ " _____/ (____)\r\n" + "        (____)\r\n" + " _____  (____)\r\n" + "      \\_(____)\r\n"
					+ "         !  !\r\n" + "         !  !\r\n" + "         \\__/   " );
			System.out.println (
					"If you're not selecting a file from this projects folder you'll have to give the absolute path:" );
			System.out.println ( "I.E C:\\Users\\YOUR-USER-HERE\\PATH-TO-TEXT-FILE\\YourTextFile.txt" );

			e.printStackTrace ( );
		}
		return customerItem;

	}
}
