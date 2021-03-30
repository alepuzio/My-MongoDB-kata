package net.alepuzio.mymongodbkata;


//import com.mongodb.MongoClient;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.bson.Document;
import java.util.ArrayList;
import java.util.List;
public class Connection {
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
    
    public MongoClient client(URL url){
        return MongoClients.create(new URL().value());
    }
    
    public List<Document> documents(MongoClient mongoClient){
    	return mongoClient
        		.listDatabases()
        		.into(new ArrayList<>());
    }

    public void json(List<Document> databases){
    	databases.forEach(db -> System.out.println(db.toJson()));
    }

    public void close(MongoClient client){
        client.close();
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