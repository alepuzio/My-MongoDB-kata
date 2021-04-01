package net.alepuzio.mymongodbkata.aggregate;

import static com.mongodb.client.model.Aggregates.count;
import static com.mongodb.client.model.Aggregates.match;

import java.util.Arrays;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoClient;

import net.alepuzio.mymongodbkata.AggregateData;


public class MongoDBAggregateData implements AggregateData {

	private MongoClient mongoClient;
	
	public MongoDBAggregateData(MongoClient newMongoClient){
		this.mongoClient = newMongoClient;
	}
	
	//@Override
	public Document aggregate(String database, String collection, BasicDBObject filter) {
		return mongoClient.getDatabase(database).getCollection(collection)
		.aggregate(Arrays.asList(
			      match(filter),
			      count())
				).first();
		
	}

	@Override
	public void mapReduce() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void validate() {
		// TODO Auto-generated method stub
		
	}

}
