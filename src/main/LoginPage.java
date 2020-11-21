package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;
import javax.swing.*;

public class LoginPage implements ActionListener, KeyListener
{
	//Create main Frame
	JFrame frame = new JFrame ( );

	//Create Login and Reset buttons for frame
	JButton loginButton = new JButton ( "Login" );
	JButton resetButton = new JButton ( "Reset" );

	//Create the two text fields to input username and password
	JTextField unField = new JTextField ( );
	JPasswordField pwField = new JPasswordField ( );

	//Create label for username and password to seperate boxes
	JLabel unLabel = new JLabel ( "User Name: " );
	JLabel pwLabel = new JLabel ( "Password: " );

	//Unnesseccary image label field, unutilized at the moment
	JLabel messageLabel = new JLabel ( );
	ImageIcon img = new ImageIcon ( "C:\\Users\\tyler\\Desktop\\Photos-new-icon.png" );
	JLabel imagelabel = new JLabel ( img );

	//Create Hashmap for the login page so user can properly log-in
	HashMap<String, String> loginInfo = new HashMap<String, String> ( );

	LoginPage(HashMap<String, String> loginInfoOriginal)
	{
		loginInfo = loginInfoOriginal;

		//Create message Label for logging in
		messageLabel.setBounds ( 125, 250, 250, 35 );
		messageLabel.setFont ( new Font ( null, Font.BOLD, 25 ) );

		//Create username and Password text label bounds,
		unLabel.setBounds ( 100, 50, 150, 25 );
		pwLabel.setBounds ( 100, 100, 75, 25 );

		//Create text box area for password and UN input
		unField.setBounds ( 175, 50, 200, 25 );
		pwField.setBounds ( 175, 100, 200, 25 );

		//Create and design Login Button
		loginButton.setBounds ( 175, 150, 100, 25 );
		loginButton.setFocusable ( false );
		loginButton.addActionListener ( this );

		//Create and design resetButton
		resetButton.setBounds ( 275, 150, 100, 25 );
		resetButton.addActionListener ( this );
		resetButton.setFocusable ( false );

		//code to set Default focus of Root Pane to loginButton so user can just press the enter key to submit form
		frame.getRootPane().setDefaultButton(loginButton);
		loginButton.requestFocus();

		//Build default Frame for LoginPage
		frame.setDefaultCloseOperation ( JFrame.EXIT_ON_CLOSE );
		frame.setSize ( 500, 250 );
		frame.setLayout ( null );
		frame.setVisible ( true );
		frame.add ( unLabel );
		frame.add ( pwLabel );
		frame.setLocation ( 600, 250 );
		frame.add ( messageLabel );
		frame.add ( unField );
		frame.add ( pwField );
		frame.add ( resetButton );
		frame.add ( loginButton );
		frame.setTitle ( "Store Access Portal" );
		frame.add ( imagelabel );
		frame.setIconImage (Toolkit.getDefaultToolkit ( ).getImage ( "C:\\Users\\tyler\\Desktop\\Photos-new-icon.png" ) );
		frame.setLocationRelativeTo ( null );

	}

	@Override
	public void actionPerformed( ActionEvent e )
	{
		if ( e.getSource ( ) == resetButton )
		{
			unField.setText ( "" );
			pwField.setText ( "" );

		}
		// TODO Auto-generated method stub
		if ( e.getSource ( ) == loginButton )
		{

			String userID = unField.getText ( );
			String password = String.valueOf ( pwField.getPassword ( ) );

			if ( loginInfo.containsKey ( userID ) )
			{
				if ( loginInfo.get ( userID ).equals ( password ) )
				{
					messageLabel.setForeground ( Color.green );
					messageLabel.setText ( "Successful" );
					messageLabel.setSize ( 125, 125 );
					frame.dispose ( );
					new WelcomePage ( userID );

				}
			}

			else
			{
				messageLabel.setForeground ( Color.red );
				messageLabel.setText ( "Wrong username or password" );

			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent e) {

	}
}
