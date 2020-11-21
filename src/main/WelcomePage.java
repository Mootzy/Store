package main;

import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.awt.*;

@SuppressWarnings("ALL")
public class WelcomePage implements ActionListener {

    //File for inputing admin's inventory text file.
    final File item = new File("Item.txt");
    File itemTemp = new File("ItempTemp.txt");

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
    final JTextArea warning = new JTextArea("Look to the console in your IDE for updated qty, after 'add to cart' button...");
    final JTextArea cartPrev = new JTextArea();
    final JTextArea cartBanner = new JTextArea("cart preview");
    final JTextArea totalPreview = new JTextArea();
    final JTextArea totalPreviewBanner = new JTextArea("total: ");
    final JTextArea inventoryPreview = new JTextArea();
    final JTextArea inventoryPreviewBanner = new JTextArea("Inventory Preview");
    //ADMIN selectbutton to pick new inventory .txt fild to upload
    final JButton selectButton = new JButton("Select");

    //USER buttons to viewCart and AddtoCart, NEITHER currently working
    final JButton viewCart = new JButton(("view cart/checkout"));
    final JButton addToCart = new JButton("add to cart");
    final JButton logout = new JButton("logout");
    final JButton managerViewButton = new JButton("Insight");

    //Alert user when out of Qty for object
    final JOptionPane outOfQty = new JOptionPane();

    //Create list for user to see what
    final ArrayList<Item> userInventory = new ArrayList<>();
    final ArrayList<JRadioButton> radioButtons = new ArrayList<>();
    final ArrayList<String> userCart = new ArrayList<>();


    final JTable inventoryTable = new JTable();

    //columns for Table
    String [] columns = new String[]{

            "Name",
            "Color",
            "Cost",
            "Quantity",
            "Sales this Month"
    };



    //Used to find value of radio Button Selection from USER
    //currently bugged... probably try using hashmap next
    //always provides value of "Joggers" ATM
    Integer userChoice = 0;

    //Track users total
    Double ongoingTotal = 0.0;
    Double cost = 0.0;




    String oldContent = "";



