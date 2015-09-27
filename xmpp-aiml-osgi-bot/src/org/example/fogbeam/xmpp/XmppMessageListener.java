package org.example.fogbeam.xmpp;

import org.jivesoftware.smack.ConnectionListener;
import org.jivesoftware.smack.XMPPConnection;


public class XmppMessageListener implements ConnectionListener
{
	
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

