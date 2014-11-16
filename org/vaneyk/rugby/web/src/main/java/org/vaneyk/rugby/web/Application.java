package org.vaneyk.rugby.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.vaneyk.commons.spring.debug.SpringDebugUtils;
import org.vaneyk.rugby.web.configuration.WebConfiguration;

@Configuration
@EnableAutoConfiguration
@ComponentScan( value = { "org.vaneyk.rugby.web" } )
@Import( WebConfiguration.class )
public class Application
{
    private static final Logger LOGGER = LoggerFactory.getLogger( Application.class );
    
    private static void setProfile()
    {
        System.setProperty( "spring.profiles.active", "development" );
    }

    private static void dump( ApplicationContext applicationContext )
    {
        SpringDebugUtils.dumpApplicationContext( applicationContext );
        //SpringDebugUtils.dumpBean( applicationContext, "foo" );
    }
    
    public static void main( String[] args )
    {
        Application.setProfile();
        ApplicationContext applicationContext = null;
        try
        {
            applicationContext = SpringApplication.run( Application.class, args );
        }
        catch( Exception e )
        {
            Application.LOGGER.error( "", e );
        }
        finally
        {
            Application.dump( applicationContext );
        }
    }
}
