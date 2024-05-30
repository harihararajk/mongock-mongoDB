package com.tw.mongock;

import com.mongodb.*;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import io.mongock.runner.springboot.EnableMongock;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;

@EnableMongock
@SpringBootApplication
public class MongockApplication {

	public final static String ORDER_COLLECTION_NAME = "orders";

	@Value(value = "${spring.data.mongodb.uri}")
	private String mongoUri;
	@Value(value = "${spring.data.mongodb.database}")
	private String database;

	public static void main(String[] args) {
		SpringApplication.run(MongockApplication.class, args);
	}

	@Bean
	public MongoTransactionManager transactionManager(MongoTemplate mongoTemplate) {
		MongoClientSettings settings = MongoClientSettings.builder()
				.applyConnectionString(new ConnectionString(mongoUri))
				.build();

		MongoClient mongoClient = MongoClients.create(settings);
		MongoDatabaseFactory mongoDatabaseFactory = new SimpleMongoClientDatabaseFactory(mongoClient, database);
		return new MongoTransactionManager(mongoDatabaseFactory);
	}
}
