package org.vaneyk.rugby.data.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.vaneyk.rugby.data.domain.entity.Story;
import org.vaneyk.rugby.data.domain.repository.StoryRepository;

@Controller
@RequestMapping( "/story" )
public class StoryController
{
    private StoryRepository storyRepository;

    @Autowired
    public StoryController( StoryRepository storyRepository )
    {
        this.storyRepository = storyRepository;
    }
    
    @RequestMapping( method   = RequestMethod.PUT,
                     consumes = MediaType.APPLICATION_JSON_VALUE ,
                     produces = MediaType.APPLICATION_JSON_VALUE ) 
    public Story put( Story story )
    {
        Story updatedStory = this.storyRepository.save( story );
        
        return updatedStory;
    }

    @RequestMapping( method   = RequestMethod.GET,
                     produces = MediaType.APPLICATION_JSON_VALUE ) 
    public Story get( String id )
    {
        Story story = this.storyRepository.findOne( id );
        
        return story;
    }
    
    @RequestMapping( method   = RequestMethod.DELETE,
                     produces = MediaType.APPLICATION_JSON_VALUE ) 
    public void delete( String id )
    {
        this.storyRepository.delete( id );
    }
}
