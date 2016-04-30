package com.udemy.dropbookmarks.resources;

import static org.junit.Assert.*;

import javax.ws.rs.core.MediaType;

import org.junit.ClassRule;
import org.junit.Test;

import io.dropwizard.testing.junit.ResourceTestRule;

/**
 * 
 * @author icampbell2
 */
public final class HelloResourceTest {

	@ClassRule
	public static final ResourceTestRule RULE = ResourceTestRule.builder()
			.addResource(new HelloResource())
			.build();
	
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
