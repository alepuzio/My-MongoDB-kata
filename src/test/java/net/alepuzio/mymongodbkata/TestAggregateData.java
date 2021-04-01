package net.alepuzio.mymongodbkata;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import org.bson.Document;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoClient;

import net.alepuzio.mymongodbkata.aggregate.MongoDBAggregateData;

public class TestAggregateData {

	private Connection connection = null;

	@Before
	public void setUp(){
		this.connection = new Connection();
	}

	@After
	public void tearDown() throws Exception {
		this.connection = null;
	}


	@Test
	public void should_aggregate_when_2_author_not_modified() {
		// When
		MongoClient mongoClient = connection.client(new URL());
		MongoDBAggregateData aggregateData = new MongoDBAggregateData(mongoClient);
		BasicDBObject filter = new BasicDBObject().append("author", "Dante");
		Document document = aggregateData.aggregate("book", "book", filter);
		// Then
		assertThat(document, is(notNullValue()));
		assertEquals(1, document.size());
		assertEquals(3, document.get("count"));
	
			
	}
	@Test
	public void mapReduce() {
		fail("Not yet implemented");
		
	}
	@Test
	public void validate() {
		fail("Not yet implemented");
	}
}
