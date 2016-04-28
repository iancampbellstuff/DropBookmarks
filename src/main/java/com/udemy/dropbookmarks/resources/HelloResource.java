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
		return HelloEnum.HELLO_WORLD.toString();
	}
}
