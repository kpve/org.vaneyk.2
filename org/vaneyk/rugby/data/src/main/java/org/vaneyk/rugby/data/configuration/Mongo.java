package org.vaneyk.rugby.data.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.MongoClient;

@Configuration
@EnableMongoRepositories
class Mongo extends AbstractMongoConfiguration
{
    @Override
    protected String getDatabaseName()
    {
        return "rugby-data";
    }

    @Override
    public com.mongodb.Mongo mongo() throws Exception
    {
        return new MongoClient();
    }
    
//    @Bean
//    public Mongo mongo() throws Exception
//    {
//        // mongodb://[username:password@]host1[:port1][,host2[:port2],...[,hostN[:portN]]][/[database][?options]]
//        MongoClient mongoClient = new MongoClient( "127.0.0.1" );//new MongoClient( new MongoClientURI( "mongodb://127.0.0.1/rugby-data" ) );
//        
//        return mongoClient;
//    }

}
