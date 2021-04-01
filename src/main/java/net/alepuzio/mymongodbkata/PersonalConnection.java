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
	  public List<Document> readAllDocumentsAllDatabase(MongoClient mongoClient);
	  
	  public Set<String> readAllCollectionsOneDatabase(MongoClient mongoClient, String databaseName);
	  
	    public Document readOneDocument(MongoClient mongoClient, String database, String collection, BasicDBObject filter);
		
	public void readAllCollections();
		
	public void insertOneDocument(MongoClient mongoClient, Document newDocument) ;
		
	public UpdateResult updateOneDocument(MongoClient mongoClient,Bson oldDocument, Bson newDocument);
		
	public void updateMoreDocuments(MongoClient mongoClient,Bson oldDocument, Bson newDocument);
		
	public void removeOneDocuments(MongoClient mongoClient,Bson tmp);
	
	public void insertOneDocumentInAsyncWay(MongoClient mongoClient,Document newDocument);		
    
    
    public void printDocumentsInJSON(List<Document> databases);
    
}
