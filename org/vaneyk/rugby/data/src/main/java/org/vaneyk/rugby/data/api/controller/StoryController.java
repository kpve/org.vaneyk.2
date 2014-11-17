package org.vaneyk.rugby.data.api.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.vaneyk.rugby.common.domain.Story;
import org.vaneyk.rugby.data.domain.repository.StoryRepository;

@Controller
@RequestMapping( "/story" )
public class StoryController
{
    private StoryRepository storyRepository;

    public StoryController()
    {
        this(  null );
    }
    
    @Autowired
    public StoryController( StoryRepository storyRepository )
    {
        this.storyRepository = storyRepository;
    }
    
    private Story save( Story story, HttpServletResponse httpServletResponse  ) throws IOException
    {
        Story updatedStory = null;
        
        if( story == null )
        {
            httpServletResponse.sendError( HttpServletResponse.SC_BAD_REQUEST, "no content" );
        }
        else
        {
            updatedStory = this.storyRepository.save( story );
        }
        
        return updatedStory;
    }
    
    @RequestMapping( method   = RequestMethod.POST,
                     consumes = MediaType.APPLICATION_JSON_VALUE,
                     produces = MediaType.APPLICATION_JSON_VALUE  )
    @ResponseBody
    public Story post( @RequestBody Story story, HttpServletResponse httpServletResponse ) throws IOException
    {
        Story createdStory = null;

        if( story == null )
        {
            httpServletResponse.sendError( HttpServletResponse.SC_BAD_REQUEST, "no content" );
        }
        else if( story.getId() != null )
        {
            httpServletResponse.sendError( HttpServletResponse.SC_BAD_REQUEST, "new stories are not allowed to have and id value" );            
        }
        else
        {
            createdStory = this.save( story, httpServletResponse );
            httpServletResponse.setStatus( HttpServletResponse.SC_CREATED );
        }
        
        return createdStory;
    }
    
    @RequestMapping( value    ="/{id}",
                     method   = RequestMethod.PUT,
                     consumes = MediaType.APPLICATION_JSON_VALUE ,
                     produces = MediaType.APPLICATION_JSON_VALUE ) 
    public Story put( @PathVariable String id,  @RequestBody Story story, HttpServletResponse httpServletResponse ) throws IOException
    {
        Story updatedStory = null;
        
        if( story == null )
        {
            httpServletResponse.sendError( HttpServletResponse.SC_BAD_REQUEST, "no content" );
        }
        else if( !story.getId().equals( id ) )
        {
            httpServletResponse.sendError( HttpServletResponse.SC_NOT_FOUND, "resourd id: " + id + " does not match content id: " + story.getId() );
        }
        else
        {
            updatedStory = this.save( story, httpServletResponse );
        }
        
        return updatedStory;
    }

    // TODO revisit paging
    @RequestMapping( value    ="/",
                     method   = RequestMethod.GET,
                     produces = MediaType.APPLICATION_JSON_VALUE )
    @ResponseBody
    public List<Story> getAll( HttpServletResponse httpServletResponse )
    {
        List<Story> stories = this.storyRepository.findAll();

        if( stories == null )
        {
           httpServletResponse.setStatus( HttpServletResponse.SC_NOT_FOUND );
        }

        return stories;
    }
    
    @RequestMapping( value    ="/{id}",
                     method   = RequestMethod.GET,
                     produces = MediaType.APPLICATION_JSON_VALUE )
    @ResponseBody
    public Story get( @PathVariable( value = "id" ) String id, HttpServletResponse httpServletResponse )
    {
        Story story = this.storyRepository.findOne( id );
        
        if( story == null )
        {
            httpServletResponse.setStatus( HttpServletResponse.SC_NOT_FOUND );
        }
        
        return story;
    }
    
    @RequestMapping( value    ="/{id}",
                     method   = RequestMethod.DELETE,
                     produces = MediaType.APPLICATION_JSON_VALUE ) 
    @ResponseBody
    public Story delete( @PathVariable( value = "id" ) String id, HttpServletResponse httpServletResponse )
    {
        Story story = this.get( id, httpServletResponse );
        
        if( story == null )
        {
            httpServletResponse.setStatus( HttpServletResponse.SC_NOT_FOUND );
        }
        else
        {
            this.storyRepository.delete( id );
        }
        
        return story;
    }
}
