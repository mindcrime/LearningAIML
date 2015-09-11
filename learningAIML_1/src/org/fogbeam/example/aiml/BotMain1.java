package org.fogbeam.example.aiml;

import org.alicebot.ab.Bot;
import org.alicebot.ab.Chat;

public class BotMain1
{

	public static void main( String[] args )
	{
		String botname="mybot";
		String path=".";
		Bot bot = new Bot(botname, path);
		
		Chat chatSession = new Chat(bot);
		
		// String request = "Hello.";
		String request = "Hello.  Are you alive?  Who is Alice?";
		String response = chatSession.multisentenceRespond(request);
		System.out.println("RESPONSE: " + response);
		
		System.out.println( "done" );
	}
}
