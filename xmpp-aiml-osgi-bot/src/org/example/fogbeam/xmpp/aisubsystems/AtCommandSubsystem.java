package org.example.fogbeam.xmpp.aisubsystems;

import java.text.SimpleDateFormat;
import java.util.Date;

/* There's really nothing AI about this in its present incarnation.  The question is, can
 * we come up with a way for the bot to learn "@commands" dynamically?  Consider that an
 * open research project. In the meantime, hard-coded @commands give us a set of primitives
 * to use to control the bot, as well as a handy way to make it do actually useful stuff
 * until we figure out how to make it learn how.  
 */
public class AtCommandSubsystem implements AISubsystem
{

	SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss zzz" );
	
	@Override
	public String processMessage( String msgBody ) 
	{
		
		if( msgBody == null )
		{
			return null;
		}
		
		System.out.println( "received incoming message: " + msgBody );
		
		String response = "";
		
		if( !msgBody.isEmpty() && msgBody.startsWith("@"))
		{
			switch( msgBody )
			{
				case "@time":
					Date now = new Date();
					response = sdf.format( now );
					break;
				default:
					response = "Unknown command";
					break;
			}
		}
		
		System.out.println("RESPONSE: " + response);
		
		// A little pause here, it's creepy if the bot responds too fast.
		try
		{
			Thread.sleep( 1250 );
		}
		catch( Exception e )
		{
			// NOP, doesn't matter if this sleep is interrupted
		}
		
		return response;
	}	
	
}