    WelcomePage(String userID) {

        //BufferedReader reader = new BufferedReader(new FileReader(item));

        welcomeLabel.setBounds(175, 0, 200, 35);
        welcomeLabel.setFont(new Font(null, Font.PLAIN, 25));
        welcomeLabel.setText("Hello, " + userID);

        itemList.setBounds(50, 200, 500, 50);
        itemList.setFont(new Font(null, Font.BOLD, 15));
        itemList.setEditable(false);
        itemList.setWrapStyleWord(true);
        itemList.setLineWrap(true);
        itemList.setBackground(new Color(frame.getBackground().getRGB(), true));

        logout.setBounds(185, 555, 125, 25);
        logout.setVisible(true);
        logout.setFocusable(false);
        logout.addActionListener(this::logoutAction);
        logout.addActionListener(this::radioButtonValue);


        //code for if on ADMIN Welcome-Page build JFRAME
        if (userID.equalsIgnoreCase("admin")) {


            selectButton.setBounds(200, 275, 75, 25);
            selectButton.setVisible(true);
            selectButton.setFocusable(false);
            // this took 2 hours to figure out you have to also add actionListener...
            selectButton.addActionListener(this);



            managerViewButton.setBounds(40, 475, 125, 25);
            managerViewButton.setVisible(true);
            managerViewButton.setFocusable(false);
            //managerView.addActionListener(this::managerViewAction);



            inventoryPreview.setText(readFile(item));
            inventoryPreview.setBounds(50,400,500,75);
            inventoryPreview.setEditable(false);
            inventoryPreview.setFont(new Font(null, Font.TRUETYPE_FONT, 12));
            inventoryPreview.setBackground(new Color(frame.getBackground().getRGB(), true));
            inventoryPreview.setVisible(true);


            inventoryPreviewBanner.setBounds(50,375,500,25);
            inventoryPreviewBanner.setEditable(false);
            inventoryPreviewBanner.setFont(new Font(null, Font.BOLD, 15));
            inventoryPreviewBanner.setBackground(new Color(frame.getBackground().getRGB(), true));
            inventoryPreviewBanner.setVisible(true);


            frame.add(logout);
            frame.add(inventoryPreviewBanner);
            frame.add(inventoryPreview);
            frame.add(managerViewButton);
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

            JRadioButton userOptions;

            shopMenu.setText(readFile(item));
            System.out.println(userInventory.size() + " here i am the size of userInventory");

            //****CREATES JRADIONBUTTON FOR EACH userInventory index, increments userChoice to associate a numerical value with radioButton
            for (int i = 0; i < userInventory.size() ; i++) {
                //x = width, y = height
                int x = 40;
                int y = 250;

                //Create new RadioButton with the reduced toString ' iteminfo'
                userOptions = new JRadioButton(userInventory.get(i).itemInfo(userInventory.get(i)));

                //Used to assign numerical value to each new JRadioButton
                userChoice += i -1  ;

                //Add new JRadioButton to the ArrayList<JRadioButton> radioButtons
                radioButtons.add(userOptions);

                //Create bounds && conditions for the new JRadioButton...Pretty proud of the forumla to 'stack' the buttons uniformly.
                userOptions.setBounds(x, y - (25 * i), 375, 25);
                userOptions.setVisible(true);
                userOptions.setFocusable(false);
                userOptions.addActionListener(this::actionPerform);
                userOptions.setActionCommand(userOptions.getName());

                //When the 'addToCart' button is selected the actionCommand returned == the name of the button i.e ' Nike-shirt Red 9.99$"
                //addToCart.setActionCommand(userOptions.getName());
                addToCart.setActionCommand(radioButtons.get(i).getText());

                //Add to the JFrame the JRadioButton at index [i] of the ArrayList radioButtons
                frame.add(radioButtons.get(i));

            }

            //TEST CODE TO SEE IF ARRAY IS PROPERPLY POPULATING
            System.out.println(userInventory.toString() + "\n" + "PAY ATTENTION TO 4th data member, aka the first Integer. " +
                    "This is a reflection of the quantitiy in store record BEFORE you 'add to cart' ");

            //Test code to see radionButtons<> elements
           // System.out.print(radioButtons.toString() + " radioBUTTONS TO STRING");
            //System.out.println();

            //Add to cart button
            addToCart.setBounds(50, 500, 175, 25);
            addToCart.setVisible(true);
            addToCart.setFocusable(false);
            addToCart.addActionListener(this::actionPerform);
            addToCart.addActionListener(this::addToCartAction);
            addToCart.addActionListener(this::radioButtonValue);
            addToCart.setActionCommand(radioButtons.get(userChoice).getText());

            //ViewCart Button
            viewCart.setBounds(225, 500, 175, 25);
            viewCart.setVisible(true);
            viewCart.setFocusable(false);
            viewCart.addActionListener(this::viewCartAction);

            //Create logout Button
            logout.setBounds(165, 535, 125, 25);
            logout.setVisible(true);
            logout.setFocusable(false);
            logout.addActionListener(this::logoutAction);
            logout.addActionListener(this::radioButtonValue);
            logout.setActionCommand(radioButtons.get(userChoice).getText());

            //Create Welcome banner " hi, user"
            custWelcomeTxt.setBounds(50, 50, 500, 50);
            custWelcomeTxt.setFont(new Font(null, Font.BOLD, 15));
            custWelcomeTxt.setEditable(false);
            custWelcomeTxt.setWrapStyleWord(true);
            custWelcomeTxt.setLineWrap(true);
            custWelcomeTxt.setBackground(new Color(frame.getBackground().getRGB(), true));

            //Warning to check console for updated qty after addToCart buttpn event
            warning.setFont(new Font(null, Font.BOLD, 10));
            warning.setEditable(false);
            warning.setWrapStyleWord(true);
            warning.setLineWrap(true);
            warning.setBackground(new Color(frame.getBackground().getRGB(), true));
            warning.setBounds(50, 575, 600, 25);

            //Create Banner " Our Current Selection:"
            userPortalBanner.setBounds(50, 115, 500, 50);
            userPortalBanner.setFont(new Font(null, Font.BOLD, 25));
            userPortalBanner.setEditable(false);
            userPortalBanner.setWrapStyleWord(true);
            userPortalBanner.setLineWrap(true);
            userPortalBanner.setBackground(new Color(frame.getBackground().getRGB(), true));

            //Create "Preview Cart" Banner
            cartBanner.setBounds(50,375, 500, 25);
            cartBanner.setEditable(false);
            cartBanner.setWrapStyleWord(true);
            cartBanner.setLineWrap(true);
            cartBanner.setVisible(true);
            cartBanner.setFont(new Font(null, Font.BOLD, 15));
            cartBanner.setBackground(new Color(frame.getBackground().getRGB(), true));

            //Create Cart Preview
            cartPrev.setBounds(50,425,500,25);
            cartPrev.setFont(new Font(null, Font.HANGING_BASELINE, 10));
            cartPrev.setVisible(true);
            cartPrev.setEditable(false);
            cartPrev.setBackground(new Color(frame.getBackground().getRGB(), true));

            //Create Total Preview
            totalPreview.setBounds(50,475,500,25);
            totalPreview.setFont(new Font(null, Font.HANGING_BASELINE, 10));
            totalPreview.setVisible(true);
            totalPreview.setEditable(false);
            totalPreview.setBackground(new Color(frame.getBackground().getRGB(), true));

            //code to make default button on userPortal addToCart, until they have something in their cart then change it to "checkout"
            //sneaky marketing ploys
            if(userCart.isEmpty()) {
                frame.getRootPane().setDefaultButton(addToCart);
                addToCart.requestFocus();
            }

            if(userCart.equals(null)){

                addToCart.update(addToCart.getGraphics());
                frame.getRootPane().setDefaultButton(viewCart);
                viewCart.requestFocus();
            }

            //Create frame to view all items
            frame.add(logout);
            frame.add(totalPreview);
            frame.add(cartPrev);
            frame.add(cartBanner);
            frame.add(addToCart);
            frame.add(userPortalBanner);
            frame.add(viewCart);
            frame.add(custWelcomeTxt);
            frame.setTitle("Customer Portal");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(welcomeLabel);
            frame.setLocation(525,200);
            frame.setSize(500, 620);
            frame.setLayout(null);
            frame.setVisible(true);
            frame.setIconImage(
                    Toolkit.getDefaultToolkit().getImage("C:\\Users\\tyler\\Desktop\\Photos-new-icon.png"));
            frame.add(warning);
            System.out.println(radioButtons.get(0).getText());



        }
    }






