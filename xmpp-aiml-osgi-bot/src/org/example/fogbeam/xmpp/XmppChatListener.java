package org.example.fogbeam.xmpp;

import org.jivesoftware.smack.chat.Chat;
import org.jivesoftware.smack.chat.ChatManagerListener;

public class XmppChatListener implements ChatManagerListener
{
	@Override
	public void chatCreated(Chat chat, boolean createdLocally)
	{
		if (!createdLocally)
			chat.addMessageListener(new AIML_Interpreter_MessageListener());;
	}
}
