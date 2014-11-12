package org.vaneyk.commons.spring.data.mongo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document( collection = Counter.COLLECTION_NAME )
public class Counter
{
    static final String COLLECTION_NAME     = "counters";
    static final String ID_FIELD_NAME       = "_id";
    static final String SEQUENCE_FIELD_NAME = "sequence";

    @Id private String id;
    private Long sequence;
    
    public Counter()
    {
        this( null );
    }

    public Counter( String id )
    {
        this( id, 0L );
    }
    
    public Counter( String id, Long sequence )
    {
        this.setId( id );
        this.setSequence( sequence );
    }
    
    public String getId()
    {
        return this.id;
    }
    
    public void setId( String id )
    {
        this.id = id;
    }
    
    public Long getSequence()
    {
        return this.sequence;
    }
    
    public void setSequence( Long sequence )
    {
        this.sequence = sequence;
    }

}