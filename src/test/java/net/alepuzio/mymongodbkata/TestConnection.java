package net.alepuzio.mymongodbkata;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Set;

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
	public void should_createANewMongoClientConnectedToLocalhost() {
		// When
		// TODO: get/create the MongoClient
		MongoClient mongoClient = connection.client(new URL());
		// Then
		assertThat(mongoClient, is(notNullValue()));
	}

	@Test
	public void should_readOneDocument() {
    	List<Document> docs = this.connection.readAllDocumentsAllDatabase(connection.client(new URL()));
	}

	
	@Test
	public void should_reaAllCollectionsInExistingDb(){
		Set<String> result = this.connection.readAllCollectionsOneDatabase(
				connection.client(new URL()), "local"
				);
		int expected = 0 ;//TODO create a collction in db , 0 is shared value in not-existing db
		assertEquals(expected, result.size());
	}

	@Test
	public void should_reaAllCollectionsInAbsentDb(){
		Set<String> result = this.connection.readAllCollectionsOneDatabase(
				connection.client(new URL()), "local_not_exists"
				);
		assertEquals(0, result.size());
	}

	@Test
	public void should_insertOneDocument() {
		throw new UnsupportedOperationException();
	}

	@Test
	public void should_updateOneDocument() {
		throw new UnsupportedOperationException();
	}

	@Test
	public void should_updateMoreDocuments() {
		throw new UnsupportedOperationException();
	}

	@Test
	public void should_removeOneDocuments() {
		throw new UnsupportedOperationException();
	}
	
	@Test
	public void should_insertOneDocumentInAsyncWay() {
		throw new UnsupportedOperationException();
	}

	@After
	public void tearDown(){
		this.connection = null;
	}
	@Test
    public void should_readMoreDocuments(){
    	List<Document> docs = this.connection.readAllDocumentsAllDatabase(connection.client(new URL()));
		assertThat(docs, is(notNullValue()));
		assertEquals(3, docs.size());
    }
	
	@Test
    public void should_printDocumentsInJSON(){
    	this.connection.json(this.connection.readAllDocumentsAllDatabase(connection.client(new URL())));
    	assertTrue(true);
    }
}
