package main;

import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.awt.*;

public class WelcomePage implements ActionListener {

    //JRadioButton test = new JRadioButton(userInventory.get(0).toUserString());


    //File for inputing admin's inventory text file.
    final File item = new File("Item.txt");

    //Frame to display entier GUI
    final JFrame frame = new JFrame();

    final String userDir = System.getProperty("user.home");

    //FileChooser for when ADMIN selects
    final JFileChooser fc = new JFileChooser(userDir);

    //ADMIN label denoting this is the stores current Inventory
    final JLabel welcomeLabel = new JLabel("Store Inventory");

    //Admin Text area prompting for new .txt file to update inventory
    final JTextArea itemList = new JTextArea("Please Select a file to open to import new inventory");

    //USER text area to display what is available to purchase and welcome mssgs.
    final JTextArea shopMenu = new JTextArea();
    final JTextArea custWelcomeTxt = new JTextArea("Welcome Valued Customer!");
    final JTextArea userPortalBanner = new JTextArea("Our Current Selection: " + "\n");

    //ADMIN selectbutton to pick new inventory .txt fild to upload
    final JButton selectButton = new JButton("Select");

    //USER buttons to viewCart and AddtoCart, NEITHER currently working
    final JButton viewCart = new JButton(("view cart/checkout"));
    final JButton addToCart = new JButton("add to cart");

    //Create list for user to see what
    final ArrayList<Item> userInventory = new ArrayList<>();
    final ArrayList<JRadioButton> radioButtons = new ArrayList<>();


    //ArrayList<Item> userInventory = new ArrayList<>();

