package org.example.fogbeam.xmpp;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.ConnectionListener;
import org.jivesoftware.smack.ReconnectionManager;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.chat.Chat;
import org.jivesoftware.smack.chat.ChatManager;
import org.jivesoftware.smack.chat.ChatManagerListener;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;
import org.jivesoftware.smack.util.TLSUtils;

public class BotMain 
{
	public static void main( String[] args ) throws Exception
	{
		
		XMPPTCPConnectionConfiguration.Builder configBuilder = XMPPTCPConnectionConfiguration.builder();
		configBuilder.setUsernameAndPassword("testuser1", "password");
		configBuilder.setResource("SomeResource");
		configBuilder.setServiceName("fogbeam.org");
		configBuilder.setHost( "www.fogbeam.org" );
		
		// accept all certificate - just for testing  
		try 
		{  
		    TLSUtils.acceptAllCertificates(configBuilder);  
		} 
		catch (NoSuchAlgorithmException e) {  
		
		} 
		catch (KeyManagementException e) {  
		
		}  
		
		AbstractXMPPConnection connection = new XMPPTCPConnection(configBuilder.build());
		
	
		class MyConnectionListener  implements ConnectionListener {
			
			boolean m_reconnect = false;
			
			public boolean isReconnect()
			{
				return m_reconnect;
			}
			
			@Override
			public void connectionClosedOnError(Exception arg0) {
				System.out.println( "connectionClosedOnError called!");
				m_reconnect = true;
				Thread.currentThread().interrupt();
			}

			@Override
			public void authenticated(XMPPConnection arg0, boolean arg1) {
				System.out.println( "authenticated called!");
				
			}

			@Override
			public void connected(XMPPConnection arg0) {
				System.out.println( "connected called!");
				
			}

			@Override
			public void connectionClosed() {
				System.out.println( "connectionClosed called!");
				
			}

			@Override
			public void reconnectingIn(int arg0) {
				System.out.println( "reconnectingIn called!");
				
			}

			@Override
			public void reconnectionFailed(Exception arg0) {
				System.out.println( "reconnectionFailed called!");
				
			}

			@Override
			public void reconnectionSuccessful() {
				System.out.println( "reconnectionSuccessful called!");
				
			}
		};		
		
		
		MyConnectionListener connectionListener = new MyConnectionListener();

		// make sure auto reconnect is turned on.
		ReconnectionManager.getInstanceFor(connection).enableAutomaticReconnection();
		
		try
		{				
			System.out.println( "Connecting...");
				
					
			connection.addConnectionListener( connectionListener );
			
			// Connect to the server
			connection.connect();
	
			// Log into the server
			connection.login();
	
			ChatManager chatManager = ChatManager.getInstanceFor(connection);
			chatManager.addChatListener(
				new ChatManagerListener() {
					@Override
					public void chatCreated(Chat chat, boolean createdLocally)
					{
						if (!createdLocally)
							chat.addMessageListener(new MyNewMessageListener());;
					}
				});	
		
			
			System.out.println( "running..." );
			
			while( true )
			{
				try
				{
					Thread.sleep( 1000000 );
				}
				catch( InterruptedException e )
				{
					e.printStackTrace();
				}
			}
		}
		catch( Exception e )
		{
			System.err.println( "Uh oh... something broke!");
			e.printStackTrace();
		}

		
	}
}