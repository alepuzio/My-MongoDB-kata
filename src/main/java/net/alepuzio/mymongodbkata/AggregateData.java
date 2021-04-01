package net.alepuzio.mymongodbkata;


import java.util.List;

import org.bson.Document;

import com.mongodb.BasicDBObject;

public interface AggregateData {

	public Document aggregate( String database, String collection, BasicDBObject filter);	
	public List<Document> mapReduce(String database, String collection, Document filter) ;
	
	public void validate();
}
