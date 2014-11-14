package org.vaneyk.rugby.common.domain;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.annotation.Transient;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.vaneyk.commons.spring.data.mongo.CounterService;
import org.vaneyk.commons.spring.data.mongo.MetaData;

// TODO revisit mutability

@Document( collection = Task.COLLECTION_NAME )
public class Task
{
    static final String COLLECTION_NAME = "tasks";

    @Transient private CounterService counterService;
    
    @Indexed Long   id;
    @Indexed String name;
             String storyId;
    @Version Long   version;
    
    // TODO revisit, required by spring framework
    public Task()
    {
    }
    
    @PersistenceConstructor
    public Task( Long id, String name, @Value(MetaData.ROOT_ID_VALUE_EXPRESSION)String storyId, Long version )
    {
        this( null, id, name, storyId, version );
    }
    
    public Task( CounterService counterService, Long id, String name, String storyId, Long version )
    {
        this.setCounterService( counterService );
        
        this.setId( id );
        this.setName( name );
        this.setVersion( version );
        this.setStoryId( storyId );
    }

    public CounterService getCounterService()
    {
        return this.counterService;
    }

    public void setCounterService( CounterService counterService )
    {
        this.counterService = counterService;
    }
    
    public Long getId()
    {
        return this.id;
    }

    public void setId( Long id )
    {
        this.id = ( id == null ? this.counterService.getNextSequenceValue( Task.COLLECTION_NAME ) : id );
    }

    public String getName()
    {
        return this.name;
    }

    public void setName( @NotNull String name )
    {
        this.name = name;
    }

    public Long getVersion()
    {
        return this.version;
    }

    public void setVersion( Long version )
    {
        this.version = version;
    }

    public String getStoryId()
    {
        return this.storyId;
    }

    public void setStoryId( String storyId )
    {
        this.storyId = storyId;
    }

    @Override
    public String toString()
    {
        String toString = new ToStringBuilder( this )
                                  .append( this.counterService )
                                  .append( this.id )
                                  .append( this.name )
                                  .append( this.storyId )
                                  .toString();
        
        return toString;
    }
}
