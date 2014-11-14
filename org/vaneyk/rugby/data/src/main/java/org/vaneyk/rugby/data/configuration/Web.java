package org.vaneyk.rugby.data.configuration;

import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration.WebMvcAutoConfigurationAdapter;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
class Web extends WebMvcAutoConfigurationAdapter
{
    @Bean
    public EmbeddedServletContainerFactory servletContainer()
    {
        TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory();
        
        tomcat.setPort( org.vaneyk.rugby.common.configuration.Configuration.Port.DATA.getValue() );
        
        return tomcat;
    }

//    private Connector createSslConnector()
//    {
//        Connector connector = new Connector( "org.apache.coyote.http11.Http11NioProtocol" );
//        Http11NioProtocol protocol = (Http11NioProtocol)connector.getProtocolHandler();
//        try
//        {
//            File keystore = new ClassPathResource("keystore").getFile();
//            File truststore = new ClassPathResource("keystore").getFile();
//            connector.setScheme("https");
//            connector.setSecure(true);
//            connector.setPort(8443);
//            protocol.setSSLEnabled(true);
//            protocol.setKeystoreFile(keystore.getAbsolutePath());
//            protocol.setKeystorePass("changeit");
//            protocol.setTruststoreFile(truststore.getAbsolutePath());
//            protocol.setTruststorePass("changeit");
//            protocol.setKeyAlias("apitester");
//            return connector;
//        }
//        catch (IOException ex) {
//            throw new IllegalStateException("can't access keystore: [" + "keystore"
//                    + "] or truststore: [" + "keystore" + "]", ex);
//        }
//    }
    
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
