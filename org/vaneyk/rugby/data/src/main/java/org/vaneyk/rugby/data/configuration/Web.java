package org.vaneyk.rugby.data.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableMongoRepositories
class Web extends WebMvcConfigurationSupport
{
    @Bean
    public InternalResourceViewResolver defaultViewResolver()
    {
        return new InternalResourceViewResolver();
    }
    
//    @Override
//    protected void addViewControllers( ViewControllerRegistry registry )
//    {
//        // Lifted from org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration. [WLW]
//        registry.addViewController("/").setViewName("forward:/index.html");
//    }
    
    @Override
    public void addResourceHandlers( ResourceHandlerRegistry registry )
    {
        registry.addResourceHandler( "/**" ).addResourceLocations( "/webapp" );
        // TODO some day add support for external resources :)
        //<mvc:resources mapping="/images/**" location="file:///c:/absolute/path/to/the/resource/folder/" />
        registry.addResourceHandler( "/**" ).addResourceLocations( "file:/some/path");
    }
}
