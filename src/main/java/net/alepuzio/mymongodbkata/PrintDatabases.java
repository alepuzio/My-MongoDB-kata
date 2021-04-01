package net.alepuzio.mymongodbkata;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

public class PrintDatabases {
	public static void main(String[] args) {
		String connectionString = new URL().value();
		try (MongoClient mongoClient = MongoClients.create(connectionString)) {
			List<Document> databases = mongoClient.listDatabases().into(new ArrayList<>());
			databases.forEach(db -> System.out.println(db.toJson()));
		}
	}

}
