package org.vaneyk.commons.spring.debug;

import java.util.Arrays;

import org.springframework.context.ApplicationContext;

public class SpringDebugUtils
{
    public static void dumpApplicationContext( ApplicationContext applicationContext )
    {
        String[] beanNames = applicationContext.getBeanDefinitionNames();
        Arrays.sort( beanNames );
        for( String beanName : beanNames )
        {
            System.out.println( beanName );
        }
    }

    public static void dumpBean( ApplicationContext applicationContext, String beanName )
    {
        String dump = null;
        
        if( applicationContext ==  null )
        {
            dump = "undefined application context";
        }
        else
        {
            Object bean = applicationContext.getBean( beanName );
            if( bean == null )
            {
                dump = "bean '" + beanName + "' not found";
            }
            else
            {
                dump = bean.toString();
            }
        }
        
        System.out.println( dump );
    }
}
