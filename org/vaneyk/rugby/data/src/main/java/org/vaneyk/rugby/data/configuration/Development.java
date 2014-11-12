package org.vaneyk.rugby.data.configuration;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.vaneyk.rugby.data.Foo;
import org.vaneyk.rugby.data.domain.entity.Story;
import org.vaneyk.rugby.data.domain.entity.Task;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

@Configuration
@Profile( "development" )
@PropertySource( "classpath:application.development.properties" )
public class Development 
{
    @Bean
    public Foo foo( @Value( value = "foo" ) String value )
    {
        return new Foo( value );
    }
    
}
