package org.vaneyk.rugby.data.service;

import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vaneyk.commons.spring.data.mongo.CounterService;
import org.vaneyk.rugby.common.domain.Story;
import org.vaneyk.rugby.common.domain.Task;
import org.vaneyk.rugby.data.domain.repository.StoryRepository;

@Service
public class HelloWorldService implements Runnable
{
    private static final Logger LOGGER = LoggerFactory.getLogger( HelloWorldService.class );
    
    private CounterService counterService;
    private StoryRepository storyRepository;

    @Autowired
    public HelloWorldService( @NotNull CounterService counterService, StoryRepository storyRepository )
    {
        this.counterService = counterService;
        this.storyRepository = storyRepository;
    }
    
    @Override
    public void run()
    {
        try
        {
            Task  task         = new Task( this.counterService, null, "task two", null, null );
            
            Story story        = new Story( null, "story two", null, null );
            story.getTasks().add( task );
            task.setStoryId( story.getId() );
            
            Story putStory     = this.storyRepository.save( story );
            HelloWorldService.LOGGER.error( "putStory=" + putStory );
            
            Story getStory     = this.storyRepository.findOne( story.getId() );
            HelloWorldService.LOGGER.error( "getStory=" + getStory );
        }
        catch( Exception e )
        {
            e.printStackTrace();
        }
    }
}
