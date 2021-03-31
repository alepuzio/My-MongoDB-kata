package net.alepuzio.mymongodbkata;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bson.Document;

//import com.mongodb.MongoClient;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
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
    	return result;

    }
    
    @Override
    public Set<String> readOneDocument(MongoClient mongoClient, String filter){
    	return null;
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
	public void insertOneDocument() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateOneDocument() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateMoreDocuments() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeOneDocuments() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insertOneDocumentInAsyncWay() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void moreDocuments() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void printDocumentsInJSON() {
		// TODO Auto-generated method stub
		
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