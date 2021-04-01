package net.alepuzio.mymongodbkata;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import com.mongodb.client.result.UpdateResult;

public class Connection implements PersonalConnection {

	@Override
	public MongoClient client(URL url) {
		return MongoClients.create(new URL().value());
	}

	@Override
	public List<Document> readAllDatabasesAsDocument(MongoClient mongoClient) {
		return mongoClient.listDatabases().into(new ArrayList<>());
	}

	@Override
	public Set<Document> readAllCollectionsOneDatabase(MongoClient mongoClient, String databaseName) {
		Set<Document> result = new HashSet<Document>();
		MongoDatabase db = mongoClient.getDatabase(databaseName);
		MongoIterable<Document> colls = db.listCollections();//;CollectionNames();
		MongoCursor<Document> it = colls.iterator(); 
		while (it.hasNext()) {
			result.add(it.next());
		}
		// System.out.println("databases:"+result); TODO put log
		return result;
	}

	@Override
	public Document readOneDocument(MongoClient mongoClient, String database, String collection, BasicDBObject filter) {
		FindIterable<Document> result = mongoClient.getDatabase(database).getCollection(collection).find(filter);
		return result.first();
	}


	public void json(List<Document> databases) {
		databases.forEach(db -> System.out.println(db.toJson()));
	}

	public void close(MongoClient client) {
		client.close();
	}

	@Override
	public void readAllCollections() {
		// TODO Auto-generated method stub

	}

	@Override
	public void insertOneDocumentInNotAsyncWay(MongoClient mongoClient, String database, String collection,
			Document newDocument) {
		mongoClient.getDatabase(database).getCollection(collection).insertOne(newDocument);
	}

	@Override
	public UpdateResult updateOneDocument(MongoClient mongoClient, String database, String collection, Bson filter,
			Bson updateData) {
		return mongoClient.getDatabase(database).getCollection(collection).updateOne(filter, updateData);
	}

	@Override
	public void updateMoreDocuments(MongoClient mongoClient, String database, String collection, Bson filter,
			Bson newData) {
		mongoClient.getDatabase(database).getCollection(collection).updateMany(filter, newData);
	}

	@Override
	public void removeOneDocument(MongoClient mongoClient, String database, String collection, Bson tmp) {
		mongoClient.getDatabase(database).getCollection(collection).deleteOne(tmp);
	}

	@Override
	public void insertOneDocumentInAsyncWay(MongoClient mongoClient, String database, 
			String collection, Document newDocument) {
		/*mongoClient.getDatabase(database)
		.getCollection(collection).insertOne(newDocument,
				new SingleResultCallback<Void>() {
		    @Override
		    public void onResult(final Void result, final Throwable t) {
		        System.out.println("Inserted!");
		    }
		});*/		
	}

	@Override
	public void printDocumentsInJSON(List<Document> databases) {
		databases.forEach(db -> System.out.println(db.toJson()));
	}

	@Override
	public Set<Document> readAllCollectionsOneDatabaseWithLimit(MongoClient mongoClient, String databaseName,
			String collectionName, Bson filter, int limit) {
		Set<Document> result = new HashSet<Document>();
		MongoIterable<Document> colls = mongoClient
				.getDatabase(databaseName)
				.getCollection(collectionName)
				.find(filter).limit(limit);
		MongoCursor<Document> it = colls.iterator(); 
		while (it.hasNext()) {
			result.add(it.next());
		}
		return result;
	}

	@Override
	public Set<Document> readSkippedCollectionsOneDatabase(MongoClient mongoClient, String databaseName,
			String collectionName, Bson filter, int skip) {
		MongoIterable<Document> colls = mongoClient
				.getDatabase(databaseName)
				.getCollection(collectionName)
				.find(filter).skip(skip);
		 return StreamSupport.stream(colls.spliterator(), false).collect(Collectors.toSet());
	}

}

class URL {

	private String uri = null;// "mongodb.uri"

	URL() {
		this.uri = "mongodb://localhost:27017";
	}

	String value() {
		return this.uri;
	}
}