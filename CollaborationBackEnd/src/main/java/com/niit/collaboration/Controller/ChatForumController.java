package com.niit.collaboration.Controller;

import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.niit.collaboration.model.Message;
import com.niit.collaboration.model.OutputMessage;

import java.util.Date;

import org.slf4j.Logger;
@Controller
public class ChatForumController {

	
	private static final Logger Logger = LoggerFactory.getLogger(ChatForumController.class);
	
	@MessageMapping("/chat_forum")
	@SendTo("/queue/message")
	public OutputMessage sendMessage(Message message)
	{
		Logger.debug("calling the method sendMessage");
		Logger.debug("Message : ",message.getId());
		Logger.debug(" Message ID : ",message.getId());
		
		return new OutputMessage(message, new Date());
	}
}
