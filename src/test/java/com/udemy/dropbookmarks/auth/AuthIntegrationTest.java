package com.udemy.dropbookmarks.auth;

import static org.junit.Assert.*;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

import org.junit.After;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;

import com.udemy.dropbookmarks.DropBookmarksApplication;
import com.udemy.dropbookmarks.DropBookmarksConfiguration;

import io.dropwizard.testing.junit.DropwizardAppRule;

/**
 * 
 * @author icampbell2
 */
public final class AuthIntegrationTest {

	/**
	 * 
	 */
	private static final String CONFIG_PATH = "config.yml";
	
	/**
	 * 
	 */
	@ClassRule
	public static final DropwizardAppRule<DropBookmarksConfiguration> RULE
			= new DropwizardAppRule<>(DropBookmarksApplication.class, CONFIG_PATH);
	
	/**
	 * 
	 */
	private static final String TARGET = "http://localhost:8080";
	
	/**
	 * 
	 */
	private static final String PATH = "/hello/secured";
	
	/**
	 * 
	 */
	private Client client;
	
	/**
	 * 
	 */
	@Before
	public void setUp() {
		client = ClientBuilder.newClient();
	}
	
	/**
	 * 
	 */
	@After
	public void tearDown() {
		client.close();
	}
	
	/**
	 * 
	 */
	@Test
	public void testPath() {
		Response response = client.target(TARGET)
				.path(PATH)
				.request()
				.get();
		
		assertEquals(Response.Status
				.UNAUTHORIZED.getStatusCode(),
				response.getStatus());
	}
}
