package net.alepuzio.mymongodbkata;

import java.util.List;
import java.util.Set;

import org.bson.Document;

import com.mongodb.client.MongoClient;

public interface PersonalConnection {
	
	  public MongoClient client(URL url);
		
	  public List<Document> readAllDocumentsAllDatabase(MongoClient mongoClient);
	  
	  public Set<String> readAllCollectionsOneDatabase(MongoClient mongoClient, String databaseName);
	  
	    public Set<String> readOneDocument(MongoClient mongoClient, String filter);
		
	public void readAllCollections();
		
	public void insertOneDocument() ;
		
	public void updateOneDocument() ;
		
	public void updateMoreDocuments() ;
		
	public void removeOneDocuments() ;
	
	public void insertOneDocumentInAsyncWay() ;
		
    public void moreDocuments();
    
    public void printDocumentsInJSON();
    
}
