package org.vaneyk.rugby.common.configuration;

public class Configuration
{
    public enum Port
    {
        DATA( 8010 ),
        WEB ( 8080 );
        
        private int value;
        
        private Port( int value )
        {
            this.value = value;
        }
        
        public int getValue()
        {
            return this.value;
        }
    }
}
