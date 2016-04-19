package be.vlaanderen.sbs.s6.taaladvies.ldap;

import netscape.ldap.LDAPConnection;
import netscape.ldap.LDAPEntry;
import netscape.ldap.LDAPException;
import netscape.ldap.LDAPSearchResults;
import be.vlaanderen.sbs.s6.framework.logging.AppLogger;

public class LDAPQuerier
{
	private String host;
	private int port;
	private String baseDN;
	
	public LDAPQuerier(String host, int port, String baseDN)
	{
		this.host = host;
		this.port = port;
		this.baseDN = baseDN;
		AppLogger.getInstance().debug("New LDAPQuerier instantiated: " + host + "|" + port + "|" + baseDN);
	}
	
	private LDAPConnection openConnection() throws LDAPException
	{
		LDAPConnection connection = new LDAPConnection();
		connection.connect(host, port);		
		return connection;
	}
	
	private void closeConnection(LDAPConnection connection) throws LDAPException
	{
		if ( connection != null && connection.isConnected())
		{
			connection.disconnect();
		}
	}
	
	private String getDN(String uid) throws LDAPException
	{
		LDAPConnection connection = null;
		LDAPSearchResults results = null;
		LDAPEntry entry = null;
		String filter = "uid=" + uid;
		String dn = null;
		
		try
		{
			connection = this.openConnection();
			results = connection.search(baseDN, LDAPConnection.SCOPE_SUB, filter, null, false);
			if ( results.hasMoreElements())
			{
				entry = results.next();
				dn = entry.getDN();
				if ( results.hasMoreElements())
				{
					AppLogger.getInstance().warn(uid + " exists more than once on LDAP-server");
				}
				else
				{
					AppLogger.getInstance().debug(uid + " exist on LDAP-server");
				}
			}
			else
			{
				AppLogger.getInstance().debug(uid + " doesn't exist on LDAP-server");
			}
			closeConnection(connection);
			return dn;
		}
		catch (LDAPException ex)
		{
			AppLogger.getInstance().debug("Error trying to close connection ??");
			closeConnection(connection);			
			throw ex;
		}
	}
	
	public boolean authenticate(String uid, String password) throws LDAPException
	{
		LDAPConnection connection = null;
		boolean isAuthentic = false;
		if ( password.trim().equals(""))
		{
			return false;
		}
		try
		{
			connection = this.openConnection();
			try
			{
				String dnUid = getDN(uid);
				connection.authenticate(dnUid, password);
				if(dnUid == null)
					AppLogger.getInstance().debug("Invalid userid(" + uid +") entered");
				else
				{
					isAuthentic = true;					
					AppLogger.getInstance().debug("valid userid(" + uid +") entered");
				}
			}
			catch (LDAPException ex)
			{
				switch(ex.getLDAPResultCode())
				{
					case 48: 	AppLogger.getInstance().debug("Invalid userid(" + uid +") entered");
								break;
					case 49: 	AppLogger.getInstance().debug("Invalid password for userid(" + uid +") entered");
								break; 	
					default: 	if(connection != null)
									closeConnection(connection);
								throw ex;
				}				
			}
			
			closeConnection(connection);
			return isAuthentic;
		}
		catch (LDAPException ex)
		{
			closeConnection(connection);
			throw ex;
		}		
	}
}		