   //Adds actionEvent to addToCart Button... stil need to make 'add another item' prompt function properly
    public void actionPerform(ActionEvent a) {
        if (a.getSource() == addToCart) {

            JOptionPane.showOptionDialog(
                    null,
                    "Add Another Item?",
                    "Add to Cart",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.INFORMATION_MESSAGE, null,
                    new String[]{"no", "yes"}, null);

            //TODO put code here that adds the current selection to an arrayList 'cart' inorder to keep track of users prior additions.

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
                frame.dispose();
                new InventoryPage(file);

            }

        }
    }

    /**
     * Create ActionEvent for when addToCart JButton is selected... takes value of selected JRadioButton, subtracts 1 from QTY of selected item, adds 1 to SALES of selected item.
     * @param e
     */
    public void addToCartAction(ActionEvent e) {

        if (e.getSource() == addToCart) {
            //  Create variables for what to do to items Qty and Sales
           int qtyRemoved = userInventory.get(userChoice).getQty()-1;
           int saleAdded = userInventory.get(userChoice).getSales() + 1;

           if(userInventory.get(userChoice).getQty() == 0){

           }

            //  Set/Change the values of the QTY and SALES
            userInventory.get(userChoice).setQty(qtyRemoved);
            userInventory.get(userChoice).setSales(saleAdded);

            System.out.println((
                    userInventory.toString() + "\n" +
                    "PAY ATTENTION TO 4th data member, aka the first Integer for QTY." + "\n" +
                            "PAY ATTENTION to 5th data member, aka the last number for SALES " + "\n" +
                    "This is a reflection of the quantitiy in store record AFTER you 'add to cart'" +
                            "Including: adding to SALES, & subcracting from QTY respectively"
            ));

            /**
             * to be used for adding cart to arrayList and keeping track of all the customers choices.
             */
            userCart.equals(userCart.add(userInventory.get(userChoice).getName() +  userInventory.get(userChoice).getCost()) + "\n");
            ongoingTotal += ((Double) userInventory.get(userChoice).getCost()) ;
            //ArrayList<Item> cart = new ArrayList<>();
            cartPrev.setText(userCart.toString().replace("[", "").replace("]",""));
            cartPrev.update(cartPrev.getGraphics());

            totalPreview.setText("total: $" + ongoingTotal.toString());
            totalPreview.update(totalPreview.getGraphics());
            frame.add(cartPrev);

            System.out.println(userCart.toString());
            System.out.println(ongoingTotal + "(tax not yet included)");
        }

    }

    public void logoutAction(ActionEvent e){
        if (e.getSource()==logout)
        {
            frame.dispose();

            IDandPasswords idandPasswords = new IDandPasswords ( );
            LoginPage loginPage = new LoginPage ( idandPasswords.getLoginInfo ( ) );
        }
    }

    /**
     * Test ActionEvent that relays the value of the selected JRadioButton... initially used for bugTracking.
     * @param e
     */

    public void radioButtonValue(ActionEvent e) {
        if (e.getSource() == addToCart) {
            String returnValue = e.getActionCommand();
            System.out.println(
                    "\n" + "Selected value of radio button = " + e.getActionCommand());
            System.out.println(returnValue);
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

    /**
     * Method to read ADMIN's 'uploaded' text file and populate arrayList for menu
     * Method only presents Cost, Color and Item Name for User
     * @param file
     * @return
     */
    public String readFile(File file) {
        String itemData = null;
        //JTextArea ta = new JTextArea();
        String customerItem = "";

        try {
            Scanner reader = new Scanner(file);

            while (reader.hasNextLine()) {


                String name = reader.next() + " ";
                String color = reader.next() + " ";
                cost = Double.parseDouble(reader.nextDouble() + " ");
                //double cost = Double.valueOf(reader.next().substring(1)); ****BAD CODE NOT FINDING RIGHT DOUBLE VALUE*****
                int qty = reader.nextInt();
                int sales = reader.nextInt();

                Item newItem = new Item(name, color, cost, qty, sales);
                String itemInfo = newItem.getName() + " ";
                itemInfo += newItem.getColor() + " ";
                itemInfo += newItem.getCost() + " ";
                itemInfo += newItem.getQty() + " ";
                itemInfo += newItem.getSales() + "\n";

                if(userInventory != null) {

                    userInventory.add(newItem);
                    //customerItem += newItem.itemInfo(newItem);
                    customerItem += newItem.toString() + "\n";
                }
                //code to debug only printing cost color and name... working in console
               // System.out.println(newItem.itemInfo(newItem) + " here i am");



                //customerItem += newItem.itemInfo(newItem);
                if (userInventory == null){

                    Collections.fill(userInventory,newItem);

                }

                //itemData = userInventory.toString() + "\n";
                itemData = newItem.itemInfo(newItem);
                itemData += reader.nextLine() + " \n";


               // System.out.println(itemData);
                // ta.setText(itemData + "\n");


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
    public String readFileDataUserNeeds(File file) {
        String itemData = null;
        JTextArea ta = new JTextArea();
        String customerItem = "";

        try {
            Scanner reader = new Scanner(file);

            while (reader.hasNextLine()) {

                //updating readFile Method to-DO!!!!
                String name = reader.next() + " ";
                String color = reader.next() + " ";
                cost = Double.parseDouble(reader.nextDouble() + " ");
                //double cost = Double.valueOf(reader.next().substring(1)); ****BAD CODE NOT FINDING RIGHT DOUBLE VALUE*****
                int qty = reader.nextInt();
                int sales = reader.nextInt();

                Item newItem = new Item(name, color, cost, qty, sales);
                String itemInfo = newItem.getName() + " ";
                itemInfo += newItem.getColor() + " ";
                itemInfo += newItem.getCost() + " ";

                if(userInventory != null) {

                    userInventory.add(newItem);
                    customerItem += newItem.itemInfo(newItem);

                }
                //code to debug only printing cost color and name... working in console
                // System.out.println(newItem.itemInfo(newItem) + " here i am");



                //customerItem += newItem.itemInfo(newItem);
                if (userInventory == null){

                    Collections.fill(userInventory,newItem);

                }

                //itemData = userInventory.toString() + "\n";
                itemData = newItem.itemInfo(newItem);
                itemData += reader.nextLine() + " \n";


                // System.out.println(itemData);
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
    public void setAddToCart(ActionEvent e){
        if (e.getSource() == addToCart){

        }
    }
    public void viewCartAction(ActionEvent e){
        if (e.getSource() == viewCart){
            JFrame checkout = new JFrame();
            checkout.setTitle("Checkout");
            checkout.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            checkout.setVisible(true);
            checkout.setBounds(250,250, 250, 250);
            cartPrev.setBounds(0,25,200,50);
            checkout.add(cartPrev);
            checkout.add(totalPreview);

        }
    }

    public void editFile(File file, String filePath, String oldString, String newString){

    String oldContent = "";
    String newContent = "";
    Scanner reader = null;
    FileWriter writer = null;
    String newLine = null;

    String totalEdit =  ongoingTotal.toString();


        try {
            writer = new FileWriter(file);
            BufferedWriter writerOut = new BufferedWriter(writer);
            reader = new Scanner(file);
            //BufferedReader readerOut = new BufferedReader(reader);

           // String dataToReplace = ((String) reader.nextInt()) + reader.nextInt();

            while (reader.hasNextLine()) {

                cost = Double.parseDouble(reader.nextDouble() + " ");

                int qty = reader.nextInt();
                int sales = reader.nextInt();
                newLine = String.valueOf(qty) + " " + String.valueOf(sales) ;
                writerOut.write(newLine);
                item.delete();
                boolean successful = itemTemp.renameTo(item);


                //dataToReplace += qty + " " +  sales;

                //oldContent = oldContent +
            }
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane alert = new JOptionPane("Sorry");
            frame.add(alert);
            alert.setBounds(50,50,250,300);
            alert.setVisible(true);
        }
    }





}
