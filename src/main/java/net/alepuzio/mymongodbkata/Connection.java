package net.alepuzio.mymongodbkata;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
	
    public static void main(String[] args) {
        String connectionString = new URL().value();
        //System.getProperty("mongodb.uri");
        try (MongoClient mongoClient = MongoClients.create(connectionString)) {
            List<Document> databases = mongoClient
            		.listDatabases()
            		.into(new ArrayList<>());
            databases.forEach(db -> System.out.println(db.toJson()));
        }
    }
    
    @Override
    public MongoClient client(URL url){
        return MongoClients.create(new URL().value());
    }
    
    @Override
    public List<Document> readAllDocumentsAllDatabase(MongoClient mongoClient){
    	return mongoClient
        		.listDatabases()
        		.into(new ArrayList<>());
    }
    
    @Override
    public Set<String> readAllCollectionsOneDatabase(MongoClient mongoClient, String databaseName){
    	Set<String> result = new HashSet<String>();
    	MongoDatabase db = mongoClient.getDatabase( "databaseName" );
    	MongoIterable<String> colls = db.listCollectionNames();
    	MongoCursor<String> it = colls.iterator();
    	while (it.hasNext()){
    		result.add(it.next());
    	}
    	System.out.println("databases:"+result);
    	return result;

    }
    
    @Override
    public Document readOneDocument(MongoClient mongoClient, String database, String collection, BasicDBObject filter){
    	FindIterable<Document> result = mongoClient
    			.getDatabase(database)
    			.getCollection(collection)
    			.find(filter);
    	return result.first();
    }

    public void json(List<Document> databases){
    	databases.forEach(db -> System.out.println(db.toJson()));
    }

    public void close(MongoClient client){
        client.close();
    }


	@Override
	public void readAllCollections() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insertOneDocument(MongoClient mongoClient, Document newDocument) {
		mongoClient.getDatabase("databaseName").getCollection("nameCollection")
    			.insertOne(newDocument);
	}

	@Override
	public UpdateResult updateOneDocument(MongoClient mongoClient,Bson oldDocument, Bson newDocument) {
		return mongoClient.getDatabase("databaseName").getCollection("nameCollection")
		.updateOne(oldDocument, newDocument);
	
	}

	@Override
	public void updateMoreDocuments(MongoClient mongoClient,Bson oldDocument, Bson newDocument) {
		 mongoClient.getDatabase("databaseName").getCollection("nameCollection")
				.updateMany(oldDocument, newDocument);
		
		
	}

	@Override
	public void removeOneDocuments(MongoClient mongoClient,Bson tmp) {
		 mongoClient.getDatabase("databaseName").getCollection("nameCollection").deleteOne(tmp);
	}

	@Override
	public void insertOneDocumentInAsyncWay(MongoClient mongoClient,Document newDocument) {
		mongoClient.getDatabase("databaseName").getCollection("nameCollection").insertOne(newDocument);		
	}


	@Override
	public void printDocumentsInJSON(List<Document> databases){
    	databases.forEach(db -> System.out.println(db.toJson()));		
	}

}

class URL {
	
	private String uri = null;//"mongodb.uri"
	URL(){
		this.uri = "mongodb://localhost:27017";
	}
	
	String value(){
		return this.uri;
	}
}