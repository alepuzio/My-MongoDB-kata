package net.alepuzio.mymongodbkata;


import org.bson.Document;

import com.mongodb.BasicDBObject;

public interface AggregateData {

	public Document aggregate( String database, String collection, BasicDBObject filter);	
	public void mapReduce();
	
	public void validate();
}
