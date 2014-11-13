package org.vaneyk.rugby.data.api.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

// TODO not used yet
//@ControllerAdvice
class GlobalDefaultExceptionHandler
{
    public static final String DEFAULT_ERROR_VIEW = "error";

    @ExceptionHandler( value = Exception.class )
    public ModelAndView defaultErrorHandler( HttpServletRequest req, Exception e ) throws Exception
    {
        if( AnnotationUtils.findAnnotation( e.getClass(), ResponseStatus.class ) != null )
        {
            throw e;
        }
        else
        {
            ModelAndView mav = new ModelAndView();
            mav.addObject( "exception", e );
            mav.addObject( "url", req.getRequestURL() );
            mav.setViewName( GlobalDefaultExceptionHandler.DEFAULT_ERROR_VIEW );
            
            return mav;
        }
    }
}