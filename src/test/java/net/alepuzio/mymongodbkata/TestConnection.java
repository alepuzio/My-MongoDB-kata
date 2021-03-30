package net.alepuzio.mymongodbkata;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.bson.Document;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.mongodb.client.MongoClient;

/**
 * from https://developer.mongodb.com/quickstart/java-setup-crud-operations/
 * */
public class TestConnection {

	private Connection connection = null;

	@Before
	public void setUp(){
		this.connection = new Connection();
	}
	@Test
	public void shouldCreateANewMongoClientConnectedToLocalhost() throws Exception {
		// When
		// TODO: get/create the MongoClient
		MongoClient mongoClient = connection.client(new URL());
		// Then
		assertThat(mongoClient, is(notNullValue()));
	}
	
	@After
	public void tearDown(){
		this.connection = null;
	}
	@Test
    public void documents(){
    	List<Document> docs = this.connection.documents(connection.client(new URL()));
		assertThat(docs, is(notNullValue()));
		assertEquals(3, docs.size());
    }
	
	@Test
    public void json(){
    	this.connection.json(this.connection.documents(connection.client(new URL())));
    	assertTrue(true);
    }
}
