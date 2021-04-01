package net.alepuzio.mymongodbkata;

import java.util.List;
import java.util.Set;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoClient;
import com.mongodb.client.result.UpdateResult;

public interface PersonalConnection {
	public MongoClient client(URL url);

	public List<Document> readAllDatabasesAsDocument(MongoClient mongoClient);

	public Set<Document> readAllCollectionsOneDatabase(MongoClient mongoClient, String databaseName);

	public Set<Document> readAllCollectionsOneDatabaseWithLimit(MongoClient mongoClient, String databaseName,
			String collectionName, Bson filter, int limit);

	public Set<Document> readSkippedCollectionsOneDatabase(MongoClient mongoClient, String databaseName,
			String collectionName, Bson filter, int skip);
	public Document readOneDocument(MongoClient mongoClient, String database, String collection, BasicDBObject filter);

	public void readAllCollections();

	public UpdateResult updateOneDocument(MongoClient mongoClient, String database, String collection, Bson oldDocument,
			Bson newDocument);

	public void updateMoreDocuments(MongoClient mongoClient, String database, String collection, Bson oldDocument,
			Bson newDocument);

	public void removeOneDocument(MongoClient mongoClient, String database, String collection, Bson tmp);

	public void insertOneDocumentInAsyncWay(MongoClient mongoClient, String database, String collection,
			Document newDocument);

	public void insertOneDocumentInNotAsyncWay(MongoClient mongoClient, String database, String collection,
			Document newDocument);

	public void printDocumentsInJSON(List<Document> databases);

}
