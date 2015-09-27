package org.example.fogbeam.xmpp;

import java.util.ArrayList;
import java.util.List;

import org.example.fogbeam.xmpp.aisubsystems.AIML_InterpreterSubsystem;
import org.example.fogbeam.xmpp.aisubsystems.AISubsystem;
import org.example.fogbeam.xmpp.aisubsystems.AtCommandSubsystem;
import org.jivesoftware.smack.chat.Chat;
import org.jivesoftware.smack.chat.ChatMessageListener;
import org.jivesoftware.smack.packet.Message;

public class MultiSystem_MessageListener implements ChatMessageListener 
{
	List<AISubsystem> subsystems = new ArrayList<AISubsystem>();
	
	public MultiSystem_MessageListener()
	{
		subsystems.add( new AtCommandSubsystem() );
		subsystems.add( new AIML_InterpreterSubsystem() );
	
	}
	
	@Override
	public void processMessage( Chat chat, Message message )
	{
		// This is the highest level input / output system, which exists
		// just to bridge between XMPP and the executive that manages the
		// various processing sub-systems.
		// Once we receive a message here, it is passed off to all of the
		// available processing modules, any (or more) of which can generate
		// a response to the input.  The executive mitigates which response(s)
		// to utilize, cancels no-longer-needed requests, etc.
		
		// TODO: add threading support so the different subsystems can
		// run asynchronously.  Until then, we'll rely on the fact that
		// Lists are ordered, and make sure the AtCommandSubsystem is listed
		// first, so simple @commands get first shot at being processed.
		// if we get no response from that subsystem, we go on to whatever
		// is next (which, for now, is just the AIML subsystem).
		String messageBody = message.getBody();
		if( messageBody == null || messageBody.isEmpty() )
		{
			return;
		}
		
		String response = "";
		for( AISubsystem subsystem : subsystems )
		{
			response = subsystem.processMessage(messageBody);
			if( response != null && !response.isEmpty() )
			{
				break;
			}
		}
		
		if( response.isEmpty() )
		{
			response = "I have no response for that";
		}
		try
		{
			chat.sendMessage( response );
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
	}
}