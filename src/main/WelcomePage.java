package main;

import java.awt.event.*;

import javax.swing.*;
import java.io.*;
import java.util.Scanner;
import java.awt.*;

public class WelcomePage implements ActionListener
{
	JFrame frame = new JFrame ( );

	FileDialog fd = new FileDialog ( frame, "Choose a file to load the list", FileDialog.LOAD );

	String userDir = System.getProperty ( "user.home" );
	JFileChooser fc = new JFileChooser ( userDir );

	JLabel welcomeLabel = new JLabel ( "Store Inventory" );

	JTextArea itemList = new JTextArea ( "Please Select a file to open to import new inventory" );

	JButton selectButton = new JButton ( "Select" );

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

		selectButton.setBounds ( 200, 275, 75, 25 );
		selectButton.setVisible ( true );
		selectButton.setFocusable ( false );
		selectButton.addActionListener ( this );
		// this took 2 hours to figure out you have to also add actionListener...

		frame.add ( selectButton );
		frame.add ( itemList );
		frame.setTitle ( "Store Inventory Portal" );
		frame.setDefaultCloseOperation ( JFrame.EXIT_ON_CLOSE );
		frame.add ( welcomeLabel );
		frame.setLocation ( 600, 250 );
		frame.setSize ( 500, 620 );
		frame.setLayout ( null );
		frame.setVisible ( true );
		frame.setIconImage (
				Toolkit.getDefaultToolkit ( ).getImage ( "C:\\Users\\tyler\\Desktop\\Photos-new-icon.png" ) );

	}

	public void actionPerformed( ActionEvent e )
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
	
	
	
	
	
	public  String readFile( File file )
	{
		String itemData = null;
		JTextArea ta = new JTextArea();
		ta.setText("");
		ta.setBounds ( 50, 200, 500, 50 );
		ta.setFont ( new Font ( null, Font.BOLD, 15 ) );
		ta.setEditable ( false );
		ta.setWrapStyleWord ( true );
		ta.setLineWrap ( true );
		ta.setBackground ( new Color ( frame.getBackground ( ).getRGB ( ), true ) );
		// JTextArea text = new JTextArea ( );
		// JLabel txtGui = new JLabel ( );

		/* text.append ( readFile(shirt) ); */
		try
		{
			// File itemList = new File ( "Item.txt" );
			Scanner reader = new Scanner ( file );
			while ( reader.hasNextLine ( ) )
			{
				itemData = reader.nextLine ( );
				
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
		return itemData;

	}
}
