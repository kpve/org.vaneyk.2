package org.vaneyk.rugby.data;

import org.springframework.beans.factory.annotation.Value;


public class Foo
{
    @Value( value = "${foo}" )
    private String value;

    public Foo()
    {
        this( null );
    }
    
    public Foo( String value )
    {
        this.value = value;
    }
    
    @Override
    public String toString()
    {
        return super.toString() + ": " + this.value;
    }
}
