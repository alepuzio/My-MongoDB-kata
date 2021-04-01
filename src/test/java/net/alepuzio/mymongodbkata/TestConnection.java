package net.alepuzio.mymongodbkata;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;

import java.util.List;
import java.util.Set;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoClient;
import com.mongodb.client.result.UpdateResult;
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
    public void should_readMoreDocuments(){
    	List<Document> docs = this.connection.readAllDatabasesAsDocument(connection.client(new URL()));
		assertThat(docs, is(notNullValue()));
		final int expectedReadBooks = 4; 
		assertEquals(expectedReadBooks, docs.size());
    }
	
	@Test
    public void should_printDocumentsInJSON(){
    	this.connection.json(this.connection.readAllDatabasesAsDocument(connection.client(new URL())));
    	assertTrue(true);
    }

	@Test
	public void should_readOneDocument() {
		BasicDBObject filter = new BasicDBObject().append("Author", "Ada Lovelace");//TODO create collection and document
    	Document result = this.connection.readOneDocument(connection.client(new URL()), "book", "book", filter);
    	Document expected = new Document()
    			.append("Author", "Ada Lovelace")
    			.append("age", new Double("205.0"))//TODO transform in integer
    			;
    	assertEquals(expected.get("Author"), result.get("Author"));
    	assertEquals(expected.get("age"), result.get("age"));
	}

	
	@Test
	public void should_reaAllCollectionsInExistingDb(){
		Set<String> result = this.connection.readAllCollectionsOneDatabase(
				connection.client(new URL()), "book"
				);
		int expected = 1 ;// 0 is shared value in not-existing db
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
	@Ignore
	public void should_insertOneDocumentInNotAsyncWay() {
		Document newDocument = new Document().append("Author", "William Gibson");
		this.connection.insertOneDocumentInNotAsyncWay(connection.client(new URL()), "book", "book",newDocument);
		assertTrue(true);
	}

	@Test
	public void should_updateOneDocument() {
		Bson filter = new BasicDBObject("_id", new ObjectId("606581e9ccadb15a1957ad48"));
        Bson updateOperation = new BasicDBObject("$set", new BasicDBObject("topic", "science-fiction"))	;
		UpdateResult result = this.connection.updateOneDocument(connection.client(new URL()), 
				"book", 
				"book",
				filter,
				updateOperation);
        assertNotNull( result);
	}

	
	@Test
	public void should_updateMoreDocuments() {
		Bson filter = new Document("topic", "science-fiction");
        Bson updateOperation = new Document("$set", new Document("country", "Canada"));
		this.connection.updateMoreDocuments(connection.client(new URL()),"book", "book", filter, updateOperation);
	}

	@Test
	public void should_removeOneDocument() {
		Bson toRemove = new Document().append("title", "The Odyssey");
		this.connection.removeOneDocument(connection.client(new URL()), "book", "book", toRemove);
	
	}
	
	@Ignore
	@Test
	public void should_insertOneDocumentInAsyncWay() {
		Document newDocument = null; //TODO instantiate
		this.connection.insertOneDocumentInAsyncWay(connection.client(new URL()),"book","book", newDocument);
	}


	@Test
    public void should_readMoreDocuments_limit(){
    	List<Document> docs = this.connection.readAllDatabasesAsDocument(connection.client(new URL()));
		assertThat(docs, is(notNullValue()));
		final int expectedReadBooks = 4; 
		assertEquals(expectedReadBooks, docs.size());
    }
	@After
	public void tearDown(){
		this.connection = null;
	}
}
