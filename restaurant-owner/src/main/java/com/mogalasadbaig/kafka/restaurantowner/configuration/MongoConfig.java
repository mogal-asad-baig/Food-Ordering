package com.mogalasadbaig.kafka.restaurantowner.configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

@Configuration
public class MongoConfig{

//    //private final List<Converter<?, ?>> converters = new ArrayList<Converter<?, ?>>();
//
//    @Override
//    protected String getDatabaseName() {
//        return "mydb";
//    }
//    
//    MongoClientOptions options = MongoClientOptions.builder().sslEnabled(true).build();
//
//    MongoClient mongoClient = new MongoClient(new ServerAddress("host1", 27017),
//                                              Arrays.asList(credential),
//                                              options);

   @Bean
    public MongoClient mongoClient() {
//        final ConnectionString connectionString = new ConnectionString("mongodb://127.0.0.1:27017/mydb");
//        final MongoClientOptions options = MongoClientOptions.builder().socketKeepAlive(true).connectTimeout(4000).socketTimeout(4000).heartbeatConnectTimeout(4000).build();
//        final MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
////            .applyConnectionString(connectionString)
////            .
////            .build();
//        return MongoClients.create(mongoClientSettings);
//      //  MongoClient mongoClient = new MongoClient(new ServerAddress("host1", 27017),
//           //     Arrays.asList(credential),
//              //  options);
    	
    	MongoClient mongoClient = MongoClients.create(
    			   MongoClientSettings.builder().applyConnectionString(new ConnectionString("mongodb://127.0.0.1:27017/mydb"))
    			      .build());
    	return mongoClient;
    }

//    @Override
//    public Collection<String> getMappingBasePackages() {
//        return Collections.singleton("org.spring.mongo.demo");
//    }
}
