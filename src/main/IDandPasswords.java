package main;

import java.util.HashMap;

public class IDandPasswords
{
	HashMap<String, String> logininfo = new HashMap<String, String> ( );

	IDandPasswords()
	{
		logininfo.put ( "admin", "admin" );
		logininfo.put ( "user", "user" );
	}

	protected HashMap getLoginInfo( )
	{
		return logininfo;
	}
}
