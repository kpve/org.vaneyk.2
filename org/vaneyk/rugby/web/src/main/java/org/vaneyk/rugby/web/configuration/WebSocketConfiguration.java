package org.vaneyk.rugby.web.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurationSupport;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfiguration extends WebSocketMessageBrokerConfigurationSupport //implements WebSocketMessageBrokerConfigurer
{

    @Override
    protected void registerStompEndpoints( StompEndpointRegistry registry )
    {
        // TODO Auto-generated method stub
        
    }
//    @Override
//    public void configureMessageBroker( MessageBrokerRegistry config )
//    {
//        config.setApplicationDestinationPrefixes( "/app" );
//        config.enableSimpleBroker( "/queue", "/topic" );
//    }
//
//    @Override
//    public void registerStompEndpoints( StompEndpointRegistry registry )
//    {
//        registry.addEndpoint( "/portfolio" ).withSockJS();
//    }


}
