package org.vaneyk.rugby.data;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.vaneyk.rugby.data.debug.SpringDebugUtils;
import org.vaneyk.rugby.data.domain.entity.Story;
import org.vaneyk.rugby.data.domain.entity.Task;
import org.vaneyk.rugby.data.domain.repository.StoryRepository;
import org.vaneyk.rugby.data.domain.repository.TaskRepository;

@Configuration
// TODO revisit scanning, auto config etc
@ComponentScan
@EnableAutoConfiguration
public class Application implements CommandLineRunner 
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
        }
        catch( Exception e )
        {
            Application.LOGGER.error( "~~~~~~~~~~~~~~~~~~~~~~~~~~~~ " + e.getMessage() );
        }
        finally
        {
            //Application.dump( applicationContext );
        }
    }
    
    @Override
    public void run( String... args ) throws Exception
    {
        Task  task         = new Task( "task one", null );
        this.taskRepository.save( task );
             
        Story story        = new Story( "story one", null );
        story.getTasks().add( task );
        task.setStory( story );
        
        Story putStory     = this.storyRepository.save( story );
        Application.LOGGER.error( "putStory=" + putStory );
        Story getStory     = this.storyRepository.findOne( story.getId() );
        Application.LOGGER.error( "getStory=" + getStory );        
        
    }

}
