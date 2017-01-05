package com.niit.collaboration.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

@Configuration
@EnableWebSocketMessageBroker
@ComponentScan(basePackages="com.niit")
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer{
	
	private static final Logger Logger = LoggerFactory.getLogger(WebSocketConfig.class);

	@Override
	public void configureMessageBroker(MessageBrokerRegistry config) {
		Logger.debug("Starting method config message Broker");
		config.enableSimpleBroker("/topic", "/queue");
		config.setApplicationDestinationPrefixes("/app");
		Logger.debug("Ending method Config Message Broker");
	}
	
	
	public void registerStompEndpoints(StompEndpointRegistry registery) {
		Logger.debug("Starting method registerStompEndPoints");
		registery.addEndpoint("/chat").withSockJS();
		Logger.debug("Ending method registerStompEndPoints");
		/*Logger.debug("Starting method chatforumregisterStompEndPoints");
		registery.addEndpoint("/chat_forum").withSockJS();
		Logger.debug("Ending method chatforumregisterStompEndPoints");
*/
		
		}
}