    WelcomePage(String userID) {


        welcomeLabel.setBounds(175, 0, 200, 35);
        welcomeLabel.setFont(new Font(null, Font.PLAIN, 25));
        welcomeLabel.setText("Hello, " + userID);

        itemList.setBounds(50, 200, 500, 50);
        itemList.setFont(new Font(null, Font.BOLD, 15));
        itemList.setEditable(false);
        itemList.setWrapStyleWord(true);
        itemList.setLineWrap(true);
        itemList.setBackground(new Color(frame.getBackground().getRGB(), true));
        /* itemList.setText ( "test" ); */


        //code for if on ADMIN Welcome-Page build JFRAME
        if (userID.equalsIgnoreCase("admin")) {


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
            //frame.setIconImage(
            //   Toolkit.getDefaultToolkit().getImage("C:\\Users\\tyler\\Desktop\\Photos-new-icon.png"));
            //no icon path currently set, does nothing ATM
        }

        //code for if on USERS Welcome-Page
        else {


            //Text area for Customer's Menu
            shopMenu.setBounds(50, 175, 500, 700);
            shopMenu.setFont(new Font(null, Font.BOLD, 15));
            shopMenu.setEditable(false);
            shopMenu.setWrapStyleWord(true);
            shopMenu.setLineWrap(true);
            shopMenu.setBackground(new Color(frame.getBackground().getRGB(), true));

            //File item = new File("Item.txt");
            JRadioButton test;
            readFile(item);


            //String userView = null;
            //shopMenu.setText(userInventory.toString());
            //shopMenu.setText(InventoryPage.inventory.toString());
            shopMenu.setText(readFile(item));
            System.out.println(userInventory.size() + " here i am the size of userInventory");


            //This is a very BAD for-loop
            //for some reason the userInventory.size() is double what is should be
            //so we compensate by subtracting half of it...
            //****CREATES JRADIONBUTTON FOR EACH userInventory index
            for (int i = 0; i < userInventory.size() - userInventory.size() / 2; i++) {
                int x = 40;
                int y = 250;
                test = new JRadioButton(userInventory.get(i).itemInfo(userInventory.get(i)));
                radioButtons.add(test);

                test.setBounds(x, y - (25 * i), 375, 25);
                test.setVisible(true);
                test.setFocusable(false);
                test.addActionListener(this::actionPerform);
                frame.add(radioButtons.get(i));


                //****Bad code that hardcoded location of y vertex based on i... changed algo on line 135***
                //group.add(test);
				/*if (i == 0) {
					test.setBounds(x, y - 50, 375, 25);
				}
				if (i ==1) {
					test.setBounds(x,y-75,375,25);
				}
				if (i ==2) {
					test.setBounds(x, y - 105, 375, 25);
				}
				if (i ==3 ){
					test.setBounds(x, y -125, 375, 25);
				}*/
                //test.setBounds( x, 400, 375, 25);
            }
            //frame.add(radioButtons.get(radioButtons.size()-1));

            //TEST CODE TO SEE IF ARRAY IS PROPERPLY POPULATING
            System.out.println(userInventory + " Test code to see if array is properly pupulating for menu");

            //Test code to see radionButtons<> elements
            System.out.print(radioButtons.toString() + " radioBUTTONS TO STRING");
            System.out.println();

            //Add to cart button
            addToCart.setBounds(50, 500, 175, 25);
            addToCart.setVisible(true);
            addToCart.setFocusable(false);
            addToCart.addActionListener(this::actionPerform);

            //ViewCart Button
            viewCart.setBounds(225, 500, 175, 25);
            viewCart.setVisible(true);
            viewCart.setFocusable(false);
            viewCart.addActionListener(this);

            //Create Welcome banner " hi, user"
            custWelcomeTxt.setBounds(50, 50, 500, 50);
            custWelcomeTxt.setFont(new Font(null, Font.BOLD, 15));
            custWelcomeTxt.setEditable(false);
            custWelcomeTxt.setWrapStyleWord(true);
            custWelcomeTxt.setLineWrap(true);
            custWelcomeTxt.setBackground(new Color(frame.getBackground().getRGB(), true));

            //Create Banner " Our Current Selection:"
            userPortalBanner.setBounds(50, 115, 500, 50);
            userPortalBanner.setFont(new Font(null, Font.BOLD, 25));
            userPortalBanner.setEditable(false);
            userPortalBanner.setWrapStyleWord(true);
            userPortalBanner.setLineWrap(true);
            userPortalBanner.setBackground(new Color(frame.getBackground().getRGB(), true));

            //BUILD JFRAME TO DISPLAY
            frame.add(addToCart);
            frame.add(userPortalBanner);
            frame.add(viewCart);
            frame.add(custWelcomeTxt);
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
     *
     * @param a
     **/
    public void actionPerform(ActionEvent a) {
        if (a.getSource() == addToCart) {

            int returnVal =
                    JOptionPane.showOptionDialog(
                            null,
                            "Add Another Item?",
                            "Add to Cart",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.INFORMATION_MESSAGE, null,
                            new String[]{"no", "yes"}, null);

        }
    }

    /**
     * Action listener for selectButton, opens fileChooser dialog inorder to import txt file. ****ADMINS WELCOMEPAGE****
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == selectButton) {
            int returnVal = fc.showOpenDialog(fc);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                fc.setCurrentDirectory(new File("C:\\"));
                File file = fc.getSelectedFile();
                //readFile(file);
                frame.dispose();
                new InventoryPage(file);

            }

        }
    }

    public void addToCartAction(ActionEvent e) {
        if (e.getSource() == addToCart) {
            int returnVal;
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
    public String readFile(File file) {
        String itemData = null;
        JTextArea ta = new JTextArea();
        String customerItem = "";

        try {
            // File itemList = new File ( "Item.txt" );

            Scanner reader = new Scanner(file);

            while (reader.hasNextLine()) {

                //updating readFile Method to-DO!!!!
                String name = reader.next() + " ";
                String color = reader.next() + " ";
                double cost = Double.parseDouble(reader.nextDouble() + " ");
                //double cost = Double.valueOf(reader.next().substring(1)); ****BAD CODE NOT FINDING RIGHT DOUBLE VALUE*****
                int qty = reader.nextInt();
                int sales = reader.nextInt();

                Item newItem = new Item(name, color, cost, qty, sales);
                String itemInfo = newItem.getName() + " ";
                itemInfo += newItem.getColor() + " ";
                itemInfo += newItem.getCost() + " ";

                userInventory.add(newItem);

                //code to debug only printing cost color and name... working in console
                System.out.println(newItem.itemInfo(newItem) + " here i am");
                customerItem += newItem.itemInfo(newItem);


                //itemData = userInventory.toString() + "\n";
                itemData = newItem.itemInfo(newItem);
                itemData += reader.nextLine() + " \n";


                System.out.println(itemData);
                ta.setText(itemData + "\n");


            }
            reader.close();

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
        return customerItem;

    }


    public void removeFromQty(ArrayList<Item> userInventory, int elementToRemove) {

    }
}
