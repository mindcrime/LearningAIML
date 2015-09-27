package org.example.fogbeam.xmpp.aisubsystems;

import org.alicebot.ab.Bot;

public class AIML_InterpreterSubsystem implements AISubsystem
{

	private org.alicebot.ab.Chat chatSession;
	
	public AIML_InterpreterSubsystem()
	{
		String botname="mybot";
		String path=".";
		Bot bot = new Bot(botname, path);
		
		
		chatSession = new org.alicebot.ab.Chat(bot);
	}
	
	@Override
	public String processMessage( String msgBody ) 
	{
		
		if( msgBody == null )
		{
			return null;
		}
		
		System.out.println( "received incoming message: " + msgBody );
		
		String response = "";
		
		try
		{
			if( !msgBody.isEmpty())
			{				
				
				if( !msgBody.startsWith("@"))
				{
					// if it's a general input string, not an @command
					response = chatSession.multisentenceRespond(msgBody);
				}
				else
				{
					// NOP, we have a separate subsystem for processing @commands
				}

				System.out.println("RESPONSE: " + response);
			
				// A little pause here, it's creepy if the bot responds too fast.
				Thread.sleep( 1250 );
			}
			
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
		
		return response;

	}
}