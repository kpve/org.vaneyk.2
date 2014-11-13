package org.vaneyk.rugby.data.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.vaneyk.commons.spring.data.mongo.CounterService;
import org.vaneyk.rugby.data.Foo;
import org.vaneyk.rugby.data.domain.repository.StoryRepository;
import org.vaneyk.rugby.data.service.HelloWorldService;

@Configuration
@Profile( "development" )
@PropertySource( "classpath:application-development.properties" )
public class Development 
{
    @Bean
    public Foo foo( @Value( value = "foo" ) String value )
    {
        return new Foo( value );
    }
    
    @Bean
    @Autowired
    public HelloWorldService helloWorldService( CounterService counterService, StoryRepository storyRepository )
    {
        HelloWorldService helloWorldService = new HelloWorldService( counterService, storyRepository );
        
        return helloWorldService;
    }
}
