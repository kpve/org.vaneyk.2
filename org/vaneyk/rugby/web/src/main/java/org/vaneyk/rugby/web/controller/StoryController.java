package org.vaneyk.rugby.web.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.vaneyk.rugby.common.domain.Story;

@Controller
public class StoryController
{
    private static final Logger LOGGER = LoggerFactory.getLogger( StoryController.class );
    
    private Story put( Story story, HttpServletResponse httpServletResponse  ) throws IOException
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
    
    @RequestMapping( method   = RequestMethod.PUT,
                     consumes = MediaType.APPLICATION_JSON_VALUE,
                     produces = MediaType.APPLICATION_JSON_VALUE  )
    @ResponseBody
    public Story create( @RequestBody Story story, HttpServletResponse httpServletResponse ) throws IOException
    {
        Story createdStory = null;
        
        Story existingStory = this.storyRepository.findOne( story.getId() );
        if( existingStory != null )
        {
            httpServletResponse.sendError( HttpServletResponse.SC_CONFLICT );
        }
        else
        {
            createdStory = this.put( story, httpServletResponse );
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
            updatedStory = this.put( story, httpServletResponse );
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
    
//    @SubscribeMapping("/positions")
//    public List<PortfolioPosition> getPositions(Principal principal) throws Exception
//    {
//        logger.debug("Positions for " + principal.getName());
//        Portfolio portfolio = this.portfolioService.findPortfolio(principal.getName());
//        return portfolio.getPositions();
//    }
//    
//    @MessageMapping("/trade")
//    public void executeTrade(Trade trade, Principal principal)
//    {
//        trade.setUsername(principal.getName());
//        logger.debug("Trade: " + trade);
//        this.tradeService.executeTrade(trade);
//    }
//    
//    @MessageExceptionHandler
//    @SendToUser("/queue/errors")
//    public String handleException(Throwable exception)
//    {
//        return exception.getMessage();
//    }
    
    @MessageMapping( "/subscribe/stories" )
    @SendTo( "/topic/stories" )
    public void subscribeToStoriesEvents( HelloMessage message ) throws Exception
    {
         return new Greeting("Hello, " + message.getName() + "!");
   }
}
