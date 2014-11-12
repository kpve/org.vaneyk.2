package org.vaneyk.rugby.data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.vaneyk.rugby.data.debug.SpringDebugUtils;
import org.vaneyk.rugby.data.domain.repository.StoryRepository;
import org.vaneyk.rugby.data.domain.repository.TaskRepository;

@Configuration
// TODO revisit scanning, auto config etc
@ComponentScan( value = { "org.vaneyk.commons.spring.data.mongo", "org.vaneyk.rugby.data" } )
@EnableAutoConfiguration
public class Application
{
    private static final Logger LOGGER = LoggerFactory.getLogger( Application.class );
    
    @Autowired
    private StoryRepository storyRepository;
    @Autowired
    private TaskRepository taskRepository;
    
    private static void setProfile()
    {
        System.setProperty( "spring.profiles.active", "development" );
    }

    private static void dump( ApplicationContext applicationContext )
    {
        SpringDebugUtils.dumpApplicationContext( applicationContext );
        SpringDebugUtils.dumpBean( applicationContext, "foo" );
    }
    
    public static void main( String[] args )
    {
        Application.setProfile();
        ApplicationContext applicationContext = null;
        try
        {
            applicationContext = SpringApplication.run( Application.class, args );
            Runnable helloWorldService = applicationContext.getBean( "helloWorldService", Runnable.class );
            helloWorldService.run();
            Application.LOGGER.info( "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~helloWorldService=" + helloWorldService );
        }
        catch( Exception e )
        {
            Application.LOGGER.error( "~~~~~~~~~~~~~~~~~~~~~~~~~~~~ " + e.getMessage() );
        }
        finally
        {
            Application.dump( applicationContext );
        }
    }
}
