package main;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.lang.reflect.Array;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.*;

public class InventoryPage implements ActionListener {
	JFrame frame = new JFrame();
	JLabel welcomeLabel = new JLabel("Name/Color/Price/Quantity/Sales(monthly )");
	JTextArea ta = new JTextArea();
	JLabel columns = new JLabel("(Name/Color/Price/Quantity/Monthly Units Sold )");
	JOptionPane pane = new JOptionPane();
	JButton addButton = new JButton("add to list");


	 static ArrayList<Item> inventory = new ArrayList<>();
	static List<Item>userInventory = new ArrayList<>();
	//userInventory.equals(inventory);

	InventoryPage(File file) {
//userInventory.equals(inventory);
		//Code for JLabel dimensions etc...

		welcomeLabel.setBounds(0, 0, 400, 35);
		welcomeLabel.setVisible(true);
		welcomeLabel.setFont(new Font(null, Font.PLAIN, 12));

		//code for addButton's dimensions etc..
		addButton.setBounds(50, 450, 75, 25);
		addButton.setVisible(true);
		addButton.setFocusable(false);
		addButton.addActionListener(this);


		int userConfirm = pane.YES_NO_CANCEL_OPTION;


		String itemData = "";

		//text-area for the list of inventory items displayed on main page.
		JTextArea ta = new JTextArea();
		ta.setBounds(50, 100, 500, 700);
		ta.setFont(new Font(null, Font.BOLD, 15));
		ta.setEditable(false);
		ta.setWrapStyleWord(true);
		ta.setLineWrap(true);
		ta.setBackground(new Color(frame.getBackground().getRGB(), true));
		ta.setText(readFile(file));

		//Code to create the frame and all it's components
		frame.add(addButton);
		frame.setTitle("Store Inventory **CONFIDENTIAL**");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(welcomeLabel);
		frame.setLocation(600, 250);
		frame.setSize(500, 620);
		frame.setLayout(null);
		frame.setVisible(true);
		frame.setIconImage(
				Toolkit.getDefaultToolkit().getImage("C:\\Users\\tyler\\Desktop\\Photos-new-icon.png"));
		frame.add(ta);


		itemData += userInput() + "\n";
		ta.setText(ta.getText() + "" + (itemData));

		frame.add(ta);

		/*
		 * itemData += userInput ( ) + "\n"; ta.setText ( ta.getText ( ) + "" + ( itemData ) );
		 *
		 * frame.add ( ta );
		 */

	}


	/**
	 * Code to read in a text file and create objects from it's values ***ADMINS WELCOME-PAGE***
	 *
	 * @param file
	 * @return
	 */
	public String readFile(File file) {
		String itemData = null;
		JTextArea ta = new JTextArea();

		try {

			Scanner reader = new Scanner(file);
			//itemData = reader.nextLine ( ) + "\n";

			while (reader.hasNextLine()) {

				String name = reader.next() + " ";
				String color = reader.next() + " ";
				double cost = Double.valueOf(reader.next().substring(1));
				int qty = reader.nextInt();
				int sales = reader.nextInt();

				Item newItem = new Item(name, color, cost, qty, sales);
				inventory.add(newItem);


				itemData = inventory.toString() + "\n";
				ta.setText(ta.getText() + "\n" + itemData);
				itemData += reader.nextLine() + "\n";

			}
			/* userInput ( ); */

			reader.close();
			for (int i = 0; i < inventory.size(); i++) {
				ta.setText(ta.getText() + "\n" + inventory);
			}
			System.out.println("****I'm the Text file contents added to the array and output as text, (currently i only output to the console because bad):");
			System.out.println(inventory.toString() + "\n");

			//System.out.println ( itemData );
		} catch (FileNotFoundException e) {
			System.out.println(" ____________________\r\n" + "/                    \\\r\n" + "!  file not found :( !\r\n"
					+ "!                    !\r\n" + "\\____________________/\r\n" + "         !  !\r\n"
					+ "         !  !\r\n" + "         L_ !\r\n" + "        / _)!\r\n" + "       / /__L\r\n"
					+ " _____/ (____)\r\n" + "        (____)\r\n" + " _____  (____)\r\n" + "      \\_(____)\r\n"
					+ "         !  !\r\n" + "         !  !\r\n" + "         \\__/   ");
			System.out.println(
					"If you're not selecting a file from this projects folder you'll have to give the absolute path:");
			System.out.println("I.E C:\\Users\\YOUR-USER-HERE\\PATH-TO-TEXT-FILE\\YourTextFile.txt");

			e.printStackTrace();
		}
		return itemData;

	}


