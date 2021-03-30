package net.alepuzio.mymongodbkata;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.core.Is.is;
import com.mongodb.client.MongoClient;
import org.junit.Test;
//import com.mongodb.client.MongoClient;

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
		assertTrue(true);
	}
	
	@After
	public void tearDown(){
		this.connection = null;
	}
}
