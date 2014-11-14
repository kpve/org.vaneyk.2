package org.vaneyk.rugby.web.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurationSupport;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfiguration extends WebSocketMessageBrokerConfigurationSupport //implements WebSocketMessageBrokerConfigurer
{
    @Override
    public void configureMessageBroker( MessageBrokerRegistry messageBrokerRegistry )
    {
        messageBrokerRegistry.setApplicationDestinationPrefixes( "/app" );
        messageBrokerRegistry.enableSimpleBroker( "/queue", "/topic" );
    }
    
    @Override
    protected void registerStompEndpoints( StompEndpointRegistry stompEndpointRegistry )
    {
        stompEndpointRegistry.addEndpoint( "/stories" ).withSockJS();        
    }

}
