package net.alepuzio.mymongodbkata.aggregate;

import static com.mongodb.client.model.Aggregates.count;
import static com.mongodb.client.model.Aggregates.match;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

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
	public List<Document> mapReduce(String database, String collection, Document filter) {
		// get the test db,use your own
		MongoCollection<Document> coll = mongoClient.getDatabase(database).getCollection(collection);
		System.out.println("MongoCollection<Document>.count():"+coll.count());
		MongoCursor<Document> a = mongoClient.getDatabase(database).getCollection(collection).find(filter).iterator();
		int i = 0;
		while(a.hasNext()){
			System.out.println("a.next():"+a.next()); i++;
		}
		System.out.println("filter requires the typization of the data, not every data is a String = "+i);
		// map function to categorize overspeed cars
		String carMap = "function (){ var criteria;"
				+ "if ( this.speed > 70 ) { criteria = 'overspeed';"
				+ "emit(criteria,this.speed); }/*end if*/ };";

		//reduce function to add all the speed and calculate the average speed
		String carReduce = "function(key, speed) { var total =0;"
				+ "for (var i = 0; i < speed.length; i++) {"
				+ "total = total+speed[i]; } /*end for */"
				+ "return total/speed.length; };";

		MongoCursor<Document> cars = coll.mapReduce(carMap, carReduce)/*.filter(filter)*/.cursor();
		// print the average speed of cars
		List<Document> result = new ArrayList<Document>();
		while(cars.hasNext()){
			Document o = cars.next();
			result.add(o);
			System.out.println("o.toString()"+o.toString());
		}
		return result;
	}

	@Override
	public void validate() {
		// TODO Auto-generated method stub
		
	}

}
