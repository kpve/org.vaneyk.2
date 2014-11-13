package org.vaneyk.rugby.data.domain.entity;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

//TODO revisit mutability

@Document( collection="stories" )
public class Story
{
    @Id      String     id;
    @Indexed String     name;
             List<Task> tasks;
    @Version Long       version;

    // TODO revisit, required by spring framework
    public Story()
    {
    }
    
    @PersistenceConstructor
    public Story( String id, String name, List<Task> tasks, Long version )
    {
        this.setId( id );
        this.setName( name );
        this.setTasks( tasks );
        this.setVersion( version );
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
        return this.name;
    }

    public void setName( @NotNull String name )
    {
        this.name = name;
    }

    public List<Task> getTasks()
    {
        return this.tasks;
    }

    public void setTasks( List<Task> tasks )
    {
        this.tasks = ( tasks == null ? new ArrayList<Task>() : tasks );
    }

    public Long getVersion()
    {
        return this.version;
    }

    public void setVersion( Long version )
    {
        this.version = version;
    }

    @Override
    public String toString()
    {
        String toString = new ToStringBuilder( this )
                                  .append( this.id )
                                  .append( this.name )
                                  .append( this.tasks )
                                  .append( this.version )
                                  .toString();
        
        return toString;
    }
}
