package com.udemy.dropbookmarks.resources;

import static org.junit.Assert.*;

import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;

import com.google.common.base.Optional;
import com.udemy.dropbookmarks.core.User;

import io.dropwizard.auth.AuthFactory;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicAuthFactory;
import io.dropwizard.auth.basic.BasicCredentials;
import io.dropwizard.testing.junit.ResourceTestRule;

/**
 * 
 * @author icampbell2
 */
public final class HelloResourceTest {
	
	/**
	 * 
	 */
	private static final HttpAuthenticationFeature FEATURE = HttpAuthenticationFeature.basic("u", "p");
	
	/**
	 * 
	 */
	private static final Authenticator<BasicCredentials, User> AUTHENTICATOR = new Authenticator<BasicCredentials, User>() {

		@Override
		public Optional<User> authenticate(BasicCredentials credentials) throws AuthenticationException {
			return Optional.of(new User());
		}		
	};

	/**
	 * 
	 */
	@ClassRule
	public static final ResourceTestRule RULE = ResourceTestRule.builder()
			.addProvider(AuthFactory.binder(new BasicAuthFactory<>(AUTHENTICATOR, "realm", User.class)))
			.addResource(new HelloResource())
			.build();
	
	/**
	 * 
	 */
	@BeforeClass
	public static final void setUpClass() {
		RULE.getJerseyTest().client().register(FEATURE);
	}
	
	/**
	 * 
	 */
	@Test
	public void testGreeting() {
		Hello helloWorld = Hello.HELLO_WORLD;
		String result = RULE.getJerseyTest()
				.target(helloWorld.getPath())
				.request(MediaType.TEXT_PLAIN)
				.get(String.class);
		
		assertEquals(result, helloWorld.getGreeting());
	}
	
	/**
	 * 
	 */
	@Test
	public void testGetSecuredGreeting() {
		Hello helloSecuredWorld = Hello.HELLO_SECURED_WORLD;
		String result = RULE.getJerseyTest()
				.target(helloSecuredWorld.getPath())
				.request(MediaType.TEXT_PLAIN)
				.get(String.class);
		
		assertEquals(result, helloSecuredWorld.getGreeting());
	}
}
