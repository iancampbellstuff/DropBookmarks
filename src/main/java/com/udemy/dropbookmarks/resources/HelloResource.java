package com.udemy.dropbookmarks.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * 
 * @author icampbell2
 */
@Path("/hello")
public final class HelloResource {
	
	/**
	 * 
	 * @return
	 */
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getGreeting() {
		return Hello.HELLO_WORLD.getGreeting();
	}
	
	/**
	 * 
	 * @return
	 */
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/secured") // sub-resource path as /hello/secured
	public String getSecuredGreeting() {
		return Hello.SECURED_HELLO_WORLD.getGreeting();
	}
}
