package com.udemy.dropbookmarks.auth;

import static org.junit.Assert.*;

import javax.net.ssl.SSLContext;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.SslConfigurator;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.junit.After;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;

import com.udemy.dropbookmarks.DropBookmarksApplication;
import com.udemy.dropbookmarks.DropBookmarksConfiguration;
import com.udemy.dropbookmarks.resources.Hello;

import io.dropwizard.testing.junit.DropwizardAppRule;

/**
 * 
 * @author icampbell2
 */
public final class HttpsAuthIntegrationTest {

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
	private static final HttpAuthenticationFeature FEATURE
			= HttpAuthenticationFeature.basic("username", "p@ssw0rd");
	
	/**
	 * 
	 */
	private static final String TARGET = "https://localhost:8443/";
	
	/**
	 * 
	 */
	private static final Hello helloSecuredWorld = Hello.HELLO_SECURED_WORLD;
	
	/**
	 * 
	 */
	private static final String TRUST_STORE_FILENAME = "dropbookmarks.keystore";
	
	/**
	 * 
	 */
	private static final String TRUST_STORE_PASSWORD = "p@ssw0rd";
	
	/**
	 * 
	 */
	private static final String PATH = helloSecuredWorld.getPath();
	
	/**
	 * 
	 */
	private Client client;
	
	/**
	 * 
	 */
	@Before
	public void setUp() {
		SslConfigurator configurator = SslConfigurator.newInstance();
		configurator.trustStoreFile(TRUST_STORE_FILENAME)
				.trustStorePassword(TRUST_STORE_PASSWORD);
		SSLContext context = configurator.createSSLContext();
		client = ClientBuilder.newBuilder()
				.sslContext(context)
				.build();
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
	public void testSecuredPath() {
		Response response = client.target(TARGET)
				.path(PATH)
				.request()
				.get();
		
		assertEquals(Response.Status
				.UNAUTHORIZED.getStatusCode(),
				response.getStatus());
	}
	
	/**
	 * 
	 */
	@Test
	public void testSecuredGreeting() {
		client.register(FEATURE);
		
		String result = client.target(TARGET)
				.path(PATH)
				.request(MediaType.TEXT_PLAIN)
				.get(String.class);
		
		assertEquals(result, helloSecuredWorld.getGreeting());
	}
}
