package org.vaneyk.rugby.web.controller;

public class API
{
    public enum MessagingDestination
    {
        ALL( "topic/all" );
        
        private String destination;
        
        // TODO add unique check here
        private MessagingDestination( String destination )
        {
            this.destination = destination;
        }
        
        public String getDestination()
        {
            return this.destination;
        }
    }
}
