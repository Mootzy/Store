package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.*;
import javax.swing.*;

public class LoginPage implements ActionListener
{

	JFrame frame = new JFrame ( );

	JButton loginButton = new JButton ( "Login" );
	JButton resetButton = new JButton ( "Reset" );

	JTextField unField = new JTextField ( );
	JPasswordField pwField = new JPasswordField ( );

	JLabel unLabel = new JLabel ( "User Name: " );
	JLabel pwLabel = new JLabel ( "Password: " );

	JLabel messageLabel = new JLabel ( );
	ImageIcon img = new ImageIcon ( "C:\\Users\\tyler\\Desktop\\Photos-new-icon.png" );
	JLabel imagelabel = new JLabel ( img );

	HashMap<String, String> loginInfo = new HashMap<String, String> ( );

	LoginPage(HashMap<String, String> loginInfoOriginal)
	{
		loginInfo = loginInfoOriginal;

		messageLabel.setBounds ( 125, 250, 250, 35 );
		messageLabel.setFont ( new Font ( null, Font.BOLD, 25 ) );
		unLabel.setBounds ( 100, 50, 75, 25 );
		pwLabel.setBounds ( 100, 100, 75, 25 );

		unField.setBounds ( 175, 50, 200, 25 );
		pwField.setBounds ( 175, 100, 200, 25 );

		loginButton.setBounds ( 175, 150, 100, 25 );
		loginButton.setFocusable ( false );
		resetButton.setBounds ( 275, 150, 100, 25 );

		loginButton.addActionListener ( this );
		resetButton.addActionListener ( this );
		resetButton.setFocusable ( false );

		Color backgroundFrame = Color.decode ( "#DEF9FF" );

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
		frame.setIconImage (
				Toolkit.getDefaultToolkit ( ).getImage ( "C:\\Users\\tyler\\Desktop\\Photos-new-icon.png" ) );
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
	public void actionPerformeduser(ActionEvent e){

	}
	public void keyAction( KeyEvent e )
	{
		if ( e.getKeyCode ( ) == KeyEvent.VK_ENTER )
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
		}
	}
}
