package org.vaneyk.rugby.web.configuration;

import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration.WebMvcAutoConfigurationAdapter;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfiguration extends WebMvcAutoConfigurationAdapter //WebMvcConfigurerAdapter 
{
    @Bean
    public EmbeddedServletContainerFactory servletContainer()
    {
        TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory();
        
        tomcat.setPort( org.vaneyk.rugby.common.configuration.Configuration.Port.WEB.getValue() );
        
        return tomcat;
    }
    
// REVISIT caching, IE seems to:  http://stackoverflow.com/questions/16098430/angular-ie-caching-issue-for-http
// TODO ?? needed or is the @Override addInterceptors(...) enough?
//    @Bean
//    public WebContentInterceptor webContentInterceptor()
//    {
//        WebContentInterceptor webContentInterceptor = new WebContentInterceptor();
//        webContentInterceptor.setCacheSeconds( 0 );
//        webContentInterceptor.setUseExpiresHeader( true );
//        webContentInterceptor.setUseCacheControlHeader( true );
//        webContentInterceptor.setUseCacheControlNoStore( true );
//
//        return webContentInterceptor;
//    }

//    @Override
//    public void addInterceptors( InterceptorRegistry registry )
//    {
//        // TODO revisit this, some brow
//        WebContentInterceptor webContentInterceptor = new WebContentInterceptor();
//        webContentInterceptor.setCacheSeconds( 0 );
//        webContentInterceptor.setUseExpiresHeader( true );
//        webContentInterceptor.setUseCacheControlHeader( true );
//        webContentInterceptor.setUseCacheControlNoStore( true );
//
//        this.registry.addInterceptor( webContentInterceptor );
//    }
}
