package org.vaneyk.rugby.data.configuration;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class AOP
{
    @Aspect
    public static class LoggingAspect
    {
        private static final Logger LOGGER = LoggerFactory.getLogger( AOP.LoggingAspect.class );
                
        @Before( "execution(* org.vaneyk.rugby.data..*(..)) && !execution(* org.vaneyk.rugby.data.configuration.AOP.*(..))" )
        public void logBefore( JoinPoint joinPoint )
        {
            // TODO change log level from error to trace
            LoggingAspect.LOGGER.error( "TRACE: " + joinPoint.getSignature() );
        }
    }
    
    @Bean
    public LoggingAspect loggingAspect()
    {
        return new LoggingAspect();
    }
}