	/**
	 * Code to take in user inputed values and create object of the Class "Item" then append to arrayList 'inventory' *ADMINS WELCOME-PAGE*
	 *
	 * @return
	 */
	public String userInput() {


		Item userItem = new Item();
		Item hold = new Item();
		int userConfirm = 0;
		int userDeny = -1;
		String input = "";
		String item = "";

		userConfirm = JOptionPane.showOptionDialog(frame, "Would you like to add more objects to the list?", input,
				JOptionPane.YES_NO_CANCEL_OPTION, userConfirm, null, null, input);

		if (userConfirm == JOptionPane.NO_OPTION) {
			userConfirm = JOptionPane.ABORT;
		}

		while (userConfirm == JOptionPane.YES_OPTION) {
			if (userConfirm == JOptionPane.YES_OPTION) {
				Scanner name = new Scanner(JOptionPane.showInputDialog("What would you like to name the object?" + " "));
				String inputName = name.next() + " ";
				userItem.setName(inputName);
				name.close();

				Scanner color = new Scanner(JOptionPane.showInputDialog("What color is the object?") + " ");
				String inputColor = color.next();
				userItem.setColor(inputColor);
				color.close();

				Scanner cost = new Scanner(JOptionPane.showInputDialog("Whats the cost of the object?") + " ");
				double inputCost = cost.nextDouble();
				userItem.setCost(inputCost);
				cost.close();

				Scanner qty = new Scanner(JOptionPane.showInputDialog("How many are in inventory?") + " ");
				int inputQty = qty.nextInt();
				userItem.setQty(inputQty);
				qty.close();


				Scanner sales = new Scanner(JOptionPane.showInputDialog("How many do we sell a month?") + " ");
				int inputSales = sales.nextInt();
				userItem.setSales(inputSales);
				sales.close();


				Item newItem = new Item(inputName, inputColor, inputCost, inputQty, inputSales);
				inventory.add(newItem);


//				userItem.setName ( JOptionPane.showInputDialog ( "What would you like to name the object?" ) + " " );
//				userItem.setColor ( JOptionPane.showInputDialog ( "What color is the object?" )  );
//
//				userItem.setCost (
//						Double.parseDouble ( JOptionPane.showInputDialog ( "What would you like the cost to be?" ) ) );
//
//				userItem.setQty (
//						Integer.parseInt ( JOptionPane.showInputDialog ( "How many of the item do we have in stock?" ) ) );
//
//				userItem.setSales (
//						Integer.parseInt ( JOptionPane.showInputDialog ( "How many of the item did we sell last month?" ) )
//								+ " \n" );

				item += userItem.toString() + " ";
				ta.setText(item + " ");


				hold.setName(userItem.getName());
				hold.setColor(userItem.getName());
				hold.setCost(userItem.getCost());
				hold.setQty(userItem.getQty());
				hold.setSales(userItem.getSales());


				//System.out.println ( hold.toString() + "I'm the hold item" );
				//System.out.println (userItem.toString ( ) + "I'm the userItem" );


			}
			ta.setText(item + "\n");
			userConfirm = JOptionPane.showOptionDialog(frame, "Would you like to add more objects to the list?", input,
					JOptionPane.YES_NO_CANCEL_OPTION, userConfirm, null, null, input);

			//ta.setText ( item + "\n" );


		}
		//System.out.println ( hold.toString ( ) + "I'm the hold item");
		//System.out.println ( item);

		//System.out.println ( userItem.toString ( ) + "I'm the userItem" );


		System.out.println("I'm the method that takes user inputed objects and adds them to an array and output as text, (currently i only output to the console because bad):" + " \n");
		System.out.println(inventory.toString());
		return item;

	}

	public void actionPerformed(ActionEvent e) {
		String itemData = "";

		if (e.getSource() == addButton) {
			userInput();


		}

	}
}









//Current Garbage code
//
//	public ArrayList addToList(ArrayList<Item> inventory, Item itemToADdd) {
//
//	return null;
//	}
//	public void printArrayList(ArrayList<Item> arrayToPrint){
//		arrayToPrint.toString();
//
//		}
//
//	}
//
//
