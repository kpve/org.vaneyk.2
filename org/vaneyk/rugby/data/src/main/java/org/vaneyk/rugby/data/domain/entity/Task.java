package org.vaneyk.rugby.data.domain.entity;

import java.util.UUID;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document( collection="tasks" )
public class Task
{
    @Value("#root.tasks.qty ?: 0")
    int order;
    @Indexed
    String name;
    @Version
    long version;
    
    @DBRef
    Story story;

    public Task()
    {
        this( null, null, 1 );
    }

    public Task( String name, Story story, long version )
    {
        this( null, name, story, version );
    }
    
    @PersistenceConstructor
    public Task( String id, String name, Story story, long version )
    {
        this.id = id;
        this.name = name;
        this.story = story;
        this.version = version;
    }
    
    public String getId()
    {
        return this.id;
    }

    public void setId( String id )
    {
        this.id = id;
    }

    public String getName()
    {
        return this. name;
    }

    public void setName( String name )
    {
        this.name = name;
    }

    public Story getStory()
    {
        return this.story;
    }

    public void setStory( Story story )
    {
        this.story = story;
    }

    @Override
    public String toString()
    {
        String toString = new ToStringBuilder( this )
                                  .append( this.id )
                                  .append( this.name )
                                  .append( ( this. story == null ? "[no story]" : this.story.id ) )
                                  .toString();
        
        return toString;
    }
}
