package net.alepuzio.mymongodbkata;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Set;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.mongodb.BasicDBObject;
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

	@Test
	public void should_readOneDocument() {
		BasicDBObject filter = new BasicDBObject().append("key", "value");//TODO create collection and document
    	Document result = this.connection.readOneDocument(connection.client(new URL()), filter);
    	Document expected = null;
    	assertEquals(expected, result);
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

	@Ignore
	@Test
	public void should_insertOneDocument() {
		Document newDocument = new Document();//BasicDBObject().append("key", "value");//TODO create collection and document
		this.connection.insertOneDocument(connection.client(new URL()),newDocument);
	}

	@Test
	public void should_updateOneDocument() {
		Bson newDocument = new BasicDBObject().append("_id", "558d351236fae2f799bb5997").append("value", "old");
		Bson oldDocument = new BasicDBObject().append("_id", "558d351236fae2f799bb5997").append("value", "new");
		this.connection.updateOneDocument(connection.client(new URL()),oldDocument, newDocument);
	}

	@Ignore
	@Test
	public void should_updateMoreDocuments() {
		Bson newDocument = new BasicDBObject().append("value", "old");
		Bson oldDocument = new BasicDBObject().append("value", "new_more");
		this.connection.updateMoreDocuments(connection.client(new URL()),oldDocument, newDocument);
	}

	@Test
	public void should_removeOneDocuments() {
		Bson toRemove = new BasicDBObject().append("_id", "558d351236fae2f799bb5997");
		this.connection.removeOneDocuments(connection.client(new URL()), toRemove);
	
	}
	
	@Test
	public void should_insertOneDocumentInAsyncWay() {
		Document newDocument = null; //TODO instantiate
		this.connection.insertOneDocumentInAsyncWay(connection.client(new URL()), newDocument);
	}

	@After
	public void tearDown(){
		this.connection = null;
	}
}
