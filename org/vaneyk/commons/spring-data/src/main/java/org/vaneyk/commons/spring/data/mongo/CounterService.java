package org.vaneyk.commons.spring.data.mongo;

import javax.validation.constraints.NotNull;

import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

public class CounterService
{
    private static final Long   INVALID_SEQUENCE_VALUE         = -1L;
    
    private MongoOperations mongoOperations;

    public CounterService( @NotNull MongoOperations mongoOperations )
    {
        this.mongoOperations = mongoOperations;
        
        // TODO revisit making this lazy initialization
        if( !this.ensureCountersCollectionExists() )
        {
            throw new IllegalStateException( "failed to find or create counters collection" );
        };
    }
    
    private boolean ensureCountersCollectionExists()
    {
        boolean exists = false;
        
        try
        {
            exists = this.mongoOperations.collectionExists( Counter.class );
            if( !exists )
            {
                this.mongoOperations.createCollection( Counter.class );
                exists = true;
            }
        }
        catch( Exception e )
        {
            // TODO log
            exists = false;
        }
        
        return exists;
    }

    private Counter getNextSequenceCounter( @NotNull String counterName )
    {
        Counter counter = this.mongoOperations.findAndModify( Query.query( Criteria.where( Counter.ID_FIELD_NAME ).is( counterName ) ), 
                                                              new Update().inc( Counter.SEQUENCE_FIELD_NAME, 1 ), 
                                                              FindAndModifyOptions.options().returnNew( true ),
                                                              Counter.class );
        
        return counter;
    }
    
    private Counter createNewCounter( String counterName )
    {
        Counter newCounter = new Counter( counterName );
        this.mongoOperations.insert( newCounter );
        
        return newCounter;
    }
    
    private Counter getNextSequenceCounterAndCreateCounterIfItDoesntExist( @NotNull String counterName )
    {
        Counter counter = this.getNextSequenceCounter( counterName );
        
        if( counter == null )
        {
            counter = this.createNewCounter( counterName );
        }
        
        return counter;
    }
    
    public Long getNextSequenceValue( @NotNull String counterName,
                                               long   defawlt )
    {
        Long value = null;
        
        Counter counter = this.getNextSequenceCounterAndCreateCounterIfItDoesntExist( counterName );
        value = ( counter == null || counter.getSequence() == null ? defawlt : counter.getSequence() );
        
        return value;
    }
    
    public Long getNextSequenceValue( @NotNull String counterName )
    {
        Long value = this.getNextSequenceValue( counterName, CounterService.INVALID_SEQUENCE_VALUE );

        return value;
    }
    
}