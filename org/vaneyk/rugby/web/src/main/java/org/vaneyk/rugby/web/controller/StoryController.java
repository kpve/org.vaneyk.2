package org.vaneyk.rugby.web.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.vaneyk.rugby.common.domain.Story;

@Controller
public class StoryController
{
    private static final Logger LOGGER = LoggerFactory.getLogger( StoryController.class );
    
    private RestTemplate restTemplate;
    private String       rugbyDataStoryRestUrl;
    
    private SimpMessagingTemplate messagingTemplate;
    
    public StoryController()
    {
        // TODO delete this hard code, replace with config property...
        this( "http://localhost:8080/story/" );
    }
    
    public StoryController( String rugbyDataStoryRestUrl )
    {
        this.restTemplate = new RestTemplate();
        this.rugbyDataStoryRestUrl = rugbyDataStoryRestUrl;
    }
    
    private void broadcastChangeEvent( Story story )
    {
        // TODO not very efficient broadcasting twice... revisit
        this.messagingTemplate.convertAndSend( API.MessagingDestination.ALL.getDestination(),     story );
    }
    
    @RequestMapping( method   = RequestMethod.PUT,
                     consumes = MediaType.APPLICATION_JSON_VALUE,
                     produces = MediaType.APPLICATION_JSON_VALUE  )
    @ResponseBody
    public Story create( @RequestBody Story story, HttpServletResponse httpServletResponse ) throws IOException
    {
        Story createdStory = null;
        
        ResponseEntity<Story> responseEntity = this.restTemplate.postForEntity( this.rugbyDataStoryRestUrl, story, Story.class );
                              createdStory   = responseEntity.getBody();
        
        this.broadcastChangeEvent( createdStory );
                              
        return createdStory;
    }
    
    @RequestMapping( value    ="/{id}",
                     method   = RequestMethod.PUT,
                     consumes = MediaType.APPLICATION_JSON_VALUE ,
                     produces = MediaType.APPLICATION_JSON_VALUE ) 
    public Story put( @PathVariable String id,  @RequestBody Story story, HttpServletResponse httpServletResponse ) throws IOException
    {
        Story updatedStory = null;
        
        this.restTemplate.put( this.rugbyDataStoryRestUrl + "/" + id, story );
        
        this.broadcastChangeEvent( updatedStory );
        
        return updatedStory;
    }

    @RequestMapping( value    ="/{id}",
                     method   = RequestMethod.GET,
                     produces = MediaType.APPLICATION_JSON_VALUE )
    @ResponseBody
    public Story get( @PathVariable( value = "id" ) String id, HttpServletResponse httpServletResponse )
    {
        Story story = this.restTemplate.getForObject( this.rugbyDataStoryRestUrl + "/" + id, Story.class );
        
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
    
}
